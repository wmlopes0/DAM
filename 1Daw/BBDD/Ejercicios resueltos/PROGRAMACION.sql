-- BASE DE DATOS PROGRAMACION
-- 1º DAW
-- 23-02-2023
-- WALTER MARTIN LOPES
-- -------------------------------------------------------
-- CREACION DE LA BBDD PROGRAMACION
CREATE DATABASE PROGRAMACION;
USE PROGRAMACION;

-- ==============CREACION DE LAS TABLAS==============
-- #CREATE TABLE
-- ----------linea1----------------
CREATE TABLE PROGRAMA(
CODIGO INT PRIMARY KEY,
NOMBRE VARCHAR(50),
VERSION VARCHAR(50));

CREATE TABLE DESARROLLA(
ID_FAB INT,
CODIGO INT);

CREATE TABLE FABRICANTE(
ID_FAB INT PRIMARY KEY,
NOMBRE VARCHAR(50),
PAIS VARCHAR(30));
-- ------------linea2--------------
CREATE TABLE DISTRIBUYE(
CIF INT,
CODIGO INT,
CANTIDAD INT);

CREATE TABLE COMERCIO(
CIF INT,
NOMBRE VARCHAR(50),
CIUDAD VARCHAR(50));
-- ------------linea3--------------
CREATE TABLE REGISTRA(
CIF INT,
DNI INT,
CODIGO INT,
MEDIO VARCHAR(20));

CREATE TABLE CLIENTE(
DNI INT,
NOMBRE VARCHAR(50),
EDAD INT);
-- ---------------------------------
-- #PRIMARY KEY
ALTER TABLE DESARROLLA
ADD CONSTRAINT IDC_PK PRIMARY KEY (ID_FAB,CODIGO);

DESC DESARROLLA;

ALTER TABLE DISTRIBUYE
ADD CONSTRAINT CIFCO_PK PRIMARY KEY (CIF,CODIGO);

DESC DISTRIBUYE;

ALTER TABLE REGISTRA
ADD CONSTRAINT CIFDNICO_PK PRIMARY KEY (CIF,DNI,CODIGO);

DESC REGISTRA;
-- -----------------------------------------------
-- #FOREIGN KEY
ALTER TABLE DESARROLLA
ADD CONSTRAINT COD_FK FOREIGN KEY (CODIGO) REFERENCES PROGRAMA(CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE DESARROLLA
ADD CONSTRAINT ID_FAB_FK FOREIGN KEY (ID_FAB) REFERENCES FABRICANTE(ID_FAB) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE DISTRIBUYE
ADD CONSTRAINT CODD_FK FOREIGN KEY (CODIGO) REFERENCES PROGRAMA(CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

-- -------------SOLUCIONANDO PROBLEMAS...
ALTER TABLE COMERCIO
MODIFY CIF INT PRIMARY KEY;
-- ------------------
ALTER TABLE DISTRIBUYE
ADD CONSTRAINT CIF_FK FOREIGN KEY (CIF) REFERENCES COMERCIO(CIF) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE REGISTRA
ADD CONSTRAINT CIFR_FK FOREIGN KEY (CIF) REFERENCES COMERCIO(CIF) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE REGISTRA
ADD CONSTRAINT CODR_FK FOREIGN KEY (CODIGO) REFERENCES PROGRAMA(CODIGO) ON UPDATE CASCADE ON DELETE CASCADE;

-- -------------SOLUCIONANDO PROBLEMAS...
ALTER TABLE CLIENTE
MODIFY DNI INT PRIMARY KEY;
-- ------------------

ALTER TABLE REGISTRA
ADD CONSTRAINT DNI_FK FOREIGN KEY (DNI) REFERENCES CLIENTE(DNI) ON UPDATE CASCADE ON DELETE CASCADE;

-- ==============INSERTAR DATOS==============
INSERT INTO fabricante VALUES (1,'Oracle','Estados Unidos');
INSERT INTO fabricante VALUES (2,'Microsoft','Estados Unidos');
INSERT INTO fabricante VALUES (3,'IBM','Estados Unidos');
INSERT INTO fabricante VALUES (4,'Dinamic','España');
INSERT INTO fabricante VALUES (5,'Borland','Estados Unidos');
INSERT INTO fabricante VALUES (6,'Symantec','Estados Unidos');

INSERT INTO programa VALUES (1,'Application Server','9i');
INSERT INTO programa VALUES (2,'Oracle Database','8i');
INSERT INTO programa VALUES (3,'Database','9i');
INSERT INTO programa VALUES (4,'Database','10g');
INSERT INTO programa VALUES (5,'Developer','6i');
INSERT INTO programa VALUES (6,'Access','97');
INSERT INTO programa VALUES (7,'Access','2000');
INSERT INTO programa VALUES (8,'Access','XP');
INSERT INTO programa VALUES (9,'Windows','98');
INSERT INTO programa VALUES (10,'Windows','XP Professional');
INSERT INTO programa VALUES (11,'Windows','XP Home Edition');
INSERT INTO programa VALUES (12,'Windows','2003 Server');
INSERT INTO programa VALUES (13,'Norton Internet Security','2004');
INSERT INTO programa VALUES (14,'Freddy Hardest',NULL);
INSERT INTO programa VALUES (15,'Paradox','2');
INSERT INTO programa VALUES (16,'C++ Builder','5.5');
INSERT INTO programa VALUES (17,'DB/2','2.0');
INSERT INTO programa VALUES (18,'OS/2','1.0');
INSERT INTO programa VALUES (19,'JBuilder','X');
INSERT INTO programa VALUES (20,'La prisión','1.0');

INSERT INTO comercio VALUES (1,'El Corte Inglés','Sevilla');
INSERT INTO comercio VALUES (2,'El Corte Inglés','Madrid');
INSERT INTO comercio VALUES (3,'Jump','Valencia');
INSERT INTO comercio VALUES (4,'Centro Mail','Sevilla');
INSERT INTO comercio VALUES (5,'FNAC','Barcelona');

INSERT INTO cliente VALUES (1,'Pepe Pérez',45);
INSERT INTO cliente VALUES (2,'Juan González',45);
INSERT INTO cliente VALUES (3,'María Gómez',33);
INSERT INTO cliente VALUES (4,'Javier Casado',18);
INSERT INTO cliente VALUES (5,'Nuria Sánchez',29);
INSERT INTO cliente VALUES (6,'Antonio Navarro',58);

INSERT INTO desarrolla VALUES (1,1);
INSERT INTO desarrolla VALUES (1,2);
INSERT INTO desarrolla VALUES (1,3);
INSERT INTO desarrolla VALUES (1,4);
INSERT INTO desarrolla VALUES (1,5);
INSERT INTO desarrolla VALUES (2,6);
INSERT INTO desarrolla VALUES (2,7);
INSERT INTO desarrolla VALUES (2,8);
INSERT INTO desarrolla VALUES (2,9);
INSERT INTO desarrolla VALUES (2,10);
INSERT INTO desarrolla VALUES (2,11);
INSERT INTO desarrolla VALUES (2,12);
INSERT INTO desarrolla VALUES (6,13);
INSERT INTO desarrolla VALUES (4,14);
INSERT INTO desarrolla VALUES (5,15);
INSERT INTO desarrolla VALUES (5,16);
INSERT INTO desarrolla VALUES (3,17);
INSERT INTO desarrolla VALUES (3,18);
INSERT INTO desarrolla VALUES (5,19);
INSERT INTO desarrolla VALUES (4,20);

INSERT INTO distribuye VALUES (1,1,10);
INSERT INTO distribuye VALUES (1,2,11);
INSERT INTO distribuye VALUES (1,6,5);
INSERT INTO distribuye VALUES (1,7,3);
INSERT INTO distribuye VALUES (1,10,5);
INSERT INTO distribuye VALUES (1,13,7);
INSERT INTO distribuye VALUES (2,1,6);
INSERT INTO distribuye VALUES (2,2,6);
INSERT INTO distribuye VALUES (2,6,4);
INSERT INTO distribuye VALUES (2,7,7);
INSERT INTO distribuye VALUES (3,10,8);
INSERT INTO distribuye VALUES (3,13,5);
INSERT INTO distribuye VALUES (4,14,3);
INSERT INTO distribuye VALUES (4,20,6);
INSERT INTO distribuye VALUES (5,15,8);
INSERT INTO distribuye VALUES (5,16,2);
INSERT INTO distribuye VALUES (5,17,3);
INSERT INTO distribuye VALUES (5,19,6);

INSERT INTO distribuye VALUES (5,8,8);

-- ==============CONSULTAS==============
-- 1. Averigua el DNI de todos los clientes.
SELECT DNI FROM CLIENTE;
-- 2. Consulta todos los datos de todos los programas.
SELECT * FROM PROGRAMA;
-- 3. Obtén un listado con los nombres de todos los programas.
SELECT NOMBRE FROM PROGRAMA;
-- 4. Genera una lista con todos los comercios.
SELECT NOMBRE FROM COMERCIO;
-- 5. Genera una lista de las ciudades con establecimientos donde se venden programas, sin que aparezcan valores duplicados (utiliza DISTINCT).
SELECT DISTINCT CIUDAD FROM COMERCIO;
-- 6. Obtén una lista con los nombres de programas, sin que aparezcan valores duplicados (utiliza DISTINCT).
SELECT DISTINCT NOMBRE FROM PROGRAMA;
-- 7. ¿Cuáles son los programas cuyo código es inferior o igual a 10?
SELECT * FROM PROGRAMA 
WHERE CODIGO<=10;
-- 8. ¿Cuál es el programa cuyo código es 11?
SELECT * FROM PROGRAMA
WHERE CODIGO = 11;
-- 9. ¿Qué fabricantes son de Estados Unidos?
SELECT * FROM FABRICANTE
WHERE PAIS = "Estados Unidos";
-- 10. ¿Cuáles son los fabricantes no españoles? Utilizar el operador IN.
SELECT * FROM FABRICANTE
WHERE PAIS NOT IN("España");
-- 11. Obtén un listado con los códigos de las distintas versiones de Windows.
SELECT CODIGO,NOMBRE FROM PROGRAMA 
WHERE NOMBRE = "Windows";
-- 12. ¿Qué otros comercios hay, además de El Corte Inglés? (NO DEBE SALIR EL Corte Inglés).
SELECT NOMBRE FROM COMERCIO
WHERE NOMBRE !="El Corte Inglés";
-- 13. Genera una lista con los códigos de las distintas versiones de Windows y Access.
SELECT CODIGO,NOMBRE FROM PROGRAMA 
WHERE NOMBRE IN("Windows","Access");
-- 14. Obtén un listado que incluya los nombres de los clientes de edades comprendidas entre 10 y 25 y de los mayores de 50 años. Da una solución con BETWEEN y otra sin BETWEEN.
SELECT NOMBRE,EDAD FROM CLIENTE
WHERE EDAD>10
AND EDAD<25
OR EDAD>50;

SELECT NOMBRE,EDAD FROM CLIENTE
WHERE EDAD BETWEEN 10 AND 25
OR EDAD>50;
-- 15. Saca un listado con los comercios de Sevilla y Madrid. No se admiten valores duplicados.
SELECT DISTINCT * FROM COMERCIO
WHERE CIUDAD IN("Sevilla","Madrid");
-- 16. ¿Qué clientes terminan su nombre en la letra “o”?
SELECT * FROM CLIENTE
WHERE NOMBRE LIKE '%o';
-- 17. ¿Qué clientes terminan su nombre en la letra “o” y, además, son mayores de 30 años?
SELECT * FROM CLIENTE
WHERE NOMBRE LIKE '%o'
AND EDAD>30;
-- 18. Obtén un listado en el que aparezcan los programas cuya versión finalice por una letra i, o cuyo nombre comience por una A o por una W.
SELECT * FROM PROGRAMA 
WHERE VERSION LIKE '%i'
OR NOMBRE LIKE 'A%'
OR NOMBRE LIKE 'W%';
-- 19. Obtén un listado en el que aparezcan los programas cuya versión finalice por una letra i, o cuyo nombre comience por una A y termine por una S.
SELECT * FROM PROGRAMA 
WHERE VERSION LIKE '%i'
OR NOMBRE LIKE 'A%'
AND NOMBRE LIKE '%s';
-- 20. Obtén un listado en el que aparezcan los programas cuya versión finalice por una letra i, y cuyo nombre no comience por una A.
SELECT * FROM PROGRAMA 
WHERE VERSION LIKE '%i'
AND NOMBRE NOT LIKE 'A%';
-- 21. Genera un listado de empresas por orden alfabético descendente.
SELECT * FROM FABRICANTE
ORDER BY NOMBRE DESC;
-- 22. Obtén un listado de programas por orden de versión
SELECT * FROM PROGRAMA 
ORDER BY VERSION;
-- 23. Genera un listado de los programas que desarrolla Oracle.
SELECT P.NOMBRE,P.VERSION,F.NOMBRE AS FABRICANTE 
FROM PROGRAMA AS P INNER JOIN DESARROLLA AS D
ON P.CODIGO = D.CODIGO
INNER JOIN FABRICANTE AS F
ON D.ID_FAB = F.ID_FAB
WHERE F.NOMBRE = "Oracle";
-- 24. ¿Qué comercios distribuyen Windows?
SELECT C.NOMBRE,P.NOMBRE
FROM COMERCIO AS C INNER JOIN DISTRIBUYE AS D
ON C.CIF = D.CIF
INNER JOIN PROGRAMA AS P
ON D.CODIGO=P.CODIGO
WHERE P.NOMBRE="Windows";
-- 25. Genera un listado de los programas y cantidades que se han distribuido a El Corte Inglés de Madrid.
SELECT P.NOMBRE AS PROGRAMA, COUNT(P.NOMBRE) AS CANTIDAD,C.NOMBRE AS COMERCIO, C.CIUDAD AS CIUDAD
FROM PROGRAMA AS P INNER JOIN DISTRIBUYE AS D
ON P.CODIGO = D.CODIGO
INNER JOIN COMERCIO AS C
ON D.CIF = C.CIF
WHERE C.NOMBRE ="El Corte Inglés"
AND C.CIUDAD = "Madrid"
GROUP BY PROGRAMA;
-- 26. ¿Qué fabricante ha desarrollado Freddy Hardest?
SELECT P.NOMBRE AS PROGRAMA,F.NOMBRE AS FABRICANTE
FROM PROGRAMA AS P INNER JOIN DESARROLLA AS D
ON P.CODIGO = D.CODIGO
INNER JOIN FABRICANTE AS F
ON D.ID_FAB=F.ID_FAB
WHERE P.NOMBRE="Freddy Hardest";
-- 27. Selecciona el nombre de los programas que se registran por Internet.
SELECT P.NOMBRE AS PROGRAMA, R.MEDIO AS MEDIO
FROM PROGRAMA AS P INNER JOIN REGISTRA AS R
ON P.CODIGO = R.CODIGO
WHERE MEDIO="Internet";
-- 28. Selecciona el nombre de las personas que se registran por Internet.
SELECT C.NOMBRE AS CLIENTE,R.MEDIO AS MEDIO
FROM CLIENTE AS C INNER JOIN REGISTRA AS R
ON C.DNI=R.DNI
WHERE MEDIO="Internet";
-- 29. ¿Qué medios ha utilizado para registrarse Pepe Pérez?
SELECT C.NOMBRE AS CLIENTE,R.MEDIO AS MEDIO
FROM CLIENTE AS C INNER JOIN REGISTRA AS R
ON C.DNI=R.DNI
WHERE C.NOMBRE="Pepe Pérez";
-- 30. ¿Qué usuarios han optado por Internet como medio de registro?
SELECT C.NOMBRE AS CLIENTE,R.MEDIO AS MEDIO
FROM CLIENTE AS C INNER JOIN REGISTRA AS R
ON C.DNI=R.DNI
WHERE MEDIO="Internet";
-- 31. ¿Qué programas han recibido registros por tarjeta postal?
SELECT P.NOMBRE AS PROGRAMA,R.MEDIO AS MEDIO
FROM PROGRAMA AS P INNER JOIN REGISTRA AS R
ON P.CODIGO = R.CODIGO
WHERE R.MEDIO = "tarjeta postal";
-- 32. ¿En qué localidades se han vendido productos que se han registrado por Internet?
SELECT C.CIUDAD, R.MEDIO
FROM COMERCIO AS C INNER JOIN REGISTRA AS R
ON C.CIF = R.CIF
WHERE R.MEDIO = "Internet";
-- 33. Obtén un listado de los nombres de las personas que se han registrado por Internet, junto al nombre de los programas para los que ha efectuado el registro.
SELECT C.NOMBRE AS CLIENTE,R.MEDIO,P.NOMBRE AS PROGRAMA
FROM CLIENTE AS C INNER JOIN REGISTRA AS R
ON C.DNI=R.DNI
INNER JOIN PROGRAMA AS P
ON R.CODIGO = P.CODIGO
WHERE R.MEDIO = "Internet";
-- 34. Genera un listado en el que aparezca cada cliente junto al programa que ha registrado, el medio con el que lo ha hecho y el comercio en el que lo ha adquirido.
SELECT C.NOMBRE AS CLIENTE,P.NOMBRE AS PROGRAMA, R.MEDIO AS MEDIO, CO.NOMBRE AS COMERCIO
FROM CLIENTE AS C INNER JOIN REGISTRA AS R
ON C.DNI = R.DNI
INNER JOIN PROGRAMA AS P
ON R.CODIGO = P.CODIGO
INNER JOIN COMERCIO AS CO
ON R.CIF = CO.CIF;
-- 35. Genera un listado con las ciudades en las que se pueden obtener los productos de Oracle.
SELECT C.CIUDAD,P.NOMBRE AS PROGRAMA, F.NOMBRE AS FABRICANTE
FROM COMERCIO AS C INNER JOIN DISTRIBUYE AS D
ON C.CIF = D.CIF
INNER JOIN PROGRAMA AS P
ON D.CODIGO = P.CODIGO
INNER JOIN DESARROLLA AS DE
ON P.CODIGO = DE.CODIGO
INNER JOIN FABRICANTE AS F
ON DE.ID_FAB=F.ID_FAB
WHERE F.NOMBRE = "Oracle";
-- 36. Obtén el nombre de los usuarios que han registrado el programa "Paradox" en su versión "2".
SELECT C.NOMBRE AS USUARIO,P.NOMBRE AS PROGRAMA,P.VERSION
FROM CLIENTE AS C INNER JOIN REGISTRA AS R
ON C.DNI = R.DNI
INNER JOIN PROGRAMA AS P
ON R.CODIGO=P.CODIGO
WHERE P.NOMBRE ="Paradox"
AND P.VERSION = "2";
-- 37. Nombre de aquellos fabricantes cuyo país es el mismo que 'Oracle'. (Subconsulta).
-- 38. Nombre de aquellos clientes que tienen la misma edad que Pepe Pérez. (Subconsulta).
-- 39. Genera un listado con los comercios que tienen su sede en la misma ciudad que tiene el comercio 'Centro Mail'. (Subconsulta).
-- 40. Nombre de aquellos clientes que han registrado un producto de la misma forma que el cliente 'Pepe Pérez'. (Subconsulta).

-- 41. Obtener el número de programas que hay en la tabla programas.
SELECT COUNT(CODIGO) AS NUMERO_PROGRAMAS FROM PROGRAMA;
-- 42. Calcula el número de clientes cuya edad es mayor de 40 años.
SELECT COUNT(DNI) AS NUMERO_CLIENTES FROM CLIENTE
WHERE EDAD>40;
-- 43. Calcula el número de productos que ha vendido el establecimiento cuyo CIF es 1.
SELECT COUNT(P.CODIGO) AS NUM_PRODUCTOS_VENDIDOS,C.CIF
FROM PROGRAMA AS P INNER JOIN REGISTRA AS R
ON P.CODIGO=R.CODIGO
INNER JOIN COMERCIO AS C
ON R.CIF=C.CIF
WHERE C.CIF=1;
-- 44. Calcula la media de programas que se venden cuyo código es 7.
SELECT AVG(CANTIDAD) AS MEDIA FROM DISTRIBUYE
WHERE CODIGO=7;
-- 45. Calcula la máxima cantidad de programas de código 7 que se han vendido.
SELECT MAX(CANTIDAD) AS MAXIMA_CANTIDAD FROM DISTRIBUYE 
WHERE CODIGO = 7;
-- 46. ¿En cuántos establecimientos se vende el programa cuyo código es 7?
SELECT COUNT(CIF) AS NUMERO_ESTABLECIMIENTOS FROM DISTRIBUYE 
WHERE CODIGO=7;
-- 47. Calcular el número de registros que se han realizado por Internet.
SELECT COUNT(CODIGO) AS NUMERO_REGISTROS FROM REGISTRA
WHERE MEDIO = "Internet";
-- 48. Obtener el número total de programas que se han vendido en 'Sevilla'.
SELECT C.CIUDAD, COUNT(D.CODIGO) AS PROGRAMAS_VENDIDOS
FROM COMERCIO AS C INNER JOIN DISTRIBUYE AS D
ON C.CIF = D.CIF
WHERE C.CIUDAD = "Sevilla";
-- 49. Calcular el número total de programas que han desarrollado los fabricantes cuyo país es 'Estados Unidos'.
SELECT F.PAIS,COUNT(D.CODIGO) AS NUM_PROGRAMAS
FROM FABRICANTE AS F INNER JOIN DESARROLLA AS D
ON F.ID_FAB=D.ID_FAB
WHERE F.PAIS='Estados Unidos';
-- 50. Visualiza el nombre de todos los clientes en mayúscula. En el resultado de la consulta debe aparecer también la longitud de la cadena nombre. Utilizar las funciones upper y length.
SELECT UPPER(NOMBRE) AS CLIENTE, LENGTH(NOMBRE) AS LONGITUD FROM CLIENTE;
-- 51. Con una consulta concatena los campos nombre y versión de la tabla programa. Utilizar la función concat
SELECT CONCAT(NOMBRE," ",VERSION) AS PROGRAMA FROM PROGRAMA;