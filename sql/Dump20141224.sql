-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: fjv
-- ------------------------------------------------------
-- Server version	5.6.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `claim_about` varchar(2047) DEFAULT NULL,
  `case_task` varchar(2047) DEFAULT NULL,
  `judge` varchar(511) DEFAULT NULL,
  `defendant` varchar(511) DEFAULT NULL,
  `claimant` varchar(511) DEFAULT NULL,
  `group_claim` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `creating_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `next_reminder` timestamp NULL DEFAULT NULL,
  `current_reminder` timestamp NULL DEFAULT NULL,
  `dead_line` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (1,'хищение зерна','57','Судья Петрова','подсудимый Иванов','Бодя','Хозяйственное дело',1,'2014-12-12 19:36:03','2014-12-18 12:00:00','2014-12-15 12:00:00','2014-12-24 22:00:00'),(2,'хищение Жеки',' 23','мы','Жека','Вадя','Прочие хищения',1,'2014-12-12 19:36:03','2015-01-06 19:56:38','2013-12-30 15:00:00','2014-12-04 02:53:51'),(3,'хищение зерна','57','new suddya','подсудимый Иванов','Бодя','Хозяйственное дело',1,'2014-12-17 19:09:03','2014-12-18 12:00:00','2014-12-15 12:00:00','2014-12-24 22:00:00'),(4,'Хищение здоровья','007','Жека','Вадя','Саня','Хищение здоровья',1,'2014-12-23 19:48:46','2015-01-06 19:48:47','2013-12-30 16:00:00','2014-12-04 02:45:59');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@admin.net','admin','Sashko'),(2,'al.s@inbox.ru','111','Liberum2'),(3,'vadg@ukr.net','111','vad'),(4,'dorf3ttt@ukr.net','111','al');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-24 21:15:02
