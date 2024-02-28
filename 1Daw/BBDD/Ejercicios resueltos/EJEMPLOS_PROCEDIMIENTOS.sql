CREATE DATABASE EJEMPLOS_PROCEDIMIENTOS;
USE EJEMPLOS_PROCEDIMIENTOS;

-- EJEMPLO 1
DELIMITER //
DROP PROCEDURE IF EXISTS VAR_EJEMPLO1//
CREATE PROCEDURE VAR_EJEMPLO1()
BEGIN
DECLARE VARIABLE1 INT;
DECLARE VARIABLE2 VARCHAR(20);
SET VARIABLE1=25;
SET VARIABLE2 = "ESTO ES UNA PRUEBA";
SELECT CONCAT_WS('******',VARIABLE1,VARIABLE2) AS RESULTADO;
END //

DELIMITER ;

CALL VAR_EJEMPLO1();

-- EJEMPLO 2
CREATE TABLE ALUMNOS (
ID INT PRIMARY KEY,
ALUMNO VARCHAR(20));

INSERT INTO ALUMNOS VALUES
(1,'ALUMNO1'),
(2,'ALUMNO2'),
(3,'ALUMNO3'),
(4,'ALUMNO4'),
(5,'ALUMNO5');

DELIMITER //
DROP PROCEDURE IF EXISTS VAR_EJEMPLO2//
CREATE PROCEDURE VAR_EJEMPLO2()
BEGIN
	DECLARE V_1 INT;
    DECLARE V_2 VARCHAR(20);
    
    SELECT ID,ALUMNO INTO V_1,V_2 FROM ALUMNOS
    WHERE ID=4;
    SELECT V_1,V_2;
END; //
DELIMITER ;

CALL VAR_EJEMPLO2;
-- -----------------------------
set global log_bin_trust_function_creators=1;

delimiter //
drop function if exists function1//
create function function1()
returns varchar(100)
begin
	return 'esto es un ejemplo de uso de funciones';
end; //

delimiter ;

select function1() mensaje ;

delimiter //
drop function if exists function2//
create function function2(par1 int, par2 int) 
returns int
begin
return par1+par2;
end//
delimiter ;
select function2(10,20) resultado;

CREATE TABLE productos (
 id INT NOT NULL AUTO_INCREMENT,
 nombre VARCHAR(20) NOT NULL,
 coste FLOAT NOT NULL DEFAULT 0.0,
 precio FLOAT NOT NULL DEFAULT 0.0,
 PRIMARY KEY(id) );

INSERT INTO productos (nombre, coste, precio) 
VALUES ('Producto A', 4, 8), ('Producto B', 1, 1.5),('Producto C', 50, 80);

select * from productos;

delimiter //
drop function if exists function3//
create function function3(coste float,precio float) 
returns float
begin
	declare v_beneficio float;
    set v_beneficio=precio - coste;
	return v_beneficio;
end//

delimiter ;

select *,function3(coste,precio) beneficio from productos;

-- VIDEOTECA
USE VIDEOTECA;

DELIMITER //
DROP FUNCTION IF EXISTS FUNCION4//
CREATE FUNCTION FUNCION4()
RETURNS INT 
DETERMINISTIC
BEGIN
	DECLARE TOTAL INT;
    SELECT COUNT(*) FROM ACTOR INTO TOTAL;
    RETURN TOTAL;
END;//

SELECT FUNCION4() RESULTADO;