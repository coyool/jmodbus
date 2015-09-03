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
public class SerialModbus  extends Thread {
    private SerialPort serialPort;
    private InputStream inStream;
    private OutputStream outStream;
    
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

    public void enviar(byte[] trama) {
        String tramaStr = "| ";
        for(int i = 0; i < trama.length; i++){
            tramaStr += String.valueOf(new Byte(trama[i])) + " | ";
        }
        System.out.println("SECUENCIA A ENVIAR - - -> " + tramaStr);
        
        try {
            outStream = this.serialPort.getOutputStream();
            System.out.println(outStream);
            outStream.write(trama);
            System.out.println("Trama enviada");
        } catch (IOException ex) {
            System.out.println("CATCH");
            Logger.getLogger(SerialModbus.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() { //REPRESENTA AL RECIBIR!!!!!
        while (true) {
            try {
                List<Byte> tramaRecibida = new ArrayList<Byte>();
                System.out.println("RECIBE");
                inStream = this.serialPort.getInputStream();
                int bit = inStream.read();
                boolean control = true;
                boolean seg = false;
                while (control) {
                    tramaRecibida.add((byte) bit);
                    if (true) {
                        control = false;
                        bit = inStream.read();                       
                    }else{
                        bit = inStream.read();
                    }              
                    
                }   
                System.out.println("Respuesta: " + tramaRecibida); 

            } catch (IOException ex) {
                Logger.getLogger(SerialModbus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
