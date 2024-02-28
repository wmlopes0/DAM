-- BASE DE DATOS FARMACIA
-- 1º DAW
-- 14-02-2023
-- WALTER MARTIN LOPES
-- -------------------------------------------------------
CREATE DATABASE FARMACIA;
USE FARMACIA;

-- ===========================CREACION DE TABLAS=======================================
CREATE TABLE CLIENTES(
ID_CLIENTE INT PRIMARY KEY,
NOMBRE VARCHAR(50),
APE1 VARCHAR(50),
APE2 VARCHAR(50),
DNI VARCHAR(9),
DIRECCION VARCHAR(50),
TELEFONO CHAR(9));

CREATE TABLE EMPLEADOS(
ID_EMPLEADO INT,
NOMBRE VARCHAR(50) NOT NULL,
APE1 VARCHAR(50) NOT NULL,
APE2 VARCHAR(50) NOT NULL,
DNI VARCHAR(9) NOT NULL,
DIRECCION VARCHAR(40),
TELEFONO_EM CHAR(9),
SUELDO DECIMAL(10,2) NOT NULL);

CREATE TABLE MEDICAMENTOS(
ID_MEDICAMENTO INT PRIMARY KEY,
NOMCOMERCIAL VARCHAR(50) NOT NULL,
PRINCIPIO DECIMAL(5,2) NOT NULL,
LABORATORIO VARCHAR(50) NOT NULL);

CREATE TABLE VENTAS(
ID_VENTAS INT PRIMARY KEY,
FECHA_VENTAS DATE NOT NULL,
ID_CLIENTE INT,
ID_EMPLEADO INT,
ID_MEDICAMENTO INT);

-- ==========================================PRIMARY KEY Y FOREIGN KEY===================================
-- AÑADO LA PK DE LA TABLA EMPLEADOS Y LAS FK CORRESPONDIENTES DE LA TABLA VENTAS
ALTER TABLE EMPLEADOS
MODIFY ID_EMPLEADO INT PRIMARY KEY;

DESC EMPLEADOS;

-- FK
ALTER TABLE VENTAS
ADD CONSTRAINT ID_C_FK FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTES(ID_CLIENTE) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT ID_E_FK FOREIGN KEY(ID_EMPLEADO) REFERENCES EMPLEADOS(ID_EMPLEADO) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT ID_M_FK FOREIGN KEY(ID_MEDICAMENTO) REFERENCES MEDICAMENTOS(ID_MEDICAMENTO) ON DELETE CASCADE ON UPDATE CASCADE;

-- ==============================INSERTAR DATOS=============================================
-- MODIFICO CAMPO SALARIO PARA QUE TENGA 6 NUMEROS Y 2 DECIMALES
ALTER TABLE EMPLEADOS
MODIFY SUELDO DECIMAL(6,2) NOT NULL;

-- AÑADO COLUMNA QUE SE ME HABIA OLVIDADO EN MEDICAMENTOS
ALTER TABLE MEDICAMENTOS
ADD COLUMN PRECIO DECIMAL(3,2) NOT NULL AFTER PRINCIPIO;
DESC MEDICAMENTOS;

ALTER TABLE MEDICAMENTOS 
MODIFY COLUMN PRECIO DECIMAL(7,2) NOT NULL;

ALTER TABLE MEDICAMENTOS
MODIFY COLUMN PRINCIPIO VARCHAR(50) NOT NULL;

-- INSERTO
insert into clientes values
(1,'JOSE','PEREZ','LOPEZ','11222222S','C/SOL','608112223'),
(2,'ANTONIO','LOPEZ','MATEOS','12000333A','C/LUNA','912322323'),
(3,'MARIA','SANCHEZ','SANCHEZ','09888777D','ARAPILES','665656678'),
(4,'JESÚS','RAMOS','RAMOS','11876656F','LA MOTA','454545454'),
(5,'CARMEN','DIAZ','SANCHEZ','06767887D','EL MONTE','927898989'),
(6,'JUAN','PEREZ','PEREZ','23332233E','LA POZA','924565655'),
(7,'LUISA','SANJUAN','LUNA','12898989J','GRANDE','924555555');


INSERT INTO MEDICAMENTOS VALUES
(1,'GELOCATIL','PARACETAMOL','2.20','FYB'),
(2,'ALGIDOL','PARACETAMOL','2.25','BERENGUER'),
(3,'MOTOSOL','AMBROXOL','3.46','EUROPHARM'),
(4,'PHARMATON','VITAMINAS','6.20','EUROPHARM'),
(5,'STREPSILS','ALCOHOL DICLOROBENCILO','3.56','BOOTS HEAL'),
(6,'RHODOGIL','ESPIRAMICINA','7.89','RHONE POUL'),
(7,'ASPIRINA','ACIDO ACETILSALICILICO','2.45','BAYER'),
(8,'ALERGICAL','FLUOCINOLO','2.76', 'IQUINOSA'),
(9,'PECTOX','CARBOCISTEINA','4.05','ITALFARMAC'),
(10,'ANGILEPTOL','BENZOCAINA','4.45','AZQUE');

INSERT INTO EMPLEADOS VALUES
(1,'JUAN','RODRIGUEZ','RODRIGUEZ','12233445T','C/POLEN','609889977',1022.24),
(2,'MARIA','GARCIA','GARCIA','23449987U','CLAVELLINAS','789900000',1044.79),
(3,'JOSE','MARTINEZ','MARTINEZ','12776688P','LA ROSA','924677676',1552.13);

INSERT INTO VENTAS
VALUES 
(1,'2009/1/21',2,2,1),
(2,'2009/1/4',3,3,9),
(3,'2010/1/30',4,1,8),
(4,'2010/1/22',2,2,10),
(5,'2010/2/12',2,3,8),
(6,'2010/2/12',6,2,10),
(7,'2011/2/12',2,1,5),
(8,'2011/2/11',6,2,10),
(9,'2013/2/14',2,1,4),
(10,'2013/2/28',5,2,7),
(11,'2013/3/1',7,3,2),
(12,'2013/3/3',3,3,3),
(13,'2013/3/3',2,2,3),
(14,'2013/3/5',3,1,10),
(15,'2013/3/04',5,2,4),
(16,'2013/3/6',5,2,5),
(17,'2013/3/12',4,3,5),
(18,'2013/3/16',3,2,6),
(19,'2013/3/2',4,1,10),
(20,'2013/3/25',5,2,10);

-- ========================2-MODIFICACION DE TABLAS Y DE DATOS==============================
-- 1A 
ALTER TABLE CLIENTES 
MODIFY NOMBRE VARCHAR(50) NOT NULL,
MODIFY APE1 VARCHAR(50) NOT NULL,
MODIFY APE2 VARCHAR(50) NOT NULL;

-- 1B
CREATE INDEX I_CLIENTE_DNI ON CLIENTES(DNI);

-- 2A
ALTER TABLE EMPLEADOS 
MODIFY NOMBRE VARCHAR(50);

ALTER TABLE EMPLEADOS 
MODIFY SUELDO DECIMAL(10,2) NOT NULL CHECK(SUELDO BETWEEN 1000 AND 2500);

ALTER TABLE EMPLEADOS
MODIFY DIRECCION VARCHAR(50);

-- 3A
UPDATE CLIENTES
SET DIRECCION = 'Calle Mayor 5'
WHERE APE1='Pérez';

-- 3B
UPDATE CLIENTES
SET TELEFONO = '722748406'
WHERE DNI= '’00000001A';

-- 3C
UPDATE EMPLEADOS
SET SUELDO = SUELDO*1.1;

-- 3D
DELETE FROM CLIENTES 
WHERE NOMBRE='Antonio López Mateos';

-- 3E
UPDATE MEDICAMENTOS
SET PRECIO=5.25
WHERE NOMCOMERCIAL='GELOCATIL';

-- 3F
UPDATE MEDICAMENTOS 
SET LABORATORIO='BAYER'
WHERE ID_MEDICAMENTO=7;

-- 3G
UPDATE MEDICAMENTOS
SET PRECIO = PRECIO*1.05
WHERE PRINCIPIO='Paracetamol';

-- 3H
DELETE FROM MEDICAMENTOS
WHERE LABORATORIO = 'AZQUE';

-- ========================3-CONSULTAS==============================
-- 1. Crea una consulta que seleccione todos los registros de la tabla CLIENTES.
SELECT * FROM CLIENTES;
-- 2. Crea una consulta que seleccione todos los registros de la tabla EMPLEADOS.
SELECT * FROM EMPLEADOS;
-- 3. Crea una consulta que seleccione todos los registros de la tabla MEDICAMENTOS.
SELECT * FROM MEDICAMENTOS;
-- 4. Crea una consulta que muestre el nombre, ape1, ape2 y teléfono de los clientes.
SELECT NOMBRE, APE1, APE2 , TELEFONO FROM CLIENTES;
-- 5. Crea una consulta que muestre el ID_cliente y la dirección de los clientes.
SELECT ID_CLIENTE,DIRECCION FROM CLIENTES;
-- 6. Crea una consulta que muestre el nombre, ape1, ape2 y sueldo de los empleados.
SELECT NOMBRE,APE1,APE2, SUELDO FROM EMPLEADOS;
-- 7. Haz la misma consulta del ejercicio 6, pero ahora el campo ape1 se mostrará con la etiqueta
-- PRIMER APELLIDO, el campo ape2 con SEGUNDO APELLIDO, y para el campo sueldo aparecerá en
-- la cabecera SALARIO.
SELECT NOMBRE,APE1 AS PRIMER_APELLIDO,APE2 AS SEGUNDO_APELLIDO, SUELDO AS SALARIO FROM EMPLEADOS;
-- 8. Crea una consulta que muestre los laboratorios que fabrican los medicamentos, sin duplicados.
SELECT DISTINCT LABORATORIO  FROM MEDICAMENTOS;
-- 9. Crea una consulta que muestre el nombre, ape1, ape2 y teléfono del cliente cuyo Id_cliente=2
SELECT NOMBRE, APE1,APE2,TELEFONO FROM CLIENTES
WHERE ID_CLIENTE=2;
-- 10. Crea una consulta que muestre el ID_cliente y la dirección de los clientes cuyo segundo apellido es SANCHEZ O SÁNCHEZ.
SELECT ID_CLIENTE,DIRECCION FROM CLIENTES
WHERE APE2 IN('SANCHEZ','SÁNCHEZ');
-- 11. Crea una consulta que muestre el nombre, ape1, ape2 y sueldo de los empleados con ID_empleado 2 y 3.
SELECT NOMBRE,APE1,APE2,SUELDO FROM EMPLEADOS
WHERE ID_EMPLEADO IN(2,3);
-- 12. Crea una consulta que muestre el nombre, ape1, ape2 de los empleados con sueldo mayor de 2000.
SELECT NOMBRE,APE1,APE2 FROM EMPLEADOS
WHERE SUELDO>2000;
-- 13. Crea una consulta que muestre el nombre y dirección de los empleados cuyo sueldo es distinto de 1500.
SELECT NOMBRE,DIRECCION FROM EMPLEADOS 
WHERE SUELDO != 1500;
-- 14. Crea una consulta que muestre el nombre, ape1 de los empleados con sueldo comprendido entre 1000 y 3000 (SIN LOS VALORES EXTREMOS).
SELECT NOMBRE,APE1,SUELDO FROM EMPLEADOS
WHERE SUELDO > 1000 AND SUELDO < 3000;
-- 15. Crea una consulta que muestre el nombre comercial, principio activo y laboratorio de los medicamentos fabricados en el laboratorio BAYER.
SELECT NOMCOMERCIAL,PRINCIPIO, LABORATORIO FROM MEDICAMENTOS
WHERE LABORATORIO='BAYER';
-- 16. Crea una consulta que muestre el nombre comercial, principio activo y laboratorio de los
-- medicamentos fabricados por el laboratorio EUROPHARM y de precio superior a 5 €.
SELECT NOMCOMERCIAL,PRINCIPIO,LABORATORIO FROM MEDICAMENTOS
WHERE LABORATORIO='EUROPHARM'
AND PRECIO>5;
-- 17. Crea una consulta que muestre el laboratorio y el precio de los medicamentos cuyo principio
-- activo es PARACETAMOL O VITAMINAS.
SELECT LABORATORIO, PRECIO FROM MEDICAMENTOS 
WHERE PRINCIPIO IN('PARACETAMOL','VITAMINAS');
-- 18. Crea una consulta que muestre el id_ventas de las ventas realizadas en el año 2009.
SELECT ID_VENTAS FROM VENTAS 
WHERE year(FECHA_VENTAS) = 2009;
-- 19. Crea una consulta que muestre el nombre comercial de los medicamentos que empiezan por A,
-- que valen menos de 3€ y fabricados por el laboratorio AZQUE.
SELECT NOMCOMERCIAL FROM MEDICAMENTOS
WHERE NOMCOMERCIAL LIKE 'A%'
AND PRECIO<3
AND LABORATORIO ='AZQUE';
-- 20. Crea una consulta que muestre los empleados con ape1=’perez’. No escribas PEREZ, aunque esté
-- así en la tabla. Utiliza la función de postgresql LOWER(), que convierte una cadena de caracteres a
-- minúsculas. (busca más información sobre esta función en Internet). ¿Cuál es la función contraria
-- que recibe como parámetro una cadena en minúscula y la convierte a mayúsculas?
SELECT * FROM EMPLEADOS
WHERE lower(APE1)='perez';

-- ========================4-CONSULTAS II==============================
-- 21. Crea una consulta que muestre ID_venta y fecha de ventas de las ventas realizadas en 3 fechas
-- concretas (busca en tu tabla ventas fechas válidas)
SELECT ID_VENTAS, FECHA_VENTAS
FROM VENTAS
WHERE FECHA_VENTAS IN ( '2010/1/30','2010/2/12','2013/3/12');
-- 22. Crea una consulta que muestre el id_venta y la fecha de ventas de los registros donde
-- Id_cliente=1 y Id_medicamento=5.
SELECT ID_VENTAS, FECHA_VENTAS
FROM VENTAS
WHERE ID_CLIENTE=1
AND ID_MEDICAMENTO =5;
-- 23. Crea una consulta que muestre todos los campos de la tabla ventas que cumplan Id_cliente=2 o
-- ID_cliente=3
SELECT * FROM VENTAS
WHERE ID_CLIENTE IN(2,3);
-- 24. Crea una consulta que muestre todos los campos de la tabla ventas que cumplan Id_empleado=2
-- y ID_medicamento=3
SELECT * FROM VENTAS
WHERE ID_CLIENTE = 2
AND ID_MEDICAMENTO =3;
-- 25. Crea una consulta que muestre todos los campos de la tabla ventas que cumplan Id_cliente=2 o
-- ID_cliente=3 y Id_empleado=1
SELECT * FROM VENTAS
WHERE ID_CLIENTE IN(2,3)
 AND ID_EMPLEADO=1;
-- 26. Crea una consulta que muestre todos los campos de la tabla ventas que cumplan Id_cliente=2 o
-- ID_cliente=3
SELECT * FROM VENTAS
WHERE ID_CLIENTE IN(2,3);
-- 27. Crea una consulta que muestre todos los campos de la tabla ventas que cumplan Id_cliente=5 y
-- ID_empleado=3 y id_medicamento=1 o id_medicamento=5
SELECT * FROM VENTAS 
WHERE (ID_CLIENTE=5 AND ID_EMPLEADO=3)
AND (ID_MEDICAMENTO IN(1,5));
-- 28. Crea una consulta que muestre todos los campos de la tabla ventas que cumplan Id_cliente=2 o
-- ID_cliente=3 y fecha_venta= (busca una fecha válida en tu tabla).
SELECT * FROM VENTAS 
WHERE ID_CLIENTE IN(2,3)
AND FECHA_VENTAS='2010/1/30';
-- 29. Crea una consulta que muestre todos los campos de la tabla ventas que cumplan Id_cliente=4,
-- id_empleado=1, id_medicamento=3 y fecha_venta= (fecha válida en tu tabla).
SELECT * FROM VENTAS 
WHERE ID_CLIENTE=4
AND ID_EMPLEADO=1
AND ID_MEDICAMENTO=3
AND  FECHA_VENTAS='2010/1/30';
-- 30. Crea una consulta que muestre el ID_cliente, nombre y dirección de los clientes cuyo nombre termina por A.
SELECT ID_CLIENTE,NOMBRE,DIRECCION 
FROM CLIENTES
WHERE NOMBRE LIKE '%A';
-- 31. Crea una consulta que muestre el ID_cliente, dirección y teléfono de los clientes cuyo teléfono
-- tiene prefijo de la provincia de Cáceres (927).
SELECT ID_CLIENTE,DIRECCION,TELEFONO
FROM CLIENTES
WHERE TELEFONO LIKE '927%';
-- 32. Crea una consulta que muestre el ID_cliente y la dirección de los clientes cuyo primer apellido empieza por S.
SELECT ID_CLIENTE,DIRECCION
FROM CLIENTES
WHERE APE1 LIKE 'S%';
-- 33. Crea una consulta que muestre el ID_medicamento, principio activo y laboratorio de los
-- medicamentos fabricados por un laboratorio que empieza por B.
SELECT ID_MEDICAMENTO,PRINCIPIO,LABORATORIO
FROM MEDICAMENTOS
WHERE LABORATORIO LIKE 'B%';
-- 34. Crea una consulta que muestre el ID_medicamento, nombre comercial y precio de los
-- medicamentos cuyo nombre termina en OL.
SELECT ID_MEDICAMENTO,NOMCOMERCIAL,PRECIO
FROM MEDICAMENTOS
WHERE NOMCOMERCIAL LIKE '%OL';
-- 35. Crea una consulta que muestre el nombre comercial, principio activo y laboratorio de los
-- medicamentos fabricados por un laboratorio que empieza por B y cuyo precio es menor de 5€.
SELECT NOMCOMERCIAL,PRINCIPIO,LABORATORIO
FROM MEDICAMENTOS
WHERE LABORATORIO LIKE 'B%' 
AND PRECIO<5;
-- 36. Crea una consulta que muestre el ID_medicamento y principio activo de los medicamentos
-- fabricados por un laboratorio que empieza por E y cuyo principio activo contiene una X.
SELECT ID_MEDICAMENTO,PRINCIPIO
FROM MEDICAMENTOS
WHERE LABORATORIO LIKE 'E%' 
AND PRINCIPIO LIKE '%X%';

-- ========================5-CONSULTAS III==============================

-- 37. Crear una consulta que obtenga el número total de clientes.
SELECT COUNT(ID_CLIENTE) AS NUMERO_DE_CLIENTES
FROM CLIENTES;
-- 38. Crear una consulta que obtenga el número de medicamentos por cada laboratorio.
SELECT LABORATORIO,COUNT(ID_MEDICAMENTO) AS MEDICAMENTOS
FROM MEDICAMENTOS
GROUP BY LABORATORIO;
-- 39. Crear una consulta que cuente el número de ventas realizadas en el año 2010.
SELECT COUNT(ID_VENTAS) AS VENTAS_2010
FROM VENTAS
WHERE YEAR(FECHA_VENTAS) = '2010';
-- 40. Crear una consulta que cuente el número de clientes que han comprado el medicamento 1
SELECT COUNT(ID_CLIENTE) AS NUM_CLIENTES_MEDICAMENTO1
FROM VENTAS
WHERE ID_MEDICAMENTO = 1;

-- ========================CONSULTAS FINALES IV==============================
-- 1.Crea una consulta que muestre el Nombre del cliente y su dirección para todos los que realizaron una compra el día 2009-01-04.
SELECT C.NOMBRE,C.DIRECCION,V.FECHA_VENTAS
FROM CLIENTES AS C INNER JOIN VENTAS AS V
ON C.ID_CLIENTE=V.ID_CLIENTE
WHERE V.FECHA_VENTAS='2009-01-04';

-- -- 2. Crea una consulta que muestre el nombre, ape1, ape2 y sueldo de los empleados que atendieron ventas el mes de Enero.
SELECT E.NOMBRE,E.APE1,E.APE2,E.SUELDO,V.FECHA_VENTAS
FROM EMPLEADOS AS E INNER JOIN VENTAS AS V
ON E.ID_EMPLEADO = V.ID_EMPLEADO
WHERE month(V.FECHA_VENTAS)='01';

-- 3 Crea una consulta que muestre el nombre comercial, principio activo y laboratorio de los medicamentos fabricados por el laboratorio EUROPHARM y de precio SUPERIOR a 5 € vendidos en el mes de marzo
SELECT M.NOMCOMERCIAL AS NOMBRE_COMERCIAL, M.PRINCIPIO,M.LABORATORIO,M.PRECIO,V.FECHA_VENTAS
FROM MEDICAMENTOS AS M INNER JOIN VENTAS AS V
ON M.ID_MEDICAMENTO=V.ID_MEDICAMENTO
WHERE M.PRECIO>5
AND M.LABORATORIO = 'EUROPHARM'
AND MONTH(V.FECHA_VENTAS)='03';

-- 4. Crea una consulta que muestre el nombre comercial, principio activo y laboratorio de los medicamentos fabricados en el laboratorio EUROPHARM y que fueron vendidos por el empleado MARIA GARCIA .
SELECT M.NOMCOMERCIAL AS NOMBRE_COMERCIAL,M.PRINCIPIO,M.LABORATORIO,E.NOMBRE AS VENDEDOR
FROM MEDICAMENTOS AS M INNER JOIN VENTAS AS V
ON M.ID_MEDICAMENTO=V.ID_MEDICAMENTO
INNER JOIN EMPLEADOS AS E
ON  V.ID_EMPLEADO = E.ID_EMPLEADO
WHERE M.LABORATORIO = 'EUROPHARM'
AND E.NOMBRE='MARIA'
AND E.APE1 = 'GARCIA';

-- 5. Crea una consulta que muestre el NOMBRE Y PRIMER APELLIDO DEL CLIENTE, nombre comercial, principio activo y laboratorio de los medicamentos fabricados por el laboratorio EUROPHARM y de precio SUPERIOR a 5 €
SELECT C.NOMBRE, C.APE1, M.NOMCOMERCIAL, M.PRINCIPIO, M.LABORATORIO
FROM CLIENTES AS C INNER JOIN VENTAS AS V
ON C.ID_CLIENTE=V.ID_CLIENTE
INNER JOIN MEDICAMENTOS AS M
ON M.ID_MEDICAMENTO = V.ID_MEDICAMENTO
WHERE M.LABORATORIO = 'EUROPHARM'
AND M.PRECIO>5;

-- 6. Crea una consulta que muestre el NOMBRE Y PRIMER APELLIDO DEL CLIENTE, nombre comercial, principio activo y laboratorio de los medicamentos fabricados por el laboratorio EUROPHARM y el nombre y primer apellido del empleado que atendió esa venta.
SELECT C.NOMBRE,C.APE1,M.NOMCOMERCIAL,M.PRINCIPIO,M.LABORATORIO,E.NOMBRE AS NOMBRE_EMPLEADO, E.APE1 AS APELLIDO_EMPLEADO
FROM CLIENTES AS C INNER JOIN VENTAS AS V
ON C.ID_CLIENTE=V.ID_CLIENTE
INNER JOIN MEDICAMENTOS AS M
ON M.ID_MEDICAMENTO = V.ID_MEDICAMENTO
INNER JOIN EMPLEADOS AS E
ON E.ID_EMPLEADO= V.ID_EMPLEADO
WHERE M.LABORATORIO = 'EUROPHARM';

-- 7. Crea una consulta que muestre el nombre del medicamento y el nombre del empleado de las ventas realizadas entre 2 fechas determinadas, por ejemplo entre '2009-01-21' y '2009-03-03'.
SELECT M.NOMCOMERCIAL AS NOMBRE,E.NOMBRE AS VENDEDOR,V.FECHA_VENTAS
FROM MEDICAMENTOS AS M INNER JOIN VENTAS AS V
ON M.ID_MEDICAMENTO=V.ID_MEDICAMENTO
INNER JOIN EMPLEADOS AS E
ON  V.ID_EMPLEADO = E.ID_EMPLEADO
WHERE V.FECHA_VENTAS IN('2009-01-21','2009-03-03');

-- 8. Crea una consulta que muestre el nombre comercial de los medicamentos que empiezan por A, que valen menos de 3€,NO fabricados por el laboratorio AZQUE y comprados por el cliente CARMEN DÍAZ.
SELECT M.NOMCOMERCIAL AS MEDICAMENTO,M.PRECIO,M.LABORATORIO,concat(C.NOMBRE," ",C.APE1) AS CLIENTE
FROM CLIENTES AS C INNER JOIN VENTAS AS V
ON C.ID_CLIENTE=V.ID_CLIENTE
INNER JOIN MEDICAMENTOS AS M
ON M.ID_MEDICAMENTO = V.ID_MEDICAMENTO
WHERE M.NOMCOMERCIAL LIKE 'A%'
AND M.LABORATORIO != 'AZQUE'
AND concat(C.NOMBRE," ",C.APE1) = "CARMEN DÍAZ";

-- 9 Crear una consulta que muestre el nombre del MEDICAMENTO y el número de veces que se ha comprado cada medicamento. Ordenar la consulta por el campo Nombre MEDICAMENTO.
SELECT M.NOMCOMERCIAL AS MEDICAMENTO, COUNT(V.ID_MEDICAMENTO) AS VECES_COMPRADO
FROM MEDICAMENTOS AS M INNER JOIN VENTAS AS V
ON M.ID_MEDICAMENTO = V.ID_MEDICAMENTO
GROUP BY MEDICAMENTO;

-- 10 Crear una consulta que muestre el nombre del CLIENTE y el número de veces que ha comprado. Ordenar la consulta por el campo nombre del cliente.
SELECT C.NOMBRE AS CLIENTE, COUNT(V.ID_CLIENTE) AS COMPRAS
FROM CLIENTES AS C INNER JOIN VENTAS AS V
ON C.ID_CLIENTE = V.ID_CLIENTE
GROUP BY CLIENTE
ORDER BY CLIENTE;

-- 11 Crear una consulta que muestre el nombre del CLIENTE, el nombre del medicamento y el número de veces que cada cliente compró cada medicamento.Deben salir todos los clientes aunque no hayan comprado ninguna vez el medicamento. Ordenar la consulta por el campo ID_MEDICAMENTO.  

SELECT C.NOMBRE AS CLIENTE,M.NOMCOMERCIAL AS MEDICAMENTO, COUNT(V.ID_MEDICAMENTO) AS COMPRAS
FROM CLIENTES AS C LEFT JOIN VENTAS AS V
ON C.ID_CLIENTE = V.ID_CLIENTE
INNER JOIN MEDICAMENTOS AS M
ON M.ID_MEDICAMENTO = V.ID_MEDICAMENTO
GROUP BY CLIENTE,MEDICAMENTO
ORDER BY MEDICAMENTO;

-- 12. Crear una consulta que muestre el número de ventas realizadas por cada empleado y el nombre del empleado en el año 2010.
SELECT E.NOMBRE AS EMPLEADO,COUNT(V.ID_EMPLEADO) AS VENTAS
FROM EMPLEADOS AS E INNER JOIN VENTAS AS V
ON E.ID_EMPLEADO = V.ID_EMPLEADO
WHERE YEAR(V.FECHA_VENTAS) = '2010'
GROUP BY EMPLEADO;

-- 13. Crear una consulta que muestre el número de ventas realizadas por cada empleado y el nombre del empleado en el año 2010. Mostrar sólo filas en las que haya más de 1 venta.
SELECT E.NOMBRE AS EMPLEADO,COUNT(V.ID_EMPLEADO) AS VENTAS
FROM EMPLEADOS AS E INNER JOIN VENTAS AS V
ON E.ID_EMPLEADO = V.ID_EMPLEADO
WHERE YEAR(V.FECHA_VENTAS) = '2010'
GROUP BY EMPLEADO
HAVING VENTAS > 1;