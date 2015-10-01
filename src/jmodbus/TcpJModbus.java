/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmodbus;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mnanom
 */
public class TcpJModbus extends JModbus {
    
    private Socket tcpModbus;

    public TcpJModbus(String dirIP, int puerto, int rate, int timeout, int retries, int deviseId, int address, int nvar, int functionNumber) {
        super(rate, timeout, retries, deviseId, address, nvar, functionNumber);
        try {
            this.tcpModbus = new Socket(dirIP, puerto);
        } catch (IOException ex) {
            Logger.getLogger(TcpJModbus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected List<Byte> addHeaderFrame(List<Byte> frame) {
        /* Transaction identifier (2 bytes)*/
        frame.add((byte) (1));
        frame.add((byte) (1));
        
        /* Protocol (1 bytes)*/
        frame.add((byte) (0));
        frame.add((byte) (0));
        
        /* Length*/
        frame.add((byte) (0));
        frame.add((byte) (6));
        
        /* Identificador de slave */
        frame.add((byte) (this.deviseId));
        
        return frame;
    }

    @Override
    protected List<Byte> addErrorCheck(List<Byte> frame) {
        return frame;
    }

    @Override
    protected void setInputStream() {
        try {
            inStream = this.tcpModbus.getInputStream();
            filterInputStream = new BufferedInputStream(inStream);
        } catch (IOException ex) {
            Logger.getLogger(TcpJModbus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void setOutputStream() {
        try {
            outStream = this.tcpModbus.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(TcpJModbus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() {
        if(this.tcpModbus != null){
            try {
                this.tcpModbus.close();
            } catch (IOException ex) {
                Logger.getLogger(TcpJModbus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
