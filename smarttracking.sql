-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 23, 2014 at 10:02 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `secureplanet`
--

-- --------------------------------------------------------

--
-- Table structure for table `smarttracking`
--

CREATE TABLE IF NOT EXISTS `smarttracking` (
  `rfid` varchar(20) NOT NULL,
  `lat` varchar(20) NOT NULL,
  `lng` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `smarttracking`
--

INSERT INTO `smarttracking` (`rfid`, `lat`, `lng`) VALUES
('$0009725134', '17.4416', '78.3826'),
('$0004358427', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826'),
('$0009725134', '17.4416', '78.3826');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
