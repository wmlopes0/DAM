-- BASE DE DATOS SAKILA
-- 1ºDAW
-- 25-05-2023
-- WALTER MARTIN LOPES
-- --------------------------------
USE SAKILA;

-- ***********************************************************************************
-- ***********************************PROCEDIMIENTOS**********************************
-- ***********************************************************************************
-- 1. Crea un procedimiento que visualice todas las películas cuyo coste de reemplazo (replacement_cost en la tabla FILM) sea superior a un valor que se pasará como parámetro de entrada. ¿Cuántas películas tienen un costo de reemplazo superior a 20€? NOTA: puedes ver la estructura de la tabla con la orden DESC FILM
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO1;
// CREATE PROCEDURE PROCEDIMIENTO1(P_COSTE DECIMAL(5,2))
BEGIN 
	SELECT * FROM FILM 
    WHERE replacement_cost > P_COSTE;
END; //
DELIMITER ;

DESC FILM;

CALL PROCEDIMIENTO1(20);

-- 2. Crea un procedimiento que visualice todas las películas cuyo coste de reemplazo (replacement_cost en la tabla FILM) esté comprendido entre dos cantidades que se pasarán como parámetros de entrada. ¿Cuántas películas tienen un coste de reemplazo entre a 20€ y 21,99 €?
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO2;
// CREATE PROCEDURE PROCEDIMIENTO2(P1 DECIMAL(5,2),P2 DECIMAL(5,2))
BEGIN 
	SELECT * FROM FILM 
    WHERE replacement_cost BETWEEN P1 AND P2;
END; //
DELIMITER ;

CALL PROCEDIMIENTO2(20,21.99);

-- 3. Crear un PA que usando la tabla costumer, cambie el mail de un cliente por otro que se pasará como parámetro. El PA recibirá dos parámetros, el identificador del cliente y el nuevo mail (en la tabla son los campos customer_id, email). Ejecutar el PA para cambiar: 2,'patricia@gmail.com
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO3;
// CREATE PROCEDURE PROCEDIMIENTO3(P_ID SMALLINT UNSIGNED,P_EMAIL VARCHAR(50))
BEGIN
	UPDATE CUSTOMER
    SET EMAIL = P_EMAIL
    WHERE CUSTOMER_ID = P_ID;
END; //
DELIMITER ;

CALL PROCEDIMIENTO3(2,'patricia@gmail.com');
SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = 2;

-- 4. Crear un procedure que tenga como parámetros de entrada el nombre, apellidos y el nuevo mail de un cliente de la tabla COSTUMER, (NOTA: utiliza el procedure del ejercicio anterior para cambiar el mail.)
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO4;
// CREATE PROCEDURE PROCEDIMIENTO4(P_NOMBRE VARCHAR(45),P_APELLIDOS VARCHAR(45),P_EMAIL VARCHAR(50))
BEGIN
	DECLARE V_ID SMALLINT UNSIGNED;
    SELECT CUSTOMER_ID INTO V_ID FROM CUSTOMER
    WHERE FIRST_NAME = P_NOMBRE
    AND LAST_NAME = P_APELLIDOS;
    
    CALL PROCEDIMIENTO3(V_ID,P_EMAIL);
END; //
DELIMITER ;

CALL PROCEDIMIENTO4('MARY','SMITH','mary1@gmail.com');
SELECT * FROM CUSTOMER;

-- 5. Crea un procedimiento que muestre los campos TITLE, DESCRIPTION de las películas cuya categoría (comedia, drama,… se pasa como parámetro). Llama después a este procedimiento para obtener todas las películas de la categoría “Comedy”
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO5;
// CREATE PROCEDURE PROCEDIMIENTO5(P_CATEGORIA VARCHAR(25))
BEGIN
	SELECT F.TITLE,F.DESCRIPTION,C.NAME FROM FILM AS F
    INNER JOIN FILM_CATEGORY AS FC USING(FILM_ID)
    INNER JOIN CATEGORY AS C USING(CATEGORY_ID)
    WHERE C.NAME = P_CATEGORIA;
END; //
DELIMITER ;

CALL PROCEDIMIENTO5('COMEDY');

-- 6. Crea un procedimiento que pase dos parámetros de entrada, identificador de categoría e identificador de actor y obtenga los datos de las películas sobre esa categoría en las que ha trabajado ese actor. Utiliza las tablas FILM, FILM_ACTOR, FILM_CATEGORY (utiliza el comando DESC table para ver su estructura).
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO6;
// CREATE PROCEDURE PROCEDIMIENTO6(P_IDCATEGORIA TINYINT,P_IDACTOR SMALLINT)
BEGIN

    SELECT F.* FROM FILM AS F
    INNER JOIN FILM_CATEGORY AS FC USING(FILM_ID)
    WHERE FC.CATEGORY_ID = P_IDCATEGORIA
    AND F.FILM_ID IN (SELECT F.FILM_ID FROM FILM AS F
					  INNER JOIN FILM_ACTOR AS A USING(FILM_ID)
					  WHERE A.ACTOR_ID = P_IDACTOR);
END; //
DELIMITER ;

CALL PROCEDIMIENTO6(2,180);


-- 7. Escribe un procedimiento que permita borrar un actor cuyo identificador se pasará como parámetro. Si el actor cuyo número se ha pasado como parámetro no existe, aparecerá un mensaje diciendo “EL ACTOR: Nº DEL ACTOR NO EXISTE”.. Comprueba el funcionamiento del procedimiento intentado borrar un actor que no exista.
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO7;
// CREATE PROCEDURE PROCEDIMIENTO7(P_ACTORID SMALLINT)
BEGIN
	IF (P_ACTORID IN (SELECT ACTOR_ID FROM ACTOR)) THEN
		DELETE FROM ACTOR
		WHERE ACTOR_ID = P_ACTORID;
    ELSE 
		SELECT CONCAT('EL ACTOR: ',P_ACTORID,' NO EXISTE”.. Comprueba el funcionamiento del procedimiento intentado borrar un actor que no exista.') AS MENSAJE;
    END IF;
END; //
DELIMITER ;

CALL PROCEDIMIENTO7(320);

-- ***********************************************************************************
-- *************************************FUNCIONES*************************************
-- ***********************************************************************************
-- 1. Crear una función que retorne el número de actor de la tabla ACTOR, pasando como parámetros el apellido y nombre. Ejecuta la función para ('CHASE', 'ED').
DELIMITER //
DROP FUNCTION IF EXISTS FUNCION1;
// CREATE FUNCTION FUNCION1(P_LASTNAME VARCHAR(45),P_FIRSTNAME VARCHAR(45)) RETURNS SMALLINT
DETERMINISTIC
BEGIN
	DECLARE V_ACTORID SMALLINT;
    
    SELECT ACTOR_ID INTO V_ACTORID FROM ACTOR
    WHERE FIRST_NAME = P_FIRSTNAME
    AND LAST_NAME = P_LASTNAME;
    
    RETURN V_ACTORID;
END; //
DELIMITER ;

SELECT FUNCION1('CHASE','ED') AS NUMERO;

-- 2. Crea una función que devuelva el número de años completos que hay entre dos fechas que se pasan como parámetros. Utiliza la funcióN DATEDIFF para calcular los días transcurridos entre las dos fechas, divide los días obtenidos entre 365 y usa la función TRUNCATE para quedarte con el número de años completos.
DELIMITER //
DROP FUNCTION IF EXISTS FUNCION2;
// CREATE FUNCTION FUNCION2(P_FECHA1 DATE,P_FECHA2 DATE) RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE V_NUMERO INT;
	
    SELECT TRUNCATE((DATEDIFF(P_FECHA1,P_FECHA2)/365),0) INTO V_NUMERO;
    
    RETURN V_NUMERO;
END; //
DELIMITER ;

SELECT FUNCION2('2018-05-25','1971-02-18') AS AÑOS_COMPLETOS;

-- 3. Crear una función que, utilizando la función anterior, devuelve los trienios que hay entre dos fechas que le pasamos por parámetro.
DELIMITER //
DROP FUNCTION IF EXISTS FUNCION3;
// CREATE FUNCTION FUNCION3(P_FECHA1 DATE,P_FECHA2 DATE) RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE V_NUMEROAÑOS INT;
    DECLARE V_RESULTADO INT;
	
    SELECT FUNCION2(P_FECHA1,P_FECHA2) INTO V_NUMEROAÑOS;
    SELECT (V_NUMEROAÑOS/3) INTO V_RESULTADO;
    
    RETURN V_RESULTADO;
END; //
DELIMITER ;

SELECT FUNCION3('2018-05-25','1971-02-18') AS NUMERO_TRIENIOS;
-- ***********************************************************************************
-- ************************************DISPARADORES***********************************
-- ***********************************************************************************
-- 1. Visualiza los triggers de la BD sakila. Hay dos formas diferentes.
-- a. SHOW TRIGGERS
-- b. select * from information_schema.triggers where trigger_Schema='sakila'
SHOW TRIGGERS;
select * from information_schema.triggers where trigger_Schema='sakila';

-- 2. Construir un disparador que permita auditar las operaciones de borrado de datos que se realicen en la tabla actor según las siguientes especificaciones:
	-- a. Crear la tabla AUDITARACTOR, con una sola columna col1 VARCHAR2(200). Cuando se produzca 	 cualquier borrado de datos en esta tabla se insertará una fila que contendrá: Fecha y hora, Número de actor, Apellido de actor
CREATE TABLE AUDITARACTOR(COL1 VARCHAR(200));

DELIMITER //
DROP TRIGGER IF EXISTS TIGRE1;
// CREATE TRIGGER TIGRE1 
AFTER DELETE ON ACTOR
FOR EACH ROW
BEGIN
	INSERT INTO AUDITARACTOR VALUES(CONCAT_WS(' ','El actor',OLD.ACTOR_ID,'de apellido',OLD.LAST_NAME,'fue borrado el día',CURRENT_DATE()));
END; //
DELIMITER ;

-- PROBAMOS EL FUNCIONAMIENTO DEL TRIGGER INSERTANDO FILAS Y LUEGO BORRANDOLAS Y COMPROBANDO EL CONTENIDO DE LA TABLA AUDITARACTOR
INSERT INTO ACTOR (ACTOR_ID,FIRST_NAME,LAST_NAME) VALUES
(444,'Mar','Lopez'),
(445,'Mar','Lopez'),
(446,'Mar','Lopez');

SELECT * FROM ACTOR;
DELETE FROM ACTOR WHERE ACTOR_ID = 444;
SELECT * FROM AUDITARACTOR;

DELETE FROM ACTOR WHERE FIRST_NAME = 'Mar';

-- 3  Escribir un trigger para auditar las modificaciones de nombre y apellidos en la tabla actores insertado en la tabla auditar_actor los siguientes datos: - Fecha - Número de actor - La operación de actualización: MODIFICACIÓN. - El valor anterior y el valor nuevo del nombre y el apellido. Inserta de nuevo alguna de las filas del ejercicio 2 y luego realiza una operación de actualización sobre esa fila para comprobar los cambios en la tabla AUDITAR_ACTOR.
DELIMITER //
DROP TRIGGER IF EXISTS TIGRE2;
// CREATE TRIGGER TIGRE2 
AFTER UPDATE ON ACTOR
FOR EACH ROW
BEGIN
	IF (OLD.FIRST_NAME != NEW.FIRST_NAME) THEN
		INSERT INTO AUDITARACTOR VALUES(CONCAT_WS(' ',CURRENT_DATE(),'MODIFICACIÓN',OLD.FIRST_NAME,NEW.FIRST_NAME));
    END IF;
    IF (OLD.LAST_NAME != NEW.LAST_NAME) THEN
		INSERT INTO AUDITARACTOR VALUES(CONCAT_WS(' ',CURRENT_DATE(),'MODIFICACIÓN',OLD.LAST_NAME,NEW.LAST_NAME));
    END IF;
END; //
DELIMITER ;

-- COMPROBAMOS EL FUNCIONAMIENTO
INSERT INTO ACTOR (ACTOR_ID,FIRST_NAME,LAST_NAME) VALUES(444,'Mar','Lopez');
UPDATE ACTOR SET FIRST_NAME = 'MARINA' WHERE ACTOR_ID = 444;
SELECT * FROM AUDITARACTOR;

-- 4. Crea un trigger para la tabla film para que cuando se realice una actualización al campo replacement_cost no pueda sufrir un incremento mayor del 10% (es decir, si la operación de actualización va a suponer un aumento mayor del 10%, se pondrá por defecto una subida del 10%.) Comprueba el funcionamiento del trigger actualizando por ejemplo el replacement_cost de la película con identificador 1 a un valor de 60 ¿Qué coste de reemplazo tiene ahora el film ?
DELIMITER //
DROP TRIGGER IF EXISTS TIGRE3;
// CREATE TRIGGER TIGRE3 
BEFORE UPDATE ON FILM
FOR EACH ROW
BEGIN
	DECLARE MAXIMA_SUBIDA DECIMAL(5,2);
    DECLARE ACTUALIZACION DECIMAL(5,2);

	-- SI SE HA MODIFICADO EL CAMPO
	IF (OLD.replacement_cost != NEW.replacement_cost) THEN
		-- ACTUALIZAMOS VARIABLES
		SELECT (OLD.replacement_cost * 0.1) INTO MAXIMA_SUBIDA;
        SELECT (NEW.replacement_cost - OLD.replacement_cost) INTO ACTUALIZACION;
        -- CONTROLAMOS SI ES SUPERIOR A LA MAXIMA
        IF (ACTUALIZACION > MAXIMA_SUBIDA) THEN
			SET NEW.replacement_cost = (OLD.replacement_cost + MAXIMA_SUBIDA);
        END IF;
	END IF;
END; //
DELIMITER ;

-- COMPROBAMOS EL FUNCIONAMIENTO
SELECT * FROM FILM;
UPDATE FILM SET replacement_cost = 60 WHERE FILM_ID=1;

-- 5. Crear un trigger AFTER UPDATE que solo audite las modificaciones de título o descripción de la tabla film, de modo que si se ha producido una modificación de alguna de las dos columnas, se inserte en una nueva tabla el valor antiguo y el nuevo del título y/o la descripción. Realiza comprobaciones modificando alguno de los dos campos y también haciendo actualizaciones que no afecten a uno de esos campos (en este caso el trigger no debe dispararse)
CREATE TABLE AUDITAR_FILM(COL1 VARCHAR(200));
DELIMITER //
DROP TRIGGER IF EXISTS TIGRE4;
// CREATE TRIGGER TIGRE4 
AFTER UPDATE ON FILM
FOR EACH ROW
BEGIN
	IF (OLD.TITLE != NEW.TITLE) THEN
		INSERT INTO AUDITAR_FILM VALUES(CONCAT_WS(' ',CURRENT_DATE(),'MODIFICACIÓN',OLD.TITLE,NEW.TITLE));
    END IF;
    IF (OLD.DESCRIPTION != NEW.DESCRIPTION) THEN
		INSERT INTO AUDITAR_FILM VALUES(CONCAT_WS(' ',CURRENT_DATE(),'MODIFICACIÓN',OLD.DESCRIPTION,NEW.DESCRIPTION));
    END IF;
END; //
DELIMITER ;

UPDATE FILM SET TITLE='PROBANDO' WHERE FILM_ID=1;
SELECT * FROM AUDITAR_FILM;
