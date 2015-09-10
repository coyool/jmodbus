/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import jmodbus.Modbus;
import jmodbus.SerialModbus;
import view.Config;

/**
 *
 * @author LUCAS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Modbus modbus = new Modbus("COM2", 9600, 30, 3, "1", 0, 10, 3);        

        //modbus.ejecutarPeticion();
        //modbus.ejecutarPeticion(null);
        new Config().setVisible(true);
    }
    
}
