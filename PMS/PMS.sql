-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: localhost    Database: PMS
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Architect`
--

DROP TABLE IF EXISTS `Architect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Architect` (
  `ArchitectID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ArchitectID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Architect`
--

LOCK TABLES `Architect` WRITE;
/*!40000 ALTER TABLE `Architect` DISABLE KEYS */;
INSERT INTO `Architect` VALUES (1,'John','Smith','123-456-7890','john.smith@example.com','123 Architect St'),(2,'Jane','Doe','098-765-4321','jane.doe@example.com','456 Architect Ave');
/*!40000 ALTER TABLE `Architect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Contractor`
--

DROP TABLE IF EXISTS `Contractor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Contractor` (
  `ContractorID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ContractorID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contractor`
--

LOCK TABLES `Contractor` WRITE;
/*!40000 ALTER TABLE `Contractor` DISABLE KEYS */;
INSERT INTO `Contractor` VALUES (1,'Bob','Builder','234-567-8901','bob.builder@example.com','789 Contractor Rd'),(2,'Alice','Construct','876-543-2109','alice.construct@example.com','101 Contractor Blvd');
/*!40000 ALTER TABLE `Contractor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'Mike','Tyson','345-678-9012','mike.tyson@example.com','123 Customer Ln'),(2,'Jared','Goldman','765-432-1098','jared.goldman@example.com','456 Customer Dr');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Project`
--

DROP TABLE IF EXISTS `Project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Project` (
  `ProjectNo` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `ERFNo` varchar(50) DEFAULT NULL,
  `TotalFee` decimal(10,2) DEFAULT NULL,
  `AmountPaid` decimal(10,2) DEFAULT NULL,
  `Deadline` date DEFAULT NULL,
  `Finalised` tinyint(1) DEFAULT NULL,
  `CompletionDate` date DEFAULT NULL,
  `ArchitectID` int DEFAULT NULL,
  `ContractorID` int DEFAULT NULL,
  `CustomerID` int DEFAULT NULL,
  PRIMARY KEY (`ProjectNo`),
  KEY `ArchitectID` (`ArchitectID`),
  KEY `ContractorID` (`ContractorID`),
  KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`ArchitectID`) REFERENCES `Architect` (`ArchitectID`),
  CONSTRAINT `project_ibfk_2` FOREIGN KEY (`ContractorID`) REFERENCES `Contractor` (`ContractorID`),
  CONSTRAINT `project_ibfk_3` FOREIGN KEY (`CustomerID`) REFERENCES `Customer` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Project`
--

LOCK TABLES `Project` WRITE;
/*!40000 ALTER TABLE `Project` DISABLE KEYS */;
INSERT INTO `Project` VALUES (1,'House Tyson','House','123 Tyson St','ERF001',1000000.00,500000.00,'2024-12-31',0,NULL,1,1,1),(2,'Apartment Goldman','Apartment','456 Goldman Ave','ERF002',2000000.00,1000000.00,'2025-06-30',0,NULL,2,2,2);
/*!40000 ALTER TABLE `Project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-11  9:37:00
