# Scores schema

# --- !Ups
CREATE TABLE IF NOT EXISTS `conveyordb`.`scores` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL DEFAULT NULL,
    `scores` VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8;

# --- !Downs
drop table 'conveyordb.scores';