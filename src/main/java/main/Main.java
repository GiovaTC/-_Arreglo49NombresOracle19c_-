package main;

import config.ConexionOracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Main
{

    public static void main(String[] args) {
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

        for (int i = 0; i < nombres.length; i++) {
            System.out.println("NOMBRE " + (i + 1) + ": " + nombres[i]);
        }

        System.out.println("--------------------------------------");
        System.out.println("TOTAL de NOMBRES: " + nombres.length);

        // ==========================================
        // 3. PROCESAR LOS NOMBRES EN ORACLE
        // ==========================================

        String sql = "{call PR_INSERTAR_NOMBRE(?)}";

        Connection conexion = null;

        try {
            conexion = ConexionOracle.conectar();
            conexion.setAutoCommit(false);

            System.out.println("\nConexión con Oracle 19c exitosa.");
            System.out.println("======================================");
            System.out.println("   PROCESANDO INFORMACION EN ORACLE");
            System.out.println("======================================");

            int registros = 0;

            try (CallableStatement cs = conexion.prepareCall(sql)) {
                for (String nombre : nombres) {
                    cs.setString(1, nombre);
                    cs.execute();

                    registros++;

                    System.out.println(
                            "Registro " + registros
                                    + " almacenado: " + nombre
                    );
                }
            }

            // Confirmar únicamente cuando todos los registros
            // hayan sido procesados correctamente.
            conexion.commit();

            System.out.println("\n======================================");
            System.out.println("       PROCESAMIENTO FINALIZADO");
            System.out.println("======================================");
            System.out.println("TOTAL de registros insertados: " + registros);

            System.out.println("Base de datos: Oracle 19c");
            System.out.println("Estado: TRANSACCION CONFIRMADA");

        } catch (SQLException e)
        {
            System.err.println(
                    "\nError procesando la información: " + e.getMessage()
            );

            if (conexion != null) {
                try {
                    conexion.rollback();
                    System.out.println(
                            "Se ejecuto ROLLBACK. No se confirmaron "
                                    + "los registros de esta ejecución."
                    );
                } catch (SQLException rollbackError) {
                    System.err.println(
                            "Error al ejecutar ROLLBACK: "
                                    + rollbackError.getMessage()
                    );
                }
            }
            } finally {

                if (conexion != null)
                {
                    try
                    {
                        conexion.close();
                    } catch (SQLException ex) {
                        System.err.println(
                                "Error cerrando la conexión: " + ex.getMessage()
                        );
                    }
                }
        }
    }
}   