/***********************************************************
CF DAM-DAW MODULO BBDD -
EXAMEN BASE DE DATOS 31 DE MAYO DE 2021
PUERTO CRUZ MATEOS
*************************************************************/
DROP DATABASE IF EXISTS FINAL_MAYO21_NACHO;
CREATE DATABASE FINAL_MAYO21_NACHO;
USE FINAL_MAYO21_NACHO;

DROP TABLE IF EXISTS PRUEBAS;
CREATE TABLE PRUEBAS (
  CODIGOPRUEBA int NOT NULL DEFAULT '0' PRIMARY KEY,
  TIPOPRUEBA varchar(50) DEFAULT NULL
  );

  
-- ----------------------------
INSERT INTO PRUEBAS VALUES ('3', 'EXAMEN TEMA TEORIA');
INSERT INTO PRUEBAS VALUES ('1', 'EXAMEN TIPO TEST 20 PREGUNTAS');
INSERT INTO PRUEBAS VALUES ('2', 'SUPUESTOS PRACTICOS (3)');
                                   
DROP TABLE IF EXISTS TRIBUNAL;
CREATE TABLE TRIBUNAL (
  CODIGOTRIBUNAL int NOT NULL DEFAULT '0'  PRIMARY KEY,
  PRESIDENTE varchar(50) DEFAULT NULL,
  SECRETARIO varchar(50) DEFAULT NULL,
  VOCAL1 varchar(50) DEFAULT NULL,
  VOCAL2 varchar(50) DEFAULT NULL
 );

-- ----------------------------
-- Records of TRIBUNAL
-- ----------------------------
INSERT INTO TRIBUNAL VALUES ('1', 'PEDRO FERNANDEZ', 'ISABEL FRESNEDA', 'LUIS MERLO', 'ANTONIO ROBLES'),
							 ('2', 'ANA LIMA', 'TOMAS RODRIGUEZ', 'ROCIO CINTIA', 'MODESTA HERMOSO'),
							 ('3', 'PEDRO MUOZ', 'ANTONIO JESUS MUÑOZ', 'EVA MATA', 'MONTSERRAT ISAMA'),
							 ('4', 'JOAQUIN TENA', 'MANOLO SABINA', 'ANA CIMA', 'VICTOR FUENTES'),
							 ('5', 'MATIAS PRATS', 'FEDERICO JIMENEZ', 'IÑAQUI ASEUTUNA', 'LUIS DEL OLMO');
DROP TABLE IF EXISTS ALUMNOS;
CREATE TABLE ALUMNOS (
  DNI varchar(9) NOT NULL DEFAULT '',
  NOMBRE varchar(255) DEFAULT NULL,
  CIUDAD varchar(255) DEFAULT NULL,
  TELEFONO varchar(9) DEFAULT NULL,
  TRIBUNAL_EXAMINA int DEFAULT NULL,
  PRIMARY KEY (DNI),
  CONSTRAINT opo_tri_fk FOREIGN KEY (TRIBUNAL_EXAMINA) REFERENCES TRIBUNAL (CODIGOTRIBUNAL) ON DELETE CASCADE ON UPDATE CASCADE
);
-- ----------------------------
-- Records of ALUMNOS
-- ----------------------------
INSERT INTO ALUMNOS VALUES ('1173391', 'GUTIERREZ MARTIN, JUAN JOSE', 'BADAJOZ', '924493332', '1'),
 ('11841394', 'MAESTRE PEÑALBA, PATRICIA', 'CACERES', '927493333', '1'),
 ('11843671', 'TRUJILLO GALLEGO, MONICA', 'CACERES', '927493334', '2'),
 ('11843982', 'PLACER DE MIGUEL, VICTOR', 'BADAJOZ', '924493335', '3'),
('11844272', 'KAISSI CHERRIB, LAMIA', 'BADAJOZ', '924493336', '4'),
 ('11845343', 'GIGORRO CUBERO, IZASKUN', 'PLASENCIA', '927493337', '5'),
 ('11849578', 'ROJAS GARCIA, MARTA', 'BADAJOZ', '924493338', '5'),
 ('11850727', 'SANCHEZ RUIZ, MARTA', 'PLASENCIA', '927493339', '5'),
 ('11850969', 'JARDON MENDEZ, MANUEL LEOPOLDO', 'CACERES', '927493340', '5'),
 ('11851761', 'CRISTINA FERNANDEZ, ROBERTO', 'BADAJOZ', '924493341', '4'),
 ('11852345', 'ALONSO MUÑOZ, ALEJANDRO', 'CACERES', '927493342', '4'),
 ('14308556', 'COLINA MORA, ANGEL', 'PLASENCIA', '927493343', '3'),
 ('1932986', 'MUÑOZ SANCHEZ, DAVID', 'BADAJOZ', '924493344', '2'),
 ('1933685', 'COBO LENDINEZ, JUAN ANTONIO', 'NAVALMORAL', '927493345', '2'),
 ('1937101', 'ALONSO GARCIA, JESUS', 'PLASENCIA', '927493346', '2'),
 ('2238233', 'COBOS MARTIN, ANA BELEN', 'TRUJILLO', '927493347', '2'),
 ('2263918', 'GARCIA BALLESTEROS, MYRIAM', 'BADAJOZ', '924493348', '2'),
 ('2267650', 'ARIAS ESCOLAR, MARI CARMEN', 'CACERES', '927493349', '2'),
 ('2269028', 'MOLINERO RODRIGUEZ, FERNANDO', null, '924493350', '2'),
 ('2270188', 'LOPEZ GIL, CRISTINA', 'BADAJOZ', '924493351', '2');

DROP TABLE IF EXISTS NOTAS;
CREATE TABLE NOTAS (
  DNIALUMNO varchar(9) NOT NULL DEFAULT '',
  CODIGOPRUEBA int NOT NULL DEFAULT '0',
  NOTA int DEFAULT NULL,
  PRIMARY KEY (DNIALUMNO,CODIGOPRUEBA),
  CONSTRAINT notas_ALU_fk FOREIGN KEY (DNIALUMNO) REFERENCES ALUMNOS (DNI),
  CONSTRAINT notas_ibfk_1 FOREIGN KEY (CODIGOPRUEBA) REFERENCES PRUEBAS (CODIGOPRUEBA)
);
-- ----------------------------
-- Records of notas
-- ----------------------------
INSERT INTO NOTAS VALUES 
('1173391', '1', '2'),
('1173391', '2', '5'),
('1173391', '3', '6'),
('11841394', '1', '3'),
 ('11841394', '2', '5'),
 ('11841394', '3', '3'),
 ('11843671', '1', '3'),
 ('11843671', '2', '4'),
 ('11843671', '3', '3'),
 ('11843982', '1', '4'),
 ('11843982', '2', '7'),
 ('11843982', '3', '3'),
 ('11844272', '1', '5'),
 ('11844272', '2', '9'),
 ('11844272', '3', '3'),
 ('11845343', '1', '6'),
('11845343', '2', '8'),
 ('11845343', '3', '3');
DESCRIBE NOTAS;
DESCRIBE PRUEBAS;


-- ------------------------------------------------------------------
-- 1.A  Crea un procedimiento almacenado PARA INSERTAR  en la tabla PRUEBAS una nueva prueba cuyos datos 
-- pasamos por parámetro. Es obligatorio  controlar mediante HANDLER:
-- que el código de prueba introducido no existe en la tabla PRUEBAS (SQLSTATE '23000', FILA DUPLICADA).
-- ---------------------------------------------------------------------
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO1;
// CREATE PROCEDURE PROCEDIMIENTO1(P_CODIGOPRUEBA int,P_TIPOPRUEBA varchar(50))
BEGIN
	-- DECLARO HANDLER
    DECLARE EXIT HANDLER FOR SQLSTATE '23000' 
    BEGIN
		SELECT 'ERROR.FILA DUPLICADA' AS MENSAJE;
    END;
    
    INSERT INTO PRUEBAS VALUES (P_CODIGOPRUEBA,P_TIPOPRUEBA);
    SELECT * FROM PRUEBAS;
END; //
DELIMITER ;

CALL PROCEDIMIENTO1('8', 'EXAMEN TEMA TEORIA');

DESC PRUEBAS;
 -- ------------------------------------------------------------------
-- EJERCICIO 1-B.  Crea un procedimiento almacenado que toma un DNI como parámetro y borra al ALUMNO  de la tabla ALUMNOS 
-- Es obligatorio controlar que la tabla ALUMNOS existe (SQLSTATE '42S02', TABLA NO EXISTE); en es caso devolverá un mensaje.
-- (2 PUNTOS).  
-- Comprueba que el procedimiento funciona ejecutándolo para borrar el alumno con DNI: 11733911 
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO2;
//CREATE PROCEDURE PROCEDIMIENTO2(P_DNI varchar(9))
BEGIN
	-- DECLARO HANDLER
    DECLARE EXIT HANDLER FOR SQLSTATE '42S02' SELECT 'TABLA NO EXISTE' AS MENSAJE;
    
    DELETE FROM ALUMNOS
    WHERE DNI =P_DNI;
END;//
DELIMITER ;

CALL PROCEDIMIENTO2(1173391);
SELECT * FROM ALUMNOS;
DESC ALUMNOS;

 -- ------------------------------------------------------------------
-- EJERCICIO 2-A. Crea un procedimiento que muestre el DNI y la nota media de los alumnos que han obtenido 
-- una media mayor a un número entero que le pasamos como parámetro.  
-- Ejecuta el procedimiento mostrando a los alumnos con nota media mayor que 4.  
-- -------------------------------------------------------------------
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO3;
//CREATE PROCEDURE PROCEDIMIENTO3(P_NOTA INT)
BEGIN
	SELECT DNIALUMNO,AVG(NOTA) FROM NOTAS
	GROUP BY DNIALUMNO
    HAVING AVG(NOTA) > P_NOTA;
END;//
DELIMITER ;

CALL PROCEDIMIENTO3(4);

DESC NOTAS;
 -- ------------------------------------------------------------------
-- EJERCICIO 3-A. Crea una función  que toma como parámetro el DNI del alumno y devuelve su nota media. Ejecuta la función para:  
-- A) Obtener la nota media de todos los alumnos.
-- B) Obtener la nota media del alumno con DNI: '11845343'
 -- ------------------------------------------------------------------

 -- ------------------------------------------------------------------
 -- EJERCICIO 4-A.  Modifica la tabla NOTAS añadiéndole un nuevo campo EXENTO,  que solo podrá tener el valor SI o NO. Por defecto valdrá NO. 
 -- Cambia el valor EXENTO A SI para los alumnos de Cáceres. Después crea una función que tomará como parámetro 
 -- el dni de un alumno y el código de prueba.
 -- Si el campo EXENTO  vale SI y el código de prueba que hemos pasado por parámetro es un 2, la función devolverá 
 -- el siguiente mensaje:EXENTO DE LA PRUEBA PRACTICA
 -- SI NO, NOTA=NOTA+1


-- ------------------------------------------------------------------
-- TRIGGER 1: AUDITORIA DE LA TABLA NOTAS
--  Crea un TRIGGER que cada vez que se actualice la tabla NOTAS, inserte un registro en la tabla HISTORIALNOTAS,
-- Esta tabla tendrá un solo campo de tipo varchar, donde se almacenará: 
-- la fecha, hora, dnialumno, codigoprueba, la nota antigua,  la nota nueva y el usuario que hizo la modificación.
-- --------

 -- ------------------------------------------------------------------
-- TRIGGER 2: Crea un trigger que cada vez que borremos un registro de la tabla alumnos,
-- lo inserte en la tabla EXCLUIDOS. Para ello primero crea la tabla EXCLUIDOS con las columnas: 
-- FECHA, DNIALUMNO.
-- Comprueba que el trigger funciona intentando borrar el alumno con DNI='2270188' 
-- y mostrando el contenido de la tabla EXCLUIDOS
-- ------------------------------------------------------------------

-- -----------------------------------------------------------------------------------------------------------------------------------
-- PROC CON CURSOR: CREAR UN PROCEDIMIENTO QUE MEDIANTE UN CURSOR RECORRA LA TABLA DE ALUMNOS Y LE VAYA ASIGNANDO DIFERENTES CODIGOS POSTALES SEGÚN SU LOCALIDAD.
-- PARA ELLO, MODIFICAR LA TABLA ALUMNOS AÑADIENDO UN NUEVO CAMPO CODPOSTAL VARCHAR(5) CON VALOR POR DEFECTO '00000'
-- ----------------------------------------------------------------------------------------------------------------------------------
DELIMITER //
DROP PROCEDURE IF EXISTS ejercicio4_sp //
CREATE PROCEDURE ejercicio4_sp(P_POBLACION FLOAT, P_NOMBRE VARCHAR(35))
BEGIN
	-- DECLARO VARIABLE
    DECLARE NOM CHAR(50);
    -- HANDLER FUERA DE RANGO
	DECLARE EXIT HANDLER FOR 1264
    BEGIN
		UPDATE COUNTRY SET POPULATION = POPULATION + (POPULATION * 0.10) WHERE NAME = P_NOMBRE;
    END;
    -- HANDLER OBJETO NO ENTONTRADO
    DECLARE EXIT HANDLER FOR NOT FOUND SELECT 'El país no existe' AS MENSAJE;
	-- ANTES DE ACTUALIZAR UN PAIS QUE PUEDE QUE NO EXISTA, 
    -- INTENTO ALMACENAR EN LA VARIABLE NOM EL PAIS CON EL NOMBRE QUE PASO POR PARAMETRO,
    -- SI NO EXISTE EL PAIS Y NO PUEDE ALMACENAR NADA SALTA EL HANDLER NOTFOUND
	SELECT NAME INTO NOM FROM COUNTRY WHERE NAME = P_NOMBRE;
	UPDATE COUNTRY SET POPULATION = P_POBLACION WHERE NAME = P_NOMBRE;
END; //
DELIMITER ;