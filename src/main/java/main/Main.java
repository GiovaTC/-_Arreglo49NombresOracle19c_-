package main;

import config.ConexionOracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Main
{

    public static void main(String[] args)
    {
        // ==========================================
        // 1. ARREGLO CON 49 NOMBRES
        // ==========================================

        String[] nombres = {
                "Alejandro",
                "Andres",
                "Carlos",
                "Daniel",
                "David",
                "Diego",
                "Eduardo",
                "Felipe",
                "Fernando",
                "Francisco",
                "Gabriel",
                "Gustavo",
                "Hector",
                "Hernando",
                "Ivan",
                "Javier",
                "Jorge",
                "Jose",
                "Juan",
                "Julian",
                "Leonardo",
                "Luis",
                "Manuel",
                "Marcelo",
                "Marco",
                "Mario",
                "Mateo",
                "Miguel",
                "Nicolas",
                "Oscar",
                "Pablo",
                "Pedro",
                "Rafael",
                "Ricardo",
                "Roberto",
                "Samuel",
                "Santiago",
                "Sebastian",
                "Sergio",
                "Tomas",
                "Victor",
                "Adriana",
                "Andrea",
                "Camila",
                "Carolina",
                "Diana",
                "Laura",
                "Maria",
                "Valentina"
        };

        // ==========================================
        // 2. IMPRIMIR EL ARREGLO EN CONSOLA
        // ==========================================

        System.out.println("======================================");
        System.out.println("       ARREGLO DE 49 NOMBRES");
        System.out.println("======================================");

        for (int i = 0; i < nombres.length; i++)
        {
            System.out.println("NOMBRE " + (i + 1) + ": " + nombres[i]);
        }

        System.out.println("--------------------------------------");
        System.out.println("TOTAL de NOMBRES: " + nombres.length);

        // ==========================================
        // 3. PROCESAR LOS NOMBRES EN ORACLE
        // ==========================================
    }   
}