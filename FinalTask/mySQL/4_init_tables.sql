USE `poputka_db`;


//CAR_brand
insert into car_brands (brand) value ('ACURA');
insert into car_brands (brand) value ('ALFA ROMEO');
insert into car_brands (brand) value ('AUDI');
insert into car_brands (brand) value ('BMW');
insert into car_brands (brand) value ('BUICK');
insert into car_brands (brand) value ('CADILLAC');
insert into car_brands (brand) value ('CHERY');
insert into car_brands (brand) value ('CHEVROLET');
insert into car_brands (brand) value ('CHRYSLER');
insert into car_brands (brand) value ('CITROEN');
insert into car_brands (brand) value ('DACIA');
insert into car_brands (brand) value ('DAEWOO');
insert into car_brands (brand) value ('DAIHATSU');
insert into car_brands (brand) value ('DATSUN');
insert into car_brands (brand) value ('DODGE');
insert into car_brands (brand) value ('FIAT');
insert into car_brands (brand) value ('FORD');
insert into car_brands (brand) value ('GAZ');
insert into car_brands (brand) value ('GEELY');
insert into car_brands (brand) value ('GMC');
insert into car_brands (brand) value ('GREAT WALL');
insert into car_brands (brand) value ('HONDA');
insert into car_brands (brand) value ('HUMMER');
insert into car_brands (brand) value ('HYUNDAI');
insert into car_brands (brand) value ('INFINITI');
insert into car_brands (brand) value ('ISUZU');
insert into car_brands (brand) value ('IVECO');
insert into car_brands (brand) value ('JAGUAR');
insert into car_brands (brand) value ('JEEP');
insert into car_brands (brand) value ('KIA');
insert into car_brands (brand) value ('LADA');
insert into car_brands (brand) value ('LANCIA');
insert into car_brands (brand) value ('LAND ROVER');
insert into car_brands (brand) value ('LEXUS');
insert into car_brands (brand) value ('LIFAN');
insert into car_brands (brand) value ('LINCOLN');
insert into car_brands (brand) value ('MAYBACH');
insert into car_brands (brand) value ('MAZDA');
insert into car_brands (brand) value ('MCLAREN');
insert into car_brands (brand) value ('MERCEDES-BENZ');
insert into car_brands (brand) value ('MINI');
insert into car_brands (brand) value ('MITSUBISHI');
insert into car_brands (brand) value ('MOSKVICH');
insert into car_brands (brand) value ('NISSAN');
insert into car_brands (brand) value ('OPEL');
insert into car_brands (brand) value ('PEUGEOT');
insert into car_brands (brand) value ('PONTIAC');
insert into car_brands (brand) value ('PORSCHE');
insert into car_brands (brand) value ('PROTON');
insert into car_brands (brand) value ('RENAULT');
insert into car_brands (brand) value ('ROLLS-ROYCE');
insert into car_brands (brand) value ('ROVER');
insert into car_brands (brand) value ('SAAB');
insert into car_brands (brand) value ('SEAT');
insert into car_brands (brand) value ('SKODA');
insert into car_brands (brand) value ('SMART');
insert into car_brands (brand) value ('SANGYONG');
insert into car_brands (brand) value ('SUBARU');
insert into car_brands (brand) value ('SUZUKI');
insert into car_brands (brand) value ('TESLA');
insert into car_brands (brand) value ('TOYOTA');
insert into car_brands (brand) value ('UAZ');
insert into car_brands (brand) value ('VOLKSWAGEN');
insert into car_brands (brand) value ('VOLVO');
insert into car_brands (brand) value ('ZAZ');



insert into car_models (brand_id, model) value (1, 'CL');
insert into car_models (brand_id, model) value (1, 'CSX');
insert into car_models (brand_id, model) value (1, 'EL');
insert into car_models (brand_id, model) value (1, 'ILX');
insert into car_models (brand_id, model) value (1, 'INTEGRA');
insert into car_models (brand_id, model) value (1, 'LEGEND');
insert into car_models (brand_id, model) value (1, 'MDX');
insert into car_models (brand_id, model) value (1, 'NSX');
insert into car_models (brand_id, model) value (1, 'RDX');
insert into car_models (brand_id, model) value (1, 'RL');
insert into car_models (brand_id, model) value (1, 'RLX');
insert into car_models (brand_id, model) value (1, 'RSX');
insert into car_models (brand_id, model) value (1, 'SLX');
insert into car_models (brand_id, model) value (1, 'TL');
insert into car_models (brand_id, model) value (1, 'TLX');
insert into car_models (brand_id, model) value (1, 'TSX');
insert into car_models (brand_id, model) value (1, 'VIGOR');
insert into car_models (brand_id, model) value (1, 'ZDX');




//Countries:
insert into countries (country_name) value ('Беларусь');
insert into countries (country_name) value ('Россия');
insert into countries (country_name) value ('Польша');
insert into countries (country_name) value ('Украина');
insert into countries (country_name) value ('Литва');
insert into countries (country_name) value ('Латвия');

insert into gender (gender) value ('Male');
insert into gender (gender) value ('Famale');

INSERT INTO `users` (
	`login`,
	`password`,
	`role`,
	`salt`
) VALUES (
	"admin",
	"39E573D2DF8B07AA235852DCA093EE6E", /*  хэш пароля "admin" */
	0,
	"A82D1A169EB72F0C1867F1E815747952" /* hash соответствует при данной salt */
);


//journey
INSERT INTO `poputka_db`.`journey` (`driver_id`, `start_address_id`, `destination_address_id`, `departure_time`, `departure_date`, `cost`, `currency_id`, `number_of_passengers`, `baggage`, `additional_information`) VALUES (2, 55, 159, '14:00:00', '2019-12-12', 3, 1, 3, 0, 'посадка на ст.м.Малиновка ')
INSERT INTO `poputka_db`.`journey` (`driver_id`, `start_address_id`, `destination_address_id`, `departure_time`, `departure_date`, `cost`, `currency_id`, `number_of_passengers`, `baggage`, `additional_information`) VALUES (2, 57, 10, '10:00:00', '2019-12-18', 3, 1, 3, 0, 'посадка на ст.м.Малиновка ')

INSERT INTO `poputka_db`.`journey` (`driver_id`, `start_address_id`, `destination_address_id`, `departure_time`, `departure_date`, `cost`, `currency_id`, `number_of_passengers`, `baggage`, `additional_information`) VALUES (13, 57, 10, '10:00:00', '2019-12-18', 3, 1, 3, 0, 'посадка на ст.м.Уручье ')
INSERT INTO `poputka_db`.`journey` (`driver_id`, `start_address_id`, `destination_address_id`, `departure_time`, `departure_date`, `cost`, `currency_id`, `number_of_passengers`, `baggage`, `additional_information`) VALUES (2, 5, 101, '12:00:00', '2019-12-18', 4, 2, 3, 0, 'посадка на ст.м.Малиновка ')
INSERT INTO `poputka_db`.`journey` (`driver_id`, `start_address_id`, `destination_address_id`, `departure_time`, `departure_date`, `cost`, `currency_id`, `number_of_passengers`, `baggage`, `additional_information`) VALUES (13, 257, 110, '11:00:00', '2019-12-18', 5, 3, 4, 0, 'посадка на ст.м.Малиновка ')
INSERT INTO `poputka_db`.`journey` (`driver_id`, `start_address_id`, `destination_address_id`, `departure_time`, `departure_date`, `cost`, `currency_id`, `number_of_passengers`, `baggage`, `additional_information`) VALUES (13, 157, 210, '08:00:00', '2019-12-18', 6, 1, 3, 0, 'посадка на ст.м.Малиновка ')
INSERT INTO `poputka_db`.`journey` (`driver_id`, `start_address_id`, `destination_address_id`, `departure_time`, `departure_date`, `cost`, `currency_id`, `number_of_passengers`, `baggage`, `additional_information`) VALUES (13, 7, 110, '10:30:00', '2019-12-18', 3, 2, 3, 0, 'посадка на ст.м.Малиновка ')
INSERT INTO `poputka_db`.`journey` (`driver_id`, `start_address_id`, `destination_address_id`, `departure_time`, `departure_date`, `cost`, `currency_id`, `number_of_passengers`, `baggage`, `additional_information`) VALUES (2, 17, 180, '10:00:00', '2019-12-18', 3, 1, 4, 0, 'посадка на ст.м.Малиновка ')
INSERT INTO `poputka_db`.`journey` (`driver_id`, `start_address_id`, `destination_address_id`, `departure_time`, `departure_date`, `cost`, `currency_id`, `number_of_passengers`, `baggage`, `additional_information`) VALUES (2, 52, 30, '10:00:00', '2019-12-18', 3, 3, 3, 0, 'посадка на ст.м.Малиновка ')
INSERT INTO `poputka_db`.`journey` (`driver_id`, `start_address_id`, `destination_address_id`, `departure_time`, `departure_date`, `cost`, `currency_id`, `number_of_passengers`, `baggage`, `additional_information`) VALUES (2, 47, 120, '10:00:00', '2019-12-18', 3, 1, 4, 0, 'посадка на ст.м.Малиновка ')
INSERT INTO `poputka_db`.`journey` (`driver_id`, `start_address_id`, `destination_address_id`, `departure_time`, `departure_date`, `cost`, `currency_id`, `number_of_passengers`, `baggage`, `additional_information`) VALUES (2, 37, 20, '10:00:00', '2019-12-18', 3, 1, 3, 0, 'посадка на ст.м.Малиновка ')
INSERT INTO `poputka_db`.`journey` (`driver_id`, `start_address_id`, `destination_address_id`, `departure_time`, `departure_date`, `cost`, `currency_id`, `number_of_passengers`, `baggage`, `additional_information`) VALUES (2, 87, 50, '10:00:00', '2019-12-18', 3, 1, 3, 0, 'посадка на ст.м.Малиновка ')
