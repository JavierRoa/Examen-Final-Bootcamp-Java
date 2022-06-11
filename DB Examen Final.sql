CREATE DATABASE  IF NOT EXISTS `practicafinal` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `practicafinal`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: practicafinal
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'$2a$12$SmArVUD1JNPvb0PiaUPf1.LfsfaOs0rGNLrc5.KMrtoJcGfcxaNLm','admin','José');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contenidos` varchar(255) NOT NULL,
  `cupos_disponibles` int NOT NULL,
  `descripción` varchar(255) NOT NULL,
  `fecha_inicio` datetime(6) NOT NULL,
  `fecha_termino` datetime(6) NOT NULL,
  `imagen_ref` varchar(255) NOT NULL,
  `cupos_restantes` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (1,'Este curso está diseñado para desarrollar las habilidades de back-end, front-end y manejo de base de datos',80,'FullStack Java','2022-06-15 00:00:00.000000','2022-08-17 00:00:00.000000','https://www.pngkey.com/png/full/264-2646582_logo-transparent-background-java.png',5),(2,'Este curso está diseñado para desarrollar las habilidades de back-end, front-end y manejo de base de datos',80,'FullStack Ruby','2022-06-15 00:00:00.000000','2022-08-17 00:00:00.000000','https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Ruby_logo.svg/1024px-Ruby_logo.svg.png',10),(3,'Este curso está diseñado para desarrollar las habilidades de front-end de forma profunda, además del uso de diferentes framework para ello.',80,'Dev. Front-End','2022-06-15 00:00:00.000000','2022-08-17 00:00:00.000000','https://image.shutterstock.com/image-vector/frontend-concept-icon-software-development-260nw-1556812094.jpg',0);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(70) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cctf52vdydyhebl1sotdxrwyw` (`descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (3,'Antofagasta'),(1,'Arica y Parinacota'),(4,'Atacama'),(15,'Aysén del General Carlos Ibáñez del Campo'),(11,'Biobío'),(5,'Coquimbo'),(12,'La Araucanía'),(8,'Libertador General Bernardo O\'Higgins'),(14,'Los Lagos'),(13,'Los Ríos'),(16,'Magallanes y de la Antártica Chilena'),(9,'Maule'),(7,'Metropolitana de Santiago'),(10,'Ñuble'),(2,'Tarapacá'),(6,'Valparaiso');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `primer_apellido` varchar(255) NOT NULL,
  `segundo_apellido` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `edad` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `primer_nombre` varchar(255) NOT NULL,
  `segundo_nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rut` varchar(255) NOT NULL,
  `telefono` int NOT NULL,
  `region_id` bigint DEFAULT NULL,
  `curso_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKobd0w0n1b9nx16eideh12rcow` (`region_id`),
  KEY `FKoyy18bfj08k5e0rxq1qhx68s7` (`curso_id`),
  CONSTRAINT `FKobd0w0n1b9nx16eideh12rcow` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`),
  CONSTRAINT `FKoyy18bfj08k5e0rxq1qhx68s7` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Roa','García','Los Cipreses 5651',30,'jroa2010@alu.uct.cl','Javier','César','$2a$12$SmArVUD1JNPvb0PiaUPf1.LfsfaOs0rGNLrc5.KMrtoJcGfcxaNLm','17916106-0',946685348,12,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-10 23:41:04
