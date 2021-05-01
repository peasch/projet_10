-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ladedb
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `commentary`
--

LOCK TABLES `commentary` WRITE;
/*!40000 ALTER TABLE `commentary` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `length`
--

LOCK TABLES `length` WRITE;
/*!40000 ALTER TABLE `length` DISABLE KEYS */;
INSERT INTO `length` VALUES (1,1,'6C','Cul de Sac',8),(2,2,'6A','Le Dièdre',0),(3,3,'7B','Touché Coulet',15),(4,4,'6A+','C\'est Chouette',13),(5,5,'6A','Belle des bois',10),(6,6,'6B+','Le Bourdon',10),(7,7,'5C','Essaim d\'abeilles',12),(8,8,'6A','Moustiquaire',8),(9,9,'6B','Ready to Bloc',9),(10,10,'6A','Tibloc',10),(11,11,'7A+','Polymorphe',10),(12,12,'7A','Anamorphose',10),(13,13,'6A+','Mystère de l\'ouest',10),(14,14,'6B','Atlantide',0),(15,15,'6A','Memory',0),(16,16,'5C','Les corbeaux',8),(17,17,'6B','Erudit',8),(18,18,'6A+','Sauvageons',8),(19,19,'6A','Snow',8),(20,20,'6A','La faux',8),(21,21,'5C','Ice and Fire',8);
/*!40000 ALTER TABLE `length` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,1,8),(2,1,0),(3,2,15),(4,2,13),(5,2,10),(6,2,10),(7,2,12),(8,2,8),(9,2,9),(10,2,10),(11,3,10),(12,3,10),(13,3,10),(14,3,0),(15,3,0),(16,4,8),(17,4,8),(18,4,8),(19,4,8),(20,4,8),(21,4,8);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sector`
--

LOCK TABLES `sector` WRITE;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` VALUES (1,1,'secteur du fond','secteur à 2 voies, une condamnée'),(2,1,'secteur Nord Ouest','Secteur avec des voies très intéressantes, blocs et technique... Le rocher est parfois péteux'),(3,1,'secteur Ouest','secteurs à 5 voies'),(4,1,'le mur du Nord','secteur à 6 voies'),(5,2,'Secteur du plateau','secteur contenant 11 blocs de 2 à 4 voies'),(6,2,'Secteur Pierres Posées','secteur contenant 6 blocs de 2 à 3 voies'),(7,2,'Secteur Blocs Roses','secteur contenant 10 blocs de 1 a 4 voies'),(8,2,'Secteur Usine','secteur contenant 4 blocs de 1 a 3 voies'),(9,2,'Secteur de la cave','secteur contenant 2 blocs de 2 voies'),(10,1,'tralala','vachement bien');
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spit`
--

LOCK TABLES `spit` WRITE;
/*!40000 ALTER TABLE `spit` DISABLE KEYS */;
/*!40000 ALTER TABLE `spit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spot`
--

LOCK TABLES `spot` WRITE;
/*!40000 ALTER TABLE `spot` DISABLE KEYS */;
INSERT INTO `spot` VALUES (1,0,'Chardonnay','Chardonnay 71700 France','46.51559344','4.85709039',NULL),(2,0,'Mont Bottey','Verosvres 71220 France','46.400345','4.444090',NULL),(3,NULL,'Cormatin','Cormatin 71460, Saoneet-Loire France','46.32165','4.41187',NULL),(4,NULL,'Le Viaduc des Fauvettes','Palaiseau, Ile de France, Essonne 91 France','48.4043','2.99',NULL),(5,NULL,'Falaise d\'Appy','Appy 09250 France','42.790115','1.732001',NULL),(15,NULL,'spot de tom','chez tom','12.12','13.13',3),(16,NULL,'t','t','t','t',3);
/*!40000 ALTER TABLE `spot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `topo`
--

LOCK TABLES `topo` WRITE;
/*!40000 ALTER TABLE `topo` DISABLE KEYS */;
INSERT INTO `topo` VALUES (1,1,'Grimpe Chardonnay, descend le pinard','topo de grimpe sur Chardonnay','71700 Chardonnay France','2020-02-22 11:32:12',1,1);
/*!40000 ALTER TABLE `topo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `topo_has_spot`
--

LOCK TABLES `topo_has_spot` WRITE;
/*!40000 ALTER TABLE `topo_has_spot` DISABLE KEYS */;
INSERT INTO `topo_has_spot` VALUES (1,1);
/*!40000 ALTER TABLE `topo_has_spot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Schaming','Pierre-Alain','boss@boss.fr',1,'',''),(2,'Calade','Alex','alex@boss.fr',0,'',''),(3,'tom','Saywer','tom@saw.fr',NULL,'test123','TomSaw'),(4,'Koffe','Jean-Pierre','jpk@jpk.fr',NULL,'test123','JPK'),(5,'Drucker','Michel','michel@orange.fr',NULL,'test123','Druckmich'),(6,'Schaming','Emilie','truc@much.fr',NULL,'123456','truc');
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

-- Dump completed on 2021-05-01 16:30:52
