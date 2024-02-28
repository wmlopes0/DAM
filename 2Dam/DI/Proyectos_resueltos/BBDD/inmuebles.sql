-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-12-2023 a las 10:03:09
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `inmobiliaria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inmuebles`
--

CREATE TABLE `inmuebles` (
  `idInmueble` int(11) NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `ventaAlquiler` varchar(255) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `telefono` int(11) DEFAULT 722748406
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inmuebles`
--

INSERT INTO `inmuebles` (`idInmueble`, `titulo`, `descripcion`, `foto`, `ventaAlquiler`, `precio`, `telefono`) VALUES
(1, 'Acogedor estudio en el centro', 'Estudio recientemente renovado, con 30m2 de espacio, ideal para una persona o pareja. Cuenta con modernas comodidades y diseño óptimo para el máximo aprovechamiento del espacio.', 'https://casasinhaus.com/wp-content/uploads/2021/01/casa-formentor-en-valencia-casa-modular-1-1150x646.jpg', 'Alquiler', 700, 722748406),
(2, 'Amplio apartamento con vistas al mar', 'Apartamento espacioso de 120m2, incluye 3 habitaciones, 2 baños completos, amplia terraza con vista panorámica al mar. Perfecto para familias o quienes buscan comodidad y una ubicación privilegiada.', 'https://phantom-expansion.unidadeditorial.es/7a01447f134fa1a4baadf1c5174644cf/resize/640/assets/multimedia/imagenes/2021/03/16/16158875126483.jpg', 'Venta', 250000, 722748406),
(3, 'Chalet adosado con jardín privado', 'Chalet de 200m2 con 4 amplias habitaciones, 3 baños completos, un hermoso jardín y garaje privado. Espacio ideal para familias, en una zona tranquila y con todas las comodidades para una vida confortable.', 'https://pics.nuroa.com/piso_de_lujo_de_84_m2_en_venta_en_avenida_eduard_maristany_badalona_provincia_de_barcelona_catalu%C3%B1a_7030095664457302008.jpg', 'Venta', 350000, 722748406),
(4, 'Piso moderno cerca de la universidad', 'Piso de 90m2, completamente amueblado, con 2 habitaciones espaciosas. Diseñado para estudiantes, ofrece un ambiente cómodo y práctico, cercano a universidades y servicios esenciales.', 'https://www.arquitecturaydiseno.es/medio/2021/08/28/casa-verona-fachada_13af2536_1500x1000.jpg', 'Alquiler', 800, 722748406),
(5, 'Oficina céntrica ideal para startups', 'Oficina de 150m2, diáfana y luminosa, equipada con dos aseos modernos y una amplia sala de reuniones. Ideal para empresas que buscan un espacio versátil y listo para ocupar en una ubicación estratégica.', 'https://s1.eestatic.com/2019/08/09/omicrono/omicrono_420219253_131917136_1706x960.jpg', 'Alquiler', 1500, 722748406);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `inmuebles`
--
ALTER TABLE `inmuebles`
  ADD PRIMARY KEY (`idInmueble`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `inmuebles`
--
ALTER TABLE `inmuebles`
  MODIFY `idInmueble` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
