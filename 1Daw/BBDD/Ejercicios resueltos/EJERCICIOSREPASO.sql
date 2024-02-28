-- --------------------------------------------------------------------------------
-- BASES DE DATOS EJERCICIOS DE REPASO
-- 1º DAW
-- 21-03-2023
-- WALTER MARTIN LOPES
-- -------------------------------------------------------------------------------

-- *********************************************************************************
-- ********************************EJERCICIOS REPASO********************************
-- *********************************************************************************

-- ***************************
-- *****BBDD-SUMINISTROS******
-- ***************************

CREATE DATABASE SUMINISTROS;
USE SUMINISTROS;

-- ================CREACION DE TABLAS================
CREATE TABLE SUMINISTRADOR (
    COD VARCHAR(10) PRIMARY KEY,
    NOMBRE VARCHAR(40),
    CIUDAD VARCHAR(40),
    CATEGORIA VARCHAR(10)
);

CREATE TABLE PRODUCTO (
    COD VARCHAR(10) PRIMARY KEY,
    NOMBRE VARCHAR(40),
    PRECIO DECIMAL(5,2)
);

CREATE TABLE SUMINISTRA (
    CODS VARCHAR(10),
    CODP VARCHAR(10),
    CANTIDAD INTEGER,
    PRIMARY KEY (CODS, CODP),
    FOREIGN KEY (CODS) REFERENCES SUMINISTRADOR(COD) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (CODP) REFERENCES PRODUCTO(COD) ON UPDATE CASCADE ON DELETE CASCADE
);

-- ================CARGA DE DATOS================
INSERT INTO PRODUCTO VALUES
(111,'GRAPADORA',10),
(112,'HOJAS',5),
(113,'BORRADORES',1),
(114,'LAPICES',0.5),
(115,'ARCHIVADORES',3);

INSERT INTO SUMINISTRADOR VALUES
(1,'CARLOS MARTINEZ','MADRID',5),
(2,'ADOLFO BERNAL','MADRID',4),
(3,'RODRIGO DIAZ','BARCELONA',1),
(4,'MARIA LOPEZ','SEVILLA',1);


INSERT INTO SUMINISTRA VALUES
(1,111,3),
(1,112,4),
(1,113,4),
(2,112,45),
(4,111,2),
(4,112,2),
(4,113,2),
(4,114,2),
(4,115,2);

-- ==========CONSULTAS==========
-- 1. Número y Nombre de suministradores que viven en la misma ciudad que el SUMINISTRADOR 1.
SELECT COD, NOMBRE , CIUDAD
FROM SUMINISTRADOR 
WHERE CIUDAD = (SELECT CIUDAD FROM SUMINISTRADOR WHERE COD = '1');

-- 2. Nombre de los suministradores que suministran al menos un producto.
SELECT NOMBRE,COD
FROM SUMINISTRADOR
WHERE COD IN (SELECT DISTINCT CODS FROM SUMINISTRA);

-- 3. Nombre de los suministradores que no suministran ningún producto
SELECT NOMBRE,COD
FROM SUMINISTRADOR 
WHERE COD NOT IN (SELECT DISTINCT CODS FROM SUMINISTRA);

-- 4. Nº total de suministradores que han suministrado algún producto
SELECT COUNT(*) AS Total_Suministradores
FROM SUMINISTRADOR
WHERE COD IN (SELECT CODS FROM SUMINISTRA);

-- 5. Nombre de suministradores con menor categoría que el máximo de las categorías.
SELECT s.NOMBRE
FROM SUMINISTRADOR s
WHERE s.CATEGORIA < (SELECT MAX(CATEGORIA) FROM SUMINISTRADOR);



-- ***************************
-- *******BBDD-LIBROS*********
-- ***************************
CREATE DATABASE LIBROS;
USE LIBROS;

-- ================CREACION DE TABLAS================
CREATE TABLE LIBROS(
CODIGO INT PRIMARY KEY,
TITULO VARCHAR(40),
AUTOR VARCHAR(30),
EDITORIAL VARCHAR(20),
PRECIO DECIMAL(5,2));
-- ================CARGA DE DATOS================
INSERT INTO LIBROS
  VALUES (1, 'Cien años de soledad', 'Gabriel García Márquez', 'Plaza & Janés', 18.99),
         (2, '1984', 'George Orwell', 'Debolsillo', 12.50),
         (3, 'Orgullo y prejuicio', 'Jane Austen', 'Alma', 16.00),
         (4, 'El amor en los tiempos del cólera', 'Gabriel García Márquez', 'Plaza & Janés', 19.95),
         (5, 'Crimen y castigo', 'Fyodor Dostoevsky', 'Austral', 14.99),
         (6, 'Un mundo feliz', 'Aldous Huxley', 'Debolsillo', 10.50),
		 (7, 'Matar a un ruiseñor', 'Harper Lee', 'Vintage', 11.99),
         (8, 'Los pilares de la tierra', 'Ken Follett', 'Plaza & Janés', 24.95),
         (9, 'La sombra del viento', 'Carlos Ruiz Zafón', 'Planeta', 21.00),
         (10, 'Don Quijote de la Mancha', 'Miguel de Cervantes', 'Alma', 22.50);
-- ================CONSULTAS================
-- 1. Título y precio de un libro (Cien años de soledad) y la diferencia entre su precio y el máximo valor:
SELECT TITULO,PRECIO,((SELECT MAX(PRECIO) FROM LIBROS)-PRECIO) AS "DIFERENCIA CON MAXIMO VALOR"
FROM LIBROS
WHERE TITULO ='Cien años de soledad';

-- 2. Mostrar el título y precio del libro más caro:
SELECT TITULO, PRECIO FROM LIBROS
WHERE TITULO = (SELECT TITULO FROM LIBROS
				ORDER BY PRECIO DESC
				LIMIT 1);
                
-- 3. Actualizar el precio del libro con máximo precio; ahora vale 45€:
UPDATE LIBROS 
SET PRECIO = 45
WHERE PRECIO IN (SELECT * FROM(SELECT MAX(PRECIO) FROM LIBROS)AS TABLA_TEMPORAL);
                
-- 4. Eliminar los libros con precio menor:
DELETE FROM LIBROS
WHERE PRECIO IN(SELECT * FROM (SELECT MIN(PRECIO) FROM LIBROS) AS TABLA_TEMPORAL);
                
SELECT * FROM LIBROS;


-- ****************************
-- ********BBDD-ALUMNOS********
-- ****************************
CREATE DATABASE ALUMNOS;
USE ALUMNOS;
-- ================CREACION DE TABLAS================
CREATE TABLE ALUMNOS (
    DOCUMENTO CHAR(8) PRIMARY KEY,
    NOMBRE VARCHAR(30),
    NOTA DECIMAL(4,2)
);
-- ================CARGA DE DATOS================
INSERT INTO ALUMNOS (DOCUMENTO, NOMBRE, NOTA) VALUES
('12345678', 'Ana Pérez', 9.5),
('23456789', 'Carlos López', 8.0),
('34567890', 'María Sánchez', 7.5),
('45678901', 'Juan García', 6.0),
('56789012', 'Esther Ramírez', 8.5),
('67890123', 'Diego Navarro', 5.0),
('78901234', 'Laura Ortiz', 9.0),
('89012345', 'Roberto Castro', 4.5),
('90123456', 'Sonia Guzmán', 3.0),
('01234567', 'Daniel Montoya', 7.0);
-- ================CONSULTAS================
-- 1. Obtener todos los datos de los alumnos con la nota más alta
SELECT * FROM ALUMNOS
WHERE NOTA = (SELECT MAX(NOTA) FROM ALUMNOS);

-- 2. Mostrar el nombre de los alumnos que tienen una nota menor al promedio, mostrando también su nota, y la diferencia con el promedio
SELECT NOMBRE, NOTA, ((SELECT AVG(NOTA) FROM ALUMNOS) - NOTA) AS DIFERENCIA_PROMEDIO
FROM ALUMNOS
WHERE NOTA < (SELECT AVG(NOTA) FROM ALUMNOS);

-- 3. Cambia la nota del alumno que tiene la menor nota. Ahora tiene un 4
UPDATE ALUMNOS
SET NOTA = 4.0
WHERE NOTA IN (SELECT * FROM (SELECT MIN(NOTA) FROM ALUMNOS) AS TABLA_TEMPORAL);

-- 4. Elimine los alumnos cuya nota es menor al promedio
DELETE FROM ALUMNOS
WHERE NOTA < (SELECT * FROM (SELECT AVG(NOTA) FROM ALUMNOS) AS TABLA_TEMPORAL);


-- ***************************************
-- ********BBDD-EDITORIALES_LIBROS********
-- ***************************************
CREATE DATABASE EDITORIALES_LIBROS;
USE EDITORIALES_LIBROS;

-- ================CREACION DE TABLAS================
CREATE TABLE EDITORIALES (
CODIGO INT PRIMARY KEY,
NOMBRE VARCHAR(30)
);

CREATE TABLE LIBROS (
CODIGO INT PRIMARY KEY,
TITULO VARCHAR(40),
AUTOR VARCHAR(30),
CODIGOEDITORIAL INT,
FOREIGN KEY (CODIGOEDITORIAL) REFERENCES EDITORIALES(CODIGO) ON UPDATE CASCADE ON DELETE CASCADE
);

-- ================CARGA DE DATOS================
INSERT INTO EDITORIALES 
		 VALUES (1, 'Editorial A'),
                (2, 'Editorial B'),
                (3, 'Editorial C'),
                (4, 'Editorial D'),
                (5, 'Editorial E');


INSERT INTO LIBROS 
		 VALUES (1, 'Libro 1', 'Borges', 1),
                (2, 'Libro 2', 'TANEMBAUM', 2),
                (3, 'Libro 3', 'García Márquez', 3),
                (4, 'Libro 4', 'Borges', 4),
                (5, 'Libro 5', 'Orwell', 5);
                
-- ================CONSULTAS================
-- 1. Mostrar el nombre de las editoriales que han publicado libros del autor "Borges"
SELECT E.NOMBRE
FROM EDITORIALES E
WHERE E.CODIGO IN (
			SELECT L.CODIGOEDITORIAL
			FROM LIBROS L
			WHERE L.AUTOR = 'Borges');

-- 2. Mostrar ahora las editoriales que no han publicado libros de "TANEMBAUM"
SELECT E.NOMBRE
FROM EDITORIALES E
WHERE E.CODIGO IN (
			SELECT L.CODIGOEDITORIAL
			FROM LIBROS L
			WHERE L.AUTOR = 'TANEMBAUM');