/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmodbus;

import gnu.io.CommPortIdentifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

/**
 *
 * @author LUCAS
 */
public class PortConfiguration {
    
    public Collection buscarDispositivos(){
        Collection retorno = new ArrayList();
        
        CommPortIdentifier portId;  
        Enumeration portList;  
        portList = CommPortIdentifier.getPortIdentifiers();  
         while (portList.hasMoreElements()){  
             portId = (CommPortIdentifier) portList.nextElement();  
             if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL){  
                 System.out.println("Found port: " + portId.getName());
                 retorno.add(portId.getName());
             }    
       }  
        return retorno;
    }
}
