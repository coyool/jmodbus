/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmodbus;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mnanom
 */
public class SerialModbus extends Thread {

    private SerialPort serialPort;
    private InputStream inStream;
    private OutputStream outStream;
    private int responseLength = 100;
    private int deviseId = 0;

    public SerialModbus(String nombrePuerto) {
        CommPortIdentifier portId;
        Enumeration portList;
        portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL && portId.getName().equalsIgnoreCase(nombrePuerto)) {
                try {
                    this.serialPort = (SerialPort) portId.open(portId.getName(), 1971);
                    this.serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
                } catch (Exception ex) {
                    Logger.getLogger(SerialModbus.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void enviar(byte[] trama, int responseLength) {
        this.responseLength = responseLength;
        String tramaStr = "| ";
        for (int i = 0; i < trama.length; i++) {
            if (i == 0) {
                this.deviseId = parseUnsignedInt(trama[i]);
            }
            int byteInt = parseUnsignedInt(trama[i]);
            tramaStr += String.valueOf(byteInt) + " | ";
        }
        System.out.println("ENVIAR -> " + tramaStr);

        try {
            outStream = this.serialPort.getOutputStream();
            outStream.write(trama);
            System.out.println("Trama enviada");
        } catch (IOException ex) {
            Logger.getLogger(SerialModbus.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String execute(byte[] trama, int responseLength) {
        this.responseLength = responseLength;
        String tramaStr = "| ";
        for (int i = 0; i < trama.length; i++) {
            if (i == 0) {
                this.deviseId = parseUnsignedInt(trama[i]);
            }
            int byteInt = parseUnsignedInt(trama[i]);
            tramaStr += String.valueOf(byteInt) + " | ";
        }
        System.out.println("ENVIAR - - -> " + tramaStr);

        try {
            outStream = this.serialPort.getOutputStream();
            outStream.write(trama);
            System.out.println("Trama enviada");
        } catch (IOException ex) {
            Logger.getLogger(SerialModbus.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Espero la respuesta */
        try {
            System.out.println("RECIBE");
            inStream = this.serialPort.getInputStream();
            int bit = inStream.read();
            boolean control = true;
            tramaStr = "| ";
            String tramaHex = "| ";
            String tramaBinary = "| ";
            /* Se queda esperando a que la trama traiga el ID del dispositivo */
            while (control) {
                if (parseUnsignedInt(bit) == deviseId) {
                    control = false;
                }
                bit = inStream.read();
            }

            /* Lee la trama completa */
            for (int i = 0; i < responseLength; i++) {
                int byteInt = parseUnsignedInt(bit);
                tramaStr += String.valueOf(byteInt) + " | ";
                tramaHex += Integer.toHexString(byteInt) + " | ";
                tramaBinary += Integer.toBinaryString(byteInt) + " | ";
                bit = inStream.read();
            }
            System.out.println("Respuesta decimal: " + tramaStr);
            System.out.println("Respuesta hex: " + tramaHex);
            System.out.println("Respuesta binario: " + tramaBinary);
        } catch (IOException ex) {
            Logger.getLogger(SerialModbus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tramaStr;
    }

    public void close(){
        if(this.serialPort != null){
            this.serialPort.close();
        }        
    }
    
    public int parseUnsignedInt(int toConvert) {
        byte b = (byte) toConvert;
        return (b & 0xFF);
    }

    public String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes()));
    }

    @Override
    public void run() { //REPRESENTA AL RECIBIR!!!!!
        while (true) {
            try {
                System.out.println("RECIBE");
                inStream = this.serialPort.getInputStream();
                int bit = inStream.read();
                boolean control = true;
                String tramaStr = "| ";
                String tramaHex = "| ";
                String tramaBinary = "| ";
                /* Se queda esperando a que la trama traiga el ID del dispositivo */
                while (control) {
                    if (parseUnsignedInt(bit) == deviseId) {
                        control = false;
                    }
                    bit = inStream.read();
                }

                /* Lee la trama completa */
                for (int i = 0; i < responseLength; i++) {
                    int byteInt = parseUnsignedInt(bit);
                    tramaStr += String.valueOf(byteInt) + " | ";
                    tramaHex += Integer.toHexString(byteInt) + " | ";
                    tramaBinary += Integer.toBinaryString(byteInt) + " | ";
                    bit = inStream.read();
                }
                System.out.println("Respuesta decimal: " + tramaStr);
                System.out.println("Respuesta hex: " + tramaHex);
                System.out.println("Respuesta binario: " + tramaBinary);
            } catch (IOException ex) {
                Logger.getLogger(SerialModbus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
