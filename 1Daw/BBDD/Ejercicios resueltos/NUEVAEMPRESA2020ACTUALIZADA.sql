-- --------------------------------------------------------------------------------
-- BASE DE DATOS NUEVAEMPRESA2020Actualizada
-- 1º DAW
-- 14-03-2023
-- WALTER MARTIN LOPES
-- ------------------------------------------------------------------------------
USE NUEVAEMPRESA2020;

-- CONSULTAS PARA MODIFICAR TABLAS USANDO SUBCONSULTAS | CREACIÓN DE VISTAS.

-- 1. Añadir 100 euros de comisión a los empleados tengan una comisión menor de 500 euros o nula
UPDATE EMPLEADOS
SET COMISION = COALESCE(COMISION,0) + 100
WHERE EMP_NO IN (SELECT * FROM(SELECT EMP_NO FROM EMPLEADOS
				 WHERE COMISION < 500 
				 OR COMISION IS NULL) AS TABLA);

-- 2. Borrar al empleado MARTINEZ . Antes, en otra sentencia aparte ejecutada anteriormente modifica aquellos empleados de los que sea jefe MARTINEZ poniéndoles como jefe a GARRIDO (CODIGO 7698). (UTILIZA SUBCONSULTA PARA AVERIGUAR EL CODIGO DE MARTINEZ ). Después, borra a Martínez.
UPDATE EMPLEADOS
SET DIRECTOR = 7698
WHERE DIRECTOR = (SELECT * FROM (SELECT EMP_NO FROM EMPLEADOS
				  WHERE APELLIDO = 'MARTINEZ')AS TABLA);
                  
DELETE FROM EMPLEADOS
WHERE APELLIDO = 'MARTINEZ';

-- 3. Crear la vista EMPLEADOS_GARRIDO que incluirá los datos: empleado_no, apellido, salario_anual de los empleados cuyo jefe es GARRIDO (salario_anual se calcula multiplicando salario * 14)
CREATE VIEW EMPLEADOS_GARRIDO AS
SELECT EMP_NO,APELLIDO,(SALARIO*14) AS SALARIO_ANUAL FROM EMPLEADOS
WHERE DIRECTOR = (SELECT EMP_NO FROM EMPLEADOS WHERE APELLIDO ='GARRIDO');

SELECT * FROM EMPLEADOS_GARRIDO;

-- 4. Crear una vista RESUMEN_DEP de los departamentos, incluyendo todos los departamentos hasta los que no tengan ningún empleado. La información que se pide es:
-- · Nombre del departamento--> esta columna se llamará departamento
-- · Número de empleados-->esta columna se llamará empleados
-- · Suma de sus salario-->esta columna se llamará salarios
-- · Suma de sus comisiones-->esta columna se llamará comisiones
CREATE VIEW RESUMEN_DEP
AS
SELECT
D.DNOMBRE AS DEPARTAMENTO,
(SELECT COUNT(*) FROM EMPLEADOS AS E WHERE E.DEP_NO = D.DEP_NO) AS EMPLEADOS,
(SELECT SUM(SALARIO) FROM EMPLEADOS AS E WHERE E.DEP_NO = D.DEP_NO) AS SALARIOS,
(SELECT SUM(COMISION) FROM EMPLEADOS AS E WHERE E.DEP_NO = D.DEP_NO) AS COMISIONES
FROM DEPARTAMENTOS AS D;

SELECT * FROM RESUMEN_DEP;
SELECT * FROM DEPARTAMENTOS;

-- 5. Crear una vista CLIENTES_2 con las columnas: cliente_no, nombre_cliente y localidad_cliente.
CREATE VIEW CLIENTES_2
AS
SELECT CLIENTE_NO, NOMBRE, LOCALIDAD FROM CLIENTES;

SELECT * FROM CLIENTES_2;

-- 6. Hacer una vista de las filas de la vista clientes_2, solo con los empleados de SEVILLA.
CREATE VIEW CLIENTES_2_SEVILLA
AS
SELECT * FROM CLIENTES_2
WHERE LOCALIDAD = 'SEVILLA';

SELECT * FROM CLIENTES_2_SEVILLA;

-- 7. Crear una vista empleados_sin_comision con las columnas emp_no y apellido de la tabla empleados y dnombre de la tabla departamentos, con los empleados que no tengan comisión. Posteriormente hacer un listado de todas sus filas
CREATE VIEW EMPLEADOS_SIN_COMISION_BUENA AS
SELECT E.EMP_NO, E.APELLIDO, D.DNOMBRE 
FROM EMPLEADOS AS E INNER JOIN DEPARTAMENTOS AS D
ON E.DEP_NO = D.DEP_NO
WHERE E.COMISION = 0;

SELECT * FROM EMPLEADOS_SIN_COMISION_BUENA;

-- 8. Rebajar un 10 % el precio de todos los productos de los que no se ha vendido nada.
UPDATE PRODUCTOS AS P
SET PRECIO_ACTUAL = PRECIO_ACTUAL * 0.9
WHERE NOT EXISTS (SELECT DISTINCT PE.PRODUCTO_NO FROM PEDIDOS AS PE WHERE P.PRODUCTO_NO = PE.PRODUCTO_NO);

-- 9. Añadir 50 euros al salario de los empleados de la empresa que pertenezcan a departamentos de MADRID y BARCELONA
UPDATE EMPLEADOS
SET SALARIO = SALARIO + 50
WHERE EMP_NO IN (SELECT * FROM(
				SELECT EMP_NO FROM EMPLEADOS WHERE DEP_NO 
				IN (SELECT DEP_NO FROM DEPARTAMENTOS WHERE LOCALIDAD IN('MADRID','BARCELONA'))) AS TABLA);

-- 10. Incrementar en un 10% el salario del empleado que atiende al cliente que ha hecho la compra de mayor número de unidades
UPDATE EMPLEADOS 
SET SALARIO = SALARIO * 1.1
WHERE EMP_NO = (SELECT VENDEDOR_NO FROM CLIENTES 
			   WHERE CLIENTE_NO = (SELECT CLIENTE_NO FROM PEDIDOS
								   WHERE UNIDADES = (SELECT MAX(UNIDADES) FROM PEDIDOS)));
                                   
-- 11. Crear la vista EMPLE_DEP20 que incluirá todos los datos de los empleados del departamento 20
CREATE VIEW EMPLE_DEP20
AS SELECT * FROM EMPLEADOS WHERE DEP_NO = 20;

SELECT * FROM EMPLE_DEP20;

-- 12. Crear la vista V_CLIENTES que contendrá el número de cliente, el nombre y la localidad de todos los clientes que han comprado alguna vez. Los campos de la vista serán CLIENTE, NOMBRE, LOCALIDAD
CREATE VIEW V_CLIENTES AS
SELECT CLIENTE_NO AS CLIENTE, NOMBRE,LOCALIDAD FROM CLIENTES 
WHERE CLIENTE_NO IN (SELECT CLIENTE_NO FROM PEDIDOS);

SELECT * FROM CLIENTES;
SELECT * FROM PEDIDOS;

SELECT * FROM V_CLIENTES;

-- 13. Crear la vista EMPLEADOS_DIRECTORES que incluirá los datos empleado_no, apellido y salario_anual de los empleados cuyo oficio es DIRECTOR, sabiendo que el salario anual es el salario multiplicado por 14. Una vez creada mostrar, y utilizando la vista, mostrar los empleados cuyo salario total supere los 50.000 euros.
CREATE VIEW EMPLEADOS_DIRECTORES AS
SELECT EMP_NO,APELLIDO,(SALARIO*14) AS SALARIO_ANUAL FROM EMPLEADOS
WHERE OFICIO = 'DIRECTOR';

SELECT * FROM EMPLEADOS_DIRECTORES;

SELECT * FROM EMPLEADOS_DIRECTORES
WHERE SALARIO_ANUAL > 50000;

-- 14. Crear la vista JEFES que incluirá los apellidos de todos los empleados de la empresa y el número de empleados que tienen directamente a su cargo (de los que es jefe según lo indicado en el campo dir). Deben mostrarse todos los empleados sean o no jefes

-- HAGO UN LEFT JOIN CON LA MISMA TABLA
SELECT E1.APELLIDO AS JEFE_APELLIDO, COUNT(E2.EMP_NO) AS NUM_EMPLEADOS
FROM EMPLEADOS AS E1
LEFT JOIN EMPLEADOS AS E2
ON E1.EMP_NO = E2.DIRECTOR
GROUP BY E1.EMP_NO, E1.APELLIDO;