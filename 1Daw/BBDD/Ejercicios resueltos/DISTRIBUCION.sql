-- BASE DE DATOS DISTRIBUCION
-- 1º DAW
-- 14-03-2023
-- WALTER MARTIN LOPES
-- -------------------------------------------------------
CREATE DATABASE DISTRIBUCION;
USE DISTRIBUCION;
-- ===========================CREACION DE TABLAS=======================================
CREATE TABLE FABRICANTES(
COD_FAB INT PRIMARY KEY,
NOMBRE VARCHAR(30));

CREATE TABLE ARTICULOS(
COD_ART INT PRIMARY KEY,
NOMBRE VARCHAR(30),
PRECIO DECIMAL(10,2),
FAB INT);

ALTER TABLE ARTICULOS
ADD CONSTRAINT FAB_FK FOREIGN KEY (FAB) REFERENCES FABRICANTES(COD_FAB) ON UPDATE CASCADE ON DELETE CASCADE;

-- ============================INSERT============================
INSERT INTO FABRICANTES
VALUES
(1,'FABRICANTE1'),
(2,'FABRICANTE2'),
(3,'FABRICANTE3');


INSERT INTO ARTICULOS
VALUES
(1,'ORDENADOR','520.50',1),
(2,'RATON','5.20',1),
(3,'PORTATIL','520.50',1),
(4,'TABLET','320.50',2),
(5,'IMPRESORA LASER','250.50',2),
(6,'ALTAVOCES','12.50',3),
(7,'DISCO DURO','52.50',3);

-- ============================CONSULTAS============================
-- 1. Obtener el precio medio de todos los artículos.
SELECT AVG(PRECIO) AS MEDIA FROM ARTICULOS;
-- 2. Obtener el precio medio de los artículos cuyo código de fabricante sea 2.
SELECT FAB AS CODIGO_FAB, AVG(PRECIO) AS MEDIA FROM ARTICULOS
WHERE FAB = 2;
-- 3. Obtener el número de artículos cuyo precio sea mayor o igual a 100.
SELECT COUNT(*) AS NUMERO_ARTICULOS FROM ARTICULOS
WHERE PRECIO >=100;
-- 4. Obtener el nombre y el precio de los artículos con precio mayor o igual a 100 y ordenarlos por precio descendente y por nombre ascendente.
SELECT NOMBRE, PRECIO FROM ARTICULOS
WHERE PRECIO >= 100
ORDER BY PRECIO DESC, NOMBRE;
-- 5. Obtener un listado de todos los artículos incluyendo por cada uno los datos completos de su fabricante. (Hacer primero con INNER JOIN y después con subconsulta EXISTS).
SELECT A.*,F.* 
FROM ARTICULOS AS A INNER JOIN FABRICANTES AS F
ON A.FAB=F.COD_FAB;

SELECT A.*,F.*
FROM ARTICULOS AS A,FABRICANTES AS F 
WHERE EXISTS(SELECT * FROM FABRICANTES);
-- 6. Obtener el precio medio de los artículos de cada fabricante, mostrando además el código de fabricante.
SELECT AVG(PRECIO), FAB FROM ARTICULOS
GROUP BY FAB;
-- 7. Obtener el precio medio de los artículos de cada fabricante, mostrando además el nombre del fabricante. (INNER JOIN).
SELECT AVG(PRECIO), FABRICANTES.NOMBRE
FROM ARTICULOS INNER JOIN FABRICANTES
ON ARTICULOS.FAB=FABRICANTES.COD_FAB
GROUP BY FAB;
-- 8. Obtener los nombres de los fabricantes que ofrezcan artículos cuyo precio medio sea mayor o igual a 100. (PRIMERO con INNER JOIN y después con subconsulta).
select f.nombre,avg(precio)
from fabricantes as f inner join articulos as a
on f.cod_fab=a.fab
group by f.nombre
having avg(precio)>=100;

-- SUBCONSULTAS
select fabricantes.nombre,avg(precio)
from articulos,fabricantes
WHERE cod_fab in (SELECT fab FROM ARTICULOS
group by fab
having avg(precio)>100)
AND ARTICULOS.FAB=FABRICANTES.COD_FAB
group by fabricantes.nombre;
-- 9. Obtener el nombre y el precio del artículo más barato ( (PRIMERO con subconsulta y después busca una solución sin utilizar subconsulta).
SELECT MIN(PRECIO), NOMBRE
FROM ARTICULOS
GROUP BY NOMBRE
ORDER BY 1
LIMIT 1;

SELECT PRECIO, NOMBRE
FROM ARTICULOS
WHERE PRECIO=(SELECT MIN(PRECIO) FROM ARTICULOS);

SELECT PRECIO,NOMBRE
FROM ARTICULOS
WHERE PRECIO='5.20';

-- 10. Añadir a la tabla un nuevo producto: ORDENADOR 500€, FABRICANTE 2.
INSERT INTO ARTICULOS VALUES (8,'ORDENADOR','500',2);
-- 11. Cambiar el nombre al producto nº 8. Ahora se llama IMPRESORA LÁSER.
UPDATE ARTICULOS
SET NOMBRE = 'IMPRESORA LÁSER'
WHERE COD_ART=8;
-- 12. Aplicar un descuento del 10% a todos los artículos del fabricante 3.
UPDATE ARTICULOS
SET PRECIO = PRECIO * 0.9
WHERE FAB=3;
-- 13. Obtener una lista con el nombre y el precio de los artículos más caros de cada fabricante, incluyendo el nombre del fabricante. Utiliza subconsulta.
SELECT A.NOMBRE,A.PRECIO,F.NOMBRE
FROM ARTICULOS A INNER JOIN FABRICANTES F
ON A.FAB=F.COD_fAB
WHERE A.PRECIO = (SELECT MAX(A.PRECIO)
				  FROM ARTICULOS A 
                  WHERE A.FAB=F.COD_FAB);