<<<<<<< HEAD
<<<<<<< HEAD
CREATE DATABASE  IF NOT EXISTS `iwish`;
USE `iwish`;


--
-- Table structure for table `collected`
--

DROP TABLE IF EXISTS `collected`;

CREATE TABLE `collected` (
  `date` date NOT NULL,
  `username` varchar(50) NOT NULL,
  `itemname` varchar(100) NOT NULL,
  `image` blob,
  `price` varchar(45) NOT NULL DEFAULT '0',
  `notify` varchar(45) NOT NULL,
  PRIMARY KEY (`username`,`itemname`),
  CONSTRAINT `collected_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) 

--
-- Dumping data for table `collected`
--

LOCK TABLES `collected` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `friendrequest`
--

DROP TABLE IF EXISTS `friendrequest`;

CREATE TABLE `friendrequest` (
  `fromuser` varchar(50) NOT NULL,
  `touser` varchar(50) NOT NULL,
  `status` varchar(45) NOT NULL,
  `notified` varchar(10) NOT NULL,
  KEY `fromuser` (`fromuser`),
  KEY `touser` (`touser`),
  CONSTRAINT `friendrequest_ibfk_1` FOREIGN KEY (`fromuser`) REFERENCES `users` (`username`),
  CONSTRAINT `friendrequest_ibfk_2` FOREIGN KEY (`touser`) REFERENCES `users` (`username`)
)

--
-- Dumping data for table `friendrequest`
--

LOCK TABLES `friendrequest` WRITE;
INSERT INTO `friendrequest` VALUES ('test1','test2','Accepted','No');



END */;;
DELIMITER ;


--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;

CREATE TABLE `friends` (
  `username` varchar(50) NOT NULL,
  `friendname` varchar(50) NOT NULL,
  `date` date NOT NULL,
  KEY `username` (`username`),
  KEY `friendname` (`friendname`),
  CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`friendname`) REFERENCES `users` (`username`)
) 

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
INSERT INTO `friends` VALUES ('test1','test2','2023-03-19'),('test1','test2','2023-03-19'),('test2','test1','2023-03-19');
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `itemname` varchar(100) NOT NULL,
  `category` varchar(45) NOT NULL DEFAULT 'Other',
  `price` decimal(10,0) NOT NULL,
  `image` blob,
  PRIMARY KEY (`itemname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;

UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `user_name_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
)

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES ('aly','123','ali@gmail.com','0102000'),('test1','123','email','0101010'),('test2','123','email2','0101010');
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;

CREATE TABLE `wishlist` (
  `image` blob,
  `date` date NOT NULL,
  `username` varchar(50) NOT NULL,
  `itemname` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL DEFAULT 'Other',
  `price` decimal(10,0) NOT NULL DEFAULT '0',
  `remained` decimal(10,0) NOT NULL,
  PRIMARY KEY (`username`,`itemname`),
  CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) 

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;

UNLOCK TABLES;

=======
CREATE DATABASE  IF NOT EXISTS `iwish`;
USE `iwish`;


--
-- Table structure for table `collected`
--

DROP TABLE IF EXISTS `collected`;

CREATE TABLE `collected` (
  `date` date NOT NULL,
  `username` varchar(50) NOT NULL,
  `itemname` varchar(100) NOT NULL,
  `image` blob,
  `price` varchar(45) NOT NULL DEFAULT '0',
  `notify` varchar(45) NOT NULL,
  PRIMARY KEY (`username`,`itemname`),
  CONSTRAINT `collected_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) 

--
-- Dumping data for table `collected`
--

LOCK TABLES `collected` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `friendrequest`
--

DROP TABLE IF EXISTS `friendrequest`;

CREATE TABLE `friendrequest` (
  `fromuser` varchar(50) NOT NULL,
  `touser` varchar(50) NOT NULL,
  `status` varchar(45) NOT NULL,
  `notified` varchar(10) NOT NULL,
  KEY `fromuser` (`fromuser`),
  KEY `touser` (`touser`),
  CONSTRAINT `friendrequest_ibfk_1` FOREIGN KEY (`fromuser`) REFERENCES `users` (`username`),
  CONSTRAINT `friendrequest_ibfk_2` FOREIGN KEY (`touser`) REFERENCES `users` (`username`)
)

--
-- Dumping data for table `friendrequest`
--

LOCK TABLES `friendrequest` WRITE;
INSERT INTO `friendrequest` VALUES ('test1','test2','Accepted','No');



END */;;
DELIMITER ;


--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;

CREATE TABLE `friends` (
  `username` varchar(50) NOT NULL,
  `friendname` varchar(50) NOT NULL,
  `date` date NOT NULL,
  KEY `username` (`username`),
  KEY `friendname` (`friendname`),
  CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`friendname`) REFERENCES `users` (`username`)
) 

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
INSERT INTO `friends` VALUES ('test1','test2','2023-03-19'),('test1','test2','2023-03-19'),('test2','test1','2023-03-19');
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `itemname` varchar(100) NOT NULL,
  `category` varchar(45) NOT NULL DEFAULT 'Other',
  `price` decimal(10,0) NOT NULL,
  `image` blob,
  PRIMARY KEY (`itemname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;

UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `user_name_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
)

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES ('aly','123','ali@gmail.com','0102000'),('test1','123','email','0101010'),('test2','123','email2','0101010');
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;

CREATE TABLE `wishlist` (
  `image` blob,
  `date` date NOT NULL,
  `username` varchar(50) NOT NULL,
  `itemname` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL DEFAULT 'Other',
  `price` decimal(10,0) NOT NULL DEFAULT '0',
  `remained` decimal(10,0) NOT NULL,
  PRIMARY KEY (`username`,`itemname`),
  CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) 

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;

UNLOCK TABLES;

>>>>>>> e8588edbd4c4679f674738d9d36badd595942ed8
=======
CREATE DATABASE  IF NOT EXISTS `iwish`;
USE `iwish`;


--
-- Table structure for table `collected`
--

DROP TABLE IF EXISTS `collected`;

CREATE TABLE `collected` (
  `date` date NOT NULL,
  `username` varchar(50) NOT NULL,
  `itemname` varchar(100) NOT NULL,
  `image` blob,
  `price` varchar(45) NOT NULL DEFAULT '0',
  `notify` varchar(45) NOT NULL,
  PRIMARY KEY (`username`,`itemname`),
  CONSTRAINT `collected_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) 

--
-- Dumping data for table `collected`
--

LOCK TABLES `collected` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `friendrequest`
--

DROP TABLE IF EXISTS `friendrequest`;

CREATE TABLE `friendrequest` (
  `fromuser` varchar(50) NOT NULL,
  `touser` varchar(50) NOT NULL,
  `status` varchar(45) NOT NULL,
  `notified` varchar(10) NOT NULL,
  KEY `fromuser` (`fromuser`),
  KEY `touser` (`touser`),
  CONSTRAINT `friendrequest_ibfk_1` FOREIGN KEY (`fromuser`) REFERENCES `users` (`username`),
  CONSTRAINT `friendrequest_ibfk_2` FOREIGN KEY (`touser`) REFERENCES `users` (`username`)
)

--
-- Dumping data for table `friendrequest`
--

LOCK TABLES `friendrequest` WRITE;
INSERT INTO `friendrequest` VALUES ('test1','test2','Accepted','No');



END */;;
DELIMITER ;


--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;

CREATE TABLE `friends` (
  `username` varchar(50) NOT NULL,
  `friendname` varchar(50) NOT NULL,
  `date` date NOT NULL,
  KEY `username` (`username`),
  KEY `friendname` (`friendname`),
  CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`friendname`) REFERENCES `users` (`username`)
) 

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
INSERT INTO `friends` VALUES ('test1','test2','2023-03-19'),('test1','test2','2023-03-19'),('test2','test1','2023-03-19');
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `itemname` varchar(100) NOT NULL,
  `category` varchar(45) NOT NULL DEFAULT 'Other',
  `price` decimal(10,0) NOT NULL,
  `image` blob,
  PRIMARY KEY (`itemname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;

UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `user_name_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
)

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES ('aly','123','ali@gmail.com','0102000'),('test1','123','email','0101010'),('test2','123','email2','0101010');
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;

CREATE TABLE `wishlist` (
  `image` blob,
  `date` date NOT NULL,
  `username` varchar(50) NOT NULL,
  `itemname` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL DEFAULT 'Other',
  `price` decimal(10,0) NOT NULL DEFAULT '0',
  `remained` decimal(10,0) NOT NULL,
  PRIMARY KEY (`username`,`itemname`),
  CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) 

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;

UNLOCK TABLES;

>>>>>>> 4f4a31b95ab73ecc8328cf5320b516fababc8e11
