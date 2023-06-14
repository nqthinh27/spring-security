create database verify_email_app;

CREATE TABLE `verify_email_app`.`user` (
  `id` INT NOT NULL,
  `user_name` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(100) NULL,
  `email` VARCHAR(45) NOT NULL,
  `activated` BOOLEAN NULL DEFAULT false,
  `role` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`));

CREATE TABLE `verify_email_app`.`role` (
  `id` int NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `verify_email_app`.`role` (`id`, `role`) VALUES ('1', 'ROLE_USER');
INSERT INTO `verify_email_app`.`role` (`id`, `role`) VALUES ('2', 'ROLE_ADMIN');

CREATE TABLE `verify_email_app`.`activation_key` (
  `id` INT NOT NULL auto_increment,
  `user_id` INT NULL,
  `key` VARCHAR(255) NULL,
  constraint fk_activation_user foreign key (user_id) references user(id),
  PRIMARY KEY (`id`));

