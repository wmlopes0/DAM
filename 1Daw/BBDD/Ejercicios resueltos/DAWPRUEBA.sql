SHOW DATABASES;

USE DawPrueba;

create table empleados
(
Cod_Emp int primary key,
Nombre varchar(20) not null
);

alter table empleados
rename to emp;

show tables;

alter table emp
add apellidos varchar(50);

desc emp;

alter table emp
add DNI char(9) after cod_emp;

alter table emp
modify DNI char(9) 
after apellidos;

alter table emp
drop column apellidos;

alter table emp
modify Nombre varchar(5) not null;

alter table emp
rename column DNI to DNI_EMP;

create table empleados
(
Cod_Emp int unsigned auto_increment primary key,
Nombre varchar(20) not null
);

insert into empleados (Nombre) values
('Walter'),
('Nacho');

desc empleados;

alter table empleados
add CodPostal char(5) default '10600';


select * from empleados;

insert into empleados (Nombre) values
('Pepe'),
('Nachete');

CREATE TABLE ARTICULO
(
COD_ARTICULO INT NOT NULL,
NOMBRE VARCHAR(25) NOT NULL,
PRECIO DECIMAL (10,2) DEFAULT '3.5' NOT NULL
);

drop table empleados;

CREATE TABLE EMPLEADOS
(
COD_EMP VARCHAR(9) NOT NULL,
DNI VARCHAR(9) NOT NULL,
CONSTRAINT EMP_UK UNIQUE(DNI)
);

ALTER TABLE EMPLEADOS
ADD CONSTRAINT EMP_PK PRIMARY KEY(COD_EMP);

CREATE TABLE VENTAS
 (
COD_CLIENTE INT NOT NULL PRIMARY KEY,
COD_PRODUCTO INT NOT NULL PRIMARY KEY
-- CONSTRAINT VEN_PK PRIMARY KEY (COD_CLIENTE,COD_PRODUCTO)
);

desc empleados;
