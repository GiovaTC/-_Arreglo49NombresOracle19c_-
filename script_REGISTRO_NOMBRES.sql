-- 1. Crear la tabla.
CREATE TABLE REGISTRO_NOMBRES (
    ID NUMBER GENERATED ALWAYS AS IDENTITY,
    NOMBRE VARCHAR2(100) NOT NULL,
    FECHA_REGISTRO DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT PK_REGISTRO_NOMBRES PRIMARY KEY (ID)
);

COMMIT;

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

COMMIT;

-- 3. Verificar los registros!
SELECT
    ID,
    NOMBRE,
    FECHA_REGISTRO
FROM REGISTRO_NOMBRES ORDER BY ID;

-- 4. Consultar el total de registros!
SELECT COUNT(*) AS TOTAL_REGISTROS FROM REGISTRO_NOMBRES;

--Si necesitas volver a ejecutar el ejercicio desde cero
--Este comando elimina los registros existentes, pero conserva la tabla y el procedimiento:
DELETE FROM REGISTRO_NOMBRES;
COMMIT;

--Si deseas reiniciar también el valor de la columna identidad, puedes eliminar y volver a crear la
--tabla ejecutando el script de creación (primero elimina los objetos existentes):
DROP PROCEDURE PR_INSERTAR_NOMBRE;
DROP TABLE REGISTRO_NOMBRES PURGE;