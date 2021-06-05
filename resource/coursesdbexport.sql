-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: coursesdb
-- ------------------------------------------------------
-- Server version	8.0.25

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


DROP SCHEMA IF EXISTS `coursesdb`;
CREATE SCHEMA `coursesdb`;
USE `coursesdb`;
--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client    = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('1712420','1712420','student'),('1712537','tankhoa99','student'),('admin','admin','ministry'),('admin2','admin2','ministry');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `class_id` varchar(45) NOT NULL,
  `total` int NOT NULL,
  `total_male` int NOT NULL,
  `total_female` int NOT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES ('17CTT1',100,80,20),('17CTT2',100,88,12),('17CTT3',89,23,66),('17CTT4',100,90,10);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_id` varchar(45) NOT NULL,
  `subject_id` varchar(45) NOT NULL,
  `semester_id` int NOT NULL,
  `lecturer` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `room` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `shift_id` int NOT NULL,
  `slots` int NOT NULL,
  PRIMARY KEY (`course_id`),
  KEY `fk_course_subject_idx` (`subject_id`),
  KEY `fk_course_semester_idx` (`semester_id`),
  KEY `fk_course_shift_idx` (`shift_id`),
  CONSTRAINT `fk_course_semester` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`semester_id`),
  CONSTRAINT `fk_course_shift` FOREIGN KEY (`shift_id`) REFERENCES `shift` (`id`),
  CONSTRAINT `fk_course_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('17_3-CSC13114-2','CSC13114',2,'Ngô Ngọc Đăng Khoa','E403',14,100),('17_3-CSC13115-2','CSC13115',2,'Trần Văn Quý','E403',9,100),('18_22-CSC10007-1','CSC10007',1,'Lê Viết Long','E302',17,110),('18_31-CSC13001-1','CSC13001',1,'Trần Duy Quang','E203',7,80),('18_31-CSC13003-1','CSC13003',1,'Trần Anh Duy','F201',9,90),('18_31-CSC13003-2','CSC13003',2,'Trần Anh Duy','F201',15,90),('18_31-CSC13010-1','CSC13010',1,'Trần Văn Quý','F202',13,100),('18_31-CSC13010-2','CSC13010',2,'Trần Văn Quý','F202',13,100),('19_1-CSC13102-2','CSC13102',2,'Nguyễn Văn Khiết','F202',15,100);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `student_id` varchar(45) NOT NULL,
  `course_id` varchar(45) NOT NULL,
  `registration_time` datetime NOT NULL,
  PRIMARY KEY (`student_id`,`course_id`),
  KEY `fk_registration_course_idx` (`course_id`),
  CONSTRAINT `fk_registration_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `fk_registration_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES ('1712420','17_3-CSC13115-2','2021-06-05 12:56:46'),('1712420','19_1-CSC13102-2','2021-06-05 12:56:40'),('1712537','17_3-CSC13114-2','2021-06-05 12:55:43'),('1712537','17_3-CSC13115-2','2021-06-05 12:55:14'),('1712537','19_1-CSC13102-2','2021-06-05 12:55:17');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration_session`
--

DROP TABLE IF EXISTS `registration_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration_session` (
  `session_id` int NOT NULL,
  `semester_id` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`session_id`),
  KEY `fk_session_course_idx` (`semester_id`),
  CONSTRAINT `fk_registration_session_semester` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`semester_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration_session`
--

LOCK TABLES `registration_session` WRITE;
/*!40000 ALTER TABLE `registration_session` DISABLE KEYS */;
INSERT INTO `registration_session` VALUES (1,2,'2021-03-07','2021-04-05'),(2,2,'2021-06-01','2021-08-01');
/*!40000 ALTER TABLE `registration_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `semester_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `school_year` varchar(45) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `active` int NOT NULL,
  PRIMARY KEY (`semester_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (1,'HK1','2020-2021','2021-03-09','2021-06-18',0),(2,'HK2','2020-2021','2021-03-09','2021-07-18',1),(3,'HK3','2020-2021','2020-07-19','2021-09-12',0);
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shift`
--

DROP TABLE IF EXISTS `shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shift` (
  `id` int NOT NULL AUTO_INCREMENT,
  `time` varchar(19) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shift`
--

LOCK TABLES `shift` WRITE;
/*!40000 ALTER TABLE `shift` DISABLE KEYS */;
INSERT INTO `shift` VALUES (1,'SUN(7:30 – 9:30)'),(2,'SUN(9:30 – 11:30)'),(3,'SUN(13:30 – 15:30)'),(4,'SUN(15:30 – 17:30)'),(5,'MON(7:30 – 9:30)'),(6,'MON(9:30 – 11:30)'),(7,'MON(13:30 – 15:30)'),(8,'MON(15:30 – 17:30)'),(9,'TUE(7:30 – 9:30)'),(10,'TUE(9:30 – 11:30)'),(11,'TUE(13:30 – 15:30)'),(12,'TUE(15:30 – 17:30)'),(13,'WED(7:30 – 9:30)'),(14,'WED(9:30 – 11:30)'),(15,'WED(13:30 – 15:30)'),(16,'WED(15:30 – 17:30)'),(17,'THU(7:30 – 9:30)'),(18,'THU(9:30 – 11:30)'),(19,'THU(13:30 – 15:30)'),(20,'THU(15:30 – 17:30)'),(21,'FRI(7:30 – 9:30)'),(22,'FRI(9:30 – 11:30)'),(23,'FRI(13:30 – 15:30)'),(24,'FRI(15:30 – 17:30)'),(25,'SAT(7:30 – 9:30)'),(26,'SAT(9:30 – 11:30)'),(27,'SAT(13:30 – 15:30)'),(28,'SAT(15:30 – 17:30)');
/*!40000 ALTER TABLE `shift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `dob` date NOT NULL,
  `username` varchar(45) NOT NULL,
  `class_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `fk_student_account_idx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('1712420','Khoa Phan','Male','1999-05-04','1712420','17CTT1'),('1712537','Phan Tấn Khoa','Male','1999-05-04','1712537','17CTT4');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subject_id` varchar(45) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `credits` int NOT NULL,
  PRIMARY KEY (`subject_id`),
  UNIQUE KEY `subject_id_UNIQUE` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('CSC10007','Hệ điều hành',4),('CSC13001','Lập trình Windows',4),('CSC13002','Nhập môn công nghệ phần mềm',4),('CSC13003','Kiểm thử phần mềm',4),('CSC13005','Phân tích và quản lý yêu cầu phần mềm',4),('CSC13008','Phát triển ứng dụng web',4),('CSC13009','Phát triển phần mềm cho thiết bị di động',4),('CSC13010','Thiết kế phần mềm',4),('CSC13102','Lập trình ứng dụng Java',4),('CSC13114','Phát triển ứng dụng web nâng cao',4),('CSC13115','Các công nghệ mới trong phát triển phần mềm',4);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'coursesdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-05 13:05:59
