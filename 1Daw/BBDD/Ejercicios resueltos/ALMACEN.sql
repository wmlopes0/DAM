-- BASE DE DATOS DATOSALUMNOS
-- 1º DAW
-- 07-02-2023
-- WALTER MARTIN LOPES
-- -------------------------------------------------------
CREATE DATABASE ALMACEN;
USE ALMACEN;

-- 1 CREACION DE TABLAS--
CREATE TABLE FABRICANTES(
CODIGO INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
NOMBRE VARCHAR(100) NOT NULL);

CREATE TABLE PRODUCTOS(
CODIGO INT PRIMARY KEY,
NOMBRE VARCHAR(100),
PRECIO DECIMAL(10,2),
CODIGO_FABRICANTE INT);

ALTER TABLE PRODUCTOS 
MODIFY CODIGO_FABRICANTE INT UNSIGNED;

ALTER TABLE PRODUCTOS
ADD CONSTRAINT COD_FAB_FK FOREIGN KEY(CODIGO_FABRICANTE) REFERENCES FABRICANTES(CODIGO) ON DELETE CASCADE ON UPDATE CASCADE;

-- 2 Muestra la estructura de la tabla FABRICANTES --
DESC FABRICANTES;

-- 3 Copia los siguientes comandos para insertar datos en la tabla FABRICANTES. Fíjate en cómo se utiliza la sentencia INSERT cuando se usa un campo autonumérico. ---
INSERT INTO FABRICANTES (NOMBRE) VALUES ('Asus');
INSERT INTO FABRICANTES (NOMBRE) VALUES('Lenovo');
INSERT INTO FABRICANTES (NOMBRE) VALUES('Hewlett-Packard');
INSERT INTO FABRICANTES (NOMBRE) VALUES('Samsung');
INSERT INTO FABRICANTES (NOMBRE) VALUES('Seagate');

-- 4 Inserta además los siguientes registros en FABRICANTES.
INSERT INTO FABRICANTES (NOMBRE) 
VALUES  ('Crucial'),
		('Gigabyte'),
        ('Huawei'),
        ('Xiaomi');
        
-- 5 Muestra la estructura de la tabla PRODUCTOS.
DESC PRODUCTOS;

-- 6 Copia los siguientes comandos para insertar datos en la tabla PRODUCTOS
ALTER TABLE PRODUCTOS
MODIFY CODIGO INT AUTO_INCREMENT;

INSERT INTO PRODUCTOS (NOMBRE,PRECIO,CODIGO_FABRICANTE) VALUES('Disco duro SATA3 2TB', 86.99, 5);
INSERT INTO PRODUCTOS (NOMBRE,PRECIO,CODIGO_FABRICANTE) VALUES('Memoria RAM DDR4 8GB', 120, 6);
INSERT INTO PRODUCTOS (NOMBRE,PRECIO,CODIGO_FABRICANTE) VALUES('Disco SSD 1 TB', 150.99, 4);
INSERT INTO PRODUCTOS (NOMBRE,PRECIO,CODIGO_FABRICANTE) VALUES('GeForce GTX 1050Ti', 185, 7);
INSERT INTO PRODUCTOS (NOMBRE,PRECIO,CODIGO_FABRICANTE) VALUES('GeForce GTX 1080 Xtreme', 755, 6);
INSERT INTO PRODUCTOS (NOMBRE,PRECIO,CODIGO_FABRICANTE) VALUES('Monitor 24 LED Full HD', 202, 1);
INSERT INTO PRODUCTOS (NOMBRE,PRECIO,CODIGO_FABRICANTE) VALUES('Monitor 27 LED Full HD', 245.99, 1);
INSERT INTO PRODUCTOS (NOMBRE,PRECIO,CODIGO_FABRICANTE) VALUES('Portátil Yoga 520', 559, 2);

-- 7 Copia los siguientes comandos para insertar datos en la tabla PRODUCTOS
INSERT INTO PRODUCTOS (NOMBRE,PRECIO,CODIGO_FABRICANTE) VALUES('Portátil Ideapd 320', 444.00, 2);
INSERT INTO PRODUCTOS (NOMBRE,PRECIO,CODIGO_FABRICANTE) VALUES('Impresora HP Deskjet 3720', 59.99, 3);
INSERT INTO PRODUCTOS (NOMBRE,PRECIO,CODIGO_FABRICANTE) VALUES('Impresora HP Laserjet Pro...', 180.00, 3);


-- ==================================CONSULTAS SOBRE UNA TABLA==================================

-- 1 Lista el nombre de todos los PRODUCTOS que hay en la tabla PRODUCTOS.
SELECT NOMBRE
FROM PRODUCTOS;

-- 2 Lista los nombres y los precios de todos los PRODUCTOS de la tabla PRODUCTOS.
SELECT NOMBRE,PRECIO
FROM PRODUCTOS;

-- 3 Lista todas las columnas de la tabla PRODUCTOS.
SELECT * FROM PRODUCTOS;

-- 4 Lista el nombre de los PRODUCTOS, el precio en euros y el precio en dólares estadounidenses 
-- (1dólar estadounidense equivale a 0,83 euro a fecha 2 feb 8:57 ).Redondea a 3 decimales y utiliza los siguientes alias para las columnas: nombre de PRODUCTOS, euros, dólares.
SELECT NOMBRE, PRECIO AS EUROS, round((PRECIO/0.83),3) AS DÓLARES
FROM PRODUCTOS;

-- 5 Lista los nombres y los precios de todos los PRODUCTOS de la tabla PRODUCTOS, convirtiendo los
-- nombres a mayúscula.
SELECT upper(NOMBRE),PRECIO
FROM PRODUCTOS;

-- 6 Lista los nombres y los precios de todos los PRODUCTOS de la tabla PRODUCTOS, convirtiendo los nombres a minúscula.
SELECT lower(NOMBRE),PRECIO
FROM PRODUCTOS;

-- 7 Lista el nombre de todos los fabricantes en una columna, y en otra columna obtenga en mayúsculas los dos primeros caracteres del nombre del fabricante. Saldrá algo así:
SELECT NOMBRE,upper(LEFT(NOMBRE,2))
FROM FABRICANTES;

-- 8 Lista los nombres y los precios de todos los PRODUCTOS de la tabla PRODUCTOS, redondeando el valor del precio.
SELECT NOMBRE, round(PRECIO) AS PRECIO
FROM PRODUCTOS;

-- 9 Lista los nombres y los precios de todos los PRODUCTOS de la tabla PRODUCTOS, truncando el valor del precio para mostrarlo sin ninguna cifra decimal.
SELECT NOMBRE, truncate(PRECIO,0) AS PRECIO
FROM PRODUCTOS;

-- 10 Lista el código de los fabricantes que tienen PRODUCTOS en la tabla PRODUCTOS.
SELECT CODIGO_FABRICANTE FROM PRODUCTOS;

-- 11 . Lista el código de los fabricantes que tienen PRODUCTOS en la tabla PRODUCTOS, eliminando los códigos que aparecen repetidos.
SELECT  DISTINCT CODIGO_FABRICANTE FROM PRODUCTOS;

-- 12 Lista los nombres de los fabricantes ordenados de forma ascendente.
SELECT NOMBRE FROM FABRICANTES
ORDER BY NOMBRE;

-- 13 Lista los nombres de los fabricantes ordenados de forma descendente.
SELECT NOMBRE FROM FABRICANTES
ORDER BY NOMBRE DESC;

-- 14  Lista los nombres de los PRODUCTOS ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
SELECT NOMBRE FROM PRODUCTOS
ORDER BY NOMBRE, PRECIO DESC;

-- 15 Devuelve una lista con las 5 primeras filas de la tabla fabricante.
SELECT * FROM FABRICANTES
LIMIT 5;

-- 16 Devuelve una lista con 2 filas a partir de la cuarta fila de la tabla fabricante. La cuarta fila también se debe incluir en la respuesta. /* Sin usar la palabra reservada OFFSET */
SELECT * FROM FABRICANTES
LIMIT 3,2;

-- 17 La misma consulta anterior usando LIMIT y OFFSET
SELECT * FROM FABRICANTES
LIMIT 2 OFFSET 3;

-- 18 Lista el nombre y el precio del PRODUCTOS más barato. (Utiliza solamente las cláusulas ORDER BY y
-- LIMIT) NOTA: Ten en cuenta que en el caso de que existiesen varios PRODUCTOS con el mismo
-- precio que el PRODUCTOS más barato, sólo se devolvería uno de ellos. NOTA: Aquí no podría usar
-- MIN(precio), necesitaría GROUP BY
SELECT NOMBRE, PRECIO FROM PRODUCTOS 
ORDER BY PRECIO 
LIMIT 1;

-- 19 Lista el nombre y el precio del PRODUCTOS más caro. (Utilice solamente las cláusulas ORDER BY y
-- LIMIT) NOTA: Aquí no podría usar MAX(precio), necesitaría GROUP BY, ten en cuenta que en el caso
-- de que existiesen varios PRODUCTOS con el mismo precio que el PRODUCTOS más caro, sólo se
-- devolvería uno de ellos.
SELECT NOMBRE, PRECIO FROM PRODUCTOS 
ORDER BY PRECIO DESC
LIMIT 1;

-- 20 Lista el nombre de todos los PRODUCTOS del fabricante cuyo código de fabricante es igual a 2
SELECT NOMBRE,CODIGO_FABRICANTE FROM PRODUCTOS
WHERE CODIGO_FABRICANTE = 2;

-- 21  Lista el nombre de los PRODUCTOS que tienen un precio menor o igual a 120€.
SELECT NOMBRE,PRECIO FROM PRODUCTOS
WHERE PRECIO<=120;

-- 22  Lista el nombre de los PRODUCTOS que tienen un precio mayor o igual a 400€.
SELECT NOMBRE,PRECIO FROM PRODUCTOS
WHERE PRECIO>=400;

-- 23  Lista el nombre de los PRODUCTOS que no tienen un precio mayor o igual a 400€
SELECT NOMBRE,PRECIO FROM PRODUCTOS
WHERE PRECIO<400;

-- 24 Lista todos los PRODUCTOS que tengan un precio entre 80€ y 300€. Sin utilizar el operador BETWEEN.
SELECT NOMBRE,PRECIO FROM PRODUCTOS
WHERE PRECIO>80 AND PRECIO<300;

-- 25  Lista todos los PRODUCTOS que tengan un precio entre 60€ y 200€. Utilizando el operador BETWEEN.
SELECT NOMBRE,PRECIO FROM PRODUCTOS
WHERE PRECIO BETWEEN 60 AND 200;

-- 26 Lista todos los PRODUCTOS que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6
SELECT NOMBRE,PRECIO,CODIGO_FABRICANTE FROM PRODUCTOS
WHERE CODIGO_FABRICANTE = 6
AND PRECIO >200;

-- 27 Lista todos los PRODUCTOS donde el código de fabricante sea 1, 3 o 5. Sin utilizar el operador IN.
SELECT NOMBRE,CODIGO_FABRICANTE FROM PRODUCTOS
WHERE CODIGO_FABRICANTE = 1 OR CODIGO_FABRICANTE = 3 OR CODIGO_FABRICANTE = 5;

 -- 28 Lista todos los PRODUCTOS donde el código de fabricante sea 1, 3 o 5. Utilizando el operador IN.
 SELECT NOMBRE,CODIGO_FABRICANTE FROM PRODUCTOS
WHERE CODIGO_FABRICANTE IN(1,3,5);

-- 29 Lista el nombre y el precio de los PRODUCTOS en céntimos (Habrá que multiplicar por 100 el valor del precio). Crea un alias para la columna que contiene el precio que se llame céntimos
SELECT NOMBRE,PRECIO, (PRECIO*100) AS CENTIMOS FROM PRODUCTOS;

-- 30 Lista los nombres de los fabricantes cuyo nombre empiece por la letra S.
SELECT NOMBRE FROM FABRICANTES
WHERE NOMBRE LIKE "S%";

-- 31 Lista los nombres de los fabricantes cuyo nombre termine por la vocal e.
SELECT NOMBRE FROM FABRICANTES
WHERE NOMBRE LIKE "%E";

-- 32 Lista los nombres de los fabricantes cuyo nombre contenga el carácter w.
SELECT NOMBRE FROM FABRICANTES
WHERE NOMBRE LIKE "W%"
OR NOMBRE LIKE "%W%"
OR NOMBRE LIKE "%W";

-- 33 Lista los nombres de los fabricantes cuyo nombre sea de 4 caracteres.
SELECT NOMBRE FROM FABRICANTES
WHERE char_length(NOMBRE)=4;

-- 34 Devuelve una lista con el nombre de todos los PRODUCTOS que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.
SELECT NOMBRE, PRECIO FROM PRODUCTOS
WHERE NOMBRE LIKE "MONITOR%"
OR NOMBRE LIKE "%MONITOR%"
OR NOMBRE LIKE "%MONITOR"
AND PRECIO < 215;

-- 35 Lista el nombre y el precio de todos los PRODUCTOS que tengan un precio mayor o igual a 180€.Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por elnombre (en orden ascendente).
SELECT NOMBRE, PRECIO FROM PRODUCTOS
WHERE PRECIO >= 180
ORDER BY PRECIO DESC,NOMBRE;

-- --------------------- CONSULTAS DE RESUMEN SOBRE UNA TABLA--------------------
-- 1 Calcula el número total de productos que hay en la tabla productos.
SELECT COUNT(NOMBRE) FROM PRODUCTOS;

-- 2 Calcula el número total de fabricantes que hay en la tabla fabricante.
SELECT COUNT(NOMBRE) FROM FABRICANTES;

-- 3 Calcula el número de valores distintos de código de fabricante aparecen en la tabla productos.
SELECT COUNT(DISTINCT CODIGO_FABRICANTE) FROM PRODUCTOS;
SELECT COUNT(CODIGO_FABRICANTE) FROM PRODUCTOS;

-- 4 Calcula la media del precio de todos los productos
SELECT avg(PRECIO) AS MEDIA FROM PRODUCTOS;

-- 5 Calcula el precio más barato de todos los productos.
SELECT min(PRECIO) AS PRODUCTO_BARATO FROM PRODUCTOS;

 -- 6 Calcula el precio más caro de todos los productos.
 SELECT max(PRECIO) AS PRODUCTO_CARO FROM PRODUCTOS;
 
 -- 7 Lista el nombre y el precio del producto más barato. NOTA: Aquí no podría usar MIN(precio), necesitaría GROUP BY. Utilizar ORDER BY y LIMIT.
 SELECT NOMBRE, PRECIO FROM PRODUCTOS
 ORDER BY PRECIO
 LIMIT 1;
 
 -- 8 Lista el nombre y el precio del producto más caro. NOTA: Aquí no podría usar MAX(precio), necesitaría GROUP BY. Utilizar ORDER BY y LIMIT.
 SELECT NOMBRE, PRECIO FROM PRODUCTOS
 ORDER BY PRECIO DESC
 LIMIT 1;
 
 -- 9 Calcula la suma de los precios de todos los productos.
 SELECT sum(PRECIO) AS SUMA_PRECIOS FROM PRODUCTOS;
 
 -- --------------------- CONSULTAS A VARIAS TABLAS--------------------
 
 -- 1 Devuelve una lista con el nombre del PRODUCTOS, precio y nombre de fabricante de todos los PRODUCTOS de la base de datos.
SELECT PRODUCTOS.NOMBRE,PRODUCTOS.PRECIO,FABRICANTES.NOMBRE AS NOMBRE_FABRICANTE
FROM PRODUCTOS INNER JOIN FABRICANTES
ON PRODUCTOS.CODIGO_FABRICANTE = FABRICANTES.CODIGO;

-- 2 Devuelve una lista con el nombre del PRODUCTOS, precio y nombre de fabricante de
-- todos los PRODUCTOS de la base de datos. Ordene el resultado por el nombre del
-- fabricante, por orden alfabético.
SELECT PRODUCTOS.NOMBRE,PRODUCTOS.PRECIO,FABRICANTES.NOMBRE AS NOMBRE_FABRICANTE
FROM PRODUCTOS INNER JOIN FABRICANTES
ON PRODUCTOS.CODIGO_FABRICANTE = FABRICANTES.CODIGO
ORDER BY FABRICANTES.NOMBRE;

-- 3 Devuelve una lista con el código del PRODUCTO, nombre del PRODUCTO, código del fabricante y nombre del fabricante, de todos los PRODUCTOS de la base de datos.
SELECT PRODUCTOS.CODIGO,PRODUCTOS.NOMBRE,FABRICANTES.CODIGO AS CODIGO_FABRICANTE,FABRICANTES.NOMBRE AS NOMBRE_FABRICANTE
FROM PRODUCTOS INNER JOIN FABRICANTES
ON PRODUCTOS.CODIGO_FABRICANTE = FABRICANTES.CODIGO;

-- 4 Devuelve una lista de todos los PRODUCTOS del fabricante Lenovo.
SELECT PRODUCTOS.* , FABRICANTES.NOMBRE
FROM PRODUCTOS INNER JOIN FABRICANTES
ON PRODUCTOS.CODIGO_FABRICANTE = FABRICANTES.CODIGO
WHERE FABRICANTES.NOMBRE = "LENOVO";

-- 5 Devuelve una lista de todos los PRODUCTOS del fabricante Crucial que tengan un precio mayor que 200€
SELECT PRODUCTOS.* , FABRICANTES.NOMBRE
FROM PRODUCTOS INNER JOIN FABRICANTES
ON PRODUCTOS.CODIGO_FABRICANTE = FABRICANTES.CODIGO
WHERE PRODUCTOS.PRECIO > 200;

-- 6 Devuelve un listado con todos los PRODUCTOS de los fabricantes Asus, Hewlett-Packard y Seagate. Sin utilizar el operador IN.
SELECT PRODUCTOS.* , FABRICANTES.NOMBRE
FROM PRODUCTOS INNER JOIN FABRICANTES
ON PRODUCTOS.CODIGO_FABRICANTE = FABRICANTES.CODIGO
WHERE FABRICANTES.NOMBRE = "Asus"
OR FABRICANTES.NOMBRE = "Hewlett-Packard"
OR FABRICANTES.NOMBRE = "Seagate";

-- 7  Devuelve un listado con todos los PRODUCTOS de los fabricantes Asus, Hewlett-Packardy Seagate. Utilizando el operador IN.
SELECT PRODUCTOS.* , FABRICANTES.NOMBRE
FROM PRODUCTOS INNER JOIN FABRICANTES
ON PRODUCTOS.CODIGO_FABRICANTE = FABRICANTES.CODIGO
WHERE FABRICANTES.NOMBRE IN("Asus","Hewlett-Packard","Seagate");

-- 8 Devuelve un listado con el nombre y el precio de todos los PRODUCTOS de los fabricantes cuyo nombre termine por la vocal e.
SELECT PRODUCTOS.NOMBRE,PRODUCTOS.PRECIO, FABRICANTES.NOMBRE
FROM PRODUCTOS INNER JOIN FABRICANTES
ON PRODUCTOS.CODIGO_FABRICANTE = FABRICANTES.CODIGO
WHERE FABRICANTES.NOMBRE LIKE "%E";

-- 9 Devuelve un listado con el nombre y el precio de todos los PRODUCTOS cuyo nombre de fabricante contenga el carácter w en su nombre.
SELECT PRODUCTOS.NOMBRE,PRODUCTOS.PRECIO, FABRICANTES.NOMBRE
FROM PRODUCTOS INNER JOIN FABRICANTES
ON PRODUCTOS.CODIGO_FABRICANTE = FABRICANTES.CODIGO
WHERE FABRICANTES.NOMBRE LIKE "W%"
OR FABRICANTES.NOMBRE LIKE "%W"
OR FABRICANTES.NOMBRE LIKE "%W%";

-- 10 Devuelve un listado con el nombre de PRODUCTOS, precio y nombre de fabricante, de todos los PRODUCTOS que tengan un precio mayor o igual a 180€. Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente)
SELECT PRODUCTOS.NOMBRE,PRODUCTOS.PRECIO, FABRICANTES.NOMBRE
FROM PRODUCTOS INNER JOIN FABRICANTES
ON PRODUCTOS.CODIGO_FABRICANTE = FABRICANTES.CODIGO
WHERE PRODUCTOS.PRECIO >=180
ORDER BY PRODUCTOS.PRECIO DESC,PRODUCTOS.NOMBRE;

-- 11 Devuelve un listado con el código y el nombre de fabricante, solamente de aquellos fabricantes que tienen PRODUCTOS asociados en la base de datos
SELECT FABRICANTES.CODIGO,FABRICANTES.NOMBRE, PRODUCTOS.NOMBRE
FROM FABRICANTES  INNER JOIN PRODUCTOS 
ON FABRICANTES.CODIGO = PRODUCTOS.CODIGO_FABRICANTE;

-- ==================================CONSULTAS MULTITABLA (COMPOSICION EXTERNA)==============================================
-- 1  Devuelve un listado de todos los fabricantes que existen en la base de datos, junto con los
-- PRODUCTOS que tiene cada uno de ellos. El listado deberá mostrar también aquellos
-- fabricantes que no tienen PRODUCTOS asociados
SELECT FABRICANTES.CODIGO,FABRICANTES.NOMBRE, PRODUCTOS.NOMBRE
FROM FABRICANTES  LEFT JOIN PRODUCTOS 
ON  FABRICANTES.CODIGO = PRODUCTOS.CODIGO_FABRICANTE;

-- 2 Devuelve un listado donde sólo aparezcan aquellos fabricantes que no tienen ningún PRODUCTO asociado.
SELECT FABRICANTES.CODIGO,FABRICANTES.NOMBRE AS FABRICANTE, PRODUCTOS.NOMBRE AS PRODUCTO
FROM FABRICANTES  LEFT JOIN PRODUCTOS 
ON  FABRICANTES.CODIGO = PRODUCTOS.CODIGO_FABRICANTE
WHERE PRODUCTOS.NOMBRE IS NULL;


-- ==================================CONSULTAS RESUMEN A VARIAS TABLAS=============================================
-- 1 Calcula el número de PRODUCTOS que tiene el fabricante Asus.
SELECT COUNT(P.NOMBRE) AS ASUS
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON  F.CODIGO = P.CODIGO_FABRICANTE
WHERE F.NOMBRE = "Asus";

-- 2 Calcula la media del precio de todos los PRODUCTOS del fabricante Asus.
SELECT avg(P.PRECIO) AS MEDIA
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON  F.CODIGO = P.CODIGO_FABRICANTE
WHERE F.NOMBRE = "Asus";

-- 3. Calcula el precio más barato de todos los PRODUCTOS del fabricante Asus.
SELECT min(P.PRECIO) AS BARATO
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON  F.CODIGO = P.CODIGO_FABRICANTE
WHERE F.NOMBRE = "Asus";

-- 4. Calcula el precio más caro de todos los PRODUCTOS del fabricante Asus.
SELECT max(P.PRECIO) AS CARO
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON  F.CODIGO = P.CODIGO_FABRICANTE
WHERE F.NOMBRE = "Asus";

-- 5. Calcula la suma de todos los PRODUCTOS del fabricante Asus.
SELECT sum(P.PRECIO) AS SUMA
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON  F.CODIGO = P.CODIGO_FABRICANTE
WHERE F.NOMBRE = "Asus";

-- 6. Muestra el precio máximo, precio mínimo, precio medio y el número total de PRODUCTOS que tiene el fabricante Crucial.
SELECT max(P.PRECIO) AS MAXIMO,min(P.PRECIO) AS MINIMO,avg(P.PRECIO) AS MEDIA,count(P.NOMBRE) AS NUM_PRODUCTOS
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON  F.CODIGO = P.CODIGO_FABRICANTE
WHERE F.NOMBRE = "Crucial";

-- 7. Muestra el número total de PRODUCTOS que tiene cada uno de los fabricantes. El listado
-- también debe incluir los fabricantes que no tienen ningún PRODUCTOS. El resultado
-- mostrará dos columnas, una con el nombre del fabricante y otra con el número de
-- PRODUCTOS que tiene. Ordene el resultado descendentemente por el número de PRODUCTOS
SELECT F.NOMBRE AS FABRICANTE,COUNT(P.NOMBRE) AS NUMERO_PRODUCTOS
FROM FABRICANTES AS F LEFT JOIN PRODUCTOS AS P
ON F.CODIGO = P.CODIGO_FABRICANTE
GROUP BY F.NOMBRE
ORDER BY NUMERO_PRODUCTOS DESC;

-- 8. Muestra el precio máximo, precio mínimo y precio medio de los PRODUCTOS de cada uno
-- de los fabricantes. El resultado mostrará el nombre del fabricante junto con los datos que
-- se solicitan.
SELECT F.NOMBRE AS FABRICANTE, max(P.PRECIO) AS MAXIMO,min(P.PRECIO) AS MINIMO,avg(P.PRECIO) AS MEDIA
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON  F.CODIGO = P.CODIGO_FABRICANTE
GROUP BY F.NOMBRE;

-- 9. Muestra el precio máximo, precio mínimo, precio medio y el número total de PRODUCTOS
-- de los fabricantes que tienen un precio medio superior a 200€. No es necesario mostrar el
-- nombre del fabricante, con el código del fabricante es suficiente.
SELECT P.CODIGO_FABRICANTE AS FABRICANTE,max(P.PRECIO) AS MAXIMO,min(P.PRECIO) AS MINIMO,avg(P.PRECIO) AS MEDIA,count(P.NOMBRE) AS NUM_PRODUCTOS
FROM PRODUCTOS AS P
GROUP BY CODIGO_FABRICANTE
HAVING MEDIA > 200;

-- 10. Muestra el nombre de cada fabricante, junto con el precio máximo, precio mínimo, precio
-- medio y el número total de PRODUCTOS de los fabricantes que tienen un precio medio
-- superior a 200€. Es necesario mostrar el nombre del fabricante.
SELECT F.NOMBRE AS FABRICANTE, max(P.PRECIO) AS MAXIMO,min(P.PRECIO) AS MINIMO,avg(P.PRECIO) AS MEDIA,count(P.NOMBRE) AS NUM_PRODUCTOS
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON  F.CODIGO = P.CODIGO_FABRICANTE
GROUP BY F.NOMBRE
HAVING MEDIA>200;

-- 11. Calcula el número de PRODUCTOS que tienen un precio mayor o igual a 180€.
SELECT COUNT(NOMBRE) AS NUMERO_PRODUCTOS
FROM PRODUCTOS AS P
WHERE PRECIO >=180;

-- 12. Calcula el número de PRODUCTOS que tiene cada fabricante con un precio mayor o igual a 180€.
SELECT F.NOMBRE AS FABRICANTE, COUNT(P.NOMBRE) AS NUMERO_PRODUCTOS
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON F.CODIGO = P.CODIGO_FABRICANTE
WHERE P.PRECIO>=180
GROUP BY F.NOMBRE;

-- 13. Lista el precio medio los PRODUCTOS de cada fabricante, mostrando solamente el código del fabricante.
SELECT CODIGO_FABRICANTE AS FABRICANTE, AVG(PRECIO) AS PRECIO_MEDIO
FROM PRODUCTOS
GROUP BY FABRICANTE;

-- 14. Lista el precio medio los PRODUCTOS de cada fabricante, mostrando solamente el nombre del fabricante.
SELECT F.NOMBRE AS FABRICANTE, AVG(P.PRECIO) AS PRECIO_MEDIO
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON F.CODIGO = P.CODIGO_FABRICANTE
GROUP BY FABRICANTE;

-- 15. Lista los nombres de los fabricantes cuyos PRODUCTOS tienen un precio medio mayor o igual a 150€.
SELECT F.NOMBRE AS FABRICANTE, AVG(P.PRECIO) AS PRECIO_MEDIO
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON F.CODIGO = P.CODIGO_FABRICANTE
GROUP BY FABRICANTE
HAVING PRECIO_MEDIO >=150;

-- 16. Devuelve un listado con los nombres de los fabricantes que tienen 2 o más PRODUCTOS.
SELECT F.NOMBRE AS FABRICANTE
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON F.CODIGO = P.CODIGO_FABRICANTE
GROUP BY FABRICANTE
HAVING COUNT(P.NOMBRE) >=2;

-- 17. Devuelve un listado con los nombres de los fabricantes y el número de PRODUCTOS que
-- tiene cada uno con un precio superior o igual a 220 €. No es necesario mostrar el nombre
-- de los fabricantes que no tienen PRODUCTOS que cumplan la condición.
SELECT CODIGO_FABRICANTE, COUNT(NOMBRE) AS NUMERO_PRODUCTOS
FROM PRODUCTOS
WHERE PRECIO>=220
GROUP BY CODIGO_FABRICANTE;

-- 18. Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus PRODUCTOS es superior a 1000 €.
SELECT F.NOMBRE AS FABRICANTE, SUM(P.PRECIO) AS SUMA_PRECIO_PRODUCTOS
FROM FABRICANTES AS F INNER JOIN PRODUCTOS AS P
ON F.CODIGO = P.CODIGO_FABRICANTE
GROUP BY FABRICANTE
HAVING SUMA_PRECIO_PRODUCTOS > 1000;