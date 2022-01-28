-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pay_my_buddy
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pay_my_buddy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pay_my_buddy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `pay_my_buddy` ;

-- -----------------------------------------------------
-- Table `pay_my_buddy`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pay_my_buddy`.`user` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `user_role` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pay_my_buddy`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pay_my_buddy`.`account` (
  `account_id` BIGINT NOT NULL AUTO_INCREMENT,
  `balance` DOUBLE NOT NULL,
  `user_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  INDEX `FK7m8ru44m93ukyb61dfxw0apf6` (`user_id` ASC) VISIBLE,
  FOREIGN KEY (`user_id`)
  REFERENCES `pay_my_buddy`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pay_my_buddy`.`back_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pay_my_buddy`.`bank_account` (
  `bank_name` VARCHAR(255) NULL DEFAULT NULL,
  `iban` VARCHAR(255) NOT NULL,
  `account_id` BIGINT NOT NULL,
  PRIMARY KEY (`account_id`),
    FOREIGN KEY (`account_id`)
    REFERENCES `pay_my_buddy`.`account` (`account_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pay_my_buddy`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pay_my_buddy`.`transaction` (
  `transaction_id` BIGINT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `date` DATETIME(6) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `receiver_account_id` BIGINT NULL DEFAULT NULL,
  `sender_account_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  INDEX `FKju0iwkkyfeecqgjv9dn9gxbb0` (`receiver_account_id` ASC) VISIBLE,
  INDEX `FK6rmg4ssabk05bhs84bavx65e8` (`sender_account_id` ASC) VISIBLE,
    FOREIGN KEY (`sender_account_id`)
    REFERENCES `pay_my_buddy`.`account` (`account_id`),
    FOREIGN KEY (`receiver_account_id`)
    REFERENCES `pay_my_buddy`.`account` (`account_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pay_my_buddy`.`user_connections`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pay_my_buddy`.`user_connections` (
  `user_user_id` BIGINT NOT NULL,
  `connections_user_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_user_id`, `connections_user_id`),
    FOREIGN KEY (`connections_user_id`)
    REFERENCES `pay_my_buddy`.`user` (`user_id`),
    FOREIGN KEY (`user_user_id`)
    REFERENCES `pay_my_buddy`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

