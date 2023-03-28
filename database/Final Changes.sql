<<<<<<< HEAD
<<<<<<< HEAD
--Drop table el transaction w 5ody dah 
CREATE TABLE `transaction` (
  `tranid` int NOT NULL AUTO_INCREMENT,
  `from_` varchar(45) NOT NULL,
  `to_` varchar(45) NOT NULL,
  `item` varchar(100) NOT NULL,
  `amo` decimal(10,0) NOT NULL,
  `date` date NOT NULL,
  `notifiy` varchar(10) NOT NULL DEFAULT 'no',
  `notifiy_after` varchar(10) NOT NULL DEFAULT 'no',
  PRIMARY KEY (`tranid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--------------------------------------------------------------------------------------------
--edit the trigger in friendrequest to be this
CREATE DEFINER=`root`@`localhost` TRIGGER `friendrequest_AFTER_UPDATE` AFTER UPDATE ON `friendrequest` FOR EACH ROW BEGIN
	

 IF  NEW.status = "Accepted" AND NEW.status!=OLD.status THEN
	INSERT INTO friends(username,friendname, date)
    VALUES(old.fromuser, old.touser, sysdate());	

        
	INSERT INTO friends(username,friendname, date)
        VALUES(old.touser, old.fromuser, sysdate());	


end if;

END
---------------------------------------------------------------------------------
--and the function to be this 
CREATE DEFINER=`root`@`localhost` FUNCTION `checkamount`(fromuser VARCHAR(50), touser VARCHAR(50), v_itemname VARCHAR(100), amount DECIMAL) RETURNS int
BEGIN
  DECLARE user_balance DECIMAL(10);
  DECLARE ffromuser VARCHAR(50) DEFAULT fromuser;
  DECLARE ttomuser VARCHAR(50) DEFAULT touser;
  DECLARE vv_itemname VARCHAR(100) DEFAULT v_itemname;
  DECLARE aamount DECIMAL DEFAULT amount;
  DECLARE new_balance DECIMAL(10);
  DECLARE old_remained DECIMAL(10);
  DECLARE new_remind DECIMAL(10);

  SELECT balance INTO user_balance FROM users WHERE username = fromuser;
  SELECT remained INTO old_remained FROM wishlist WHERE username = touser AND itemname = v_itemname;

  IF user_balance >= amount AND amount <= old_remained THEN
    SET new_remind = old_remained - amount;
    SET new_balance = user_balance - amount;
    UPDATE wishlist SET remained = new_remind WHERE username = touser AND itemname = v_itemname;
    INSERT INTO transaction (from_, to_, item, amo, date) VALUES (fromuser, touser, v_itemname, amount, SYSDATE());
    UPDATE users SET balance = new_balance WHERE username = fromuser;
    RETURN 1;
  ELSE
    RETURN 0;
  END IF;
END
=======
--Drop table el transaction w 5ody dah 
CREATE TABLE `transaction` (
  `tranid` int NOT NULL AUTO_INCREMENT,
  `from_` varchar(45) NOT NULL,
  `to_` varchar(45) NOT NULL,
  `item` varchar(100) NOT NULL,
  `amo` decimal(10,0) NOT NULL,
  `date` date NOT NULL,
  `notifiy` varchar(10) NOT NULL DEFAULT 'no',
  `notifiy_after` varchar(10) NOT NULL DEFAULT 'no',
  PRIMARY KEY (`tranid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--------------------------------------------------------------------------------------------
--edit the trigger in friendrequest to be this
CREATE DEFINER=`root`@`localhost` TRIGGER `friendrequest_AFTER_UPDATE` AFTER UPDATE ON `friendrequest` FOR EACH ROW BEGIN
	

 IF  NEW.status = "Accepted" AND NEW.status!=OLD.status THEN
	INSERT INTO friends(username,friendname, date)
    VALUES(old.fromuser, old.touser, sysdate());	

        
	INSERT INTO friends(username,friendname, date)
        VALUES(old.touser, old.fromuser, sysdate());	


end if;

END
---------------------------------------------------------------------------------
--and the function to be this 
CREATE DEFINER=`root`@`localhost` FUNCTION `checkamount`(fromuser VARCHAR(50), touser VARCHAR(50), v_itemname VARCHAR(100), amount DECIMAL) RETURNS int
BEGIN
  DECLARE user_balance DECIMAL(10);
  DECLARE ffromuser VARCHAR(50) DEFAULT fromuser;
  DECLARE ttomuser VARCHAR(50) DEFAULT touser;
  DECLARE vv_itemname VARCHAR(100) DEFAULT v_itemname;
  DECLARE aamount DECIMAL DEFAULT amount;
  DECLARE new_balance DECIMAL(10);
  DECLARE old_remained DECIMAL(10);
  DECLARE new_remind DECIMAL(10);

  SELECT balance INTO user_balance FROM users WHERE username = fromuser;
  SELECT remained INTO old_remained FROM wishlist WHERE username = touser AND itemname = v_itemname;

  IF user_balance >= amount AND amount <= old_remained THEN
    SET new_remind = old_remained - amount;
    SET new_balance = user_balance - amount;
    UPDATE wishlist SET remained = new_remind WHERE username = touser AND itemname = v_itemname;
    INSERT INTO transaction (from_, to_, item, amo, date) VALUES (fromuser, touser, v_itemname, amount, SYSDATE());
    UPDATE users SET balance = new_balance WHERE username = fromuser;
    RETURN 1;
  ELSE
    RETURN 0;
  END IF;
END
>>>>>>> e8588edbd4c4679f674738d9d36badd595942ed8
=======
--Drop table el transaction w 5ody dah 
CREATE TABLE `transaction` (
  `tranid` int NOT NULL AUTO_INCREMENT,
  `from_` varchar(45) NOT NULL,
  `to_` varchar(45) NOT NULL,
  `item` varchar(100) NOT NULL,
  `amo` decimal(10,0) NOT NULL,
  `date` date NOT NULL,
  `notifiy` varchar(10) NOT NULL DEFAULT 'no',
  `notifiy_after` varchar(10) NOT NULL DEFAULT 'no',
  PRIMARY KEY (`tranid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--------------------------------------------------------------------------------------------
--edit the trigger in friendrequest to be this
CREATE DEFINER=`root`@`localhost` TRIGGER `friendrequest_AFTER_UPDATE` AFTER UPDATE ON `friendrequest` FOR EACH ROW BEGIN
	

 IF  NEW.status = "Accepted" AND NEW.status!=OLD.status THEN
	INSERT INTO friends(username,friendname, date)
    VALUES(old.fromuser, old.touser, sysdate());	

        
	INSERT INTO friends(username,friendname, date)
        VALUES(old.touser, old.fromuser, sysdate());	


end if;

END
---------------------------------------------------------------------------------
--and the function to be this 
CREATE DEFINER=`root`@`localhost` FUNCTION `checkamount`(fromuser VARCHAR(50), touser VARCHAR(50), v_itemname VARCHAR(100), amount DECIMAL) RETURNS int
BEGIN
  DECLARE user_balance DECIMAL(10);
  DECLARE ffromuser VARCHAR(50) DEFAULT fromuser;
  DECLARE ttomuser VARCHAR(50) DEFAULT touser;
  DECLARE vv_itemname VARCHAR(100) DEFAULT v_itemname;
  DECLARE aamount DECIMAL DEFAULT amount;
  DECLARE new_balance DECIMAL(10);
  DECLARE old_remained DECIMAL(10);
  DECLARE new_remind DECIMAL(10);

  SELECT balance INTO user_balance FROM users WHERE username = fromuser;
  SELECT remained INTO old_remained FROM wishlist WHERE username = touser AND itemname = v_itemname;

  IF user_balance >= amount AND amount <= old_remained THEN
    SET new_remind = old_remained - amount;
    SET new_balance = user_balance - amount;
    UPDATE wishlist SET remained = new_remind WHERE username = touser AND itemname = v_itemname;
    INSERT INTO transaction (from_, to_, item, amo, date) VALUES (fromuser, touser, v_itemname, amount, SYSDATE());
    UPDATE users SET balance = new_balance WHERE username = fromuser;
    RETURN 1;
  ELSE
    RETURN 0;
  END IF;
END
>>>>>>> 4f4a31b95ab73ecc8328cf5320b516fababc8e11
-------------------------------------------------------------------------