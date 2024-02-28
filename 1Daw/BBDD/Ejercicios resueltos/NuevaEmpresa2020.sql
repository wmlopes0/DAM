-- --------------------------------------------------------------------------------
-- BASE DE DATOS NUEVAEMPRESA2020
-- 1º DAW
-- 14-03-2023
-- WALTER MARTIN LOPES
-- ------------------------------------------------------------------------------
-- CREACIÓN DE LA BBDD NUEVAEMPRESA2020
CREATE DATABASE NUEVAEMPRESA2020;
USE NUEVAEMPRESA2020;
-- ------------------
-- CREACIÓN DE TABLAS
-- ------------------

-- CREACIÓN DE LA TABLA CLIENTES
CREATE TABLE CLIENTES
(
CLIENTE_NO INT PRIMARY KEY,
NOMBRE VARCHAR(25),
LOCALIDAD VARCHAR(14),
VENDEDOR_NO INT,
DEBE DECIMAL(9,2),
HABER DECIMAL(9,2),
LIMITE_CREDITO DECIMAL(9,2)
);

-- CREACIÓN DE LA TABLA PEDIDOS
CREATE TABLE PEDIDOS
(
PEDIDO_NO INT PRIMARY KEY,
PRODUCTO_NO INT,
CLIENTE_NO INT,
UNIDADES INT,
FECHA_PEDIDO DATE
);

-- CREACIÓN DE LA TABLA EMPLEADOS
CREATE TABLE EMPLEADOS
(
EMP_NO INT PRIMARY KEY,
APELLIDO VARCHAR(8),
OFICIO VARCHAR(10),
DIRECTOR INT,
FECHA_ALTA DATE,
SALARIO DECIMAL(6,2),
COMISION DECIMAL(6,2),
DEP_NO INT
);

-- CREACIÓN DE LA TABLA PRODUCTOS
CREATE TABLE PRODUCTOS
(
PRODUCTO_NO INT PRIMARY KEY,
DESCRIPCION VARCHAR(30),
PRECIO_ACTUAL DECIMAL(8,2),
STOCK_DISPONIBLE INT
);

-- CREACIÓN DE LA TABLA DEPARTAMENTOS
CREATE TABLE DEPARTAMENTOS
(
DEP_NO INT PRIMARY KEY,
DNOMBRE VARCHAR(14),
LOCALIDAD VARCHAR(10)
);

-- ------------------------------
-- MODIFICACIONES Y RESTRICCIONES
-- ------------------------------
-- TABLA CLIENTES
ALTER TABLE CLIENTES
ADD CONSTRAINT VEN_FK FOREIGN KEY (VENDEDOR_NO) REFERENCES EMPLEADOS (EMP_NO)
ON DELETE CASCADE ON UPDATE CASCADE;

-- TABLA PEDIDOS
ALTER TABLE PEDIDOS
ADD CONSTRAINT CLI_FK FOREIGN KEY (CLIENTE_NO) REFERENCES CLIENTES (CLIENTE_NO)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE PEDIDOS
ADD CONSTRAINT PRO_FK FOREIGN KEY (PRODUCTO_NO) REFERENCES PRODUCTOS (PRODUCTO_NO)
ON DELETE CASCADE ON UPDATE CASCADE;

-- TABLA EMPLEADOS
ALTER TABLE EMPLEADOS
ADD CONSTRAINT DEP_FK FOREIGN KEY (DEP_NO) REFERENCES DEPARTAMENTOS (DEP_NO)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE EMPLEADOS
ADD CONSTRAINT DIR_FK FOREIGN KEY (DIRECTOR) REFERENCES EMPLEADOS (EMP_NO)
ON DELETE CASCADE ON UPDATE CASCADE;

-- ---------------------------
-- INSERTO DATOS EN LAS TABLAS
-- ---------------------------
-- TABLA EMPLEADOS
INSERT INTO DEPARTAMENTOS VALUES
(10, 'CONTABILIDAD','BARCELONA'),
(20, 'INVESTIGACION','VALENCIA'),
(30, 'VENTAS', 'MADRID'), 
(40, 'PRODUCCION', 'SEVILLA');

-- TABLA EMPLEADOS
INSERT INTO EMPLEADOS VALUES
(7839,'REY', 'PRESIDENTE', NULL, '1981-11-17',6000, NULL, 10),
(7698,'GARRIDO', 'DIRECTOR', 7839,'1981-05-01',3850.12, NULL, 30),
(7782,'MARTINEZ','DIRECTOR', 7839,'1981-06-09',2450, NULL, 10),
(7499,'ALONSO', 'VENDEDOR', 7698,'1981-02-23',1400, 400,30),
(7521,'LOPEZ', 'EMPLEADO', 7782,'1981-05-08',1350.50, NULL,10),      
(7654,'MARTIN', 'VENDEDOR', 7698,'1981-09-28',1500, 1600, 30),
(7844,'CALVO', 'VENDEDOR', 7698,'1981-09-08',1800, 0, 30),
(7876,'GIL', 'ANALISTA', 7782,'1982-05-06',3350, NULL, 20),
(7900,'JIMENEZ', 'EMPLEADO', 7782,'1983-03-24',1400, NULL, 20);	

-- TABLA CLIENTES
INSERT INTO CLIENTES VALUES
(101, 'DISTRIBUCIONES GOMEZ', 'MADRID', 7499, 0,0,5000),
(102, 'LOGITRONICA S.L', 'BARCELONA', 7654,0,0,5000),
(103, 'INDUSTRIAS LACTEAS S.A.', 'LAS ROZAS', 7844,0,0, 10000),
(104, 'TALLERES ESTESO S.A.', 'SEVILLA', 7654, 0, 0, 5000),
(105, 'EDICIONES SANZ', 'BARCELONA', 7499, 0,0,5000),
(106, 'SIGNOLOGIC S.A.', 'MADRID', 7654,0,0,5000),
(107, 'MARTIN Y ASOCIADOS S.L.', 'ARAVACA' , 7844,0,0, 10000),
(108, 'MANUFACTURAS ALI S.A.', 'SEVILLA', 7654, 0, 0, 5000);

-- TABLA PRODUCTOS
INSERT INTO PRODUCTOS VALUES
(10,'MESA DESPACHO MOD. GAVIOTA', 550, 50),
(20, 'SILLA DIRECTOR MOD. BUFALO', 670, 25),
(30, 'ARMARIO NOGAL DOS PUERTAS', 460, 20),
(40, 'MESA MODELO UNIÓN',340, 15),
(50, 'ARCHIVADOR CEREZO',1050, 20),
(60, 'CAJA SEGURIDAD MOD B222', 280, 15),
(70, 'DESTRUCTORA DE PAPEL A3',450, 25),
(80, 'MODULO ORDENADOR MOD. ERGOS', 550, 25);

-- TABLA PEDIDOS
INSERT INTO PEDIDOS VALUES
(1000, 20, 103, 3, '1999-10-06'),
(1001, 50, 106, 2, '1999-10-06'),
(1002, 10, 101, 4, '1999-10-07'),
(1003, 20, 105, 4, '1999-10-16'),
(1004, 40, 106, 8, '1999-10-20'),
(1005, 30, 105, 2, '1999-10-20'),
(1006, 70, 103, 3, '1999-11-03'),
(1007, 50, 101, 2, '1999-11-06'),
(1008, 10, 106, 6, '1999-11-16'),
(1009, 20, 105, 2, '1999-11-26'),
(1010, 40, 102, 3, '1999-12-08'),
(1011, 30, 106, 2, '1999-12-15'),
(1012, 10, 105, 3, '1999-12-06'),
(1013, 30, 106, 2, '1999-12-06'),
(1014, 20, 101, 4, '2000-01-07'),
(1015, 70, 105, 4, '2000-01-16'),
(1017, 20, 105, 6, '2000-01-20');

-- ---------
-- SUBCONSULTAS
-- ---------
-- 1. Listar los nombres y códigos de los departamentos en los que haya empleados
SELECT DEP_NO, DNOMBRE FROM DEPARTAMENTOS
WHERE DEP_NO IN (SELECT DISTINCT DEP_NO FROM EMPLEADOS);

-- 2. Obtener los datos de las facturas con la fecha de factura más reciente.
-- CONSULTA
SELECT * FROM PEDIDOS
ORDER BY FECHA_PEDIDO DESC
LIMIT 1;

-- SUBCONSULTA
SELECT * FROM PEDIDOS
WHERE FECHA_PEDIDO = (SELECT MAX(FECHA_PEDIDO) FROM PEDIDOS);

-- 3. Mostrar para cada puesto del departamento de VENTAS la suma de los salarios de sus empleados.
-- CONSULTA
SELECT EMPLEADOS.OFICIO, SUM(EMPLEADOS.SALARIO) FROM EMPLEADOS INNER JOIN DEPARTAMENTOS
ON EMPLEADOS.DEP_NO= DEPARTAMENTOS.DEP_NO
WHERE DNOMBRE = 'VENTAS'
GROUP BY EMPLEADOS.OFICIO;

-- SUBCONSULTA
SELECT OFICIO, SUM(SALARIO) FROM EMPLEADOS
WHERE DEP_NO = (SELECT DEP_NO FROM DEPARTAMENTOS
				WHERE DNOMBRE = 'VENTAS')
GROUP BY EMPLEADOS.OFICIO;

-- 4 Obtener los datos del producto con más unidades vendidas.
SELECT * FROM PRODUCTOS
WHERE PRODUCTO_NO = (SELECT PRODUCTO_NO FROM PEDIDOS
					GROUP BY PRODUCTO_NO
                    HAVING SUM(UNIDADES) = (SELECT SUM(UNIDADES) FROM PEDIDOS
										GROUP BY PRODUCTO_NO
										ORDER BY 1 DESC
                                        LIMIT 1));

-- 5 Mostrar los datos de los pedidos correspondientes al pedido que tenga la mayor cantidad de unidades del mismo producto. (agrupa por productos)
SELECT
    PEDIDO_NO,PRODUCTO_NO,CLIENTE_NO,UNIDADES,FECHA_PEDIDO
FROM PEDIDOS P1
WHERE UNIDADES = (
        SELECT MAX(UNIDADES)
        FROM PEDIDOS P2
        WHERE P1.PRODUCTO_NO = P2.PRODUCTO_NO);


-- 6 Mostrar el número y nombre de cliente de los clientes atendidos por el empleado que se apellida 'CALVO'.
SELECT CLIENTE_NO, NOMBRE FROM CLIENTES
WHERE VENDEDOR_NO = (SELECT EMP_NO FROM EMPLEADOS
					WHERE APELLIDO= 'CALVO');

-- 7 Mostrar los números de pedido, números de producto y fecha de los pedidos realizados por el cliente 'EDICIONES SANZ'.
SELECT PEDIDO_NO,PRODUCTO_NO,FECHA_PEDIDO
FROM PEDIDOS
WHERE CLIENTE_NO = (SELECT CLIENTE_NO
					FROM CLIENTES
                    WHERE NOMBRE='EDICIONES SANZ');

-- 8. Mostrar los datos del producto más caro
SELECT
    PRODUCTO_NO,DESCRIPCION,PRECIO_ACTUAL,STOCK_DISPONIBLE
FROM PRODUCTOS
WHERE PRECIO_ACTUAL = (
        SELECT MAX(PRECIO_ACTUAL)
        FROM PRODUCTOS);

-- 9. Mostrar los datos de los clientes que han hecho algún pedido de 'DESTRUCTORA DE PAPEL A3'.
SELECT C.CLIENTE_NO,C.NOMBRE,C.LOCALIDAD,C.VENDEDOR_NO,C.DEBE,C.HABER,C.LIMITE_CREDITO
FROM CLIENTES C
INNER JOIN PEDIDOS P 
ON C.CLIENTE_NO = P.CLIENTE_NO
INNER JOIN PRODUCTOS PR 
ON P.PRODUCTO_NO = PR.PRODUCTO_NO
WHERE PR.DESCRIPCION = 'DESTRUCTORA DE PAPEL A3';

-- 10. Mostrar los datos de los empleados que han vendido a más de dos clientes.
SELECT E.EMP_NO,E.APELLIDO,E.OFICIO,E.DIRECTOR,E.FECHA_ALTA,E.SALARIO,E.COMISION,E.DEP_NO
FROM EMPLEADOS E
WHERE
    (	SELECT COUNT(DISTINCT CLIENTE_NO)
        FROM CLIENTES
        WHERE VENDEDOR_NO = E.EMP_NO) > 2;
        
-- 11. Mostrar los apellidos y oficios de los empleados del departamento 10 cuyo oficio sea el mismo que el de algún empleado del departamento de VENTAS.
SELECT E1.APELLIDO,E1.OFICIO
FROM EMPLEADOS E1
WHERE E1.DEP_NO = 10
AND E1.OFICIO IN 
		(SELECT E2.OFICIO FROM EMPLEADOS E2
        WHERE E2.DEP_NO = 
				(SELECT DEP_NO
                FROM DEPARTAMENTOS
                WHERE DNOMBRE = 'VENTAS'));
                
-- 12. Mostrar el nombre del departamento en el que trabaja el empleado con mayor salario.
SELECT D.DNOMBRE
FROM DEPARTAMENTOS D
WHERE D.DEP_NO = (
        SELECT E.DEP_NO
        FROM EMPLEADOS E
        WHERE E.SALARIO = (
                SELECT MAX(SALARIO)
                FROM EMPLEADOS));

-- 14. Mostrar los datos de los empleados cuyo salario sea menor a la media de los salarios de su departamento.
SELECT E1.EMP_NO,E1.APELLIDO,E1.OFICIO,E1.DIRECTOR,E1.FECHA_ALTA,E1.SALARIO,E1.COMISION,E1.DEP_NO
FROM EMPLEADOS E1
WHERE E1.SALARIO < (
        SELECT AVG(E2.SALARIO)
        FROM EMPLEADOS E2
        WHERE E1.DEP_NO = E2.DEP_NO);

-- 15. Mostrar los nombres y las localidades de los clientes que hayan hecho pedidos.
SELECT DISTINCT C.NOMBRE, C.LOCALIDAD
FROM CLIENTES C
INNER JOIN PEDIDOS P 
ON C.CLIENTE_NO = P.CLIENTE_NO;



















