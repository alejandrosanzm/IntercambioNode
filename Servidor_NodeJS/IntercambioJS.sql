-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 06-02-2020 a las 06:03:34
-- Versión del servidor: 10.4.10-MariaDB
-- Versión de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `IntercambioJS`
--
CREATE DATABASE IF NOT EXISTS `IntercambioJS` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `IntercambioJS`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ShoppingList`
--

DROP TABLE IF EXISTS `ShoppingList`;
CREATE TABLE `ShoppingList` (
  `id` int(11) NOT NULL,
  `name` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 0,
  `amount` float NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `ShoppingList`
--

INSERT INTO `ShoppingList` (`id`, `name`, `quantity`, `amount`) VALUES
(1, 'Patatas', 2, 1),
(7, 'Tomates', 2, 1.4),
(9, 'Pan', 2, 0.5),
(10, 'Aceitunas', 2, 2.3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ShoppingList`
--
ALTER TABLE `ShoppingList`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ShoppingList`
--
ALTER TABLE `ShoppingList`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
