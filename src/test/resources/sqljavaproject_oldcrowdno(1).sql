-- Adminer 4.8.1 MySQL 8.0.29-21 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `account_details`;
CREATE TABLE `account_details` (
  `account_number` int NOT NULL,
  `account_type` varchar(8) NOT NULL,
  `balance` decimal(25,2) DEFAULT NULL,
  `customer_id` varchar(15) NOT NULL,
  PRIMARY KEY (`account_number`),
  KEY `customer_id_fk` (`customer_id`),
  CONSTRAINT `customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer_details` (`customer_id`),
  CONSTRAINT `account_details_chk_1` CHECK ((`account_type` in (_utf8mb4'CHECKING',_utf8mb4'SAVINGS')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `account_details` (`account_number`, `account_type`, `balance`, `customer_id`) VALUES
(1,	'Checking',	14916.58,	'0001'),
(2,	'Savings',	4973.29,	'0002'),
(3,	'Savings',	0.00,	'0001'),
(4,	'Checking',	0.00,	'0002')
ON DUPLICATE KEY UPDATE `account_number` = VALUES(`account_number`), `account_type` = VALUES(`account_type`), `balance` = VALUES(`balance`), `customer_id` = VALUES(`customer_id`);

DROP TABLE IF EXISTS `customer_details`;
CREATE TABLE `customer_details` (
  `customer_id` varchar(10) NOT NULL,
  `first_name` char(15) NOT NULL,
  `last_name` char(15) NOT NULL,
  `date_of_birth` date NOT NULL,
  `street_name` varchar(35) NOT NULL,
  `house_number` int NOT NULL,
  `zip_code` int NOT NULL,
  `city` varchar(35) NOT NULL,
  `country` varchar(25) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `customer_details` (`customer_id`, `first_name`, `last_name`, `date_of_birth`, `street_name`, `house_number`, `zip_code`, `city`, `country`) VALUES
('0001',	'Bob',	'Manson',	'1995-10-18',	'Apple Mist Road',	5,	90210,	'Los Angeles',	'United States of America'),
('0002',	'Sarah',	'Labarge',	'1999-12-25',	'Sunflower crescent',	32,	6503,	'London',	'England')
ON DUPLICATE KEY UPDATE `customer_id` = VALUES(`customer_id`), `first_name` = VALUES(`first_name`), `last_name` = VALUES(`last_name`), `date_of_birth` = VALUES(`date_of_birth`), `street_name` = VALUES(`street_name`), `house_number` = VALUES(`house_number`), `zip_code` = VALUES(`zip_code`), `city` = VALUES(`city`), `country` = VALUES(`country`);

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `transaction_id` int NOT NULL,
  `date_of_transaction` date NOT NULL,
  `transaction_type` varchar(10) NOT NULL,
  `amount` double NOT NULL,
  `account_number` int NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `account_number_fk` (`account_number`),
  CONSTRAINT `account_number_fk` FOREIGN KEY (`account_number`) REFERENCES `account_details` (`account_number`),
  CONSTRAINT `check_transaction_amount` CHECK ((((`amount` > 0) and (`transaction_type` = _utf8mb4'deposit')) or ((`amount` < 0) and (`transaction_type` = _utf8mb4'withdrawal')))),
  CONSTRAINT `transaction_chk_1` CHECK ((`transaction_type` in (_utf8mb4'WITHDRAWAL',_utf8mb4'DEPOSIT')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `transaction` (`transaction_id`, `date_of_transaction`, `transaction_type`, `amount`, `account_number`) VALUES
(1001,	'2023-09-17',	'Deposit',	16039.21,	1),
(1002,	'2006-11-24',	'Withdrawal',	-1472.8,	1),
(1003,	'2004-10-17',	'Deposit',	350.17,	1),
(1004,	'1996-01-14',	'Withdrawal',	-100.61,	2),
(1005,	'2065-05-02',	'Deposit',	5123.9,	2),
(1006,	'2010-03-09',	'Withdrawal',	-50,	2),
(1007,	'2023-09-25',	'Deposit',	500,	3),
(1008,	'2023-09-26',	'Withdrawal',	-500,	3)
ON DUPLICATE KEY UPDATE `transaction_id` = VALUES(`transaction_id`), `date_of_transaction` = VALUES(`date_of_transaction`), `transaction_type` = VALUES(`transaction_type`), `amount` = VALUES(`amount`), `account_number` = VALUES(`account_number`);

-- 2023-10-09 11:43:27
