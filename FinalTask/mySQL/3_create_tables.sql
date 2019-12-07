USE `library_db`;

CREATE TABLE `users` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`login` VARCHAR(255) NOT NULL UNIQUE,
	`password` CHAR(128) NOT NULL, /*for PBKDF2  security*/
	/*
	 * 0 - администратор (Role.ADMINISTRATOR)
	 * 1 - пользователь (Role.USER)
	 */
	`role` TINYINT NOT NULL CHECK (`role` IN (0, 1, 2))  DEFAULT 1,
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;


CREATE TABLE `cars` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`brand_and_model_id` INTEGER NOT NULL,
	`model` VARCHAR(255) NOT NULL, 
	`year_of_produce` YEAR NOT NULL CHECK (`year_of_produce` >= 1900),
	`air_conditioner` BOOLEAN NOT NULL,
	PRIMARY KEY (`id`),
	 ADD CONSTRAINT `FK_car_brand_and_model`
        FOREIGN KEY (`brand_and_model_id`) REFERENCES `car_models`(`id`)
            ON UPDATE CASCADE
            ON DELETE restrict
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

ALTER TABLE cars RENAME COLUMN brand TO brand_and_model_id;
ALTER TABLE cars DROP COLUMN model;

CREATE TABLE `gender` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`gender` VARCHAR(10) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;



CREATE TABLE `user_info` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`user_id` INTEGER,
	`surname` VARCHAR(255) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`gender_id` TINYINT NOT NULL CHECK (`gender` IN (0, 1)),
	`birthday` DATE NOT NULL,
	`country` VARCHAR(255) NOT NULL,
	`passport_number` VARCHAR(10) NOT NULL UNIQUE,
	`passport_date_of_issue` DATE NOT NULL,
	`phone` VARCHAR(255) NOT NULL UNIQUE,
	`email` VARCHAR(255) NOT NULL UNIQUE,
	`car_id` INTEGER,
	`driving_experience_since` YEAR;
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_car`
	FOREIGN KEY (`car_id`)
	REFERENCES `cars` (`id`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
	CONSTRAINT `FK_user`
	FOREIGN KEY (`user_id`)
	REFERENCES `users` (`id`)
	ON UPDATE CASCADE
	ON DELETE SET NULL
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;



ALTER TABLE `user_info`
ADD `driving_experience_since` YEAR;



CREATE TABLE `countries` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`country_name` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `regions` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`country_id` INTEGER NOT NULL,
	`region_name` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_country`
	FOREIGN KEY (`country_id`)
	REFERENCES `countries` (`id`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;


CREATE TABLE `cities` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`region_id` INTEGER NOT NULL,
	`city_name` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_region`
	FOREIGN KEY (`region_id`)
	REFERENCES `regions` (`id`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;


CREATE TABLE `address` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`city_id` INTEGER NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_cities`
	FOREIGN KEY (`city_id`)
	REFERENCES `cities` (`id`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;



CREATE TABLE `currencies` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`currency` CHAR(3) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `journey` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`driver_id` INTEGER NOT NULL,
	`start_address_id` INTEGER NOT NULL,
	`destination_address_id` INTEGER NOT NULL,
	`departure_time` TIME,
	`departure_date` DATE NOT NULL,
	`cost` DOUBLE(20, 2) NOT NULL CHECK (`cost` >= 0),
	`currency_id` INTEGER NOT NULL,
	`number_of_passengers` INTEGER NOT NULL CHECK (`number_of_passengers` > 0),
	`baggage` BOOLEAN DEFAULT (0),
	`additional_information` TEXT(65535),
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_driver`
	FOREIGN KEY (`driver_id`)
	REFERENCES `user_info` (`id`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
	CONSTRAINT `FK_currency`
	FOREIGN KEY (`currency_id`)
	REFERENCES `currencies` (`id`)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
	CONSTRAINT `FK_start_address`
FOREIGN KEY (`start_address_id`) REFERENCES `cities`(`id`)
ON UPDATE CASCADE
	ON DELETE CASCADE,
	CONSTRAINT `FK_destination_address`
FOREIGN KEY (`destination_address_id`) REFERENCES `cities`(`id`)
ON UPDATE CASCADE
	ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;




CREATE TABLE `passengers` (
	`journey_id` INTEGER NOT NULL,
	`passenger_id` INTEGER,
	CONSTRAINT `FK_journey`
	FOREIGN KEY (`journey_id`)
	REFERENCES `journey` (`id`)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	CONSTRAINT `FK_passenger`
	FOREIGN KEY (`passenger_id`)
	REFERENCES `user_info` (`id`)
	ON UPDATE CASCADE
	ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;



CREATE TABLE `comments` (
	`commentator_id` INTEGER,
	`comment_about_id` INTEGER,
    `comment` LONGTEXT not null,
	CONSTRAINT `FK_commentator`
	FOREIGN KEY (`commentator_id`)
	REFERENCES `user_info` (`id`)
	ON UPDATE CASCADE
	ON DELETE SET NULL,
	CONSTRAINT `FK_passenger`
	FOREIGN KEY (`comment_about_id``)
	REFERENCES `user_info` (`id`)
	ON UPDATE CASCADE
	ON DELETE SET NULL
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;



CREATE TABLE `car_brands` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`brand` VARCHAR(50) NOT NULL UNIQUE,
	PRIMARY KEY (`id`),
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;





CREATE TABLE `car_models` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`brand_id` INTEGER NOT NULL,
	`model` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_commentator`
	FOREIGN KEY (`brand_id`)
	REFERENCES `car_brands` (`id`)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;






















