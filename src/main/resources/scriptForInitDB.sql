-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ChatDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ChatDB`;

-- -----------------------------------------------------
-- Schema ChatDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ChatDB` DEFAULT CHARACTER SET utf8;
USE `ChatDB`;

-- -----------------------------------------------------
-- Table `ChatDB`.`chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChatDB`.`chat`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChatDB`.`t_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChatDB`.`t_user`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `chat_id`  INT         NOT NULL,
    PRIMARY KEY (`id`, `chat_id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
    INDEX `fk_t_user_chat_idx` (`chat_id` ASC) VISIBLE,
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    CONSTRAINT `fk_t_user_chat`
        FOREIGN KEY (`chat_id`)
            REFERENCES `ChatDB`.`chat` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ChatDB`.`Message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ChatDB`.`Message`
(
    `id`        INT          NOT NULL AUTO_INCREMENT,
    `text`      VARCHAR(250) NULL,
    `chat_id`   INT          NOT NULL DEFAULT 1,
    `t_user_id` INT          NOT NULL,
    PRIMARY KEY (`id`, `chat_id`, `t_user_id`),
    INDEX `fk_Message_chat1_idx` (`chat_id` ASC) VISIBLE,
    INDEX `fk_Message_t_user1_idx` (`t_user_id` ASC) VISIBLE,
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    CONSTRAINT `fk_Message_chat1`
        FOREIGN KEY (`chat_id`)
            REFERENCES `ChatDB`.`chat` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT `fk_Message_t_user1`
        FOREIGN KEY (`t_user_id`)
            REFERENCES `ChatDB`.`t_user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `ChatDB`.`chat`
-- -----------------------------------------------------
START TRANSACTION;
USE `ChatDB`;
INSERT INTO `ChatDB`.`chat` (`id`, `name`)
VALUES (1, 'BearChat');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ChatDB`.`t_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `ChatDB`;
INSERT INTO `ChatDB`.`t_user` (`id`, `login`, `password`, `chat_id`)
VALUES (1, 'Mihail', 'test', 1);

COMMIT;

