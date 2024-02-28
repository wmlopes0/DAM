-- EJERCICIO DE ENTREGA SOBRE PERMISOS Y USUARIOS
-- 1ºDAW
-- 16-05-2023
-- WALTER MARTIN LOPES
-- --------------------------------
-- ***************************************************************************
-- ********************************* HOJA 1 **********************************
-- ***************************************************************************
-- 1. Conéctate al servidor de Mysql con el usuario root y la contraseña 1234.
-- 2. Crea la BD OPOSICIONES
CREATE DATABASE OPOSICIONES;
-- 3. Con el comando show databases comprueba que la base de datos oposiciones se ha creado correctamente
SHOW DATABASES;
-- 4. Conecta a la base de datos oposiciones con el comando use oposiciones y crea las siguientes tablas: TABLA EXAMENES:
USE OPOSICIONES;

CREATE TABLE EXAMENES(
CODIGOEXA INT PRIMARY KEY DEFAULT 0,
DESCRIPCIONEXA VARCHAR(20));
-- 5. Comprueba la estructura de la tabla con el comando describe.
DESCRIBE EXAMENES;
-- 6. Inserta los siguientes registros:
INSERT INTO EXAMENES VALUES(1,'examen tipo test'),
						   (2,'supuestos practicos'),
						   (3,'teoria');
-- 7. Comprueba que la tabla se ha realizado correctamente con la sentencia select
SELECT * FROM EXAMENES;
-- 8. CREA LA TABLA TRIBUNALES, con los campos que ves en la siguiente imagen y con esos tipos de datos y restricciones.
CREATE TABLE TRIBUNALES(
CODIGOTRI INT PRIMARY KEY AUTO_INCREMENT,
PRESIDENTE VARCHAR(50),
SECRETARIO VARCHAR(50),
VOCAL1 VARCHAR(50),
VOCAL2 VARCHAR(50),
VOCAL3 VARCHAR(50));
-- 9. Inserta los siguientes registros:
INSERT INTO TRIBUNALES (PRESIDENTE,SECRETARIO,VOCAL1,VOCAL2,VOCAL3)
VALUES('PEDRO FERNANDEZ','ISABEL FRESNEDA','LUISMERLO','ANTONIO ROBLES','VIVENCE ROBLES'),
('ANA LIMA','TOMAS RODRIGUEZ','ROCIO CINTA','MODESTA HERMOSO','JUAN ARGUETA'),
('PEDRO MUNOZ','ANTONIO JESUS','EVA MATA','MONTSERRAT HERMOSO','JUAN MARTINEZ');
-- 10. Crea la tabla OPOSITORES:
CREATE TABLE OPOSITORES(
DNI VARCHAR(11) PRIMARY KEY,
NOMBRE VARCHAR(50),
CIUDAD VARCHAR(50),
CODPOSTAL VARCHAR(5),
TELEFONO VARCHAR(9),
TRIBUNAL INT,
FOREIGN KEY TRIBUNAL_FK (TRIBUNAL) REFERENCES TRIBUNALES (CODIGOTRI) ON UPDATE CASCADE ON DELETE CASCADE);
-- 11. Insertar varios registros en la tabla:
INSERT INTO OPOSITORES VALUES
('10000000A','PEREZ RODRIGUEZ, JUAN JOSE','BADAJOZ','06000','924000000',1),
('10000000B','MARTINEZ PEREZ, MARIA LUISA','BADAJOZ','06000','924000001',3),
('10000011C','LOPEZ MARTINEZ, ANTONIO','BADAJOZ','06000','924000111',1),
('22000011A','GONZALEZ LOPEZ, MANUEL JESÚS','CACERES','10010','927000111',1),
('32000011F','GONZALEZ TENA, MARIA INMACULADA','CACERES','10010','927140111',1);

-- 12. Sobre la BBDD oposiciones, da de alta a los usuarios que se muestran a continuación:
-- juan, pedro, ana, luis y eva. La contraseña será la misma que el nombre. Utiliza el comando CREATE USER y NO asignes ningún privilegio a ninguno.
-- Ejemplo: creación del usuario juan:
CREATE USER JUAN IDENTIFIED BY 'JUAN';
CREATE USER PEDRO IDENTIFIED BY 'PEDRO';
CREATE USER ANA IDENTIFIED BY 'ANA';
CREATE USER LUIS IDENTIFIED BY 'LUIS';
CREATE USER EVA IDENTIFIED BY 'EVA';

-- 13. Sobre la BBDD oposiciones da de alta a un usuario administrador en la base de datos. Su nombre será admin con la contraseña ‘administrador’. Este usuario podrá realizar cualquier tarea con la base de datos, incluso otorgar/denegar permisos. Después prueba a conectar con ese usuario.
CREATE USER ADMIN IDENTIFIED BY 'ADMINISTRADOR';
GRANT ALL ON OPOSICIONES.* TO 'ADMIN' WITH GRANT OPTION;

-- 14. Cuando estés conectado con el usuario admin que acabas de crear:
-- a. Modifica los usuarios que hiciste en el ejercicio 12 para concederle permiso de select en todas las tablas de la base de datos oposiciones. Realiza pruebas para comprobar que los usuarios pueden conectarse y realizar la operación de select en las tablas.
GRANT SELECT ON oposiciones.* TO 'JUAN';
GRANT SELECT ON oposiciones.* TO 'PEDRO';
GRANT SELECT ON oposiciones.* TO 'ANA';
GRANT SELECT ON oposiciones.* TO 'LUIS';
GRANT SELECT ON oposiciones.* TO 'EVA';
-- b. Modifica los usuarios pedro y eva para darle permisos insert/update en la tabla opositores.
GRANT INSERT, UPDATE ON oposiciones.opositores TO 'PEDRO';
GRANT INSERT, UPDATE ON oposiciones.opositores TO 'EVA';
-- c. Modifica el usuario pedro revocando el permiso de insert.
REVOKE INSERT ON oposiciones.opositores FROM 'PEDRO';

-- ***************************************************************************
-- ********************************* HOJA 2 **********************************
-- ***************************************************************************
-- 1. Crear un usuario llamado “tribunal” que tenga acceso a todas las tablas de la BD desde localhost→ tribunal@localhost (UTILIZA LA CONTRASEÑA 1234 PARA TODOS LOS USUARIOS)
CREATE USER 'tribunal'@'localhost' IDENTIFIED BY '1234';

GRANT ALL PRIVILEGES ON oposiciones.* TO 'tribunal'@'localhost';
-- 2. Crear un usuario llamado “opositor” (opositor@localhost) que tenga permiso de lectura a la tabla OPOSITORES.
CREATE USER 'opositor'@'localhost' IDENTIFIED BY '1234';

GRANT SELECT ON oposiciones.opositores TO 'opositor'@'localhost';
-- 3. Crear un usuario llamado “opositor_priv” con permisos de lectura, inserción y modificación en la tabla OPOSITORES.
CREATE USER 'opositor_priv' IDENTIFIED BY '1234';

GRANT ALL PRIVILEGES ON oposiciones.opositores TO 'opositor_priv';
-- 4. Crear un usuario llamado “super” que tenga todos los privilegios a todas las bases de datos de nuestro servidor mysql. Este usuario no tendrá la posibilidad de dar privilegios a otros usuarios, aunque sí de crearlos.
CREATE USER 'super' IDENTIFIED BY '1234';

GRANT ALL PRIVILEGES ON *.* TO 'super' WITH GRANT OPTION;

REVOKE GRANT OPTION ON *.* FROM 'super';
-- 5. Modifica el usuario anterior para que ahora sí pueda otorgar privilegios a otros usuarios.
GRANT GRANT OPTION ON *.* TO 'super';
-- 6. Crear un usuario llamado “user_ocasional” con permiso para realizar 5 consultas a la BD OPOSICIONES por hora. (WITH MAX_QUERIES_PER_HOUR 5)
CREATE USER 'user_ocasional' IDENTIFIED BY '1234';
GRANT SELECT ON oposiciones.* TO 'user_ocasional';
ALTER USER 'user_ocasional' WITH MAX_QUERIES_PER_HOUR 5;
-- 7. Cambia la contraseña del usuario tribunal@localhost a “4321”.
ALTER USER 'tribunal'@'localhost' IDENTIFIED BY '4321';
-- 8. Modifica el usuario “opositor_priv” para revocar los permisos que tenía de modificación en la tabla OPOSITORES.
REVOKE INSERT, UPDATE, DELETE ON oposiciones.opositores FROM 'opositor_priv';
-- 9. Eliminar todos los privilegios al usuario opositor@localhost
-- REVOKE ALL PRIVILEGES ON oposiciones.opositores FROM 'opositor'@'localhost';
-- 10. Muestra los privilegios de usuario opositor@localhost después de la última modificación.
SHOW GRANTS FOR 'opositor'@'localhost';

