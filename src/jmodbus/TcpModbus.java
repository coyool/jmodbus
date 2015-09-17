/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmodbus;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LUCAS
 */
public class TcpModbus {

    private Socket tcpModbus;
    private OutputStream outStream;
    private InputStream inStream;

    public TcpModbus(String dirIP, int puerto) {
        try {
            this.tcpModbus = new Socket(dirIP, puerto);
        } catch (IOException ex) {
            Logger.getLogger(TcpModbus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Integer> execute(byte[] trama) {//ES LO MISMO AL SERIE PERO SIN LOS PRINT
        List<Integer> response = new ArrayList<>();

        try {
            outStream = this.tcpModbus.getOutputStream();
            outStream.write(trama);
        } catch (IOException ex) {
            Logger.getLogger(TcpModbus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            inStream = this.tcpModbus.getInputStream();
            int bit = -1;
            
            while(true){
               bit = inStream.read();
               if(bit == -1) break;
               int byteInt = parseUnsignedInt(bit);
               response.add(byteInt); 
            }
        } catch (IOException ex) {
            Logger.getLogger(TcpModbus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    public void close(){
        if(this.tcpModbus != null){
            try {
                this.tcpModbus.close();
            } catch (IOException ex) {
                Logger.getLogger(TcpModbus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    public int parseUnsignedInt(int toConvert) {
        byte b = (byte) toConvert;
        return (b & 0xFF);
    }
}
