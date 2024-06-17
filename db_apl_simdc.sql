-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.3.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table db_apl_simdc.tb_devices
CREATE TABLE IF NOT EXISTS `tb_devices` (
  `device_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `model_id` int(10) unsigned NOT NULL,
  `serial_number` char(100) NOT NULL DEFAULT '',
  `storage_cap` float(6,2) DEFAULT NULL,
  `memory_cap` float(6,2) DEFAULT NULL,
  `cores` int(10) unsigned DEFAULT NULL,
  `location` char(100) NOT NULL,
  `rack_number` int(10) unsigned DEFAULT NULL,
  `hostname` char(255) NOT NULL,
  PRIMARY KEY (`device_id`),
  UNIQUE KEY `model_id` (`model_id`),
  KEY `FK__tb_user` (`user_id`),
  CONSTRAINT `FK__tb_dev_model` FOREIGN KEY (`model_id`) REFERENCES `tb_dev_model` (`model_id`),
  CONSTRAINT `FK__tb_user` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_apl_simdc.tb_devices: ~0 rows (approximately)

-- Dumping structure for table db_apl_simdc.tb_device_group
CREATE TABLE IF NOT EXISTS `tb_device_group` (
  `group_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `group_name` char(50) NOT NULL,
  `group_icon` char(50) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_apl_simdc.tb_device_group: ~0 rows (approximately)

-- Dumping structure for table db_apl_simdc.tb_dev_model
CREATE TABLE IF NOT EXISTS `tb_dev_model` (
  `model_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `manu_id` int(10) unsigned NOT NULL,
  `model_name` char(255) NOT NULL,
  PRIMARY KEY (`model_id`),
  KEY `FK_tb_dev_model_tb_manufacture` (`manu_id`),
  CONSTRAINT `FK_tb_dev_model_tb_manufacture` FOREIGN KEY (`manu_id`) REFERENCES `tb_manufacture` (`manu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_apl_simdc.tb_dev_model: ~0 rows (approximately)

-- Dumping structure for table db_apl_simdc.tb_manufacture
CREATE TABLE IF NOT EXISTS `tb_manufacture` (
  `manu_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` int(10) unsigned NOT NULL,
  `manu_name` char(255) NOT NULL,
  PRIMARY KEY (`manu_id`),
  KEY `FK_tb_manufacture_tb_device_group` (`group_id`),
  CONSTRAINT `FK_tb_manufacture_tb_device_group` FOREIGN KEY (`group_id`) REFERENCES `tb_device_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_apl_simdc.tb_manufacture: ~0 rows (approximately)

-- Dumping structure for table db_apl_simdc.tb_user
CREATE TABLE IF NOT EXISTS `tb_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` char(50) NOT NULL DEFAULT '0',
  `user_password` varchar(255) NOT NULL DEFAULT '',
  `user_picture` varchar(255) NOT NULL DEFAULT '',
  `user_level` enum('admin','user') NOT NULL,
  `user_status` enum('1','0') NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_apl_simdc.tb_user: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
