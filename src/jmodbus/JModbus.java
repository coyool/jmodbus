/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmodbus;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author mnanom
 */
public abstract class JModbus {

    protected InputStream inStream;
    protected OutputStream outStream;
    protected FilterInputStream filterInputStream;
    protected int[] values;
    protected int deviseId;
    protected int rate;
    protected int timeout;
    protected int retries;
    protected int address;
    protected int tempAddress;
    protected int nvar;
    protected int tempNVar;
    protected int functionNumber;
    protected boolean isConnect = false;

    private List<Integer> response = null;
    private byte[] frame;

    public JModbus(int rate, int timeout, int retries, int deviseId, int address, int nvar, int functionNumber) {
        this.rate = rate;
        this.timeout = timeout;
        this.retries = retries;
        this.deviseId = deviseId;
        this.address = address;
        this.nvar = nvar;
        this.functionNumber = functionNumber;
    }

    public String execute(int[] values, int format, JTable tableValues) {
        String respuesta = "";
        this.values = values;
        int ntramas = 1;
        if (this.functionNumber == 3) {
            ntramas = (int) (this.nvar / 125) + 1;
        }
        for (int i = 0; i < ntramas; i++) {
            response = null;
            int retries = this.retries;
            this.tempAddress = this.address + i * 125;
            if (i < ntramas - 1) {
                this.tempNVar = 125;
            } else {
                this.tempNVar = (this.nvar % 125);
            }
            /* Armamos la trama de acuerdo a la función */
            frame = createFrame(this.functionNumber);

            while (retries > 0) {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        response = execute(frame);
                    }
                });
                thread.start();

                boolean controlTimeout = true;
                long endTimeMillis = System.currentTimeMillis() + this.timeout;
                while (controlTimeout) {
                    if (response != null) {
                        controlTimeout = false;
                    }
                    if (System.currentTimeMillis() > endTimeMillis) {
                        controlTimeout = false;
                    }
                }
                if (response != null) {
                    retries = 0;
                } else {
                    retries -= 1;
                }
                thread.interrupt();
                thread.stop();
                thread = null;
            }
            if (response != null && checkFuntionCode(response)) {
                respuesta += toFormat(response, format) + "\n";
                toTableValues(tableValues, response);
            } else {
                if (response != null) {
                    respuesta += "Ocurrio un error \n" + errorMsg(response) + "\n Rx: " + toFormat(response, format);
                } else {
                    respuesta += "Ocurrio un error en la comunicacion ";
                }
            }
        }
        return respuesta;
    }

    private List<Integer> execute(byte[] frame) {
        List<Integer> response = new ArrayList<Integer>();

        /* Dejo registro de la trama que voy a enviar */
        logFrame("Tx:", frame);

        /* Envío la trama */
        try {
            if (outStream == null) {
                setOutputStream();
            }
            if (inStream == null) {
                setInputStream();
            }
            outStream.write(frame);
        } catch (IOException ex) {
            Logger.getLogger(SerialJModbus.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Espero la respuesta de la petición */
        try {

            int bit = -1;
            boolean control = true;

            /* Se queda esperando a que la trama traiga el ID del dispositivo */
            while (control) {
                if (filterInputStream != null) {
                    bit = filterInputStream.read();
                } else {
                    bit = inStream.read();
                }
                if (parseUnsignedInt(bit) == deviseId) {
                    response.add(parseUnsignedInt(bit));
                    control = false;
                }
            }

            /* Lee la trama completa */
            int frameIndex = 1;
            int tcpFrameBytesIndex = -1;
            int tcpFrameBytes = -2;
            while (true) {
                int byteInt;
                if (filterInputStream != null) {
                    bit = filterInputStream.read();
                    byteInt = parseUnsignedInt(bit);
                    if (frameIndex == 5) {
                        tcpFrameBytesIndex = 0;
                        tcpFrameBytes = byteInt;
                    }
                    if(tcpFrameBytes == tcpFrameBytesIndex){
                        response.add(byteInt);
                        break;
                    }
                    if(tcpFrameBytesIndex != -1) tcpFrameBytesIndex++;                    
                    frameIndex++;
                } else {
                    bit = inStream.read();
                    byteInt = parseUnsignedInt(bit);
                    if (bit == -1) {
                        break;
                    }
                }    
                response.add(byteInt);
            }
            /* Dejo registro de la trama que recibo */
            logFrame("Rx:", response);

        } catch (IOException ex) {
            Logger.getLogger(SerialJModbus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    private byte[] createFrame(int functionNumber) {
        byte[] frame = null;
        switch (functionNumber) {
            case 3:
                frame = createFrameFunction3();
                break;
            case 6:
                frame = createFrameFunction6(values[0]);
                break;
            case 16:
                frame = createFrameFunction16(values);
                break;
        }

        return frame;
    }

    private byte[] createFrameFunction3() {
        List<Byte> frame = new ArrayList<>();

        /* #1: (N bytes) Encabezado de la trama */
        frame = addHeaderFrame(frame);

        /* #2: (1 byte) numero de funcion 0..255 */
        frame.add(new Byte((byte) this.functionNumber));

        /* #3: (2 byte) direccion de inicio de lectura (0..255)(0..255) */
        frame.add(new Byte((byte) (this.tempAddress / 256)));
        frame.add(new Byte((byte) (this.tempAddress % 256)));

        /* #4: (2 byte) cantidad de variables (0..255)(0..255) */
        frame.add(new Byte((byte) (this.tempNVar / 256)));
        frame.add(new Byte((byte) (this.tempNVar % 256)));

        /* #5: (N bytes) Chequeo de error de la trama */
        frame = addErrorCheck(frame);

        /* Convierto el List en array */
        byte[] frameToSend = toByteArray(frame);

        return frameToSend;
    }

    private byte[] createFrameFunction6(int valor) {
        List<Byte> frame = new ArrayList<>();

        /* #1: (N bytes) Encabezado de la trama */
        frame = addHeaderFrame(frame);

        /* #2: (1 byte) numero de funcion 0..255 */
        frame.add((byte) functionNumber);

        /* #3: (2 byte) direccion de inicio de lectura (0..255)(0..255) */
        frame.add((byte) (address / 256));
        frame.add((byte) (address % 256));

        /* #4: (2 byte) cantidad de variables (0..255)(0..255) */
        frame.add((byte) (valor / 256));
        frame.add((byte) (valor % 256));

        /* #5: (N bytes) Chequeo de error de la trama */
        frame = addErrorCheck(frame);

        /* Convierto el List en array */
        byte[] frameToSend = toByteArray(frame);

        return frameToSend;
    }

    private byte[] createFrameFunction16(int[] valores) {
        //El tamaño de el arreglo de datos no debe superar los 123 registros
        //Se debe calcular la cantidad de bytes que se envian en funcion de la cantidad de registros a escribir  (n reg x 2 bytes)
        /*El formato de la trama es: [id], [funcion], [address], [cantidad de registros], [cantidad de bytes], [valores], [CRC]*/
        List<Byte> frame = new ArrayList<>();

        /* #1: (N bytes) Encabezado de la trama */
        frame = addHeaderFrame(frame);

        /* #2: (1 byte) numero de funcion 0..255 */
        frame.add((byte) functionNumber);

        /* #3: (2 byte) direccion de inicio de lectura (0..255)(0..255) */
        frame.add((byte) (address / 256));
        frame.add((byte) (address % 256));

        /* #4: (2 byte) cantidad de variables (0..255)(0..255) */
        frame.add((byte) (this.nvar / 256));
        frame.add((byte) (this.nvar % 256));

        /* #5 (2 byte) cantidad de bytes de datos */
        frame.add((byte) (this.nvar * 2));

        /* #6 (2 a 2*n bytes) valores a escribir en los registros */
        for (int valor : valores) {
            frame.add((byte) (valor / 256));
            frame.add((byte) (valor % 256));
        }

        /* #7: (N bytes) Chequeo de error de la trama */
        frame = addErrorCheck(frame);

        /* Convierto el List en array */
        byte[] frameToSend = toByteArray(frame);

        return frameToSend;
    }

    protected byte prepareByte(String byteString) {
        int value = Integer.valueOf(byteString);
        return (byte) value;
    }

    private int parseUnsignedInt(int toConvert) {
        byte b = (byte) toConvert;
        return (b & 0xFF);
    }

    private String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes()));
    }

    private byte[] toByteArray(List<Byte> frame) {
        byte[] result = new byte[frame.size()];
        for (int i = 0; i < frame.size(); i++) {
            result[i] = frame.get(i).byteValue();
        }
        return result;
    }

    private void logFrame(String msg, byte[] frame) {
        String frameStr = "| ";
        for (int i = 0; i < frame.length; i++) {
            int byteInt = parseUnsignedInt(frame[i]);
            frameStr += String.valueOf(byteInt) + " | ";
        }
        System.out.println(msg + " " + frameStr);
    }

    private void logFrame(String msg, List<Integer> frame) {
        String frameStr = "| ";
        for (Integer i : frame) {
            frameStr += String.valueOf(i) + " | ";
        }
        System.out.println(msg + " " + frameStr);
    }

    protected abstract List<Byte> addHeaderFrame(List<Byte> frame);

    protected abstract List<Byte> addErrorCheck(List<Byte> frame);

    protected abstract void setInputStream();

    protected abstract void setOutputStream();

    public abstract void close();

    /* */
    private String toFormat(List<Integer> response, int format) {
        String respuesta = "";
        switch (format) {
            case 0:
                for (Integer byteInt : response) {
                    respuesta += "[" + byteInt.toString() + "]";
                }
                break;
            case 1:
                for (Integer byteInt : response) {
                    respuesta += "[" + Integer.toHexString(byteInt) + "]";
                }
                break;
            case 2:
                for (Integer byteInt : response) {
                    respuesta += "[" + Integer.toBinaryString(byteInt) + "]";
                }
                break;
        }
        return respuesta;
    }

    private boolean checkFuntionCode(List<Integer> response) {
        if (response.get(1) >= 128) {
            return false;
        }
        return true;
    }

    private String errorMsg(List<Integer> response) {
        switch (response.get(2)) {
            case 1:
                return "El código de funcion recibida no se corresponde a ningun comando disponible en el esclavo";
            case 2:
                return "En la trama no se corresponde ninguna dirección valida del esclavo";
            case 3:
                return "El valor enviado al esclavo no es valido";
            case 6:
                return "Dispositivo ocupado";
            default:
                return "Error desconocido";

        }
    }

    private void toTableValues(JTable tableValues, List<Integer> response) {
        int row = 0;
        switch (this.functionNumber) {
            case 3:
                for (int i = 3; i < (response.size() - 2); i++) {
                    if (i % 2 == 1) {
                        String binary = Integer.toBinaryString(response.get(i)) + Integer.toBinaryString(response.get(i + 1));
                        int unsignedVal = Integer.parseInt(binary, 2);
                        tableValues.setValueAt(unsignedVal, row + this.tempAddress, 1);
                        row++;
                    }
                }
                break;
            case 6:
                for (int i = 4; i < (response.size() - 2); i++) {
                    if (i % 2 == 0) {
                        String binary = Integer.toBinaryString(response.get(i)) + Integer.toBinaryString(response.get(i + 1));
                        int unsignedVal = Integer.parseInt(binary, 2);
                        tableValues.setValueAt(unsignedVal, row, 1);
                        row++;
                    }
                }
                break;
            case 16:
                // Implementar
                break;
        }
    }
}
