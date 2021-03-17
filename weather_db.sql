/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 5.7.33 : Database - weather
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`weather` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `weather`;

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `base` varchar(255) DEFAULT NULL,
  `cod` int(11) DEFAULT NULL,
  `coord_lat` double DEFAULT NULL,
  `coord_lon` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sys_country` varchar(255) DEFAULT NULL,
  `sys_id` int(11) DEFAULT NULL,
  `sys_type` int(11) DEFAULT NULL,
  `timezone` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qsstlki7ni5ovaariyy9u8y79` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `weather_condition` */

DROP TABLE IF EXISTS `weather_condition`;

CREATE TABLE `weather_condition` (
  `city_id` int(11) NOT NULL,
  `datetime` bigint(20) NOT NULL,
  `weather_json` text,
  PRIMARY KEY (`city_id`,`datetime`),
  CONSTRAINT `FKkylpjw5mklvs07yfer82qv4yj` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
