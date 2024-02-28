-- BASE DE DATOS HOTEL
-- 1ºDAW
-- 23-05-2023
-- WALTER MARTIN LOPES
-- --------------------------------
CREATE DATABASE HOTEL;
USE HOTEL;
-- --------------------------------

-- ***********************************************************************************
-- ****************************CREACION DE LA BASE DE DATOS***************************
-- ***********************************************************************************

-- TABLA TIPO_SERVICIO
CREATE TABLE TIPO_SERVICIO(
NOMBRE_SERVICIO VARCHAR(10) PRIMARY KEY);

INSERT INTO  TIPO_SERVICIO  VALUES ('COMEDOR');
INSERT INTO  TIPO_SERVICIO  VALUES ('LAVANDERIA');

-- TABLA PAISES
CREATE TABLE PAISES(
PAIS VARCHAR(20) PRIMARY KEY);

INSERT INTO  paises  VALUES ('ALEMANIA');
INSERT INTO  paises  VALUES ('ESPAÑA');
INSERT INTO  paises  VALUES ('FRANCIA');
INSERT INTO  paises  VALUES ('PORTUGAL');

-- TABLA CLIENTES
CREATE TABLE CLIENTES(
IDENTIFICACION CHAR(12) PRIMARY KEY,
PAIS CHAR(20) NOT NULL,
NOMBRE CHAR(12) NOT NULL,
APELLIDO1 CHAR(12) NOT NULL,
APELLIDO2 CHAR(12),
DIRECCION CHAR(30) NOT NULL,
TELEFONO CHAR(12) NOT NULL,
OBSERVACIONES CHAR(50),
CONSTRAINT PAIS_FK FOREIGN KEY (PAIS) REFERENCES PAISES (PAIS) ON DELETE CASCADE ON UPDATE CASCADE );

INSERT INTO  clientes  VALUES ('12345', 'ESPAÑA', 'Felipe', 'Iglesias', 'López', 'Avda Los Castros, 44', '942344444', 'Buen cliente');
INSERT INTO  clientes  VALUES ('44444', 'ESPAÑA', 'Luis', 'García', 'García', 'Calle Mayor, 67 ', '942456444', null);
INSERT INTO  clientes  VALUES ('456789', 'FRANCIA', 'Ludovic', 'Giuly', 'Bourquin', '18 avenue Alsacen Cour', '37890194', null);

-- TABLA TIPO_HABITACION
CREATE TABLE TIPO_HABITACION(
CATEGORIA INT PRIMARY KEY,
CAMAS INT NOT NULL,
EXTERIOR CHAR(2) NOT NULL CHECK(EXTERIOR IN('SI','NO')),
SALON CHAR(2) NOT NULL CHECK(SALON IN('SI','NO')),
TERRAZA CHAR(2) NOT NULL CHECK(TERRAZA IN('SI','NO')));

INSERT INTO  tipo_habitacion  VALUES (1, 1, 'SI', 'NO', 'NO');
INSERT INTO  tipo_habitacion  VALUES (2, 2, 'SI', 'NO', 'NO');
INSERT INTO  tipo_habitacion  VALUES (3, 3, 'SI', 'NO', 'NO');
INSERT INTO  tipo_habitacion  VALUES (4, 1, 'SI', 'SI', 'NO');

-- TABLA HABITACIONES
CREATE TABLE HABITACIONES(
NUM_HABITACION INT PRIMARY KEY,
TIPO_HABITACION INT NOT NULL,
CONSTRAINT TIPO_HABITACION_FK FOREIGN KEY (TIPO_HABITACION) REFERENCES TIPO_HABITACION (CATEGORIA) ON UPDATE CASCADE ON DELETE CASCADE);

INSERT INTO  habitaciones  VALUES (101, 1);
INSERT INTO  habitaciones  VALUES (102, 1);
INSERT INTO  habitaciones  VALUES (103, 1);
INSERT INTO  habitaciones  VALUES (104, 2);
INSERT INTO  habitaciones  VALUES (105, 2);
INSERT INTO  habitaciones  VALUES (106, 3);
INSERT INTO  habitaciones  VALUES (107, 4);

-- TABLA SERVICIOS
CREATE TABLE SERVICIOS(
ID_SERVICIO INT PRIMARY KEY,
NOMBRE_SERVICIO CHAR(10) NOT NULL,
DESCRIPCION CHAR(30) NOT NULL,
PRECIO DECIMAL(5,2) NOT NULL,
IVA DECIMAL(5,2) NOT NULL,
FECHA DATE NOT NULL,
CONSTRAINT NOMBRE_SERVICIO_FK FOREIGN KEY (NOMBRE_SERVICIO) REFERENCES TIPO_SERVICIO (NOMBRE_SERVICIO) ON UPDATE CASCADE ON DELETE CASCADE);

INSERT INTO  ServicioS  VALUES (1,'COMEDOR','MENU DEL DIA','10','7','2009-01-01');
INSERT INTO  servicioS  VALUES (2,'LAVANDERIA','LAVADO DE CAMISAS','2','7','2009-01-01');
INSERT INTO  servicioS  VALUES (3,'LAVANDERIA','LAVADO DE PANTALON','1','7','2009-01-01');

-- TABLA TEMPORADA
CREATE TABLE TEMPORADA(
TEMPORADA INT PRIMARY KEY,
FECHA_INICIO DATE NOT NULL,
FECHA_FIN DATE NOT NULL,
TIPO CHAR(1) NOT NULL CHECK(TIPO IN('B','M','A')));

INSERT INTO  temporada  VALUES (1, '2009-01-01', '2009-03-31', 'B');
INSERT INTO  temporada  VALUES (2, '2009-04-01', '2009-05-31', 'M');
INSERT INTO  temporada  VALUES (3, '2009-06-01', '2009-08-31', 'A');
INSERT INTO  temporada  VALUES (4, '2009-09-01', '2009-10-31', 'M');
INSERT INTO  temporada  VALUES (5, '2009-11-01', '2009-12-31', 'B');

SELECT * FROM TEMPORADA;

-- TABLA PRECIO_HABITACION
CREATE TABLE PRECIO_HABITACION(
ID_PRECIO INT PRIMARY KEY,
PRECIO DECIMAL(5,2) NOT NULL,
TEMPORADA INT NOT NULL,
TIPO_HABITACION INT NOT NULL,
CONSTRAINT TEMPORADA_FK FOREIGN KEY (TEMPORADA) REFERENCES TEMPORADA (TEMPORADA) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT TIPO_HABITACION2_FK FOREIGN KEY (TIPO_HABITACION) REFERENCES TIPO_HABITACION (CATEGORIA) ON UPDATE CASCADE ON DELETE CASCADE);

INSERT INTO  precio_habitacion  VALUES (6, 35, 1, 2);
INSERT INTO  precio_habitacion  VALUES (7, 40, 2, 2);
INSERT INTO  precio_habitacion  VALUES (8, 45, 3, 2);
INSERT INTO  precio_habitacion  VALUES (9, 40, 4, 2);
INSERT INTO  precio_habitacion  VALUES (10, 35, 5, 2);
INSERT INTO  precio_habitacion  VALUES (11, 40, 1, 3);
INSERT INTO  precio_habitacion  VALUES (12, 45, 2, 3);
INSERT INTO  precio_habitacion  VALUES (13, 50, 3, 3);
INSERT INTO  precio_habitacion  VALUES (14, 45, 4, 3);
INSERT INTO  precio_habitacion  VALUES (15, 40, 5, 3);
INSERT INTO  precio_habitacion  VALUES (16, 50, 1, 4);
INSERT INTO  precio_habitacion  VALUES (17, 55, 2, 4);
INSERT INTO  precio_habitacion  VALUES (18, 60, 3, 4);
INSERT INTO  precio_habitacion  VALUES (19, 55, 4, 4);
INSERT INTO  precio_habitacion  VALUES (20, 50, 5, 4);

-- TABLA RESERVA_HABITAC
CREATE TABLE RESERVA_HABITAC(
ID_RESERVA INT AUTO_INCREMENT PRIMARY KEY,
FECHA_ENTRADA DATE NOT NULL,
FECHA_SALIDA DATE NOT NULL,
IVA DECIMAL(5,2) NOT NULL,
NUM_HABITACION INT NOT NULL,
CLIENTE CHAR(12) NOT NULL,
CONSTRAINT NUM_HABITACION_FK FOREIGN KEY (NUM_HABITACION) REFERENCES HABITACIONES (NUM_HABITACION) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT CLIENTE_FK FOREIGN KEY (CLIENTE) REFERENCES CLIENTES (IDENTIFICACION) ON UPDATE CASCADE ON DELETE CASCADE);

INSERT INTO  reserva_habitac   (Fecha_ENTRADA , Fecha_SALIDA  , IVA , Num_HABITACION  , CLIENTE)
VALUES ( '2009-03-15', '2009-03-25', 0.07,101, '12345');
INSERT INTO  reserva_habitac (Fecha_ENTRADA , Fecha_SALIDA  , IVA , Num_HABITACION  , CLIENTE)
 VALUES ( '2009-03-15', '2009-03-25', 0.07, 102, '12345');
INSERT INTO  reserva_habitac (Fecha_ENTRADA , Fecha_SALIDA  , IVA , Num_HABITACION  , CLIENTE)
 VALUES ( '2009-02-16', '2009-02-21', 0.07,103, '12345');
INSERT INTO  reserva_habitac  (Fecha_ENTRADA , Fecha_SALIDA  , IVA , Num_HABITACION  , CLIENTE)
VALUES ( '2009-03-16', '2009-03-21', 0.07,104, '44444');
INSERT INTO  reserva_habitac (Fecha_ENTRADA , Fecha_SALIDA  , IVA , Num_HABITACION  , CLIENTE)
 VALUES ( '2009-03-16', '2009-03-21', 0.07,105, '44444');
INSERT INTO  reserva_habitac  (Fecha_ENTRADA , Fecha_SALIDA  , IVA , Num_HABITACION  , CLIENTE)
VALUES ( '2009-03-16', '2009-03-21', 0.07,106, '44444');
INSERT INTO  reserva_habitac (Fecha_ENTRADA , Fecha_SALIDA  , IVA , Num_HABITACION  , CLIENTE)
 VALUES ( '2009-03-16', '2009-03-21', 0.07,107, '44444');
 
 -- TABLA GASTOS
 CREATE TABLE GASTOS(
 ID_GASTOS INT AUTO_INCREMENT PRIMARY KEY,
 ID_RESERVA INT NOT NULL,
 ID_SERVICIO INT NOT NULL,
 FECHA DATETIME NOT NULL,
 CANTIDAD INT NOT NULL,
 PRECIO DECIMAL(5,2) NOT NULL,
 CONSTRAINT ID_RESERVA_FK FOREIGN KEY (ID_RESERVA) REFERENCES RESERVA_HABITAC (ID_RESERVA) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT ID_SERVICIO_FK FOREIGN KEY (ID_SERVICIO) REFERENCES SERVICIOS (ID_SERVICIO) ON UPDATE CASCADE ON DELETE CASCADE);
 
 INSERT INTO  gastos  ( id_gastos,id_RESERVA  , id_SERVICIO  ,Fecha ,Cantidad ,Precio)
VALUES ( 1,1,1, '2009-03-15 12:00', 1,10);
INSERT INTO  gastos  ( id_gastos,id_RESERVA  , id_SERVICIO  ,Fecha ,Cantidad ,Precio)
VALUES (2, 1,1,  '2009-03-15 11:00', 1,10);
INSERT INTO  gastos  (id_gastos, id_RESERVA  , id_SERVICIO  ,Fecha ,Cantidad ,Precio)
VALUES (3, 4, 2, '2009-03-15 09:30', 1,2);

-- ***********************************************************************************
-- **************************MANIPULACION DE LA BASE DE DATOS*************************
-- ***********************************************************************************
-- 1. Insertar el tipo de servicio OCIO.
INSERT INTO  TIPO_SERVICIO  VALUES ('OCIO');
SELECT * FROM TIPO_SERVICIO;

-- 2. Inserta una reserva de la habitación 101 para el cliente 12345 para las noches del 2 al 4 de julio de 2009. El código de la reserva es autonumérico.
INSERT INTO  reserva_habitac   (Fecha_ENTRADA , Fecha_SALIDA  , IVA , Num_HABITACION  , CLIENTE)
VALUES ( '2009-07-02', '2009-07-04', 0.07,101, '12345');

-- 3. Actualiza el teléfono del cliente 12345. Su nuevo número es 123456789.
UPDATE CLIENTES
SET TELEFONO = 123456789
WHERE IDENTIFICACION = 12345;

-- 4. Actualiza el precio de los servicios incrementándolos en un 2%.
UPDATE SERVICIOS
SET PRECIO = PRECIO * 1.02;

-- 5. Borra la reserva de la habitación 101 realizada anteriormente.
DELETE FROM RESERVA_HABITAC
WHERE NUM_HABITACION = 101
AND FECHA_ENTRADA = '2009-07-02';

-- 6. Borra el tipo de servicio “ocio” que añadiste antes.
DELETE FROM TIPO_SERVICIO
WHERE NOMBRE_SERVICIO = 'OCIO';

-- ***********************************************************************************
-- *********************************CONSULTAS SENCILLAS*******************************
-- ***********************************************************************************
-- 7. Crea una consulta que devuelva los clientes cuyo apellido incluya la sílaba “le”, ordenados por su identificador.
SELECT * FROM CLIENTES
WHERE APELLIDO1 LIKE '%le%';

-- 8. Crea una consulta que devuelva los clientes, ordenados por su primer apellido, que tengan alguna observación.
SELECT * FROM CLIENTES
WHERE OBSERVACIONES IS NOT NULL
ORDER BY APELLIDO1;

-- 9. Crea una consulta que devuelva los servicios cuyo precio supere los 5 € ordenados por su código de servicio.
SELECT * FROM SERVICIOS
WHERE PRECIO > 5
ORDER BY ID_SERVICIO;

-- 10. Crea una consulta que devuelva las habitaciones reservadas para el día 24 de marzo de 2009.
SELECT * FROM RESERVA_HABITAC
WHERE FECHA_ENTRADA <= '2009-03-24'
AND FECHA_SALIDA >= '2009-03-24';

-- 11. Crea una consulta que devuelva los clientes procedentes de España y Francia.
SELECT * FROM CLIENTES
WHERE PAIS IN('ESPAÑA','FRANCIA');

-- ***********************************************************************************
-- **********************CONSULTAS DE COMBINACIÓN Y SUBCONSULTAS**********************
-- ***********************************************************************************
-- 12. Crea una consulta que devuelva los distintos clientes que han utilizado el servicio de comedor.
SELECT * FROM CLIENTES 
WHERE IDENTIFICACION IN (SELECT DISTINCT R.CLIENTE FROM RESERVA_HABITAC AS R
						INNER JOIN GASTOS AS G USING (ID_RESERVA)
						INNER JOIN SERVICIOS AS S USING (ID_SERVICIO)
						WHERE S.NOMBRE_SERVICIO = 'COMEDOR');

-- 13. Crea una consulta que devuelva las características de cada habitación reservada.
SELECT H.NUM_HABITACION,T.* FROM TIPO_HABITACION AS T
INNER JOIN HABITACIONES AS H ON T.CATEGORIA = H.TIPO_HABITACION
INNER JOIN RESERVA_HABITAC AS R USING (NUM_HABITACION);

-- 14. Crea una consulta que devuelva los precios de los distintos tipos de habitación por temporada.
SELECT TEMPORADA,TIPO_HABITACION,PRECIO FROM PRECIO_HABITACION
ORDER BY TEMPORADA;

-- 15. Crea una consulta que devuelva todos los clientes, y de aquellos que han realizado alguna reserva en marzo, indicar el nº de reserva.
SELECT C.*,R.ID_RESERVA,R.FECHA_ENTRADA FROM CLIENTES AS C 
LEFT JOIN RESERVA_HABITAC AS R ON C.IDENTIFICACION = R.CLIENTE
WHERE MONTH(R.FECHA_ENTRADA) = 3;

-- ***********************************************************************************
-- ******************************CONSULTAS CON GROUP BY*******************************
-- ***********************************************************************************
-- 16. Crea una consulta que devuelva (cuente) el nº de clientes por nacionalidad.
SELECT PAIS,COUNT(*) AS CLIENTES FROM CLIENTES
GROUP BY PAIS;

-- 17. Crea una consulta que devuelva (cuente) el nº de habitaciones por cada categoría de habitación.
SELECT TIPO_HABITACION,COUNT(*) AS NUMERO_HABITACIONES FROM HABITACIONES
GROUP BY TIPO_HABITACION;

-- ***********************************************************************************
-- **********************CONSULTAS CON OTRAS FUNCIONES (max,min…)*********************
-- ***********************************************************************************
-- 18. Cree una consulta que devuelva el precio del servicio más caro y del más barato.
SELECT MAX(PRECIO) AS 'SERVICIO MAS CARO', MIN(PRECIO) AS 'SERVICIO MAS BARATO' FROM SERVICIOS;

-- ***********************************************************************************
-- *************************************FUNCIONES*************************************
-- ***********************************************************************************
-- 19. Crea una función que tome como parámetros una fecha y un número de habitación y devuelva el número de veces que está reservada.
DELIMITER //
DROP FUNCTION IF EXISTS FUNCION1;
// CREATE FUNCTION FUNCION1 (P_FECHA DATE, P_NUM_HABITACION INT) RETURNS INT
DETERMINISTIC
BEGIN
	-- DECLARO VARIABLES
	DECLARE RESULTADO INT;
	-- CODIGO FUNCION
    SELECT COUNT(*) INTO RESULTADO FROM RESERVA_HABITAC 
    WHERE NUM_HABITACION = P_NUM_HABITACION
    AND FECHA_ENTRADA <= P_FECHA
    AND FECHA_SALIDA >= P_FECHA;

	RETURN RESULTADO;
END;//
DELIMITER ;
SELECT * FROM RESERVA_HABITAC;
SELECT FUNCION1('2009-03-16',102) AS RESULTADO;

-- 20. Crea una función que devuelva si una habitación está reservada en una fecha que se especifique.
DELIMITER //
DROP FUNCTION IF EXISTS FUNCION2;
// CREATE FUNCTION FUNCION2 (P_FECHA DATE, P_NUM_HABITACION INT) RETURNS VARCHAR(100)
DETERMINISTIC
BEGIN
	-- CODIGO FUNCION
   IF FUNCION1(P_FECHA, P_NUM_HABITACION) > 0 THEN
		RETURN 'LA HABITACIÓN ESTÁ RESERVADA';
	ELSE
		RETURN 'LA HABITACIÓN NO ESTÁ RESERVADA';
   END IF;
END;//
DELIMITER ;

SELECT FUNCION2('2023-03-16',102) AS RESULTADO;

-- ***********************************************************************************
-- ***************************************VISTAS**************************************
-- ***********************************************************************************
-- 21. Crear una vista que muestre todos los datos del cliente excepto las observaciones. Actualizar el nombre del cliente utilizando esta vista.
CREATE VIEW VISTA1 AS 
SELECT IDENTIFICACION,PAIS,NOMBRE,APELLIDO1,APELLIDO2,DIRECCION,TELEFONO FROM CLIENTES;

SELECT * FROM VISTA1;

-- 22. Crear una vista que muestre el ID, nombre y primer apellido de todos los clientes y el gasto total que ha realizado en el hotel en sus diferentes estancias.
CREATE VIEW VISTA2 AS 
SELECT C.IDENTIFICACION,C.NOMBRE,C.APELLIDO1, SUM((G.PRECIO*G.CANTIDAD)) AS 'TOTAL GASTO' FROM CLIENTES AS C
INNER JOIN RESERVA_HABITAC AS R ON  C.IDENTIFICACION = R.CLIENTE
INNER JOIN GASTOS AS G USING(ID_RESERVA)
GROUP BY C.IDENTIFICACION;

SELECT * FROM VISTA2;
-- ***********************************************************************************
-- ****************************OTRAS RESTRICCIONES (CHECK)****************************
-- ***********************************************************************************
-- 23. Controlar en la tabla temporada que la fecha de fin es mayor que la fecha de inicio.
ALTER TABLE TEMPORADA
ADD CONSTRAINT FECHA_CK CHECK(FECHA_FIN > FECHA_INICIO);

-- 24. Controlar en la tabla reserva_habitac que la fecha de salida es mayor o igual que fecha de entrada.
ALTER TABLE RESERVA_HABITAC
ADD CONSTRAINT FECHA_RESERVA_CK CHECK(FECHA_SALIDA > FECHA_ENTRADA);

-- 25. Controlar en la tabla servicios que el iva está comprendido entre 0 y 100,0.
ALTER TABLE SERVICIOS
ADD CONSTRAINT IVA_CK CHECK(IVA BETWEEN 0 AND 100);

-- ***********************************************************************************
-- ************************************CREATE INDEX***********************************
-- ***********************************************************************************
-- 26. Crear un índice sobre el atributo Nacionalidad de la tabla clientes, con objeto de agilizar las búsquedas.
CREATE INDEX INDICE1 ON CLIENTES (PAIS);

-- 29 Crear un índice sobre el atributo nombreServicio de la tabla servicios, con objeto de agilizar las búsquedas.
CREATE INDEX INDICE2 ON SERVICIOS (NOMBRE_SERVICIO);

-- ***********************************************************************************
-- ***********************************PROCEDIMIENTOS**********************************
-- ***********************************************************************************
-- 1. Crear un procedimiento para realizar la inserción de un nuevo país. Controlar MEDIANTE CONTROL DE ERRORES que no se inserten valores duplicados para países.
-- 1062
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO1;
// CREATE PROCEDURE PROCEDIMIENTO1(P_NOMBRE VARCHAR(30))
BEGIN 
	-- HANDLER
	DECLARE EXIT HANDLER FOR 1062 
    BEGIN
		SELECT 'NO PUEDE INESERTAR VALORES DUPLICADOS' AS ERROR;
    END;
    
    INSERT INTO PAISES VALUES(P_NOMBRE);
END;//
DELIMITER ; 

CALL PROCEDIMIENTO1('ESPAÑA');

CALL PROCEDIMIENTO1('EEUU');

-- 2. Crear un procedimiento con un cursor que aplique un descuento del 10% al precio de las habitaciones que tienen Terraza, un 5% a las que tienen salón y un aumento de precio de 10 € a las habitaciones exteriores.
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO2;
// CREATE PROCEDURE PROCEDIMIENTO2()
BEGIN
	-- DECLARO VARIABLES
    DECLARE FIN_TABLA BOOLEAN DEFAULT FALSE;
    DECLARE V_CATEGORIA INT;
    DECLARE V_EXTERIOR CHAR(2);
	DECLARE V_SALON CHAR(2);
	DECLARE V_TERRAZA CHAR(2);
    
    -- DECLARO CURSOR
	DECLARE C CURSOR FOR SELECT CATEGORIA,EXTERIOR,SALON,TERRAZA FROM TIPO_HABITACION;
    -- DECLARO HANLER
    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET FIN_TABLA = TRUE;
    -- ABRO CURSOR
    OPEN C;
    -- RECORRO CURSOR
    WHILE (FIN_TABLA != TRUE) DO
		FETCH C INTO V_CATEGORIA,V_EXTERIOR,V_SALON,V_TERRAZA;
        IF (FIN_TABLA != TRUE) THEN
			-- EXTERIOR
			IF V_EXTERIOR = 'SI' THEN
				UPDATE PRECIO_HABITACION
                SET PRECIO = PRECIO + 10
                WHERE TIPO_HABITACION = V_CATEGORIA;
            END IF;
            -- SALON
            IF V_SALON = 'SI' THEN
				UPDATE PRECIO_HABITACION
                SET PRECIO = PRECIO * 0.95
                WHERE TIPO_HABITACION = V_CATEGORIA;
            END IF;
            -- TERRAZA
            IF V_TERRAZA = 'SI' THEN
				UPDATE PRECIO_HABITACION
                SET PRECIO = PRECIO * 0.90
                WHERE TIPO_HABITACION = V_CATEGORIA;
            END IF;
        END IF;
    END WHILE;
    -- CIERRO CURSOR
    CLOSE C;
END; //
DELIMITER ;

CALL PROCEDIMIENTO2();

SELECT * FROM PRECIO_HABITACION;

-- 3. Crear un procedimiento para listar todas las habitaciones, mostrando su tipo, nº de camas, y precio para la temporada 3.
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO3;
//CREATE PROCEDURE PROCEDIMIENTO3()
BEGIN
	SELECT H.*,T.CAMAS,P.PRECIO,P.TEMPORADA FROM HABITACIONES AS H 
    INNER JOIN TIPO_HABITACION AS T ON H.TIPO_HABITACION = T.CATEGORIA
    INNER JOIN PRECIO_HABITACION AS P ON T.CATEGORIA = P.TIPO_HABITACION
    WHERE P.TEMPORADA = 3;
END;//
DELIMITER ;

CALL PROCEDIMIENTO3();
-- ***********************************************************************************
-- ************************************DISPARADORES***********************************
-- ***********************************************************************************
-- 1. Crear un trigger que sirva para controlar que cuando se inserta un gasto, la fecha del mismo está dentro de las fechas de la reserva al que se asigna. Ejemplo de ejecución del trigger:
-- El gasto que se quiere insertar corresponde a la reserva con IDRESERVA=1,
-- por lo tanto el trigger se dispara y evita anotar este gasto al estar fuera de fecha. Devolver el error asociado.
DELIMITER //
DROP TRIGGER IF EXISTS TIGRE1;
// CREATE TRIGGER TIGRE1
AFTER INSERT ON GASTOS
FOR EACH ROW
BEGIN
	-- DECLARO VARIABLES
    DECLARE V_FECHA_GASTO DATE;
    DECLARE V_FECHA_ENTRADA DATE;
    DECLARE V_FECHA_SALIDA DATE;
    SET V_FECHA_GASTO = NEW.FECHA;
    SELECT FECHA_ENTRADA INTO V_FECHA_ENTRADA FROM RESERVA_HABITAC
    WHERE ID_RESERVA = NEW.ID_RESERVA;
    SELECT FECHA_SALIDA INTO V_FECHA_SALIDA FROM RESERVA_HABITAC
    WHERE ID_RESERVA = NEW.ID_RESERVA;
    
    IF (V_FECHA_GASTO NOT BETWEEN V_FECHA_ENTRADA AND V_FECHA_SALIDA) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'EL GASTO NO SE PUEDE ANOTAR A ESTA RESERVA, FUERA DE FECHAS';
    END IF;
    
END; //

INSERT INTO GASTOS (ID_RESERVA,ID_SERVICIO,FECHA,CANTIDAD,PRECIO) VALUES
(1,1,'2009-03-26',1,10);

-- 2. Crear un trigger que controle los cambios de precio de las habitaciones. Se dispara cuando se hace una modificación en la tabla PRECIO_HABITACION, SÓLO EN EL CAMPO PRECIO. Cuando esto ocurre, automáticamente se guardan en una tabla auxiliar los siguientes campos: tipo_habitacion, temporada, precio anterior y precio actual.
-- CREO TABLA AUXILIAR
	DROP TABLE IF EXISTS TABLA_AUXILIAR;
    CREATE TABLE TABLA_AUXILIAR(TIPO_HABITACION INT,TEMPORADA INT,PRECIO_ANTERIOR DECIMAL(5,2),PRECIO_ACTUAL DECIMAL(5,2));
    
DELIMITER //
DROP TRIGGER IF EXISTS TIGRE2;
// CREATE TRIGGER TIGRE2
AFTER UPDATE ON PRECIO_HABITACION
FOR EACH ROW
BEGIN
	IF (OLD.PRECIO != NEW.PRECIO) THEN
    INSERT INTO TABLA_AUXILIAR VALUES (NEW.TIPO_HABITACION,NEW.TEMPORADA,OLD.PRECIO,NEW.PRECIO);
    END IF;
END; //

SELECT * FROM TABLA_AUXILIAR;
-- CAMBIO LOS PRECIOS
CALL PROCEDIMIENTO2();
SELECT * FROM TABLA_AUXILIAR;