CREATE DATABASE `poputka_db` DEFAULT CHARACTER SET utf8;

CREATE USER poputkaApp IDENTIFIED BY 'app';


GRANT SELECT,INSERT,UPDATE,DELETE
ON `poputka_db`.*
TO poputkaApp@'%';


CREATE TABLE `car_brands` (
    `id`   INTEGER NOT NULL AUTO_INCREMENT,
    `brand` VARCHAR(50) NOT NULL UNIQUE,
	PRIMARY KEY (`id`)
);

CREATE TABLE `car_climate` (
    `id`  INTEGER NOT NULL AUTO_INCREMENT,
    `climate_type` VARCHAR(30) NOT NULL UNIQUE,
	PRIMARY KEY (`id`)
);

CREATE TABLE `car_models` (
    `id`       INTEGER NOT NULL AUTO_INCREMENT,
   
    `brand_id` INTEGER NOT NULL,
    `model`    VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
    CONSTRAINT `FK_brand`
        FOREIGN KEY (`brand_id`) REFERENCES `car_brands` (`id`)
            ON UPDATE CASCADE
			ON DELETE CASCADE
);

CREATE TABLE `cars` (
    `id`  INTEGER NOT NULL AUTO_INCREMENT,
    `brand_and_model_id` INTEGER NOT NULL,
    `year_of_produce` YEAR NOT NULL CHECK (`year_of_produce` >= 1900),
    climate_type_id  INTEGER NOT NULL,
	PRIMARY KEY (`id`),
    CONSTRAINT `FK_car_brand_and_model`
        FOREIGN KEY (`brand_and_model_id`) REFERENCES  `car_models` (`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    CONSTRAINT `FK_climate`
        FOREIGN KEY (`climate_type_id`) REFERENCES `car_climate` (`id`)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
);

CREATE TABLE `countries`(
    `id`  INTEGER NOT NULL AUTO_INCREMENT,
    `country_name` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `currencies` (
    `id`  INTEGER NOT NULL AUTO_INCREMENT,
    `currency` CHAR(3) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `gender` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `gender` VARCHAR(10) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `regions` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `country_id`  INTEGER NOT NULL,
    `region_name` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`id`),
    CONSTRAINT `FK_country`
        FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
        ON UPDATE CASCADE
	    ON DELETE RESTRICT
);

CREATE TABLE `cities` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `region_id` INTEGER NOT NULL,
    `city_name` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`id`),
    CONSTRAINT `FK_region`
        FOREIGN KEY (`region_id`) REFERENCES `regions` (`id`)
        ON UPDATE CASCADE
	    ON DELETE RESTRICT
);

CREATE TABLE `users` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(255) NOT NULL UNIQUE,
    `password` CHAR(128) NOT NULL,  /*for PBKDF2  security*/
	`salt`     CHAR(128)  NOT NULL, /*for PBKDF2  security*/
    `role`  TINYINT NOT NULL  DEFAULT 1 CHECK (`role` IN (0, 1, 2)),
	/*
	 * 0 - администратор (Role.ADMINISTRATOR)
	 * 1 - пользователь (Role.USER)
	 * 2 - гость (Role.GUEST)
	 */
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_info` (
    `id`   INTEGER NOT NULL AUTO_INCREMENT,
    `user_id` INTEGER,
    `surname` VARCHAR(255) NOT NULL,
    `name`    VARCHAR(255) NOT NULL,
    `birthday` DATE NOT NULL,
    `country_id` INTEGER NOT NULL,
    `passport_number`  VARCHAR(10) NOT NULL UNIQUE,
    `passport_date_of_issue` DATE NOT NULL,
    `phone` VARCHAR(16) NOT NULL UNIQUE,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `car_id`  INTEGER,
    `gender_id` INTEGER,
	PRIMARY KEY (`id`),
    CONSTRAINT `FK_car`
        FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`)
            ON UPDATE CASCADE
			ON DELETE SET NULL,
    CONSTRAINT `FK_country_id`
        FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
            ON UPDATE CASCADE
			ON DELETE RESTRICT,
    CONSTRAINT `FK_gender`
        FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
    CONSTRAINT `FK_user`
        FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
            ON UPDATE CASCADE
			ON DELETE SET NULL
);

CREATE TABLE `comments` (
    `commentator_id` INTEGER,
    `comment_about_id` INTEGER,
    `comment`  LONGTEXT NOT NULL,
    CONSTRAINT `FK_comment_about`
        FOREIGN KEY (`comment_about_id`) REFERENCES `user_info` (`id`)
            ON UPDATE CASCADE
			ON DELETE SET NULL,
    CONSTRAINT `FK_commentator`
        FOREIGN KEY (`commentator_id`) REFERENCES `user_info` (`id`)
            ON UPDATE CASCADE
			ON DELETE SET NULL
);

CREATE TABLE `journey` (
    `id`  INTEGER NOT NULL AUTO_INCREMENT,
    `driver_id` INTEGER NOT NULL,
    `start_address_id` INTEGER NOT NULL,
    `destination_address_id` INTEGER NOT NULL,
    `departure_time` TIME NOT NULL,
    `departure_date` DATE NOT NULL,
    `cost`  DOUBLE(20, 2) NOT NULL CHECK (`cost` >= 0),
    `currency_id`  INTEGER NOT NULL,
    `number_of_passengers` INTEGER NOT NULL CHECK (`number_of_passengers` > 0),
    `additional_information` VARCHAR(1000),
	PRIMARY KEY (`id`),
    CONSTRAINT `FK_currency`
        FOREIGN KEY (`currency_id`) REFERENCES `currencies` (`id`)
            ON UPDATE CASCADE
			ON DELETE RESTRICT,
    CONSTRAINT `FK_destination_address`
        FOREIGN KEY (`destination_address_id`) REFERENCES `cities` (`id`)
            ON UPDATE CASCADE
			ON DELETE CASCADE,
    CONSTRAINT `FK_driver`
        FOREIGN KEY (`driver_id`) REFERENCES `user_info` (`id`)
            ON UPDATE CASCADE
			ON DELETE RESTRICT,
    CONSTRAINT `FK_start_address`
        FOREIGN KEY (`start_address_id`) REFERENCES `cities` (`id`)
            ON UPDATE CASCADE
			ON DELETE CASCADE
);

CREATE TABLE `passengers` (
    `journey_id` INTEGER NOT NULL,
    `passenger_id` INTEGER,
    CONSTRAINT `FK_journey`
        FOREIGN KEY (`journey_id`) REFERENCES `journey` (`id`)
            ON UPDATE CASCADE
			ON DELETE CASCADE,
    CONSTRAINT `FK_passenger`
        FOREIGN KEY (`passenger_id`) REFERENCES `user_info` (`id`)
            ON UPDATE CASCADE
			ON DELETE CASCADE
);
