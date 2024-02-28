-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-01-2024 a las 16:11:57
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
-- Base de datos: `cine`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarios`
--

CREATE TABLE `horarios` (
  `idHorario` int(11) NOT NULL,
  `idSala` int(11) NOT NULL,
  `idPelicula` int(11) NOT NULL,
  `NumeroSemana` int(11) NOT NULL,
  `HoraSesion1` varchar(255) DEFAULT NULL,
  `HoraSesion2` varchar(255) DEFAULT NULL,
  `HoraSesion3` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `horarios`
--

INSERT INTO `horarios` (`idHorario`, `idSala`, `idPelicula`, `NumeroSemana`, `HoraSesion1`, `HoraSesion2`, `HoraSesion3`) VALUES
(1, 1, 5, 1, '12:00', '16:00', '20:00'),
(2, 2, 3, 2, '13:00', '17:00', '21:00'),
(3, 3, 2, 3, '14:00', '18:00', '22:00'),
(4, 4, 1, 4, '12:30', '16:30', '20:30'),
(5, 5, 4, 5, '13:30', '17:30', '21:30'),
(6, 6, 6, 6, '14:30', '18:30', '22:30'),
(7, 7, 7, 7, '10:00', '14:00', '18:00'),
(8, 8, 8, 1, '11:00', '15:00', '19:00'),
(9, 1, 9, 2, '12:00', '16:00', '20:00'),
(10, 2, 10, 3, '13:00', '17:00', '21:00'),
(11, 3, 11, 4, '11:30', '15:30', '19:30'),
(12, 4, 12, 5, '12:30', '16:30', '20:30'),
(13, 5, 13, 6, '13:30', '17:30', '21:30'),
(14, 6, 1, 7, '14:30', '18:30', '22:30'),
(15, 7, 2, 1, '10:00', '14:00', '18:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peliculas`
--

CREATE TABLE `peliculas` (
  `idPelicula` int(11) NOT NULL,
  `Titulo` varchar(255) NOT NULL,
  `Duracion` int(11) NOT NULL,
  `Imagen` varchar(255) DEFAULT NULL,
  `Edad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `peliculas`
--

INSERT INTO `peliculas` (`idPelicula`, `Titulo`, `Duracion`, `Imagen`, `Edad`) VALUES
(1, 'The Godfather', 175, 'https://pics.filmaffinity.com/the_godfather_part_iii-171971720-large.jpg', 18),
(2, 'The Shawshank Redemption', 142, 'https://pics.filmaffinity.com/the_shawshank_redemption-576140557-large.jpg', 15),
(3, 'Schindlers List', 195, 'https://pics.filmaffinity.com/schindler_s_list-473662617-large.jpg', 18),
(4, 'Raging Bull', 129, 'https://pics.filmaffinity.com/raging_bull-820857019-large.jpg', 18),
(5, 'Casablanca', 102, 'https://pics.filmaffinity.com/casablanca-612211000-large.jpg', 12),
(6, 'Citizen Kane', 119, 'https://pics.filmaffinity.com/citizen_kane-217208961-large.jpg', 12),
(7, 'Gone with the Wind', 238, 'https://pics.filmaffinity.com/gone_with_the_wind-432251527-large.jpg', 12),
(8, 'The Wizard of Oz', 102, 'https://pics.filmaffinity.com/the_wizard_of_oz-385351250-large.jpg', 0),
(9, 'One Flew Over the Cuckoos Nest', 133, 'https://pics.filmaffinity.com/one_flew_over_the_cuckoo_s_nest-669408644-large.jpg', 18),
(10, 'Lawrence of Arabia', 218, 'https://pics.filmaffinity.com/lawrence_of_arabia-588633055-large.jpg', 12),
(11, 'Vertigo', 128, 'https://pics.filmaffinity.com/vertigo-154050178-large.jpg', 12),
(12, 'Psycho', 109, 'https://pics.filmaffinity.com/psycho-805182707-large.jpg', 18),
(13, 'The Godfather Part II', 202, 'https://pics.filmaffinity.com/the_godfather_part_ii-617901053-large.jpg', 18);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salas`
--

CREATE TABLE `salas` (
  `idSala` int(11) NOT NULL,
  `NumeroSala` int(11) NOT NULL,
  `NumeroButacas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `salas`
--

INSERT INTO `salas` (`idSala`, `NumeroSala`, `NumeroButacas`) VALUES
(1, 1, 150),
(2, 2, 200),
(3, 3, 120),
(4, 4, 100),
(5, 5, 180),
(6, 6, 160),
(7, 7, 110),
(8, 8, 170);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `horarios`
--
ALTER TABLE `horarios`
  ADD PRIMARY KEY (`idHorario`),
  ADD KEY `idSala` (`idSala`),
  ADD KEY `idPelicula` (`idPelicula`);

--
-- Indices de la tabla `peliculas`
--
ALTER TABLE `peliculas`
  ADD PRIMARY KEY (`idPelicula`);

--
-- Indices de la tabla `salas`
--
ALTER TABLE `salas`
  ADD PRIMARY KEY (`idSala`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `horarios`
--
ALTER TABLE `horarios`
  ADD CONSTRAINT `horarios_ibfk_1` FOREIGN KEY (`idSala`) REFERENCES `salas` (`idSala`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `horarios_ibfk_2` FOREIGN KEY (`idPelicula`) REFERENCES `peliculas` (`idPelicula`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
