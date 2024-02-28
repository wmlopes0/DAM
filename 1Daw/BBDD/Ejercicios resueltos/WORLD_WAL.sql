-- BASE DE DATOS WORLD
-- 27/05/2023
-- WALTER MARTÍN LOPES
-- ------------------------------------------------
USE WORLD;

-- ****************************************************************************************
-- ****************************** P R O C E D I M I E N T O S *****************************
-- ******************************************* Y ******************************************
-- *********************************** F U N C I O N E S **********************************
-- ****************************************************************************************

-- 1. Crea un procedimiento que reciba como parámetros de entrada el continente y el idioma y
-- que obtenga todos los países de ese continente que hablan este idioma. Una vez realizado
-- el procedimiento, utilízalo para obtener los países de Europa que tienen como idioma entre
-- otros el español.- Consultar las tablas para ver qué datos introducir como parámetros.
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO1;
//CREATE PROCEDURE PROCEDIMIENTO1(P_CONTINENTE CHAR(40),P_LENGUA CHAR(30))
BEGIN
	SELECT C.NAME,C.REGION,C.POPULATION FROM COUNTRY AS C
    INNER JOIN COUNTRYLANGUAGE AS CO 
    ON C.CODE = CO.COUNTRYCODE
    WHERE C.CONTINENT = P_CONTINENTE
    AND CO.LANGUAGE = P_LENGUA;
END;//
DELIMITER ;

CALL PROCEDIMIENTO1('EUROPE','SPANISH');

-- 2. Crear un procedimiento almacenado que CUENTE los países que empiezan por una determinada
-- letra que le pasaremos por parámetro.Utilizar la tabla COUNTRY y la función CONCAT en la cláusula
-- where del procedure (CONCAT (LETRA,’%’)). Obtén el número de países que empiezan por U.
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO2;
//CREATE PROCEDURE PROCEDIMIENTO2(P_LETRA CHAR)
BEGIN
	SELECT COUNT(*) AS 'NUMERO DE PAISES' FROM COUNTRY 
    WHERE NAME LIKE CONCAT(P_LETRA,'%');
END;//
DELIMITER ;

CALL PROCEDIMIENTO2('U');

-- 3. Crea un procedimiento para añadir un nuevo registro a la tabla city haciendo el tratamiento de
-- errores utilizando handler y mostrando los mensajes pertinentes. Ejecuta el procedimiento con los
-- siguientes datos para comprobar el funcionamiento del procedimiento:
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO3;
//CREATE PROCEDURE PROCEDIMIENTO3(P_ID int,P_Name char(35),P_CountryCode char(3),P_District char(20),P_Population int)
BEGIN
	-- 1062
    DECLARE EXIT HANDLER FOR 1062 
    BEGIN
		SELECT 'CLAVE DE CIUDAD DUPLICADA' AS MENSAJE;
    END;
    
    INSERT INTO CITY VALUES(P_ID,P_Name,P_CountryCode,P_District,P_Population);
    
    SELECT 'REGISTRO INSERTADO CORRECTAMENTE' AS MENSAJE;
END;//
DELIMITER ;

CALL PROCEDIMIENTO3(1,'PLASENCIA','ESP','CACERES',40360);

CALL PROCEDIMIENTO3(5000,'PLASENCIA','ESP','CACERES',40360);

SELECT * FROM CITY
WHERE ID = 5000;

-- 4. Crear una función que calcule el volumen de una esfera cuyo radio de tipo FLOAT se pasará como
-- parámetro. Utiliza la función para calcular el volumen de una esfera de radio 5.
DELIMITER //
DROP FUNCTION IF EXISTS FUNCION1;
// CREATE FUNCTION FUNCION1(P_RADIO FLOAT) RETURNS DECIMAL(5,2)
DETERMINISTIC
BEGIN
	DECLARE RESULTADO DECIMAL(5,2);
    
    SELECT ((4/3) * PI() * POW(P_RADIO,3)) INTO RESULTADO;
    
    RETURN RESULTADO;
END; //
DELIMITER ;

SELECT FUNCION1(5) AS VOLUMEN;

-- 5. Crear un procedimiento para actualizar la población de un determinado país. Se pasarán dos
-- parámetros, la nueva población de tipo float y el nombre del país. Realiza el procedimiento primero
-- sin hacer el tratamiento de errores y pruébalo con los siguientes valores (Angola, 1234567891234)
-- ¿Qué ocurre y porqué? No permite porque la población introducida supera el valor permitido que es int(11)
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO4;
//CREATE PROCEDURE PROCEDIMIENTO4(P_POBLACION FLOAT,P_NOMBRE CHAR(52))
BEGIN
	-- DECLARO VARIABLES
	DECLARE FUERA_RANGO BOOLEAN DEFAULT FALSE;
    DECLARE RESULTADOS INT DEFAULT 0;
    -- DECLARO HANDLERS
	DECLARE CONTINUE HANDLER FOR 1264 SET FUERA_RANGO = TRUE;
    -- COMPRUEBO SI EL PAIS EXISTE ALMACENANDO EN LA VARIABLE RESULTADO EL NUMERO DE REGISTROS QUE ME RETORNA, SI ES MAYOR A 0 EXISTE
	SELECT COUNT(*) INTO RESULTADOS FROM COUNTRY 
    WHERE NAME = P_NOMBRE;
    
    IF (RESULTADOS > 0) THEN
		IF (FUERA_RANGO) THEN
			UPDATE COUNTRY 
            SET POPULATION = POPULATION + (POPULATION * 0.1)
            WHERE NAME = P_NOMBRE;
		ELSE
			UPDATE COUNTRY 
            SET POPULATION = P_POBLACION
            WHERE NAME = P_NOMBRE;
        END IF;
	ELSE 
		SELECT 'EL PAÍS NO EXISTE' AS 'ERROR';
    END IF;
END; //
DELIMITER ;

CALL PROCEDIMIENTO4(123434536456435234234,'Angola');

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

-- Crear un trigger de modo que cada vez que se haga una operación de inserción sobre la tabla
-- country de la BD world, automáticamente se calcule por cada continente correspondiente a ese país,
-- el número de países y la media de la población de esos países. Estos datos se introducirán en una
-- tabla llamada ESTADÍSTICAS que tendrá los siguientes campos:
CREATE TABLE ESTADISTICAS(
id smallint(5) primary key auto_increment ,
num_paises int,
media_poblacion int,
continente varchar(50),
fecha date);

DELIMITER //
DROP TRIGGER IF EXISTS TIGRE1;
// CREATE TRIGGER TIGRE1
AFTER INSERT ON COUNTRY
FOR EACH ROW
BEGIN
	-- DECLARO VARIABLES
	DECLARE V_NUM_PAISES INT;
    DECLARE V_MEDIA_POBLACION DECIMAL(50,5);
    DECLARE V_CONTINENTE VARCHAR(40);
    -- ASIGNO VALOR CORRESPONDIENTE A LAS VARIABLES
    -- V_CONTINENTE
    SELECT NEW.Continent INTO V_CONTINENTE;
    -- NUM_PAISES
    SELECT COUNT(C.ID) INTO V_NUM_PAISES FROM CITY AS C
    INNER JOIN COUNTRY AS CO ON C.COUNTRYCODE = CO.CODE
    WHERE CO.CONTINENT = V_CONTINENTE;
    -- MEDIA_POBLACION
    SELECT AVG(C.POPULATION) INTO V_MEDIA_POBLACION FROM CITY AS C
    INNER JOIN COUNTRY AS CO ON C.COUNTRYCODE = CO.CODE
    WHERE CO.CONTINENT = V_CONTINENTE;
    
    -- INSERTO DATOS EN LA TABLA ESTADISTICA
    INSERT INTO ESTADISTICAS (NUM_PAISES,media_poblacion,continente,fecha)VALUES(V_NUM_PAISES,V_MEDIA_POBLACION,V_CONTINENTE,CURRENT_DATE());
END; //
DELIMITER ;

SELECT * FROM ESTADISTICAS;

insert into country values ('XXY','contientent1','Asia','dddd',18,1901,11111,99,100,1000,
'hola2','dddd','ddd',1111,'A3');


-- *********************************************************************************
-- ******************************** C U R S O R E S ********************************
-- *********************************************************************************
-- Crea un procedimiento que muestre para cada continente, los nombres y la población de los 5 países
-- más poblados. Utiliza un cursor que recorra los distintos continentes que aparecen en la tabla
-- country.
DELIMITER //
DROP PROCEDURE IF EXISTS CURSOR1_SP
// CREATE PROCEDURE CURSOR1_SP()
BEGIN
	DECLARE FINTABLA INT DEFAULT 0;
    DECLARE NOMBRE VARCHAR(50);
    DECLARE C CURSOR FOR SELECT DISTINCT CONTINENT FROM COUNTRY;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET FINTABLA=1;
    OPEN C;
    REPEAT
		FETCH C INTO NOMBRE;
        IF NOT FINTABLA THEN
			SELECT NAME, POPULATION FROM COUNTRY WHERE CONTINENT=NOMBRE ORDER BY POPULATION DESC LIMIT 5;
		END IF;
	UNTIL FINTABLA
    END REPEAT;
    CLOSE C;
END; //

CALL CURSOR1_SP;

DELIMITER //
DROP PROCEDURE IF EXISTS CURSOR1_SP_MODIFICADO
// CREATE PROCEDURE CURSOR1_SP_MODIFICADO()
BEGIN
	-- DECLARO VARIABLES
	DECLARE FINTABLA INT DEFAULT 0;
    DECLARE NOMBRE VARCHAR(50);
    DECLARE CONTADOR INT DEFAULT 0;
    DECLARE LINEA VARCHAR(200);
    -- DECLARO CURSOR
    DECLARE C CURSOR FOR SELECT DISTINCT CONTINENT FROM COUNTRY;
    -- DECLARO HANDLER
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET FINTABLA=1;
    -- CREO TABLA RESULTADO
    DROP TABLE IF EXISTS RESULTADO;
    CREATE TABLE RESULTADO(mensaje VARCHAR(200));
    -- ABRO CURSOR
    OPEN C;
    -- RECORRO CON CURSOR
    REPEAT
		FETCH C INTO NOMBRE;
        IF NOT FINTABLA THEN
			-- ------------------------------------------------------------------
			INSERT INTO RESULTADO VALUES(CONCAT('Paises más poblados de ',NOMBRE));
			INSERT INTO RESULTADO VALUES('______________________________________');
			WHILE(CONTADOR<=5) DO
				SELECT CONCAT_WS(' ',NAME,'con una poblacion de',POPULATION) INTO LINEA FROM COUNTRY WHERE CONTINENT=NOMBRE ORDER BY POPULATION DESC LIMIT 1 OFFSET CONTADOR;
				INSERT INTO RESULTADO VALUES(LINEA);
				SET CONTADOR = CONTADOR + 1;
			END WHILE;
			-- RESETEO CONTADOR
			SET CONTADOR = 0;
			INSERT INTO RESULTADO VALUES('______________________________________');
			-- ------------------------------------------------------------------
		END IF;
	UNTIL FINTABLA
    END REPEAT;
    -- CIERRO CURSOR
    CLOSE C;
END; //

CALL CURSOR1_SP_MODIFICADO();

SELECT * FROM RESULTADO;