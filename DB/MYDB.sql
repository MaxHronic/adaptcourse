CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `courseId` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `about` tinytext,
  `personId` bigint(20) DEFAULT NULL,
  `course_mark` int(11) DEFAULT NULL,
  `speciality` char(2) DEFAULT NULL,
  PRIMARY KEY (`courseId`),
  UNIQUE KEY `courseId_UNIQUE` (`courseId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'first course','about FirstCourse',1234,93,'ТР'),(2,'second course','about SecondCourse',1234,67,'ТВ'),(3,'third course','about Third',1234,42,'ТВ'),(4,'third course part 2','about ThirdCourse',1234,90,'ТР'),(5,'first course part 2','Розробка модуля збору сенсорних даних віддаленого використання',1234,94,'ТР'),(6,'first course part 3','Розробка модуля збору сенсорних даних віддаленого використання',1234,0,'ТР'),(7,'Розробка модуля збору','Розробка модуля збору сенсорних даних віддаленого використання',1234,0,'ТР'),(8,'Розробка модуля збору','Розробка модуля збору сенсорних даних віддаленого використання',1234,12,'ТИ'),(9,'тест1','про тест1',1234,0,'ТВ');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lections`
--

DROP TABLE IF EXISTS `lections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lections` (
  `lectionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `courseId` bigint(20) NOT NULL,
  `topicId` bigint(20) NOT NULL,
  `in_topicId` int(11) DEFAULT NULL,
  `lec_name` tinytext NOT NULL,
  `lec_about` text NOT NULL,
  PRIMARY KEY (`lectionId`),
  UNIQUE KEY `lectionId_UNIQUE` (`lectionId`),
  UNIQUE KEY `unique_index` (`courseId`,`topicId`,`in_topicId`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lections`
--

LOCK TABLES `lections` WRITE;
/*!40000 ALTER TABLE `lections` DISABLE KEYS */;
INSERT INTO `lections` VALUES (1,1,1,1,'Lec1','Lec1 about'),(2,1,1,2,'Lec2','Lec2 about'),(3,1,2,1,'Lec21','Lec1 about'),(4,1,2,2,'Lec22','Lec2 about'),(5,1,2,3,'Lec21','Lec1 about'),(6,1,2,4,'Lec21','Lec1 about'),(7,1,3,1,'Lec21','Lec1 about'),(8,1,3,2,'Lec21','Lec1 about'),(9,1,3,3,'Lec21','Lec1 about'),(10,1,3,4,'Lec21','Lec1 about'),(11,1,4,1,'Lec21','Lec1 about'),(12,1,4,2,'Lec21','Lec1 about'),(13,1,4,3,'Lec21','Lec1 about'),(14,1,4,4,'Lec21','Lec1 about'),(15,1,4,5,'Lec21','Lec1 about'),(16,1,4,6,'Lec21','Lec1 about'),(17,2,1,1,'Lec21','Lec1 about'),(18,2,1,2,'Lec21','Lec1 about'),(19,2,1,3,'Lec21','Lec1 about'),(20,2,1,4,'Lec21','Lec1 about'),(21,2,1,5,'Lec21','Lec1 about'),(22,2,1,6,'Lec21','Lec1 about'),(23,2,2,1,'Lec21','Lec1 about'),(24,2,2,2,'Lec21','Lec1 about'),(25,2,2,3,'Lec21','Lec1 about'),(26,2,2,4,'Lec21','Lec1 about'),(27,2,2,5,'Lec21','Lec1 about'),(28,2,2,6,'Lec21','Lec1 about'),(29,2,3,1,'Lec21','Lec1 about'),(30,2,3,2,'Lec21','Lec1 about'),(31,2,3,3,'Lec21','Lec1 about'),(32,2,3,4,'Lec21','Lec1 about'),(33,2,3,5,'Lec21','Lec1 about'),(34,2,3,6,'Lec21','Lec1 about'),(35,3,1,1,'Lec21','Lec1 about'),(36,3,1,2,'Lec21','Lec1 about'),(37,3,1,3,'Lec21','Lec1 about'),(38,3,1,4,'Lec21','Lec1 about'),(39,3,1,5,'Lec21','Lec1 about'),(40,3,1,6,'Lec21','Lec1 about'),(41,4,1,1,'Lec21','Lec1 about'),(42,4,1,2,'Lec21','Lec1 about'),(43,4,1,3,'Lec21','Lec1 about'),(44,4,1,4,'Lec21','Lec1 about'),(45,4,1,5,'Lec21','Lec1 about'),(46,4,1,6,'Lec21','Lec1 about'),(47,5,1,1,'Lec21','Lec1 about'),(48,5,1,2,'Lec21','Lec1 about'),(49,5,1,3,'Lec21','Lec1 about'),(50,5,1,4,'Lec21','Lec1 about'),(51,5,1,5,'Lec21','Lec1 about'),(52,5,1,6,'Lec21','Lec1 about'),(53,5,2,1,'Lec21','Lec1 about'),(54,5,2,2,'Lec21','Lec1 about'),(55,5,2,3,'Lec21','Lec1 about'),(56,5,2,4,'Lec21','Lec1 about'),(57,5,2,5,'Lec21','Lec1 about'),(58,5,2,6,'Lec21','Lec1 about');
/*!40000 ALTER TABLE `lections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pages`
--

DROP TABLE IF EXISTS `pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pages` (
  `pageId` bigint(20) NOT NULL AUTO_INCREMENT,
  `lectionId` bigint(20) NOT NULL,
  `topicId` bigint(20) NOT NULL,
  `text` text,
  `text_ext` text,
  `in_lectionId` int(11) DEFAULT NULL,
  PRIMARY KEY (`pageId`,`topicId`),
  UNIQUE KEY `pageId_UNIQUE` (`pageId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pages`
--

LOCK TABLES `pages` WRITE;
/*!40000 ALTER TABLE `pages` DISABLE KEYS */;
INSERT INTO `pages` VALUES (1,1,1,'text of page 1','text of page 1 topic1 lec1',1),(2,1,1,'text of page 2','text of page 2 topic1 lec1',2),(3,1,1,'text of page 3','text of page 3 topic1 lec1',3),(4,1,1,'text of page 4','text of page 3 topic1 lec1',4);
/*!40000 ALTER TABLE `pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalprogress`
--

DROP TABLE IF EXISTS `personalprogress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personalprogress` (
  `personId` bigint(20) NOT NULL,
  `courseId` bigint(20) NOT NULL,
  `topicId` bigint(20) NOT NULL,
  `pageId` bigint(20) DEFAULT NULL,
  `mark` int(11) DEFAULT NULL,
  `progress` int(11) DEFAULT NULL,
  `complete_count` int(11) DEFAULT NULL,
  `lectionId` bigint(20) DEFAULT '0',
  UNIQUE KEY `uc_progress` (`personId`,`courseId`,`topicId`,`pageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalprogress`
--

LOCK TABLES `personalprogress` WRITE;
/*!40000 ALTER TABLE `personalprogress` DISABLE KEYS */;
INSERT INTO `personalprogress` VALUES (1,1,1,1,87,0,1,0),(1234,12,1,NULL,0,NULL,2,0),(1,1,1,2,0,0,0,0),(1,2,2,1,0,0,1,0),(1234,12,1,NULL,NULL,NULL,NULL,0),(1234,12,1,NULL,NULL,NULL,NULL,0),(1234,12,1,NULL,NULL,NULL,NULL,0),(1243,1,1,1,0,0,0,0),(123,1,1,1,42,1,1,0),(123,12,5,1,0,0,0,0),(123,12,6,1,0,0,0,0),(123,12,7,1,76,100,2,0),(123,1,1,NULL,0,0,0,0),(1234,1,1,0,0,0,0,0);
/*!40000 ALTER TABLE `personalprogress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `questionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `topicId` bigint(20) DEFAULT NULL,
  `text` text,
  `answer1` tinytext,
  `weight1` float DEFAULT NULL,
  `answer2` tinytext,
  `weight2` float DEFAULT NULL,
  `answer3` tinytext,
  `weight3` float DEFAULT NULL,
  `answer4` tinytext,
  `weight4` float DEFAULT NULL,
  `q_weight` float DEFAULT NULL,
  `control_type` tinyint(1) DEFAULT NULL,
  `in_topicId` int(11) DEFAULT NULL,
  PRIMARY KEY (`questionId`),
  UNIQUE KEY `questionId_UNIQUE` (`questionId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,1,'1','1',0.1,'2',0.2,'3',0.3,'4',0.4,2,1,1),(2,2,'2','1',0.1,'2',0.2,'3',0.3,'4',0.4,2,1,1);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `topicId` bigint(20) NOT NULL AUTO_INCREMENT,
  `courseId` bigint(20) DEFAULT NULL,
  `name` tinytext,
  `counter` bigint(20) DEFAULT NULL,
  `topic_mark` int(11) DEFAULT NULL,
  `about` text,
  `in_courseId` int(11) DEFAULT NULL,
  UNIQUE KEY `topicId_UNIQUE` (`topicId`),
  UNIQUE KEY `uc_topic` (`courseId`,`topicId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,1,'test1',0,0,'about topic test1',1),(2,1,'test1.1',0,0,'about topic test1.1',2),(3,2,'topic21',0,0,'about topic test21',1),(4,3,'topic31',0,0,'about topic test31',1),(5,4,'t1',0,0,'about topic test31',1),(6,4,'t2',0,0,'about topic test122',2),(7,4,'t3',0,0,'about topic test123',3),(8,3,'topic 1',0,0,'about topic 1 in course3',1);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `personId` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  UNIQUE KEY `personId_UNIQUE` (`personId`),
  UNIQUE KEY `uc_name` (`login`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=1281 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1234,'max','123456',1,NULL,NULL),(1235,'test3','123',1,NULL,NULL),(1236,'test1','123456',1,NULL,NULL),(1237,'test','1234567',1,NULL,NULL),(1238,'max','test23',0,NULL,NULL),(1239,'max2','test123',1,NULL,NULL),(1240,'true?','123456',1,NULL,NULL),(1241,'true?1','123456',1,NULL,NULL),(1242,'maxmax','123456',1,NULL,NULL),(1243,'man','123456',0,NULL,NULL),(1244,'login\"','pas+',1,NULL,NULL),(1245,'max','qwerty123',0,NULL,NULL),(1247,'max1','qwerty123',0,NULL,NULL),(1248,'max2','qwerty123',0,NULL,NULL),(1249,'max3','qwerty123',0,NULL,NULL),(1250,'max4','qwerty123',0,NULL,NULL),(1251,'max5','qwerty123',0,NULL,NULL),(1252,'max6','qwerty',0,NULL,NULL),(1254,'max7','qwerty',0,NULL,NULL),(1256,'max8','qwerty',0,NULL,NULL),(1258,'max9','qwert2y',0,NULL,NULL),(1259,'max10','qwert2y',0,NULL,NULL),(1260,'max23','123456',1,NULL,NULL),(1261,'mtest','123456',1,NULL,NULL),(1267,'max24','123456',1,NULL,NULL),(1268,'max14','123456',0,NULL,NULL),(1269,'mtest','122345',1,NULL,NULL),(1270,'true','122345',1,NULL,NULL),(1273,'test','123456',0,NULL,NULL),(1276,'test12','123456',0,NULL,NULL),(1277,'mtest','123321',0,NULL,NULL),(1278,'тест','123456',0,NULL,NULL),(1280,'test321','test321',0,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-01 23:33:09
