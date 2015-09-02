/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmodbus;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LUCAS
 */
public class Modbus {

    private String port;
    private int rate;
    private int timeout;
    private int retries;
    private String id;
    private int address;
    private int nvar;
    private int functionNumber;
    private CRC16 crc16;

    public Modbus(String port, int rate, int timeout, int retries, String id, int address, int nvar, int functionNumber) {
        this.port = port;
        this.rate = rate;
        this.timeout = timeout;
        this.retries = retries;
        this.id = id;
        this.address = address;
        this.nvar = nvar;
        this.functionNumber = functionNumber;
        this.crc16 = new CRC16();
    }

    public String ejecutarPeticion() {
        String respuesta = "";
        /* Armamos la trama de acuerdo a la función */
        List<Integer> trama = armarTrama(this.functionNumber);

        /* Enviar la petición */
        return respuesta;
    }

    private List<Integer> armarTrama(int functionNumber) {
        List<Integer> trama = null;
        switch (functionNumber) {
            case 3:
                trama = armarTramaFunction3();
                break;
            case 6:
                trama = armarTramaFunction6();
                break;
            case 16:
                trama = armarTramaFunction16();
                break;
        }

        return trama;
    }

    private List<Integer> armarTramaFunction3() {
        List<Integer> trama = new ArrayList<Integer>();

        /* #1: (1 byte) ID del dispositivo 0..255 */
        trama.add(prepareByte(this.id));

        /* #2: (1 byte) numero de funcion 0..255 */
        trama.add(this.functionNumber);

        /* #3: (2 byte) direccion de inicio de lectura (0..255)(0..255) */
        trama.add(this.address / 256);
        trama.add(this.address % 256);

        /* #4: (2 byte) cantidad de variables (0..255)(0..255) */
        trama.add(this.nvar / 256);
        trama.add(this.nvar % 256);

        /* #5: (2 byte) CRC (0..255)(0..255) */
        return trama;
    }

    private Byte[] armarTramaFunction6(int valor) {
        List<Byte> trama = new ArrayList<>();

        /* #1: (1 byte) ID del dispositivo 0..64 */
        trama.add(prepararByte(this.id));

        /* #2: (1 byte) numero de funcion 0..255 */
        trama.add((byte) functionNumber);

        /* #3: (2 byte) direccion de inicio de lectura (0..255)(0..255) */
        trama.add((byte) (address / 256));
        trama.add((byte) (address % 256));

        /* #4: (2 byte) cantidad de variables (0..255)(0..255) */
        trama.add((byte) (valor / 256));
        trama.add((byte) (valor % 256));

        Byte[] tramaEnviar = crc16.calcularCrc16(trama);

        return tramaEnviar;
    }

    private Byte[] armarTramaFunction16(int[] valores) {
        //El tamaño de el arreglo de datos no debe superar los 123 registros
        //Se debe calcular la cantidad de bytes que se envian en funcion de la cantidad de registros a escribir  (n reg x 2 bytes)
        /*El formato de la trama es: [id], [funcion], [address], [cantidad de registros], [cantidad de bytes], [valores], [CRC]*/
        List<Byte> trama = new ArrayList<>();

        /* #1: (1 byte) ID del dispositivo 0..64 */
        trama.add(prepararByte(this.id));

        /* #2: (1 byte) numero de funcion 0..255 */
        trama.add((byte) functionNumber);

        /* #3: (2 byte) direccion de inicio de lectura (0..255)(0..255) */
        trama.add((byte) (address / 256));
        trama.add((byte) (address % 256));

        /* #4: (2 byte) cantidad de variables (0..255)(0..255) */
        trama.add((byte) (this.nvar / 256));
        trama.add((byte) (this.nvar % 256));

        /* #5 (2 byte) cantidad de bytes de datos */
        trama.add((byte) (this.nvar * 2));

        /* #6 (2 a 2*n bytes) valores a escribir en los registros */
        for (int valor : valores) {
            trama.add((byte) (valor / 256));
            trama.add((byte) (valor % 256));
        }
        
        Byte[] tramaEnviar = crc16.calcularCrc16(trama);

        return tramaEnviar;
    }

    private int prepareByte(String byteString) {
        /* Esta funcion recibe un string y lo devuelve como un byte en int */
        return Integer.parseInt(byteString);
    }

    private byte prepararByte(String byteString) {
        byte retorno;
        int valor = Integer.valueOf(byteString);
        retorno = (byte) valor;
        return retorno;
    }
}
