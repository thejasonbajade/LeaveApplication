CREATE DATABASE  IF NOT EXISTS `leaveapp` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `leaveapp`;
-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: leaveapp
-- ------------------------------------------------------
-- Server version	5.5.47-0ubuntu0.14.04.1

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
-- Table structure for table `Comments`
--

DROP TABLE IF EXISTS `Comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comments` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Comment` text,
  `LeaveHistory_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`,`LeaveHistory_ID`),
  KEY `fk_Comments_LeaveHistory1_idx` (`LeaveHistory_ID`),
  CONSTRAINT `fk_Comments_LeaveHistory1` FOREIGN KEY (`LeaveHistory_ID`) REFERENCES `LeaveHistory` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comments`
--

LOCK TABLES `Comments` WRITE;
/*!40000 ALTER TABLE `Comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Credits`
--

DROP TABLE IF EXISTS `Credits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Credits` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SPCredits` float DEFAULT NULL,
  `Employee_ID` int(11) NOT NULL,
  `SLCredits` float DEFAULT NULL,
  `VLCredits` float DEFAULT NULL,
  `ELCredits` float DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_SoloParent_Employee1_idx` (`Employee_ID`),
  CONSTRAINT `fk_SoloParent_Employee1` FOREIGN KEY (`Employee_ID`) REFERENCES `Employee` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Credits`
--

LOCK TABLES `Credits` WRITE;
/*!40000 ALTER TABLE `Credits` DISABLE KEYS */;
/*!40000 ALTER TABLE `Credits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Department` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DepartmentName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Department`
--

LOCK TABLES `Department` WRITE;
/*!40000 ALTER TABLE `Department` DISABLE KEYS */;
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `EmploymentDate` date DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `ContactNo` varchar(45) DEFAULT NULL,
  `EmploymentStatus` enum('Probational','Regular') NOT NULL,
  `Sex` enum('Male','Female') DEFAULT NULL,
  `DateRegularized` date DEFAULT NULL,
  `Department_ID` int(11) NOT NULL,
  `IsSoloParent` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_Employee_Department1_idx` (`Department_ID`),
  CONSTRAINT `fk_Employee_Department1` FOREIGN KEY (`Department_ID`) REFERENCES `Department` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee_has_Role`
--

DROP TABLE IF EXISTS `Employee_has_Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee_has_Role` (
  `Employee_ID` int(11) NOT NULL,
  `Role_ID` int(11) NOT NULL,
  PRIMARY KEY (`Employee_ID`,`Role_ID`),
  KEY `fk_Employee_has_Role_Role1_idx` (`Role_ID`),
  KEY `fk_Employee_has_Role_Employee1_idx` (`Employee_ID`),
  CONSTRAINT `fk_Employee_has_Role_Employee1` FOREIGN KEY (`Employee_ID`) REFERENCES `Employee` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_has_Role_Role1` FOREIGN KEY (`Role_ID`) REFERENCES `Role` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee_has_Role`
--

LOCK TABLES `Employee_has_Role` WRITE;
/*!40000 ALTER TABLE `Employee_has_Role` DISABLE KEYS */;
/*!40000 ALTER TABLE `Employee_has_Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LeaveHistory`
--

DROP TABLE IF EXISTS `LeaveHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LeaveHistory` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Start` datetime DEFAULT NULL,
  `End` datetime DEFAULT NULL,
  `DateFiled` datetime DEFAULT NULL,
  `Reason` text,
  `Status` enum('Pending','SupervisorApproved','HRApproved','Cancelled','Not Taken') DEFAULT NULL,
  `Employee_ID` int(11) NOT NULL,
  `LeaveType` enum('SL','VL','EL','SPL','OFFSET') DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_LeaveHistory_Employee1_idx` (`Employee_ID`),
  CONSTRAINT `fk_LeaveHistory_Employee1` FOREIGN KEY (`Employee_ID`) REFERENCES `Employee` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LeaveHistory`
--

LOCK TABLES `LeaveHistory` WRITE;
/*!40000 ALTER TABLE `LeaveHistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `LeaveHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-15 13:50:58
