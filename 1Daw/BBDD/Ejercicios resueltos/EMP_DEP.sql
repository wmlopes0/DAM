-- BASE DE DATOS EMP_DEP
-- 1º DAW
-- 26-02-2023
-- WALTER MARTIN LOPES
-- -------------------------------------------------------
CREATE DATABASE EMP_DEP;
USE EMP_DEP;
-- =====================CREACIÓN DE TABLAS=====================
CREATE TABLE EMPLEADOS(
DNI VARCHAR(8) PRIMARY KEY,
NOMBRE VARCHAR(30),
APELLIDOS VARCHAR(30),
DEPARTAMENTO INT);

CREATE TABLE DEPARTAMENTOS(
COD_DPTO INT PRIMARY KEY,
NOMBRE VARCHAR(30),
PRESUPUESTO INT);

ALTER TABLE EMPLEADOS
ADD CONSTRAINT DEP_FK FOREIGN KEY (DEPARTAMENTO) REFERENCES DEPARTAMENTOS(COD_DPTO) ON DELETE CASCADE ON UPDATE CASCADE;

DESC EMPLEADOS;
DESC DEPARTAMENTOS;
-- =====================INSERTAR DATOS=====================
-- INSERTAR EN DEPARTAMENTO.
INSERT INTO DEPARTAMENTOS
VALUES (10,'CONTABILIDAD',50000),
	(20,'INVESTIGACION',100000),
	(30,'VENTAS',25000),
	(40,'PRODUCCION',150000);

-- INSERTAR EN EMPLEADOS.

INSERT INTO EMPLEADOS 
VALUES ('10000000','ANTONIO','ALVAREZ',10),
	('20000000','MARIA','ANTUNEZ',10),
	('30000000','LUIS','ALVAREZ',20),
	('40000000','JOSE','MARTIN',20),
	('50000000','ANA','MARTINEZ',30),
	('60000000','DIEGO','LOPEZ',30),
	('70000000','MARINA','PEREZ',10),
	('80000000','JESUS','PEREZ',40),
	('90000000','MARTIN','VARGAS',20),
	('01000000','ALEJANDRA','ALVAREZ',10);

-- =====================CONSULTAS=====================
-- 1. Obtener los APELLIDOS de los empleados.
SELECT APELLIDOS FROM EMPLEADOS;
-- 2. Obtener los APELLIDOS de los empleados sin repeticiones.
SELECT DISTINCT APELLIDOS FROM EMPLEADOS;
-- 3. Obtener todos los datos de los empleados que se apellidan ‘López’
SELECT * FROM EMPLEADOS 
WHERE APELLIDOS="López";
-- 4. Obtener todos los datos de los empleados que se apellidan ‘López’ y de los que se apellidan ‘Pérez’ (hacerlo primero con OR y después con IN).
SELECT * FROM EMPLEADOS 
WHERE APELLIDOS="López"
OR APELLIDOS="Pérez";

SELECT * FROM EMPLEADOS 
WHERE APELLIDOS IN("López","Pérez");
-- 5. Obtener todos los datos de los empleados que trabajan en el departamento 20.
SELECT * FROM EMPLEADOS 
WHERE DEPARTAMENTO = 20;
-- 6. Obtener todos los datos de los empleados que trabajan en el departamento 20 y en el departamento 30 (hacerlo primero con OR y después con IN).
SELECT * FROM EMPLEADOS 
WHERE DEPARTAMENTO = 20
OR DEPARTAMENTO = 30;

SELECT * FROM EMPLEADOS 
WHERE DEPARTAMENTO IN(20,30);
-- 7. Obtener todos los datos de los empleados cuyo apellido empieza por ‘C’.
SELECT * FROM EMPLEADOS
WHERE APELLIDOS LIKE 'C%';
-- 8. Obtener el presupuesto total de todos los departamentos.
SELECT SUM(PRESUPUESTO) AS PRESUPUESTO_TOTAL FROM DEPARTAMENTOS;
-- 9. Obtener el número total de empleados de cada departamento.
SELECT COUNT(DNI) AS NUMERO_EMPLEADOS, DEPARTAMENTO FROM EMPLEADOS
GROUP BY DEPARTAMENTO;
-- 10. Obtener un listado completo de empleados, incluyendo además de sus datos, los de su departamento.
SELECT E.*, D.NOMBRE, D.PRESUPUESTO 
FROM EMPLEADOS AS E INNER JOIN DEPARTAMENTOS AS D
ON E.DEPARTAMENTO = D.COD_DPTO;
-- 11. Obtener los nombres y apellidos de los empleados que trabajan en departamentos cuyo presupuesto sea mayor de 60000.
SELECT E.NOMBRE, E.APELLIDOS, D.NOMBRE AS DEPARTAMENTO,D.PRESUPUESTO
FROM EMPLEADOS AS E INNER JOIN DEPARTAMENTOS AS D
ON E.DEPARTAMENTO = D.COD_DPTO
WHERE D.PRESUPUESTO>60000;
-- 12. Obtener los nombres (sólo los nombres) de los departamentos que tienen más de 3 empleados.
SELECT COUNT(E.DNI) AS NUMERO_EMPLEADOS, D.NOMBRE
FROM EMPLEADOS AS E INNER JOIN DEPARTAMENTOS AS D
ON E.DEPARTAMENTO = D.COD_DPTO
GROUP BY D.NOMBRE
HAVING NUMERO_EMPLEADOS>3;
-- 13. Añadir un nuevo departamento “CALIDAD”, código 50 y con presupuesto de 50000. Añadir un nuevo empleado que trabaje en este departamento, ESTHER FLORES, DNI: 00011122.
INSERT INTO DEPARTAMENTOS VALUES (50,"CALIDAD",50000);
INSERT INTO EMPLEADOS VALUES ('00011122','ESTHER','FLORES',50);
-- 14. Recortar el presupuesto de todos los departamentos un 15%.
UPDATE DEPARTAMENTOS
SET PRESUPUESTO = PRESUPUESTO*0.85;
-- 15. Reasignar a los empleados del departamento con código 10 (departamento de CONTABILIDAD) al de VENTAS (código 30).
UPDATE EMPLEADOS
SET DEPARTAMENTO = 30
WHERE DEPARTAMENTO = 10;
-- 16. Despedir a todos los empleados del departamento de VENTAS (código 30)
DELETE FROM EMPLEADOS
WHERE DEPARTAMENTO = 30;
-- 17. Despedir a todos los empleados que trabajen para un departamento con presupuesto mayor a 60000.
DELETE E 
FROM EMPLEADOS AS EINNER JOIN DEPARTAMENTOS AS D
ON E.DEPARTAMENTO = D.COD_DPTO
WHERE D.PRESUPUESTO>60000;
-- 18. Despedir a todos los empleados de la empresa
DELETE FROM EMPLEADOS;