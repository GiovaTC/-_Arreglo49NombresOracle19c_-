# -_Arreglo49NombresOracle19c_- :. 
Arreglo49NombresOracle19c:

<img width="1254" height="1254" alt="image" src="https://github.com/user-attachments/assets/05abba0d-44f2-4b3f-8bd8-c8addb96458b" />

<img width="2551" height="1077" alt="image" src="https://github.com/user-attachments/assets/42cd0d71-d0a2-4a8d-9272-aee73e510136" />   

<img width="2553" height="1079" alt="image" src="https://github.com/user-attachments/assets/00244980-d06a-4c07-949c-1b2cf1543466" />   

```

Proyecto de consola desarrollado en Java 21, Maven, JDBC e Oracle Database 19c. El programa imprime un arreglo de 49 nombres,
recorre sus elementos y almacena cada nombre en Oracle mediante un procedimiento almacenado PL/SQL.

1. Características
- Arreglo de Java con 49 nombres.
- Impresión numerada de los nombres en consola.
- Conexión a Oracle 19c mediante JDBC (ojdbc11).
- Inserción de cada nombre mediante PR_INSERTAR_NOMBRE.
- Transacción única: COMMIT cuando todo termina correctamente y ROLLBACK si ocurre un error.
- Consulta SQL para verificar los registros insertados.

Importante: cada ejecución inserta nuevamente los 49 nombres. Si ejecutas el programa varias veces, la tabla tendrá más de 49 registros, a menos que limpies los datos antes de volver a ejecutarlo.

2. Requisitos
- JDK 21.
- IntelliJ IDEA.
- Maven.
- Oracle Database 19c.
- SQL Developer (o una herramienta equivalente para ejecutar el script SQL).
- Servicio Oracle disponible. En este ejemplo se utiliza localhost:1521/orcl.

3. Estructura del proyecto

Arreglo49NombresOracle19c/
├── pom.xml
├── sql/
│   └── script_oracle.sql
└── src/
    └── main/
        └── java/
            ├── config/
            │   └── ConexionOracle.java
            └── main/
                └── Main.java

4. Configuración de Maven
Archivo: pom.xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.oracle.java</groupId>
    <artifactId>Arreglo49NombresOracle19c</artifactId>
    <version>1.0</version>

    <properties>
        <maven.compiler.release>21</maven.compiler.release>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.oracle.database.jdbc</groupId>
            <artifactId>ojdbc11</artifactId>
            <version>23.6.0.24.10</version>
        </dependency>
    </dependencies>

</project>

5. Script de Oracle Database 19c
Crea la carpeta sql y dentro el archivo script_oracle.sql. Ejecuta el script conectado con el esquema que utilizará la aplicación.

Archivo: sql/script_oracle.sql
-- =====================================================
-- PROYECTO: ARREGLO DE 49 NOMBRES
-- BASE DE DATOS: ORACLE DATABASE 19C
-- =====================================================

-- 1. Crear la tabla.
CREATE TABLE REGISTRO_NOMBRES (
    ID NUMBER GENERATED ALWAYS AS IDENTITY,
    NOMBRE VARCHAR2(100) NOT NULL,
    FECHA_REGISTRO DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT PK_REGISTRO_NOMBRES PRIMARY KEY (ID)
);

-- 2. Crear el procedimiento almacenado.
CREATE OR REPLACE PROCEDURE PR_INSERTAR_NOMBRE (
    P_NOMBRE IN VARCHAR2
)
AS
BEGIN
    INSERT INTO REGISTRO_NOMBRES (
        NOMBRE,
        FECHA_REGISTRO
    )
    VALUES (
        P_NOMBRE,
        SYSDATE
    );
END;
/

-- 3. Verificar los registros.
SELECT
    ID,
    NOMBRE,
    FECHA_REGISTRO
FROM REGISTRO_NOMBRES
ORDER BY ID;

-- 4. Consultar el total de registros.
SELECT COUNT(*) AS TOTAL_REGISTROS
FROM REGISTRO_NOMBRES;

Si necesitas volver a ejecutar el ejercicio desde cero
Este comando elimina los registros existentes, pero conserva la tabla y el procedimiento:
DELETE FROM REGISTRO_NOMBRES;
COMMIT;

Si deseas reiniciar también el valor de la columna identidad, puedes eliminar y volver a crear la
tabla ejecutando el script de creación (primero elimina los objetos existentes):
DROP PROCEDURE PR_INSERTAR_NOMBRE;
DROP TABLE REGISTRO_NOMBRES PURGE;

6. Clase de conexión a Oracle
Crea el paquete config y la clase ConexionOracle.java.
Archivo: src/main/java/config/ConexionOracle.java
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {

    private static final String URL =
            "jdbc:oracle:thin:@//localhost:1521/orcl";

    private static final String USUARIO = "system";
    private static final String PASSWORD = "tu_password";

    private ConexionOracle() {
        // Evita instanciar esta clase de utilidad.
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USUARIO,
                PASSWORD
        );
    }
}

Configurar la conexión
Modifica estos valores según tu instalación:
private static final String URL =
        "jdbc:oracle:thin:@//localhost:1521/orcl";

private static final String USUARIO = "system";
private static final String PASSWORD = "tu_password";
- localhost: servidor donde está instalado Oracle.
- 1521: puerto del listener, si es el puerto configurado en tu instalación.
- orcl: nombre del servicio Oracle. Sustitúyelo si tu servicio tiene otro nombre.
- system: usuario de conexión de ejemplo. Es preferible utilizar un usuario propio de la aplicación con permisos únicamente sobre los objetos necesarios.
- tu_password: contraseña correspondiente al usuario.

7. Programa principal Java
Crea el paquete main y la clase Main.java.

Archivo: src/main/java/main/Main.java
package main;

import config.ConexionOracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

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
            System.out.println(
                    "Nombre " + (i + 1) + ": " + nombres[i]
            );
        }

        System.out.println("--------------------------------------");
        System.out.println("Total de nombres: " + nombres.length);

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
            System.out.println(
                    "Total de registros insertados: " + registros
            );
            System.out.println("Base de datos: Oracle 19c");
            System.out.println("Estado: TRANSACCION CONFIRMADA");

        } catch (SQLException e) {

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

            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println(
                            "Error cerrando la conexión: " + e.getMessage()
                    );
                }
            }
        }
    }
}

8. Explicación del funcionamiento
A. Declaración del arreglo
String[] nombres = {
    "Alejandro",
    "Andres",
    "Carlos"
    // ... los demás nombres
};
El arreglo almacena los 49 nombres en memoria.

B. Recorrido e impresión
for (int i = 0; i < nombres.length; i++) {
    System.out.println(
        "Nombre " + (i + 1) + ": " + nombres[i]
    );
}
El ciclo for recorre las posiciones del arreglo desde 0 hasta 48. Para mostrar la numeración al usuario se suma uno al índice.

C. Invocación del procedimiento almacenado
String sql = "{call PR_INSERTAR_NOMBRE(?)}";

try (CallableStatement cs = conexion.prepareCall(sql)) {
    for (String nombre : nombres) {
        cs.setString(1, nombre);
        cs.execute();
    }
}

CallableStatement permite invocar el procedimiento PL/SQL y enviar cada nombre como parámetro.D. Control de transacciones
conexion.setAutoCommit(false);

// Inserciones...

conexion.commit();
Si ocurre una excepción SQL antes del COMMIT, el programa intenta ejecutar ROLLBACK para deshacer las inserciones de esa ejecución.

9. Ejemplo de salida en consola
La salida será similar a esta (se muestran algunos registros para abreviar):
======================================
       ARREGLO DE 49 NOMBRES
======================================
Nombre 1: Alejandro
Nombre 2: Andres
Nombre 3: Carlos
Nombre 4: Daniel
Nombre 5: David
...
Nombre 47: Laura
Nombre 48: Maria
Nombre 49: Valentina
--------------------------------------
Total de nombres: 49

Conexión con Oracle 19c exitosa.
======================================
   PROCESANDO INFORMACION EN ORACLE
======================================
Registro 1 almacenado: Alejandro
Registro 2 almacenado: Andres
Registro 3 almacenado: Carlos
...
Registro 49 almacenado: Valentina

======================================
       PROCESAMIENTO FINALIZADO
======================================
Total de registros insertados: 49
Base de datos: Oracle 19c
Estado: TRANSACCION CONFIRMADA

10. Consultas de verificación en Oracle
Consultar todos los nombres
SELECT
    ID,
    NOMBRE,
    FECHA_REGISTRO
FROM REGISTRO_NOMBRES
ORDER BY ID;

Consultar la cantidad total
SELECT COUNT(*) AS TOTAL_REGISTROS
FROM REGISTRO_NOMBRES;

Después de una ejecución sobre una tabla vacía, el resultado esperado es 49.
Consultar nombres diferentes
SELECT COUNT(DISTINCT NOMBRE) AS NOMBRES_DIFERENTES
FROM REGISTRO_NOMBRES;
Para este conjunto de datos, el resultado esperado es 49.

11. Ejecución desde IntelliJ IDEA
  1. Abre IntelliJ IDEA.
  2. Selecciona New Project y crea un proyecto Maven.
  3. Asigna el nombre Arreglo49NombresOracle19c.
  4. Selecciona JDK 21.
  5. Copia el contenido del archivo pom.xml.
  6. Espera a que Maven descargue la dependencia ojdbc11.
  7. Crea los paquetes config y main.
  8. Crea las clases ConexionOracle y Main.
  9. Ejecuta sql/script_oracle.sql en SQL Developer.
  10. Configura la URL, el usuario y la contraseña de Oracle.
  11. Ejecuta Main.java.
  12. Verifica los registros mediante las consultas SQL anteriores.

12. Problemas frecuentes
Problema	Posible solución
ORA-12514	Verifica que el nombre del servicio (orcl) sea correcto y que esté registrado en el listener.
ORA-01017	Verifica el usuario y la contraseña.
ORA-00942	Ejecuta el script en el esquema correcto y verifica que la tabla exista.
PLS-00201	Verifica que el procedimiento exista y que el usuario tenga permisos para ejecutarlo.
Connection refused	Verifica que Oracle esté iniciado y que el listener esté escuchando en el puerto configurado.
No suitable driver	Verifica que Maven haya descargado ojdbc11 y que el proyecto haya sincronizado sus dependencias.

13. Resumen técnico
Componente	Tecnología
Lenguaje	  Java 21
IDE	        IntelliJ IDEA
Gestor de dependencias	Maven
Conectividad	JDBC
Driver	Oracle JDBC ojdbc11
Base de datos	Oracle Database 19c
Almacenamiento	Tabla REGISTRO_NOMBRES
Procedimiento	PR_INSERTAR_NOMBRE
Estructura de datos	Arreglo de 49 elementos
Procesamiento	Ciclo for
Transacciones	COMMIT y ROLLBACK

Fin del proyecto.
Desarrollado como ejercicio práctico de programación Java, arreglos, ciclos, JDBC, procedimientos
almacenados y persistencia de información en Oracle Database 19c .
:. . / .
