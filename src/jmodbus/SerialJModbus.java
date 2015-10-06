/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmodbus;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author mnanom
 */
public class SerialJModbus extends JModbus {

    private SerialPort serialPort;
    
    public SerialJModbus(String port, int baudRate, int timeout, int retries, int deviseId, int address, int nvar, int functionNumber) {
        super(baudRate, timeout, retries, deviseId, address, nvar, functionNumber);
        CommPortIdentifier portId;
        Enumeration portList;
        portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL && portId.getName().equalsIgnoreCase(port)) {
                try {
                    this.serialPort = (SerialPort) portId.open(portId.getName(), 1971);
                    this.serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
                } catch (Exception ex) {
                    Logger.getLogger(SerialJModbus.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    protected List<Byte> addHeaderFrame(List<Byte> frame) {
        /* #Encabezado: (1 byte) ID del dispositivo 0..255 */
        frame.add((byte) (this.deviseId));
        return frame;
    }

    @Override
    protected List<Byte> addErrorCheck(List<Byte> frame) {
        CRC16 crc16 = new CRC16();
        /* #Error check: (2 byte) CRC (0..255)(0..255) */
        return crc16.addCrc16(frame);
    }

    @Override
    protected void setInputStream() {
        try {
            inStream = this.serialPort.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(SerialJModbus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void setOutputStream() {
        try {
            this.outStream = this.serialPort.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(SerialJModbus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() {
        if(this.serialPort != null){
            this.serialPort.close();
        } 
    }

    @Override
    protected void toTableValues(JTable tableValues, List<Integer> response) {
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
