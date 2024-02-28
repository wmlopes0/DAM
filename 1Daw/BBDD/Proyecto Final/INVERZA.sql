-- ************************************************************************************************************
--  ____                _____   ______     _____    ______     _____               _______    ____     _____  *
-- |  _ \      /\      / ____| |  ____|   |  __ \  |  ____|   |  __ \      /\     |__   __|  / __ \   / ____| *
-- | |_) |    /  \    | (___   | |__      | |  | | | |__      | |  | |    /  \       | |    | |  | | | (___   *
-- |  _ <    / /\ \    \___ \  |  __|     | |  | | |  __|     | |  | |   / /\ \      | |    | |  | |  \___ \  *
-- | |_) |  / ____ \   ____) | | |____    | |__| | | |____    | |__| |  / ____ \     | |    | |__| |  ____) | *
-- |____/  /_/    \_\ |_____/__|______|  _|_____/__|______|   |_____/  /_/___ \_\    |_|     \____/  |_____/  *
--                                                                                                            *
--                       |_   _| | \ | | \ \    / / |  ____| |  __ \  |___  /     /\                          *
--                         | |   |  \| |  \ \  / /  | |__    | |__) |    / /     /  \                         *
--                         | |   | . ` |   \ \/ /   |  __|   |  _  /    / /     / /\ \                        *
--                        _| |_  | |\  |    \  /    | |____  | | \ \   / /__   / ____ \                       *
 --                      |_____| |_| \_|     \/     |______| |_|  \_\ /_____| /_/    \_\                      *
 -- 																										  * 
--                      .//////////////////////////////,													  *
--                          .//,                         .///////.											  *
--                          .//,                                .////*										  *
--                          .//,    ///////////////////////,        ///,									  *
--                          .//,    ///                  ./////.      ///,									  *
--                          .//,    //*                        ////     .///								  *
--                          .//,    ///                          ///     ///								  *
--                          .//,    ///                            ///     ///								  *
--                          .//,    ///                             ///    ,//								  *
--                          .//,    ///                             ,//,    ///								  *
--                          .//*    ///                             ,//,    ///								  *
--                           ///    *//.                            //     //						  		  *
--                           *//.    ///                           *//,    *//								  *
--                            ///     ///,                        ///,    //,							 	  *
--                             ///.    .///                    ///*     ///,								  *
--                              *///      *////,            *////,     ,///							       	  *
--                                ///       .///////////////       *///.									  *
--                                  ,////.                        ////										  *
--                                     ./////.              /////											  *
--                                          ,/////////////////*												  *
-- ************************************************************************************************************																								
-- ENTREGA FINAL
-- 1º DAW
-- 2022 - 2023
-- Walter Martín Lopes, Alberto Rivero Nuñez, Samuel Fernandez Diaz.
-- ******************************************************************************************

CREATE DATABASE INVERZA;
USE INVERZA;

-- ******************************************************************************************
--   _____                     _   __              _        _        _     _                *
--  / ____|                   (_) /_/             | |      | |      | |   | |               *
-- | |     _ __ ___  __ _  ___ _  ___  _ __     __| | ___  | |_ __ _| |__ | | __ _ ___      *
-- | |    | '__/ _ \/ _` |/ __| |/ _ \| '_ \   / _` |/ _ \ | __/ _` | '_ \| |/ _` / __|     *
-- | |____| | |  __/ (_| | (__| | (_) | | | | | (_| |  __/ | || (_| | |_) | | (_| \__ \     *
--  \_____|_|  \___|\__,_|\___|_|\___/|_| |_|  \__,_|\___|  \__\__,_|_.__/|_|\__,_|___/     *
-- ******************************************************************************************                                                                                     
                                                                                     
CREATE TABLE POBLACIONES (
    CP INT PRIMARY KEY,
    POBLACION VARCHAR(50));

CREATE TABLE SUCURSALES (
    ID_SUCURSAL INT PRIMARY KEY,
    DIRECCION VARCHAR(50),
    CP INT,
    EMAIL VARCHAR(50),
    FOREIGN KEY CP_SUC_FK (CP) REFERENCES POBLACIONES (CP) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE TELEFONOS (
    ID_SUCURSAL INT PRIMARY KEY,
    TELEFONO VARCHAR(12),
    FOREIGN KEY ID_SUCURSAL_TEL_FK (ID_SUCURSAL) REFERENCES SUCURSALES (ID_SUCURSAL) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE PERSONAS (
    DNI CHAR(9) PRIMARY KEY,
    ID_SUCURSAL INT,
    NOMBRE VARCHAR(50),
    DIRECCION VARCHAR(50),
    CP INT,
    TELEFONO VARCHAR(12),
    FOREIGN KEY ID_SUCURSAL_PER_FK (ID_SUCURSAL) REFERENCES SUCURSALES (ID_SUCURSAL) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY CP_PER_FK (CP) REFERENCES POBLACIONES (CP) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE PRESTAMOS (
    COD_PRESTAMO INT PRIMARY KEY,
    DNI CHAR(9),
    CUANTIA DECIMAL(10, 2),
    FECHA_EMISION DATE,
    PLAZO_ANOS INT,
    TIPO VARCHAR(50) CHECK (TIPO IN ('PERSONAL', 'HIPOTECA')),
    FOREIGN KEY DNI_PRE_FK (DNI) REFERENCES PERSONAS(DNI) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE PERSONAL (
    COD_PRESTAMO INT PRIMARY KEY,
    MOTIVO VARCHAR(50),
    TAE DECIMAL(5, 2),
	FOREIGN KEY COD_PRESTAMO_PER_FK (COD_PRESTAMO) REFERENCES PRESTAMOS (COD_PRESTAMO) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE HIPOTECA (
    COD_PRESTAMO INT PRIMARY KEY,
    TIPO_VIVIENDA VARCHAR(20),
    TIPO VARCHAR(50) CHECK (TIPO IN ('T_FIJO', 'T_VARIABLE')),
    FOREIGN KEY COD_PRESTAMO_HIP_FK (COD_PRESTAMO) REFERENCES PRESTAMOS (COD_PRESTAMO) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE T_FIJO (
    COD_PRESTAMO INT PRIMARY KEY,
    TAE DECIMAL(5, 2),
    FOREIGN KEY COD_PRESTAMO_FIJ_FK (COD_PRESTAMO) REFERENCES HIPOTECA (COD_PRESTAMO) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE T_VARIABLE (
    COD_PRESTAMO INT PRIMARY KEY,
    EURIBOR DECIMAL(5, 2),
    DIFERENCIAL_FIJO DECIMAL(5, 2),
    FOREIGN KEY COD_PRESTAMO_VAR_FK (COD_PRESTAMO) REFERENCES HIPOTECA (COD_PRESTAMO) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE EMPLEADOS (
    COD_EMPLEADO INT,
    DNI CHAR(9),
    COD_EMPLEADO_JEFE INT,
    NUM_EMPLEADOS_CARGO INT,
    SALARIO DECIMAL(10, 2),
    FECHA_INICIO_CONTRATO DATE,
    ANTIGUEDAD INT,
    CUALIFICACION VARCHAR(50),
    PRIMARY KEY (COD_EMPLEADO,DNI),
    FOREIGN KEY DNI_EMP_FK (DNI) REFERENCES PERSONAS(DNI) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY COD_EMPLEADO_JEFE_FK (COD_EMPLEADO_JEFE) REFERENCES EMPLEADOS(COD_EMPLEADO) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE CLIENTES (
    COD_CLIENTE INT,
    DNI CHAR(9),
    EDAD INT,
    E_MAIL VARCHAR(50),
    PRIMARY KEY (COD_CLIENTE, DNI),
    FOREIGN KEY DNI_CLI_FK (DNI) REFERENCES PERSONAS(DNI) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE CUENTAS_BANCARIAS (
    IBAN VARCHAR(34) PRIMARY KEY,
    COD_CLIENTE INT,
    DNI CHAR(9),
    FECHA_APERTURA DATE,
    TIPO VARCHAR(50) CHECK (TIPO IN ('CUENTA_CORRIENTE', 'CUENTA_AHORRO')),
    FOREIGN KEY COD_CLIENTE_CUE_FK (COD_CLIENTE) REFERENCES CLIENTES(COD_CLIENTE) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY DNI_BAN_FK (DNI) REFERENCES CLIENTES(DNI) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE PERTENECEN_CLI_CB (
    COD_CLIENTE INT,
    DNI CHAR(9),
    IBAN VARCHAR(34),
    PRIMARY KEY(COD_CLIENTE,DNI,IBAN),
    FOREIGN KEY COD_CLIENTE_PER_FK (COD_CLIENTE) REFERENCES CLIENTES(COD_CLIENTE) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY DNI_PER_FK (DNI) REFERENCES CLIENTES(DNI) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY IBAN_PER_FK (IBAN) REFERENCES CUENTAS_BANCARIAS(IBAN) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE TRANSACCION (
    COD_TRANSACCION INT PRIMARY KEY,
    COD_CLIENTE INT,
    DNI CHAR(9),
    IBAN VARCHAR(34),
    CANTIDAD DECIMAL(10, 2),
    FECHA DATE,
    TIPO VARCHAR(50) CHECK (TIPO IN ('INGRESO', 'RETIRADA')),
    FOREIGN KEY COD_CLIENTE_TRA_FK (COD_CLIENTE) REFERENCES CLIENTES (COD_CLIENTE) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY DNI_TRA_FK (DNI) REFERENCES CLIENTES(DNI) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY IBAN_TRA_FK (IBAN) REFERENCES CUENTAS_BANCARIAS(IBAN) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE CUENTA_CORRIENTE (
    IBAN VARCHAR(34) PRIMARY KEY,
    SALDO DECIMAL(10, 2),
    TIPO VARCHAR(50) CHECK (TIPO IN ('NORMAL', 'ORO','PLATINUM')),
    FOREIGN KEY IBAN_CUE_FK (IBAN) REFERENCES CUENTAS_BANCARIAS(IBAN) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE CUENTA_AHORRO (
    IBAN VARCHAR(34) PRIMARY KEY,
    APORTACIONES DECIMAL(10, 2),
    VALOR_ACTUAL DECIMAL(10, 2),
    RENTABILIDAD DECIMAL(5, 2),
    TIPO VARCHAR(50) CHECK (TIPO IN ('PLAZO_FIJO', 'INVERSIONES')),
    FOREIGN KEY IBAN_CUEA_FK (IBAN) REFERENCES CUENTAS_BANCARIAS(IBAN) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE PLAZO_FIJO (
    IBAN VARCHAR(34) PRIMARY KEY,
    INTERES DECIMAL(5, 2),
    PLAZO INT,
    FOREIGN KEY IBAN_PLA_FK (IBAN) REFERENCES CUENTA_AHORRO(IBAN) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE INVERSIONES (
    IBAN VARCHAR(34) PRIMARY KEY,
    COD_CARTERA INT,
    FOREIGN KEY IBAN_INV_FK (IBAN) REFERENCES CUENTA_AHORRO(IBAN) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE PRODUCTO_INVERSION (
    COD_PRODUCTO INT PRIMARY KEY,
    MONTO DECIMAL(10, 2),
    TIPO VARCHAR(50) CHECK (TIPO IN ('ACCIONES', 'BONOS', 'F_INDEXADO')));

CREATE TABLE COMPUESTA (
	IBAN VARCHAR(34),
    COD_PRODUCTO INT,
    PRIMARY KEY(IBAN,COD_PRODUCTO),
    FOREIGN KEY IBAN_COM_FK (IBAN) REFERENCES INVERSIONES(IBAN) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY COD_PRODUCTO_COM_FK (COD_PRODUCTO) REFERENCES PRODUCTO_INVERSION(COD_PRODUCTO) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE ACCIONES (
    ISIN VARCHAR(50),
    COD_PRODUCTO INT,
    NOMBRE_EMPRESA VARCHAR(50),
    TICKER VARCHAR(50),
    CANTIDAD INT,
    PRECIO_COMPRA DECIMAL(10, 2),
    PRECIO_ACTUAL DECIMAL(10, 2),
    CAPITALIZACION DECIMAL(18, 2),
    RENTABILIDADYTD DECIMAL(5, 2),
    DIVIDENDOSYTD DECIMAL(5, 2),
    PRIMARY KEY (ISIN,COD_PRODUCTO),
    FOREIGN KEY COD_PRODUCTO_ACC_FK (COD_PRODUCTO) REFERENCES PRODUCTO_INVERSION (COD_PRODUCTO) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE PAISES (
    NOMBRE VARCHAR(50) PRIMARY KEY,
    TIPO_PAIS VARCHAR(50) CHECK (TIPO_PAIS IN ('DESARROLLADO', 'SUBDESARROLLADO')));

CREATE TABLE BONOS (
    COD_BONO INT,
    COD_PRODUCTO INT,
    NOMBRE VARCHAR(50),
    RENTABILIDAD DECIMAL(5, 2),
    PLAZO INT,
    PRIMARY KEY (COD_BONO,COD_PRODUCTO,NOMBRE),
    FOREIGN KEY COD_PRODUCTO_BON_FK (COD_PRODUCTO) REFERENCES PRODUCTO_INVERSION (COD_PRODUCTO) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY NOMBRE_BON_FK (NOMBRE) REFERENCES PAISES (NOMBRE) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE INDICES_REFERENCIA (
    TICKER_IR VARCHAR(50) PRIMARY KEY,
    NOMBRE VARCHAR(50),
    SECTOR VARCHAR(50),
    CAPITALIZACION DECIMAL(18, 2),
    RENTABILIDADYTD DECIMAL(5, 2),
    FOREIGN KEY NOMBRE_IND_FK (NOMBRE) REFERENCES PAISES(NOMBRE) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE PERTENECEN_A_IR (
    ISIN VARCHAR(50),
    COD_PRODUCTO INT,
    TICKER_IR VARCHAR(50),
    PRIMARY KEY (ISIN,COD_PRODUCTO,TICKER_IR),
    FOREIGN KEY ISIN_PER_FK (ISIN) REFERENCES ACCIONES (ISIN) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY COD_PRODUCTO_PER_FK (COD_PRODUCTO) REFERENCES ACCIONES (COD_PRODUCTO) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY TICKER_IR_PER_FK (TICKER_IR) REFERENCES INDICES_REFERENCIA (TICKER_IR) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE F_INDEXADO (
    ISIN VARCHAR(50),
    COD_PRODUCTO INT,
    TICKER VARCHAR(50),
    NOMBRE VARCHAR(50),
    VALOR_ACTUAL DECIMAL(10, 2),
    CANTIDAD_PARTICIPACIONES INT,
    PRECIO_COMPRA DECIMAL(10, 2),
    RENTABILIDADYTD DECIMAL(5, 2),
    NIVEL_RIESGO INT CHECK (NIVEL_RIESGO BETWEEN 1 AND 10),
    CAPITALIZACION DECIMAL(18, 2),
    PRIMARY KEY (ISIN, COD_PRODUCTO),
    FOREIGN KEY COD_PRODUCTO_F_FK (COD_PRODUCTO) REFERENCES PRODUCTO_INVERSION (COD_PRODUCTO) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY TICKER_F_FK (TICKER) REFERENCES INDICES_REFERENCIA (TICKER_IR) ON UPDATE CASCADE ON DELETE CASCADE);
    
-- ************************************************************************
--   _____                            _            _       _              *
--  / ____|                          | |          | |     | |             *
-- | |     __ _ _ __ __ _  __ _    __| | ___    __| | __ _| |_ ___  ___   *
-- | |    / _` | '__/ _` |/ _` |  / _` |/ _ \  / _` |/ _` | __/ _ \/ __|  *
-- | |___| (_| | | | (_| | (_| | | (_| |  __/ | (_| | (_| | || (_) \__ \  *
--  \_____\__,_|_|  \__, |\__,_|  \__,_|\___|  \__,_|\__,_|\__\___/|___/  *
--                   __/ |                                                *
--                  |___/                                                 *
-- ************************************************************************

INSERT INTO POBLACIONES (CP, POBLACION)
VALUES (28001, 'Madrid'),
(28002, 'Madrid'),
(28003, 'Madrid'),
(08001, 'Barcelona'),
(08002, 'Barcelona'),
(08003, 'Barcelona'),
(46001, 'Valencia'),
(46002, 'Valencia'),
(46003, 'Valencia'),
(29001, 'Málaga'),
(29002, 'Málaga'),
(41001, 'Sevilla');

INSERT INTO SUCURSALES (ID_SUCURSAL, DIRECCION, CP, EMAIL)
VALUES (1, 'Calle Mayor, 1', 28001, 'sucursal01@banco.es'),
(2, 'Calle Atocha, 34', 28002, 'sucursal02@banco.es'),
(3, 'Calle Serrano, 56', 28003, 'sucursal03@banco.es'),
(4, 'Avenida Diagonal, 123', 08001, 'sucursal04@banco.es'),
(5, 'Calle Aragón, 321', 08002, 'sucursal05@banco.es'),
(6, 'Calle Provença, 111', 08003, 'sucursal06@banco.es'),
(7, 'Calle Colón, 27', 46001, 'sucursal07@banco.es'),
(8, 'Avenida Barón de Cárcer, 48', 46002, 'sucursal08@banco.es'),
(9, 'Calle Xàtiva, 15', 46003, 'sucursal09@banco.es'),
(10, 'Calle Larios, 5', 29001, 'sucursal10@banco.es'),
(11, 'Avenida Andalucía, 66', 29002, 'sucursal11@banco.es'),
(12, 'Calle Sierpes, 33', 41001, 'sucursal12@banco.es');

INSERT INTO TELEFONOS (ID_SUCURSAL, TELEFONO)
VALUES (1, '912345678'),
(2, '912345679'),
(3, '912345680'),
(4, '932345678'),
(5, '932345679'),
(6, '932345680'),
(7, '962345678'),
(8, '962345679'),
(9, '962345680'),
(10, '952345678'),
(11, '952345679'),
(12, '954345678');

INSERT INTO PERSONAS (DNI, ID_SUCURSAL, NOMBRE, DIRECCION, CP, TELEFONO)
VALUES ('12345678A', 1, 'Juan Pérez', 'Calle Mayor, 2', 28001, '612345678'),
('23456789B', 2, 'María García', 'Calle Atocha, 35', 28002, '623456789'),
('34567890C', 3, 'Pedro Gómez', 'Calle Serrano, 57', 28003, '634567890'),
('45678901D', 4, 'Laura Martínez', 'Avenida Diagonal, 124', 08001, '645678901'),
('56789012E', 5, 'Miguel Rodríguez', 'Calle Aragón, 322', 08002, '656789012'),
('67890123F', 6, 'Ana Sánchez', 'Calle Provença, 112', 08003, '667890123'),
('78901234G', 7, 'Pablo Fernández', 'Calle Colón, 28', 46001, '678901234'),
('89012345H', 8, 'Teresa López', 'Avenida Barón de Cárcer, 49', 46002, '689012345'),
('90123456I', 9, 'Javier Torres', 'Calle Xàtiva, 16', 46003, '690123456'),
('01234567J', 10, 'Sonia Ortiz', 'Calle Larios, 6', 29001, '601234567'),
('10234567K', 11, 'Antonio Morales', 'Avenida Andalucía, 67', 29002, '602345678'),
('20345678L', 12, 'Carmen Gutiérrez', 'Calle Sierpes, 34', 41001, '603456789');

INSERT INTO CLIENTES (COD_CLIENTE, DNI, EDAD, E_MAIL)
VALUES (1, '12345678A', 25, 'juan.perez@email.com'),
       (2, '23456789B', 32, 'maria.garcia@email.com'),
       (3, '34567890C', 45, 'pedro.gomez@email.com'),
       (4, '45678901D', 29, 'laura.martinez@email.com'),
       (5, '56789012E', 37, 'miguel.rodriguez@email.com'),
       (6, '67890123F', 50, 'ana.sanchez@email.com'),
       (7, '78901234G', 28, 'pablo.fernandez@email.com'),
       (8, '89012345H', 42, 'teresa.lopez@email.com');
       
INSERT INTO PRESTAMOS (COD_PRESTAMO, DNI, CUANTIA, FECHA_EMISION, PLAZO_ANOS, TIPO)
VALUES (1, '12345678A', 10000.00, '2022-01-15', 5, 'PERSONAL'),
       (2, '23456789B', 50000.00, '2022-02-28', 10, 'HIPOTECA'),
       (3, '34567890C', 25000.00, '2022-03-12', 8, 'PERSONAL'),
       (4, '45678901D', 75000.00, '2022-04-23', 15, 'HIPOTECA'),
       (5, '56789012E', 15000.00, '2022-05-02', 3, 'PERSONAL'),
       (6, '67890123F', 30000.00, '2022-06-14', 7, 'HIPOTECA'),
       (7, '78901234G', 20000.00, '2022-07-19', 5, 'PERSONAL'),
       (8, '89012345H', 100000.00, '2022-08-31', 20, 'HIPOTECA');

INSERT INTO PERSONAL (COD_PRESTAMO, MOTIVO, TAE)
VALUES (1, 'Compra de coche', 6.50),
       (2, 'Compra de vivienda', 2.25),
       (3, 'Reformas en el hogar', 8.75),
       (4, 'Consolidación de deudas', 4.20),
       (5, 'Vacaciones', 10.00),
       (6, 'Inversión en negocio', 3.75),
       (7, 'Gastos médicos', 7.90),
       (8, 'Formación académica', 5.25);

INSERT INTO HIPOTECA (COD_PRESTAMO, TIPO_VIVIENDA, TIPO)
VALUES (1, 'Piso', 'T_FIJO'),
       (2, 'Casa', 'T_VARIABLE'),
       (3, 'Piso', 'T_FIJO'),
       (4, 'Casa', 'T_VARIABLE');

INSERT INTO T_FIJO (COD_PRESTAMO, TAE)
VALUES (1, 3.50),
       (2, 3.75);
       
INSERT INTO T_VARIABLE (COD_PRESTAMO, EURIBOR, DIFERENCIAL_FIJO)
VALUES (3, 2.50, 1.75),
       (4, 3.00, 2.00);
       
INSERT INTO EMPLEADOS (COD_EMPLEADO, DNI, COD_EMPLEADO_JEFE, NUM_EMPLEADOS_CARGO, SALARIO, FECHA_INICIO_CONTRATO, ANTIGUEDAD, CUALIFICACION)
VALUES (9, '90123456I', 9, 3, 3500.00, '2016-02-15', 7, 'Director'),
(10, '01234567J', NULL, 2, 2500.00, '2018-06-22', 4, 'Subdirector'),
(11, '10234567K', NULL, 2, 2500.00, '2018-10-30', 4, 'Subdirector'),
(12, '20345678L', NULL, 0, 1800.00, '2019-04-01', 4, 'Empleado');
       
INSERT INTO CUENTAS_BANCARIAS (IBAN, COD_CLIENTE, DNI, FECHA_APERTURA, TIPO)
VALUES ('ES0012345678001234567890', 1, '12345678A', '2023-01-10', 'CUENTA_CORRIENTE'),
       ('ES0012345678012345678901', 2, '23456789B', '2022-12-15', 'CUENTA_AHORRO'),
       ('ES0012345678023456789012', 3, '34567890C', '2021-08-25', 'CUENTA_CORRIENTE'),
       ('ES0012345678034567890123', 4, '45678901D', '2022-07-11', 'CUENTA_AHORRO'),
       ('ES0012345678045678901234', 5, '56789012E', '2022-02-21', 'CUENTA_CORRIENTE'),
       ('ES0012345678056789012345', 6, '67890123F', '2021-05-16', 'CUENTA_AHORRO'),
       ('ES0012345678067890123456', 7, '78901234G', '2023-01-05', 'CUENTA_CORRIENTE'),
       ('ES0012345678078901234567', 8, '89012345H', '2020-09-12', 'CUENTA_AHORRO');

INSERT INTO PERTENECEN_CLI_CB (COD_CLIENTE, DNI, IBAN)
VALUES (1, '12345678A', 'ES0012345678001234567890'),
       (2, '23456789B', 'ES0012345678012345678901'),
       (3, '34567890C', 'ES0012345678023456789012'),
       (4, '45678901D', 'ES0012345678034567890123'),
       (5, '56789012E', 'ES0012345678045678901234'),
       (6, '67890123F', 'ES0012345678056789012345'),
       (7, '78901234G', 'ES0012345678067890123456'),
       (8, '89012345H', 'ES0012345678078901234567'),
       (1, '12345678A', 'ES0012345678012345678901'), -- Cliente 1 pertenece a dos cuentas
       (2, '23456789B', 'ES0012345678001234567890'), -- Cliente 2 pertenece a dos cuentas
       (6, '67890123F', 'ES0012345678023456789012'), -- Cliente 6 pertenece a dos cuentas
       (8, '89012345H', 'ES0012345678045678901234'); -- Cliente 8 pertenece a dos cuentas
       
INSERT INTO TRANSACCION (COD_TRANSACCION, COD_CLIENTE, DNI, IBAN, CANTIDAD, FECHA, TIPO)
VALUES (1, 1, '12345678A', 'ES0012345678001234567890', 500.00, '2023-01-11', 'INGRESO'),
       (2, 2, '23456789B', 'ES0012345678012345678901', 1000.00, '2023-02-20', 'INGRESO'),
       (3, 3, '34567890C', 'ES0012345678023456789012', 300.00, '2023-02-28', 'RETIRADA'),
       (4, 4, '45678901D', 'ES0012345678034567890123', 750.00, '2023-03-15', 'INGRESO'),
       (5, 5, '56789012E', 'ES0012345678045678901234', 200.00, '2023-03-01', 'RETIRADA'),
       (6, 6, '67890123F', 'ES0012345678056789012345', 600.00, '2023-03-18', 'INGRESO'),
       (7, 7, '78901234G', 'ES0012345678067890123456', 900.00, '2023-03-05', 'RETIRADA'),
       (8, 8, '89012345H', 'ES0012345678078901234567', 250.00, '2023-01-20', 'INGRESO'),
       (9, 1, '12345678A', 'ES0012345678012345678901', 450.00, '2023-02-10', 'RETIRADA'),
       (10, 2, '23456789B', 'ES0012345678001234567890', 1200.00, '2023-03-10', 'INGRESO'),
       (11, 6, '67890123F', 'ES0012345678023456789012', 100.00, '2023-02-15', 'RETIRADA'),
       (12, 8, '89012345H', 'ES0012345678045678901234', 350.00, '2023-01-25', 'INGRESO');

INSERT INTO CUENTA_CORRIENTE (IBAN, SALDO, TIPO)
VALUES ('ES0012345678001234567890', 500.00, 'NORMAL'),
       ('ES0012345678023456789012', 200.00, 'ORO'),
       ('ES0012345678045678901234', 1000.00, 'PLATINUM'),
       ('ES0012345678067890123456', 600.00, 'NORMAL');

INSERT INTO CUENTA_AHORRO (IBAN, APORTACIONES, VALOR_ACTUAL, RENTABILIDAD, TIPO)
VALUES ('ES0012345678012345678901', 5000.00, 5200.00, 4.00, 'PLAZO_FIJO'),
       ('ES0012345678034567890123', 10000.00, 10200.00, 2.00, 'INVERSIONES'),
       ('ES0012345678056789012345', 15000.00, 15750.00, 5.00, 'PLAZO_FIJO'),
       ('ES0012345678078901234567', 8000.00, 8240.00, 3.00, 'INVERSIONES');
       
INSERT INTO PLAZO_FIJO (IBAN, INTERES, PLAZO)
VALUES ('ES0012345678012345678901', 4.00, 12),
       ('ES0012345678056789012345', 5.00, 24);

INSERT INTO INVERSIONES (IBAN, COD_CARTERA)
VALUES ('ES0012345678034567890123', 1),
       ('ES0012345678078901234567', 2);
       
INSERT INTO PRODUCTO_INVERSION (COD_PRODUCTO, MONTO, TIPO)
VALUES (1, 10000.00, 'ACCIONES'),
       (2, 15000.00, 'BONOS'),
       (3, 8000.00, 'F_INDEXADO'),
       (4, 12000.00, 'ACCIONES'),
       (5, 5000.00, 'BONOS'),
       (6, 6000.00, 'F_INDEXADO'),
       (7, 20000.00, 'ACCIONES'),
       (8, 9000.00, 'BONOS'),
       (9, 4500.00, 'F_INDEXADO'),
       (10, 5500.00, 'ACCIONES'),
       (11, 11000.00, 'BONOS'),
       (12, 7000.00, 'F_INDEXADO'),
       (13, 4000.00, 'ACCIONES'),
       (14, 10000.00, 'BONOS'),
       (15, 3000.00, 'F_INDEXADO');
       
INSERT INTO COMPUESTA (IBAN, COD_PRODUCTO)
VALUES ('ES0012345678034567890123', 1),
       ('ES0012345678034567890123', 2),
       ('ES0012345678034567890123', 3),
       ('ES0012345678034567890123', 4),
       ('ES0012345678034567890123', 5),
       ('ES0012345678078901234567', 6),
       ('ES0012345678078901234567', 7),
       ('ES0012345678078901234567', 8),
       ('ES0012345678078901234567', 9),
       ('ES0012345678078901234567', 10),
       ('ES0012345678078901234567', 11),
       ('ES0012345678078901234567', 12),
       ('ES0012345678078901234567', 13),
       ('ES0012345678078901234567', 14),
       ('ES0012345678078901234567', 15);

INSERT INTO PAISES (NOMBRE, TIPO_PAIS)
VALUES ('Estados Unidos', 'DESARROLLADO'),
       ('Alemania', 'DESARROLLADO'),
       ('Japón', 'DESARROLLADO'),
       ('India', 'SUBDESARROLLADO'),
       ('China', 'DESARROLLADO'),
       ('Brasil', 'SUBDESARROLLADO'),
       ('España', 'DESARROLLADO'),
       ('Francia', 'DESARROLLADO');

INSERT INTO INDICES_REFERENCIA (TICKER_IR, NOMBRE, SECTOR, CAPITALIZACION, RENTABILIDADYTD)
VALUES ('^DJI', 'Estados Unidos', 'INDUSTRIAL', 10000000000000.00, 5.00),
       ('^GDAXI', 'Alemania', 'TECNOLOGÍA', 2500000000000.00, 4.00),
       ('^N225', 'Japón', 'FINANCIERO', 3000000000000.00, 3.50),
       ('^BSESN', 'India', 'INDUSTRIAL', 2000000000000.00, 6.00),
       ('000001.SS', 'China', 'TECNOLOGÍA', 5000000000000.00, 4.50),
       ('^BVSP', 'Brasil', 'FINANCIERO', 1000000000000.00, 2.50),
       ('^IBEX', 'España', 'INDUSTRIAL', 800000000000.00, 3.00),
       ('^FCHI', 'Francia', 'TECNOLOGÍA', 1500000000000.00, 4.50);
       
INSERT INTO ACCIONES (ISIN, COD_PRODUCTO, NOMBRE_EMPRESA, TICKER, CANTIDAD, PRECIO_COMPRA, PRECIO_ACTUAL, CAPITALIZACION, RENTABILIDADYTD, DIVIDENDOSYTD)
VALUES ('US0378331005', 1, 'Apple Inc.', 'AAPL', 100, 145.00, 155.00, 2500000000000.00, 8.00, 1.50),
       ('US0231351067', 4, 'Amazon.com, Inc.', 'AMZN', 20, 3300.00, 3400.00, 1500000000000.00, 6.00, 0.00),
       ('US5949181045', 7, 'Microsoft Corporation', 'MSFT', 150, 250.00, 280.00, 2000000000000.00, 10.00, 2.00),
       ('US38259P5089', 10, 'Alphabet Inc.', 'GOOGL', 50, 2200.00, 2300.00, 1200000000000.00, 7.50, 0.00),
       ('US30303M1027', 13, 'Facebook, Inc.', 'FB', 200, 330.00, 350.00, 900000000000.00, 5.00, 0.50);

INSERT INTO BONOS (COD_BONO, COD_PRODUCTO, NOMBRE, RENTABILIDAD, PLAZO)
VALUES (1, 2, 'Estados Unidos', 2.50, 10),
       (2, 5, 'Alemania', 1.50, 5),
       (3, 8, 'Japón', 0.75, 7),
       (4, 11, 'India', 6.00, 15),
       (5, 14, 'China', 4.00, 20);

INSERT INTO F_INDEXADO (ISIN, COD_PRODUCTO, TICKER, NOMBRE, VALOR_ACTUAL, CANTIDAD_PARTICIPACIONES, PRECIO_COMPRA, RENTABILIDADYTD, NIVEL_RIESGO, CAPITALIZACION)
VALUES ('US4642868072', 3, '^DJI', 'Estados Unidos', 50000.00, 10, 48500.00, 5.00, 5, 10000000000000.00),
       ('US4642868148', 6, '^GDAXI', 'Alemania', 5500.00, 15, 5300.00, 4.00, 4, 2500000000000.00),
       ('US4642868197', 9, '^N225', 'Japón', 7000.00, 20, 6800.00, 3.50, 3, 3000000000000.00),
       ('US4642868262', 12, '^BSESN', 'India', 2000.00, 25, 1900.00, 6.00, 6, 2000000000000.00),
       ('US4642868320', 15, '000001.SS', 'China', 8000.00, 30, 7700.00, 5.50, 7, 4000000000000.00);

INSERT INTO PERTENECEN_A_IR (ISIN, COD_PRODUCTO, TICKER_IR)
VALUES ('US0378331005', 1, '^DJI'),
       ('US0231351067', 4, '^DJI'),
       ('US5949181045', 7, '^DJI'),
       ('US38259P5089', 10, '^DJI'),
       ('US30303M1027', 13, '^DJI');
       
-- ***********************************************
--   _____                      _ _              *
--  / ____|                    | | |             *
-- | |     ___  _ __  ___ _   _| | |_ __ _ ___   *
-- | |    / _ \| '_ \/ __| | | | | __/ _` / __|  *
-- | |___| (_) | | | \__ \ |_| | | || (_| \__ \  *
 -- \_____\___/|_| |_|___/\__,_|_|\__\__,_|___/  *
 -- **********************************************                                            
                                             
-- 1.Consulta para obtener la cantidad total de préstamos personales.
SELECT COUNT(*) AS "Prestamos personales" FROM PERSONAL;

-- 2.Consulta para obtener el nombre de todas las personas que han realizado al menos una transacción con un monto superior a 1000.
SELECT DISTINCT PE.NOMBRE
FROM PERSONAS PE
INNER JOIN CLIENTES C ON PE.DNI = C.DNI
INNER JOIN TRANSACCION T ON C.COD_CLIENTE = T.COD_CLIENTE
WHERE T.CANTIDAD > 1000;

-- 3.Consulta para obtener la cantidad total de préstamos personales y hipotecarios.
SELECT
    (SELECT COUNT(*) FROM PERSONAL) as "Prestamos personales",
    (SELECT COUNT(*) FROM HIPOTECA) as "Hipotecas";
    
-- 4 Consulta para obtener el nombre y la dirección de todos los empleados que tienen más de 5 años de antigüedad en la empresa.
SELECT PE.NOMBRE, PE.DIRECCION
FROM PERSONAS PE
INNER JOIN EMPLEADOS E ON PE.DNI = E.DNI
WHERE E.ANTIGUEDAD > 5;

-- 5 Consulta para obtener la suma total de las cuantías de los préstamos hipotecarios.
SELECT ROUND(SUM(CUANTIA)) AS "Suma cuantía hipotecas" FROM PRESTAMOS WHERE TIPO = 'HIPOTECA';

-- 6 Consulta para obtener el promedio de las cuantías de los préstamos personales.
SELECT ROUND(AVG(CUANTIA)) AS "Media cuantía prestamos personales" FROM PRESTAMOS WHERE TIPO = 'PERSONAL';

-- 7 Consulta para obtener el total de préstamos hipotecarios por plazo (en años).
SELECT PR.PLAZO_ANOS, COUNT(*) AS "Total Hipotecas"
FROM HIPOTECA H
INNER JOIN PRESTAMOS PR ON H.COD_PRESTAMO = PR.COD_PRESTAMO
GROUP BY PR.PLAZO_ANOS;

-- 8 Consulta para obtener el nombre y dirección de todas las sucursales junto con el nombre de la población a la que pertenecen.
SELECT S.DIRECCION, P.POBLACION
FROM SUCURSALES S
INNER JOIN POBLACIONES P ON S.CP = P.CP;

-- 9 Consulta para obtener el nombre y la dirección de todas las personas junto con el nombre de la población a la que pertenecen.
SELECT PE.NOMBRE, PE.DIRECCION, PO.POBLACION
FROM PERSONAS PE
INNER JOIN POBLACIONES PO ON PE.CP = PO.CP;

-- 10 Consulta para obtener el nombre y el teléfono de todas las personas que trabajan en cada sucursal.
SELECT PE.NOMBRE AS "NOMBRE EMPLEADO", PE.TELEFONO, S.ID_SUCURSAL
FROM PERSONAS PE
INNER JOIN EMPLEADOS E ON PE.DNI = E.DNI
INNER JOIN SUCURSALES S ON PE.ID_SUCURSAL = S.ID_SUCURSAL;

-- 11 Consulta para obtener la cantidad total de cuentas bancarias por población.
SELECT PO.POBLACION, COUNT(*) AS "Total Cuentas Bancarias"
FROM CUENTAS_BANCARIAS CB
INNER JOIN CLIENTES C ON CB.COD_CLIENTE = C.COD_CLIENTE
INNER JOIN PERSONAS PE ON C.DNI = PE.DNI
INNER JOIN POBLACIONES PO ON PE.CP = PO.CP
GROUP BY PO.POBLACION;

-- 12 Consulta para obtener la cantidad total de cuentas de ahorro.
SELECT COUNT(*) AS "CUENTAS AHORRO" FROM CUENTA_AHORRO;

-- 13 Consulta para obtener la cantidad total de préstamos personales por población.
SELECT PO.POBLACION, COUNT(*) AS "Prestamos personales"
FROM PERSONAL P
INNER JOIN PRESTAMOS PR ON P.COD_PRESTAMO = PR.COD_PRESTAMO
INNER JOIN PERSONAS PE ON PR.DNI = PE.DNI
INNER JOIN POBLACIONES PO ON PE.CP = PO.CP
GROUP BY PO.POBLACION;

-- 14 Consulta para obtener el valor total en cuentas de ahorro.
SELECT SUM(VALOR_ACTUAL) AS "VALOR TOTAL CUENTA AHORRO" FROM CUENTA_AHORRO;

-- 15 Consulta los clientes que tienen más de una cuenta bancaria.
SELECT c.*, COUNT(pcc.IBAN) AS Numero_de_Cuentas
FROM CLIENTES c
JOIN PERTENECEN_CLI_CB pcc ON c.COD_CLIENTE = pcc.COD_CLIENTE AND c.DNI = pcc.DNI
GROUP BY c.COD_CLIENTE, c.DNI
HAVING COUNT(pcc.IBAN) > 1;

-- 16 Consulta las sucursales que tienen una cantidad de empleados superior o igual a la media de empleados por sucursal.
SELECT s.ID_SUCURSAL, s.DIRECCION, s.CP, s.EMAIL, COUNT(e.COD_EMPLEADO) AS Numero_de_Empleados
FROM SUCURSALES s
JOIN PERSONAS p ON s.ID_SUCURSAL = p.ID_SUCURSAL
JOIN EMPLEADOS e ON p.DNI = e.DNI
GROUP BY s.ID_SUCURSAL, s.DIRECCION, s.CP, s.EMAIL
HAVING COUNT(e.COD_EMPLEADO) >= (
    SELECT AVG(Numero_de_Empleados) AS Media_Empleados
    FROM (
        SELECT p.ID_SUCURSAL, COUNT(e.COD_EMPLEADO) AS Numero_de_Empleados
        FROM EMPLEADOS e
        JOIN PERSONAS p ON e.DNI = p.DNI
        GROUP BY p.ID_SUCURSAL
    ) AS EmpleadosPorSucursal
);

-- 17 Consulta los préstamos con una cuantía superior al promedio de cuantía de todos los préstamos.
SELECT P.*
FROM PRESTAMOS P
WHERE P.CUANTIA > (
    SELECT AVG(CUANTIA)
    FROM PRESTAMOS
);

-- 18 Consulta las cuentas de ahorro con un valor actual superior a 5000.
SELECT *
FROM CUENTA_AHORRO 
WHERE VAlOR_ACTUAL > 5000;

-- 19 Consulta las cuentas bancarias que no tienen ninguna transacción registrada.
SELECT CB.*
FROM CUENTAS_BANCARIAS CB
LEFT JOIN TRANSACCION T ON CB.IBAN = T.IBAN
WHERE T.COD_TRANSACCION IS NULL;

-- 20 Consulta la cantidad total de empleados por sucursal.
SELECT S.ID_SUCURSAL, S.DIRECCION, COUNT(P.DNI) AS NUM_EMPLEADOS
FROM SUCURSALES S
LEFT JOIN PERSONAS P ON S.ID_SUCURSAL = P.ID_SUCURSAL
WHERE P.DNI IN (SELECT DNI FROM EMPLEADOS)
GROUP BY S.ID_SUCURSAL, S.DIRECCION
ORDER BY S.ID_SUCURSAL;

-- 21 Consulta la cantidad total de préstamos de cada tipo en la tabla de préstamos.
SELECT TIPO, COUNT(*) AS NUM_PRESTAMOS
FROM PRESTAMOS
GROUP BY TIPO;

-- 22 Consulta las sucursales que no tienen teléfono registrado.
SELECT S.*
FROM SUCURSALES S
LEFT JOIN TELEFONOS T ON S.ID_SUCURSAL = T.ID_SUCURSAL
WHERE T.TELEFONO IS NULL;

-- 23 Consulta el promedio de cuantía de los préstamos personales según su motivo.
SELECT P.MOTIVO, ROUND(AVG(PR.CUANTIA)) AS PROMEDIO_CUANTIA
FROM PERSONAL P
INNER JOIN PRESTAMOS PR ON P.COD_PRESTAMO = PR.COD_PRESTAMO
GROUP BY P.MOTIVO;

-- 24 Consulta la cantidad de clientes por tipo de cuenta bancaria.
SELECT CB.TIPO, COUNT(*) AS NUM_CLIENTES
FROM CUENTAS_BANCARIAS CB
INNER JOIN CLIENTES C ON CB.COD_CLIENTE = C.COD_CLIENTE
GROUP BY CB.TIPO;

-- 25 Consulta la suma de aportaciones por tipo de cuenta de ahorro.
SELECT CA.TIPO, SUM(CA.APORTACIONES) AS TOTAL_APORTACIONES
FROM CUENTA_AHORRO CA
GROUP BY CA.TIPO;

-- 26 Consulta la rentabilidad promedio de los fondos indexados por nivel de riesgo.
SELECT FI.NIVEL_RIESGO, AVG(FI.RENTABILIDADYTD) AS PROMEDIO_RENTABILIDAD
FROM F_INDEXADO FI
GROUP BY FI.NIVEL_RIESGO;

-- 27 Consulta la cantidad total de empleados por cualificación.
SELECT E.CUALIFICACION, COUNT(*) AS NUM_EMPLEADOS
FROM EMPLEADOS E
GROUP BY E.CUALIFICACION;

-- 28 Consulta el monto total invertido en cada tipo de producto de inversión.
SELECT PI.TIPO, SUM(PI.MONTO) AS TOTAL_INVERSION
FROM PRODUCTO_INVERSION PI
GROUP BY PI.TIPO;

-- 29 Consulta las acciones con una rentabilidad YTD superior al promedio de rentabilidad YTD de todas las acciones.
SELECT A.*
FROM ACCIONES A
WHERE A.RENTABILIDADYTD > (
    SELECT AVG(A2.RENTABILIDADYTD)
    FROM ACCIONES A2
);

-- 30 Consulta el número total de transacciones (ingresos y retiradas) por cada cliente.
SELECT C.COD_CLIENTE, C.DNI, COUNT(T.COD_TRANSACCION) AS NUM_TRANSACCIONES
FROM CLIENTES C
INNER JOIN TRANSACCION T ON C.COD_CLIENTE = T.COD_CLIENTE
GROUP BY C.COD_CLIENTE, C.DNI;

-- ************************************************************************
--  _____                        _ _           _            _             *
-- |  __ \                      | (_)         (_)          | |            *
-- | |__) | __ ___   ___ ___  __| |_ _ __ ___  _  ___ _ __ | |_ ___  ___  *
-- |  ___/ '__/ _ \ / __/ _ \/ _` | | '_ ` _ \| |/ _ \ '_ \| __/ _ \/ __| * 
-- | |   | | | (_) | (_|  __/ (_| | | | | | | | |  __/ | | | || (_) \__ \ *
-- |_|   |_|  \___/ \___\___|\__,_|_|_| |_| |_|_|\___|_| |_|\__\___/|___/ *
-- ************************************************************************                                                                       
                           
-- PROCEDIMIENTO 1- El siguiente procedimiento almacenado buscará una cuenta corriente por IBAN y realizará un depósito en la cuenta. Si el IBAN no se encuentra, el procedimiento almacenado mostrará un mensaje de error. Si el monto del depósito es negativo, también mostrará un mensaje de error. De lo contrario, actualizará la cuenta bancaria con el nuevo saldo y registrará la transacción.
DELIMITER //
DROP PROCEDURE IF EXISTS INGRESAR_DINERO;
//CREATE PROCEDURE INGRESAR_DINERO(P_IBAN VARCHAR(34),P_CANTIDAD DECIMAL(10,2))
BEGIN
    DECLARE SALDO_ACTUAL DECIMAL(10,2);
    
	-- Introduzco el saldo actual en la variable SALDO_ACTUAL
    SELECT SALDO INTO SALDO_ACTUAL
    FROM CUENTA_CORRIENTE
    WHERE IBAN = P_IBAN;

    -- Verificar si la cantidad es negativa
    IF P_CANTIDAD < 0 THEN
        SELECT 'Error: la cantidad no puede ser negativa.' AS 'ERROR';
    -- Verificar si la cuenta existe
    ELSEIF SALDO_ACTUAL IS NULL THEN
        SELECT 'Error: no se encontró la cuenta.' AS 'ERROR';
    ELSE
        -- Todo está bien, realizar la transacción
        UPDATE CUENTA_CORRIENTE
        SET SALDO = SALDO + P_CANTIDAD
        WHERE IBAN = P_IBAN;

        SELECT CONCAT_WS(" ",'Depósito realizado con éxito.','El saldo actualizado es',(SALDO_ACTUAL+P_CANTIDAD),'EUROS.') AS 'MENSAJE';
    END IF;
END //
DELIMITER ;

-- MENSAJE ERROR SALDO NEGATIVO
CALL INGRESAR_DINERO('ES0012345678001234567890',-100);

-- MENSAJE ERROR CUENTA NO ENCONTRADA
CALL INGRESAR_DINERO('999912345678001234567890',100);

-- INGRESO CORRECTO
CALL INGRESAR_DINERO('ES0012345678001234567890',100);

-- COMPROBACION
SELECT * FROM CUENTA_CORRIENTE
WHERE IBAN = 'ES0012345678001234567890';

-- PROCEDIMIENTO 2- El siguiente procedimiento almacenado buscará una cuenta corriente por IBAN y realizará un retiro en la cuenta. Si el IBAN no se encuentra, el procedimiento almacenado mostrará un mensaje de error. Si el monto del depósito es mayor al saldo , también mostrará un mensaje de error. De lo contrario, actualizará la cuenta bancaria con el nuevo saldo y registrará la transacción.

DELIMITER //
DROP PROCEDURE IF EXISTS RETIRAR_DINERO;
//CREATE PROCEDURE RETIRAR_DINERO(P_IBAN VARCHAR(34),P_CANTIDAD DECIMAL(10,2))
BEGIN
    DECLARE SALDO_ACTUAL DECIMAL(10,2);
    
	-- Introduzco el saldo actual en la variable SALDO_ACTUAL
    SELECT SALDO INTO SALDO_ACTUAL
    FROM CUENTA_CORRIENTE
    WHERE IBAN = P_IBAN;

    -- Verificar si la cantidad es negativa
    IF P_CANTIDAD > SALDO_ACTUAL THEN
        SELECT 'Error: no existen fondos suficientes.' AS 'ERROR';
    -- Verificar si la cuenta existe
    ELSEIF SALDO_ACTUAL IS NULL THEN
        SELECT 'Error: no se encontró la cuenta.' AS 'ERROR';
    ELSE
        -- Todo está bien, realizar la transacción
        UPDATE CUENTA_CORRIENTE
        SET SALDO = SALDO - P_CANTIDAD
        WHERE IBAN = P_IBAN;

        SELECT CONCAT_WS(" ",'Retiro realizado con éxito.','El saldo actualizado es',(SALDO_ACTUAL-P_CANTIDAD),'EUROS.') AS 'MENSAJE';
    END IF;
END //
DELIMITER ;

-- MENSAJE ERROR SALDO INCORRECTO
CALL RETIRAR_DINERO('ES0012345678001234567890',1400);

-- MENSAJE ERROR CUENTA NO ENCONTRADA
CALL RETIRAR_DINERO('999912345678001234567890',100);

-- RETIRO CORRECTO
CALL RETIRAR_DINERO('ES0012345678001234567890',100);

-- COMPROBACION
SELECT * FROM CUENTA_CORRIENTE
WHERE IBAN = 'ES0012345678001234567890';


-- PROCEDIMIENTO 3 (CURSOR)-Este procedimiento se encarga de crear tres nuevas tablas: Normal, Oro y Platinum. Estas tablas se utilizarán para reorganizar las cuentas corrientes existentes en la base de datos en función del tipo de cuenta.
DELIMITER //
DROP PROCEDURE IF EXISTS PROCEDIMIENTO3;
// CREATE PROCEDURE PROCEDIMIENTO3()
BEGIN 
	-- DECLARO VARIABLES
	DECLARE FIN_TABLA BOOLEAN DEFAULT FALSE;
    DECLARE V_IBAN VARCHAR(34);
	DECLARE V_SALDO DECIMAL(10,2);
	DECLARE V_TIPO VARCHAR(50);
    
    -- DECLARO CURSOR
    DECLARE C CURSOR FOR SELECT IBAN,SALDO,TIPO FROM CUENTA_CORRIENTE;
    
    -- DECLARO HANDLER
    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET FIN_TABLA = TRUE;
    
    -- CREO TABLAS
    DROP TABLE IF EXISTS NORMAL;
    CREATE TABLE NORMAL(IBAN VARCHAR(34),SALDO DECIMAL(10, 2),TIPO VARCHAR(50));
    DROP TABLE IF EXISTS ORO;
    CREATE TABLE ORO(IBAN VARCHAR(34),SALDO DECIMAL(10, 2),TIPO VARCHAR(50));
    DROP TABLE IF EXISTS PLATINUM;
	CREATE TABLE PLATINUM(IBAN VARCHAR(34),SALDO DECIMAL(10, 2),TIPO VARCHAR(50));
    
    -- ABRO CURSOR
    OPEN C;
    
    -- RECORRO CON CURSOR
    WHILE (FIN_TABLA=FALSE) DO
		FETCH C INTO V_IBAN,V_SALDO,V_TIPO;
		IF(FIN_TABLA=FALSE) THEN
			IF(V_TIPO = 'NORMAL') THEN
				INSERT INTO NORMAL VALUES (V_IBAN,V_SALDO,V_TIPO);
            ELSEIF (V_TIPO = 'ORO') THEN
				INSERT INTO ORO VALUES (V_IBAN,V_SALDO,V_TIPO);
            ELSEIF (V_TIPO = 'PLATINUM') THEN
				INSERT INTO PLATINUM VALUES (V_IBAN,V_SALDO,V_TIPO);
            END IF;
		END IF;
    END WHILE;
    
    -- CIERRO CURSOR
    CLOSE C;
END; //
DELIMITER ;

CALL PROCEDIMIENTO3();

SELECT * FROM NORMAL;
SELECT * FROM ORO;
SELECT * FROM PLATINUM;

-- *************************************************
--  ______                _                        *
-- |  ____|              (_)                       *
-- | |__ _   _ _ __   ___ _  ___  _ __   ___  ___  *
-- |  __| | | | '_ \ / __| |/ _ \| '_ \ / _ \/ __| *
-- | |  | |_| | | | | (__| | (_) | | | |  __/\__ \ *
-- |_|   \__,_|_| |_|\___|_|\___/|_| |_|\___||___/ *
-- *************************************************                                                					
                          
-- FUNCION 1- Esta función recibe el código del cliente y retorna el saldo total de todas sus cuentas corrientes.
DELIMITER //
DROP FUNCTION IF EXISTS SALDO_TOTAL_CLIENTE;
//CREATE FUNCTION SALDO_TOTAL_CLIENTE(P_CODCLIENTE INT) RETURNS DECIMAL(10, 2)
DETERMINISTIC
BEGIN
    DECLARE SALDO_TOTAL DECIMAL(10, 2);
    SELECT SUM(SALDO) INTO SALDO_TOTAL
    FROM CUENTA_CORRIENTE CC
    INNER JOIN PERTENECEN_CLI_CB PCCB ON CC.IBAN = PCCB.IBAN
    WHERE PCCB.COD_CLIENTE = P_CODCLIENTE;
    
    RETURN SALDO_TOTAL;
END//
DELIMITER ;

SELECT SALDO_TOTAL_CLIENTE(1) AS 'SALDO TOTAL';

-- FUNCION 2 Esta función recibe como parámetros el código y DNI del cliente y un rango de fechas (fecha de inicio y fecha de fin). La función luego cuenta el número de transacciones realizadas por ese cliente durante el rango de fechas especificado y devuelve este número.
DELIMITER //
DROP FUNCTION IF EXISTS CONTAR_TRANSACCIONES;
//CREATE FUNCTION CONTAR_TRANSACCIONES(P_DNI CHAR(9), FECHA_INICIO DATE, FECHA_FIN DATE) RETURNS INT 
DETERMINISTIC
BEGIN
    DECLARE TOTAL_TRANSACCIONES INT;

    SELECT COUNT(*)
    INTO TOTAL_TRANSACCIONES
    FROM TRANSACCION 
    WHERE DNI = P_DNI 
    AND FECHA BETWEEN FECHA_INICIO AND FECHA_FIN;

    RETURN TOTAL_TRANSACCIONES;
END //
DELIMITER ;

SELECT CONTAR_TRANSACCIONES('12345678A','2023-01-11','2023-02-10') AS TRANSACCIONES;


-- ******************************************************
--  _______ _                _                          *
-- |__   __(_)              | |                         *
--    | |   _  __ _ _ __ ___| |_ ___  _ __   ___  ___   *
--    | |  | |/ _` | '__/ _ \ __/ _ \| '_ \ / _ \/ __|  *
--    | |  | | (_| | | |  __/ || (_) | | | |  __/\__ \  *
--    |_|  |_|\__, |_|  \___|\__\___/|_| |_|\___||___/  *
--             __/ |                                    *
--            |___/                                     *
-- 													    *
--                         __,,,,_					    *
--          _ __..-;''`--/'/ /.',-`-.					*
--      (`/' ` |  \ \ \\ / / / / .-'/`,_				*
--     /'`\ \   |  \ | \| // // / -.,/_,'-,				*
--    /<7' ;  \ \  | ; ||/ /| | \/    |`-/,/-.,_,/')	*
--   /  _.-, `,-\,__|  _-| / \ \/|_/  |    '-/.;.\'		*
--   `-`  f/ ;      / __/ \__ `/ |__/ |					*
--        `-'      |  -| =|\_  \  |-' |					*
--              __/   /_..-' `  ),'  //					*
--          fL ((__.-'((___..-'' \__.'					*
-- ******************************************************

-- TIGRETÓN 1 - Este tigretón actualizará la rentabilidad YTD cada vez que se actualice el precio actual de las acciones.
DELIMITER //
DROP TRIGGER IF EXISTS ACTUALIZAR_RENTABILIDAD_YTD;
// CREATE TRIGGER ACTUALIZAR_RENTABILIDAD_YTD
BEFORE UPDATE ON ACCIONES
FOR EACH ROW
BEGIN
    IF NEW.PRECIO_ACTUAL != OLD.PRECIO_ACTUAL THEN
        SET NEW.RENTABILIDADYTD = ((NEW.PRECIO_ACTUAL * 100) / NEW.PRECIO_COMPRA) - 100;
    END IF;
END//
DELIMITER ;

SELECT * FROM ACCIONES;

UPDATE ACCIONES
SET PRECIO_ACTUAL = PRECIO_ACTUAL + 23
WHERE ISIN = 'US0378331005';

-- TIGRETÓN 2 - Este tigretón actualizará la tabla transaciones insertando una nueva fila cada vez que haya una retirada o ingreso de dinero en una cuenta corriente.
DELIMITER //
DROP TRIGGER IF EXISTS ACTUALIZAR_TRANSACCIONES;
//CREATE TRIGGER ACTUALIZAR_TRANSACCIONES
AFTER UPDATE ON CUENTA_CORRIENTE
FOR EACH ROW
BEGIN
  DECLARE V_COD_TRANSACCION INT;
  DECLARE V_COD_CLIENTE INT;
  DECLARE V_DNI CHAR(9);
  
  -- Damos valor a la variable V_COD_TRANSACCION
  SELECT (MAX(COD_TRANSACCION)+1) INTO V_COD_TRANSACCION FROM TRANSACCION;
  -- Damos valor a la variable V_COD_CLIENTE
  SELECT COD_CLIENTE INTO V_COD_CLIENTE FROM CUENTAS_BANCARIAS WHERE IBAN = NEW.IBAN;
  -- Damos valor a la variable V_DNI
  SELECT DNI INTO V_DNI FROM CUENTAS_BANCARIAS WHERE IBAN = NEW.IBAN;
  
  -- Comprobamos si el saldo ha disminuido, lo que implica una retirada
  IF NEW.SALDO < OLD.SALDO THEN
    INSERT INTO TRANSACCION VALUES ( V_COD_TRANSACCION,V_COD_CLIENTE,V_DNI,NEW.IBAN,(OLD.SALDO - NEW.SALDO),CURRENT_DATE(),'RETIRADA' );
  END IF;

  -- Comprobamos si el saldo ha aumentado, lo que implica un ingreso
  IF NEW.SALDO > OLD.SALDO THEN
    INSERT INTO TRANSACCION VALUES (V_COD_TRANSACCION,V_COD_CLIENTE,V_DNI,NEW.IBAN,(NEW.SALDO - OLD.SALDO),CURRENT_DATE(),'INGRESO');
  END IF;
END//
DELIMITER ;

-- INGRESAMOS DINERO USANDO NUESTRO PROCEDIMIENTO Y SEGUIDAMENTE COMPROBAMOS SI SE HA ACTUALIZADO LA TABLA TRANSACCION
CALL INGRESAR_DINERO('ES0012345678001234567890',100);

SELECT * FROM TRANSACCION;


-- *****************************************************************
--  ____    _    _   ______     _____    _____    ____     _____   *
-- / __ \  | |  | | |  ____|   |  __ \  |_   _|  / __ \   / ____|  *
-- | |  | | | |  | | | |__      | |  | |   | |   | |  | | | (___   *
-- | |  | | | |  | | |  __|     | |  | |   | |   | |  | |  \___ \  * 
-- | |__| | | |__| | | |____    | |__| |  _| |_  | |__| |  ____) | *
--  \___\_\  \____/  |______|   |_____/  |_____|  \____/  |_____/  *
--                                                                 *
--                                                                 *
--                          ____     _____                         *
--                         / __ \   / ____|                        *
--                        | |  | | | (___                          *
--                        | |  | |  \___ \                         *
--                        | |__| |  ____) |                        *
--                         \____/  |_____/                         *
--                                                                 *
--                                                                 *
--    ____    ______   _   _   _____    _____    _____             *
--   |  _ \  |  ____| | \ | | |  __ \  |_   _|  / ____|     /\     *
--   | |_) | | |__    |  \| | | |  | |   | |   | |  __     /  \    *
--   |  _ <  |  __|   | . ` | | |  | |   | |   | | |_ |   / /\ \   *
--   | |_) | | |____  | |\  | | |__| |  _| |_  | |__| |  / ____ \  *
--   |____/  |______| |_| \_| |_____/  |_____|  \_____| /_/    \_\ *     
-- *****************************************************************                                                                                                                            