USE `poputka_db`;


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

insert into `gender` (`gender`) value ('Male');
insert into `gender` (`gender`) value ('Famale');


/*CAR_brand*/
insert into `car_brands` (`brand`) value ('ACURA');
insert into `car_brands` (`brand`) value ('ALFA ROMEO');
insert into `car_brands` (`brand`) value ('AUDI');
insert into `car_brands` (`brand`) value ('BMW');
insert into `car_brands` (`brand`) value ('BUICK');
insert into `car_brands` (`brand`) value ('CADILLAC');
insert into `car_brands` (`brand`) value ('CHERY');
insert into `car_brands` (`brand`) value ('CHEVROLET');
insert into `car_brands` (`brand`) value ('CHRYSLER');
insert into `car_brands` (`brand`) value ('CITROEN');
insert into `car_brands` (`brand`) value ('DACIA');
insert into `car_brands` (`brand`) value ('DAEWOO');
insert into `car_brands` (`brand`) value ('DAIHATSU');
insert into `car_brands` (`brand`) value ('DATSUN');
insert into `car_brands` (`brand`) value ('DODGE');
insert into `car_brands` (`brand`) value ('FIAT');
insert into `car_brands` (`brand`) value ('FORD');
insert into `car_brands` (`brand`) value ('GAZ');
insert into `car_brands` (`brand`) value ('GEELY');
insert into `car_brands` (`brand`) value ('GMC');
insert into `car_brands` (`brand`) value ('GREAT WALL');
insert into `car_brands` (`brand`) value ('HONDA');
insert into `car_brands` (`brand`) value ('HUMMER');
insert into `car_brands` (`brand`) value ('HYUNDAI');
insert into `car_brands` (`brand`) value ('INFINITI');
insert into `car_brands` (`brand`) value ('ISUZU');
insert into `car_brands` (`brand`) value ('IVECO');
insert into `car_brands` (`brand`) value ('JAGUAR');
insert into `car_brands` (`brand`) value ('JEEP');
insert into `car_brands` (`brand`) value ('KIA');
insert into `car_brands` (`brand`) value ('LADA');
insert into `car_brands` (`brand`) value ('LANCIA');
insert into `car_brands` (`brand`) value ('LAND ROVER');
insert into `car_brands` (`brand`) value ('LEXUS');
insert into `car_brands` (`brand`) value ('LIFAN');
insert into `car_brands` (`brand`) value ('LINCOLN');
insert into `car_brands` (`brand`) value ('MAYBACH');
insert into `car_brands` (`brand`) value ('MAZDA');
insert into `car_brands` (`brand`) value ('MCLAREN');
insert into `car_brands` (`brand`) value ('MERCEDES-BENZ');
insert into `car_brands` (`brand`) value ('MINI');
insert into `car_brands` (`brand`) value ('MITSUBISHI');
insert into `car_brands` (`brand`) value ('MOSKVICH');
insert into `car_brands` (`brand`) value ('NISSAN');
insert into `car_brands` (`brand`) value ('OPEL');
insert into `car_brands` (`brand`) value ('PEUGEOT');
insert into `car_brands` (`brand`) value ('PONTIAC');
insert into `car_brands` (`brand`) value ('PORSCHE');
insert into `car_brands` (`brand`) value ('PROTON');
insert into `car_brands` (`brand`) value ('RENAULT');
insert into `car_brands` (`brand`) value ('ROLLS-ROYCE');
insert into `car_brands` (`brand`) value ('ROVER');
insert into `car_brands` (`brand`) value ('SAAB');
insert into `car_brands` (`brand`) value ('SEAT');
insert into `car_brands` (`brand`) value ('SKODA');
insert into `car_brands` (`brand`) value ('SMART');
insert into `car_brands` (`brand`) value ('SANGYONG');
insert into `car_brands` (`brand`) value ('SUBARU');
insert into `car_brands` (`brand`) value ('SUZUKI');
insert into `car_brands` (`brand`) value ('TESLA');
insert into `car_brands` (`brand`) value ('TOYOTA');
insert into `car_brands` (`brand`) value ('UAZ');
insert into `car_brands` (`brand`) value ('VOLKSWAGEN');
insert into `car_brands` (`brand`) value ('VOLVO');
insert into `car_brands` (`brand`) value ('ZAZ');


/*car_models*/
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


insert into car_models (brand_id, model) value (2, '33');
insert into car_models (brand_id, model) value (2, '75');
insert into car_models (brand_id, model) value (2, '145');
insert into car_models (brand_id, model) value (2, '146');
insert into car_models (brand_id, model) value (2, '147');
insert into car_models (brand_id, model) value (2, '155');
insert into car_models (brand_id, model) value (2, '156');
insert into car_models (brand_id, model) value (2, '159');
insert into car_models (brand_id, model) value (2, '164');
insert into car_models (brand_id, model) value (2, '166');
insert into car_models (brand_id, model) value (2, '168');
insert into car_models (brand_id, model) value (2, '4C');
insert into car_models (brand_id, model) value (2, '8C');
insert into car_models (brand_id, model) value (2, 'BRERA');
insert into car_models (brand_id, model) value (2, 'GIULIETTA');
insert into car_models (brand_id, model) value (2, 'GT');
insert into car_models (brand_id, model) value (2, 'GTV');
insert into car_models (brand_id, model) value (2, 'MITO');
insert into car_models (brand_id, model) value (2, 'RZ');
insert into car_models (brand_id, model) value (2, 'SPIDER');
insert into car_models (brand_id, model) value (2, 'SPORTWAGON');
insert into car_models (brand_id, model) value (2, 'SZ');







insert into car_models (brand_id, model) value (3, '80');
insert into car_models (brand_id, model) value (3, '90');
insert into car_models (brand_id, model) value (3, '100');
insert into car_models (brand_id, model) value (3, '200');
insert into car_models (brand_id, model) value (3, '500');
insert into car_models (brand_id, model) value (3, '4000');
insert into car_models (brand_id, model) value (3, '5000');
insert into car_models (brand_id, model) value (3, 'A1');
insert into car_models (brand_id, model) value (3, 'A2');
insert into car_models (brand_id, model) value (3, 'A3');
insert into car_models (brand_id, model) value (3, 'A4');
insert into car_models (brand_id, model) value (3, 'A5');
insert into car_models (brand_id, model) value (3, 'A6');
insert into car_models (brand_id, model) value (3, 'A7');
insert into car_models (brand_id, model) value (3, 'A8');
insert into car_models (brand_id, model) value (3, 'ALLROAD');
insert into car_models (brand_id, model) value (3, 'CABRIOLET');
insert into car_models (brand_id, model) value (3, 'COUPE');
insert into car_models (brand_id, model) value (3, 'Q3');
insert into car_models (brand_id, model) value (3, 'Q5');
insert into car_models (brand_id, model) value (3, 'Q7');
insert into car_models (brand_id, model) value (3, 'QUATTRO');
insert into car_models (brand_id, model) value (3, 'R8');
insert into car_models (brand_id, model) value (3, 'R8 SPYDER');
insert into car_models (brand_id, model) value (3, 'TT');
insert into car_models (brand_id, model) value (3, 'V8');






insert into car_models (brand_id, model) value (4, '1 series');
insert into car_models (brand_id, model) value (4, '2  series');
insert into car_models (brand_id, model) value (4, '3  series');
insert into car_models (brand_id, model) value (4, '4  series');
insert into car_models (brand_id, model) value (4, '5  series');
insert into car_models (brand_id, model) value (4, '6  series');
insert into car_models (brand_id, model) value (4, '7  series');
insert into car_models (brand_id, model) value (4, '8  series');
insert into car_models (brand_id, model) value (4, 'I3');
insert into car_models (brand_id, model) value (4, 'I8');
insert into car_models (brand_id, model) value (4, 'X1');
insert into car_models (brand_id, model) value (4, 'X3');
insert into car_models (brand_id, model) value (4, 'X4');
insert into car_models (brand_id, model) value (4, 'X5');
insert into car_models (brand_id, model) value (4, 'X6');
insert into car_models (brand_id, model) value (4, 'Z1');
insert into car_models (brand_id, model) value (4, 'Z3');
insert into car_models (brand_id, model) value (4, 'Z4');
insert into car_models (brand_id, model) value (4, 'Z8');





insert into car_models (brand_id, model) value (5, 'ALLURE');
insert into car_models (brand_id, model) value (5, 'CASCADA');
insert into car_models (brand_id, model) value (5, 'CENTURY');
insert into car_models (brand_id, model) value (5, 'COMMERCIAL CHASSIS');
insert into car_models (brand_id, model) value (5, 'ELECTRA');
insert into car_models (brand_id, model) value (5, 'ENCLAVE');
insert into car_models (brand_id, model) value (5, 'ENCORE');
insert into car_models (brand_id, model) value (5, 'ENVISION');
insert into car_models (brand_id, model) value (5, 'EXCELLE');
insert into car_models (brand_id, model) value (5, 'G');
insert into car_models (brand_id, model) value (5, 'LA CROSSE');
insert into car_models (brand_id, model) value (5, 'LE SABRE');
insert into car_models (brand_id, model) value (5, 'LESABRE');
insert into car_models (brand_id, model) value (5, 'LUCERNE');
insert into car_models (brand_id, model) value (5, 'PARK AVENUE');
insert into car_models (brand_id, model) value (5, 'RAINIER');
insert into car_models (brand_id, model) value (5, 'REATTA');
insert into car_models (brand_id, model) value (5, 'REGAL');
insert into car_models (brand_id, model) value (5, 'RENDEZVOUZ');
insert into car_models (brand_id, model) value (5, 'RIVIERA');
insert into car_models (brand_id, model) value (5, 'ROADMASTER');
insert into car_models (brand_id, model) value (5, 'SAIL');
insert into car_models (brand_id, model) value (5, 'SKYLARK');
insert into car_models (brand_id, model) value (5, 'TERRAZA');
insert into car_models (brand_id, model) value (5, 'VERANO');






insert into car_models (brand_id, model) value (6, '60 SPECIAL');
insert into car_models (brand_id, model) value (6, 'ALLANTE');
insert into car_models (brand_id, model) value (6, 'ATS');
insert into car_models (brand_id, model) value (6, 'BLS');
insert into car_models (brand_id, model) value (6, 'BROUGHAM');
insert into car_models (brand_id, model) value (6, 'CATERA');
insert into car_models (brand_id, model) value (6, 'COMMERCIAL CHASSIS');
insert into car_models (brand_id, model) value (6, 'CT6');
insert into car_models (brand_id, model) value (6, 'CTS');
insert into car_models (brand_id, model) value (6, 'DEVILLE');
insert into car_models (brand_id, model) value (6, 'DTS');
insert into car_models (brand_id, model) value (6, 'ELDORADO');
insert into car_models (brand_id, model) value (6, 'ELR');
insert into car_models (brand_id, model) value (6, 'ESCALADE');
insert into car_models (brand_id, model) value (6, 'FLEETWOOD');
insert into car_models (brand_id, model) value (6, 'SEVILLE');
insert into car_models (brand_id, model) value (6, 'SEVILLE II');
insert into car_models (brand_id, model) value (6, 'SRX');
insert into car_models (brand_id, model) value (6, 'STS');
insert into car_models (brand_id, model) value (6, 'XLR');
insert into car_models (brand_id, model) value (6, 'XTS');




insert into car_models (brand_id, model) value (7, 'A1');
insert into car_models (brand_id, model) value (7, 'A160');
insert into car_models (brand_id, model) value (7, 'A3');
insert into car_models (brand_id, model) value (7, 'A5');
insert into car_models (brand_id, model) value (7, 'A520');
insert into car_models (brand_id, model) value (7, 'ALIA');
insert into car_models (brand_id, model) value (7, 'AMULET');
insert into car_models (brand_id, model) value (7, 'AMULET II');
insert into car_models (brand_id, model) value (7, 'ARRIZO 3');
insert into car_models (brand_id, model) value (7, 'ARRIZO 7');
insert into car_models (brand_id, model) value (7, 'ARRIZO M7');
insert into car_models (brand_id, model) value (7, 'BEAT');
insert into car_models (brand_id, model) value (7, 'BONUS');
insert into car_models (brand_id, model) value (7, 'CELER');
insert into car_models (brand_id, model) value (7, 'CHANCE');
insert into car_models (brand_id, model) value (7, 'CIELO');
insert into car_models (brand_id, model) value (7, 'CRISTAL');
insert into car_models (brand_id, model) value (7, 'E3');
insert into car_models (brand_id, model) value (7, 'E5');
insert into car_models (brand_id, model) value (7, 'EASTAR');
insert into car_models (brand_id, model) value (7, 'EASTAR A6');
insert into car_models (brand_id, model) value (7, 'EASTAR CROSS');
insert into car_models (brand_id, model) value (7, 'EASTER');
insert into car_models (brand_id, model) value (7, 'EGO');
insert into car_models (brand_id, model) value (7, 'EQ');
insert into car_models (brand_id, model) value (7, 'FACE');
insert into car_models (brand_id, model) value (7, 'FENGYUN');
insert into car_models (brand_id, model) value (7, 'FENGYUN 2');
insert into car_models (brand_id, model) value (7, 'FORA');
insert into car_models (brand_id, model) value (7, 'FULWIN');
insert into car_models (brand_id, model) value (7, 'INDIS');
insert into car_models (brand_id, model) value (7, 'J1');
insert into car_models (brand_id, model) value (7, 'J11');
insert into car_models (brand_id, model) value (7, 'J2');
insert into car_models (brand_id, model) value (7, 'J3');
insert into car_models (brand_id, model) value (7, 'J5');
insert into car_models (brand_id, model) value (7, 'JAGGI');
insert into car_models (brand_id, model) value (7, 'KIMO');
insert into car_models (brand_id, model) value (7, 'M1');
insert into car_models (brand_id, model) value (7, 'M11');
insert into car_models (brand_id, model) value (7, 'NICHE');
insert into car_models (brand_id, model) value (7, 'Q21');
insert into car_models (brand_id, model) value (7, 'Q22');
insert into car_models (brand_id, model) value (7, 'QIYUN');
insert into car_models (brand_id, model) value (7, 'QIYUN 1');
insert into car_models (brand_id, model) value (7, 'QIYUN 2');
insert into car_models (brand_id, model) value (7, 'QIYUN 3');
insert into car_models (brand_id, model) value (7, 'QIYUN 5');
insert into car_models (brand_id, model) value (7, 'QQ');
insert into car_models (brand_id, model) value (7, 'QQ3');
insert into car_models (brand_id, model) value (7, 'QQ6');
insert into car_models (brand_id, model) value (7, 'QQME');
insert into car_models (brand_id, model) value (7, 'RELY');
insert into car_models (brand_id, model) value (7, 'S18');
insert into car_models (brand_id, model) value (7, 'S-18');
insert into car_models (brand_id, model) value (7, 'SKIN');
insert into car_models (brand_id, model) value (7, 'TAXIM');
insert into car_models (brand_id, model) value (7, 'TENGO');
insert into car_models (brand_id, model) value (7, 'TIGGO');
insert into car_models (brand_id, model) value (7, 'TIGGO 3');
insert into car_models (brand_id, model) value (7, 'TIGGO 5');
insert into car_models (brand_id, model) value (7, 'TIGGO DR');
insert into car_models (brand_id, model) value (7, 'TIGGO FL');
insert into car_models (brand_id, model) value (7, 'TRANSCAB');
insert into car_models (brand_id, model) value (7, 'TRANSCAR');
insert into car_models (brand_id, model) value (7, 'V2');
insert into car_models (brand_id, model) value (7, 'V5');
insert into car_models (brand_id, model) value (7, 'VERY');
insert into car_models (brand_id, model) value (7, 'X1');





insert into car_models (brand_id, model) value (8, 'Agile');
insert into car_models (brand_id, model) value (8, 'Alero');
insert into car_models (brand_id, model) value (8, 'Astra');
insert into car_models (brand_id, model) value (8, 'Astro');
insert into car_models (brand_id, model) value (8, 'Avalanche');
insert into car_models (brand_id, model) value (8, 'Aveo');
insert into car_models (brand_id, model) value (8, 'Beat');
insert into car_models (brand_id, model) value (8, 'Beretta');
insert into car_models (brand_id, model) value (8, 'Blazer');
insert into car_models (brand_id, model) value (8, 'Camaro');
insert into car_models (brand_id, model) value (8, 'Caprice');
insert into car_models (brand_id, model) value (8, 'Captiva');
insert into car_models (brand_id, model) value (8, 'Cavalier');
insert into car_models (brand_id, model) value (8, 'Celebrity');
insert into car_models (brand_id, model) value (8, 'Celta');
insert into car_models (brand_id, model) value (8, 'Chevelle');
insert into car_models (brand_id, model) value (8, 'Chevette');
insert into car_models (brand_id, model) value (8, 'Chevy Van');
insert into car_models (brand_id, model) value (8, 'Citation');
insert into car_models (brand_id, model) value (8, 'Cobalt');
insert into car_models (brand_id, model) value (8, 'Colorado');
insert into car_models (brand_id, model) value (8, 'Combo');
insert into car_models (brand_id, model) value (8, 'Corsa');
insert into car_models (brand_id, model) value (8, 'Corsica');
insert into car_models (brand_id, model) value (8, 'Corvair');
insert into car_models (brand_id, model) value (8, 'Corvette');
insert into car_models (brand_id, model) value (8, 'Cruze');
insert into car_models (brand_id, model) value (8, 'D20');
insert into car_models (brand_id, model) value (8, 'El Camino');
insert into car_models (brand_id, model) value (8, 'Enjoy');
insert into car_models (brand_id, model) value (8, 'Epica');
insert into car_models (brand_id, model) value (8, 'Equinox');
insert into car_models (brand_id, model) value (8, 'Evanda');
insert into car_models (brand_id, model) value (8, 'Express');
insert into car_models (brand_id, model) value (8, 'Forester');
insert into car_models (brand_id, model) value (8, 'Grand Vitara');
insert into car_models (brand_id, model) value (8, 'HHR');
insert into car_models (brand_id, model) value (8, 'Impala');
insert into car_models (brand_id, model) value (8, 'Ipanema');
insert into car_models (brand_id, model) value (8, 'Jimny');
insert into car_models (brand_id, model) value (8, 'Kadett');
insert into car_models (brand_id, model) value (8, 'Kalos');
insert into car_models (brand_id, model) value (8, 'Kommando');
insert into car_models (brand_id, model) value (8, 'Lacetti');
insert into car_models (brand_id, model) value (8, 'Lanos');
insert into car_models (brand_id, model) value (8, 'Lova');
insert into car_models (brand_id, model) value (8, 'Lumina');
insert into car_models (brand_id, model) value (8, 'Lumina APV');
insert into car_models (brand_id, model) value (8, 'LUV D-MAX');
insert into car_models (brand_id, model) value (8, 'Malibu');
insert into car_models (brand_id, model) value (8, 'Matiz');
insert into car_models (brand_id, model) value (8, 'Meriva');
insert into car_models (brand_id, model) value (8, 'Metro');
insert into car_models (brand_id, model) value (8, 'Montana');
insert into car_models (brand_id, model) value (8, 'Monte Carlo');
insert into car_models (brand_id, model) value (8, 'Monza');
insert into car_models (brand_id, model) value (8, 'MW');
insert into car_models (brand_id, model) value (8, 'N200');
insert into car_models (brand_id, model) value (8, 'N300');
insert into car_models (brand_id, model) value (8, 'Niva');
insert into car_models (brand_id, model) value (8, 'Nova');
insert into car_models (brand_id, model) value (8, 'Nubira');
insert into car_models (brand_id, model) value (8, 'Omega');
insert into car_models (brand_id, model) value (8, 'Onix');
insert into car_models (brand_id, model) value (8, 'Optra');
insert into car_models (brand_id, model) value (8, 'Orlando');
insert into car_models (brand_id, model) value (8, 'Prisma');
insert into car_models (brand_id, model) value (8, 'Prizm');
insert into car_models (brand_id, model) value (8, 'Rezzo');
insert into car_models (brand_id, model) value (8, 'S-10');
insert into car_models (brand_id, model) value (8, 'Sail');
insert into car_models (brand_id, model) value (8, 'Silverado');
insert into car_models (brand_id, model) value (8, 'Sonic');
insert into car_models (brand_id, model) value (8, 'Sonora');
insert into car_models (brand_id, model) value (8, 'Spark');
insert into car_models (brand_id, model) value (8, 'Spin');
insert into car_models (brand_id, model) value (8, 'SSR');
insert into car_models (brand_id, model) value (8, 'Suburban');
insert into car_models (brand_id, model) value (8, 'Tacuma');
insert into car_models (brand_id, model) value (8, 'Tahoe');
insert into car_models (brand_id, model) value (8, 'Tavera');
insert into car_models (brand_id, model) value (8, 'Tornado');
insert into car_models (brand_id, model) value (8, 'Tracker');
insert into car_models (brand_id, model) value (8, 'TrailBlazer');
insert into car_models (brand_id, model) value (8, 'Trans Sport');
insert into car_models (brand_id, model) value (8, 'Traverse');
insert into car_models (brand_id, model) value (8, 'Trax');
insert into car_models (brand_id, model) value (8, 'Uplander');
insert into car_models (brand_id, model) value (8, 'Utility');
insert into car_models (brand_id, model) value (8, 'Vectra');
insert into car_models (brand_id, model) value (8, 'Vega');
insert into car_models (brand_id, model) value (8, 'Venture');
insert into car_models (brand_id, model) value (8, 'Vitara');
insert into car_models (brand_id, model) value (8, 'Viva');
insert into car_models (brand_id, model) value (8, 'Vivant');
insert into car_models (brand_id, model) value (8, 'Volt');
insert into car_models (brand_id, model) value (8, 'Zafira');





insert into car_models (brand_id, model) value (9, '300C');
insert into car_models (brand_id, model) value (9, '300M');
insert into car_models (brand_id, model) value (9, 'Aspen');
insert into car_models (brand_id, model) value (9, 'Cirrus');
insert into car_models (brand_id, model) value (9, 'Concorde');
insert into car_models (brand_id, model) value (9, 'Crossfire');
insert into car_models (brand_id, model) value (9, 'Daytona');
insert into car_models (brand_id, model) value (9, 'Fifth Avenue');
insert into car_models (brand_id, model) value (9, 'Grand Voyager');
insert into car_models (brand_id, model) value (9, 'Intrepid');
insert into car_models (brand_id, model) value (9, 'LeBaron');
insert into car_models (brand_id, model) value (9, 'LHS');
insert into car_models (brand_id, model) value (9, 'Neon');
insert into car_models (brand_id, model) value (9, 'NEW Yorker');
insert into car_models (brand_id, model) value (9, 'Pacifica');
insert into car_models (brand_id, model) value (9, 'Prowler');
insert into car_models (brand_id, model) value (9, 'PT Cruiser');
insert into car_models (brand_id, model) value (9, 'Saratoga');
insert into car_models (brand_id, model) value (9, 'Sebring');
insert into car_models (brand_id, model) value (9, 'Stratus');
insert into car_models (brand_id, model) value (9, 'Town and Country');
insert into car_models (brand_id, model) value (9, 'Vision');
insert into car_models (brand_id, model) value (9, 'Voyager');



insert into car_models (brand_id, model) value (10, '2 CV');
insert into car_models (brand_id, model) value (10, 'AX');
insert into car_models (brand_id, model) value (10, 'Berlingo');
insert into car_models (brand_id, model) value (10, 'BX');
insert into car_models (brand_id, model) value (10, 'C-Crosser');
insert into car_models (brand_id, model) value (10, 'C-Elysee');
insert into car_models (brand_id, model) value (10, 'C1');
insert into car_models (brand_id, model) value (10, 'C2');
insert into car_models (brand_id, model) value (10, 'C25');
insert into car_models (brand_id, model) value (10, 'C3');
insert into car_models (brand_id, model) value (10, 'C3 Aircross');
insert into car_models (brand_id, model) value (10, 'C3 Picasso');
insert into car_models (brand_id, model) value (10, 'C4');
insert into car_models (brand_id, model) value (10, 'C4 AirCross');
insert into car_models (brand_id, model) value (10, 'C4 Picasso');
insert into car_models (brand_id, model) value (10, 'C5');
insert into car_models (brand_id, model) value (10, 'C5 Aircross');
insert into car_models (brand_id, model) value (10, 'C6');
insert into car_models (brand_id, model) value (10, 'C8');
insert into car_models (brand_id, model) value (10, 'CX');
insert into car_models (brand_id, model) value (10, 'DS3');
insert into car_models (brand_id, model) value (10, 'DS4');
insert into car_models (brand_id, model) value (10, 'DS5');
insert into car_models (brand_id, model) value (10, 'Evasion');
insert into car_models (brand_id, model) value (10, 'Jumper');
insert into car_models (brand_id, model) value (10, 'Jumpy');
insert into car_models (brand_id, model) value (10, 'Nemo');
insert into car_models (brand_id, model) value (10, 'Saxo');
insert into car_models (brand_id, model) value (10, 'Xantia');
insert into car_models (brand_id, model) value (10, 'XM');
insert into car_models (brand_id, model) value (10, 'Xsara');
insert into car_models (brand_id, model) value (10, 'Xsara Picasso');
insert into car_models (brand_id, model) value (10, 'ZX');




insert into car_models (brand_id, model) value (11, '1210');
insert into car_models (brand_id, model) value (11, '1304');
insert into car_models (brand_id, model) value (11, '1307');
insert into car_models (brand_id, model) value (11, '1309');
insert into car_models (brand_id, model) value (11, '1310');
insert into car_models (brand_id, model) value (11, '1410');
insert into car_models (brand_id, model) value (11, 'DOKKER');
insert into car_models (brand_id, model) value (11, 'DUSTER');
insert into car_models (brand_id, model) value (11, 'LODGY');
insert into car_models (brand_id, model) value (11, 'LOGAN');
insert into car_models (brand_id, model) value (11, 'LOGAN EXPRESS');
insert into car_models (brand_id, model) value (11, 'LOGAN II');
insert into car_models (brand_id, model) value (11, 'LOGAN MCV');
insert into car_models (brand_id, model) value (11, 'LOGAN MCV II');
insert into car_models (brand_id, model) value (11, 'NOVA');
insert into car_models (brand_id, model) value (11, 'PICKUP');
insert into car_models (brand_id, model) value (11, 'SANDERO');
insert into car_models (brand_id, model) value (11, 'SANDERO II');
insert into car_models (brand_id, model) value (11, 'SHIFTER');
insert into car_models (brand_id, model) value (11, 'SOLENZA');
insert into car_models (brand_id, model) value (11, 'SUPERNOVA');





insert into car_models (brand_id, model) value (12, 'Gentra');
insert into car_models (brand_id, model) value (12, 'Matiz');
insert into car_models (brand_id, model) value (12, 'Nexia');




insert into car_models (brand_id, model) value (13, '550');
insert into car_models (brand_id, model) value (13, '660');
insert into car_models (brand_id, model) value (13, '1000');
insert into car_models (brand_id, model) value (13, '1300');
insert into car_models (brand_id, model) value (13, 'ALTIS');
insert into car_models (brand_id, model) value (13, 'APPLAUSE');
insert into car_models (brand_id, model) value (13, 'APPLAUSE I');
insert into car_models (brand_id, model) value (13, 'APPLAUSE II');
insert into car_models (brand_id, model) value (13, 'ASCEND');
insert into car_models (brand_id, model) value (13, 'ATRAI');
insert into car_models (brand_id, model) value (13, 'ATRAI WAGON');
insert into car_models (brand_id, model) value (13, 'AYLA');
insert into car_models (brand_id, model) value (13, 'BEGO');
insert into car_models (brand_id, model) value (13, 'BOON');
insert into car_models (brand_id, model) value (13, 'BOON LUMINAS');
insert into car_models (brand_id, model) value (13, 'CAST');
insert into car_models (brand_id, model) value (13, 'CERIA');
insert into car_models (brand_id, model) value (13, 'CHARADE');
insert into car_models (brand_id, model) value (13, 'CHARADE CENTRO IV');
insert into car_models (brand_id, model) value (13, 'CHARADE III');
insert into car_models (brand_id, model) value (13, 'CHARADE IV');
insert into car_models (brand_id, model) value (13, 'CITIVAN');
insert into car_models (brand_id, model) value (13, 'CITY BREEZE');
insert into car_models (brand_id, model) value (13, 'COO');
insert into car_models (brand_id, model) value (13, 'COPEN');
insert into car_models (brand_id, model) value (13, 'CUORE II');
insert into car_models (brand_id, model) value (13, 'CUORE III');
insert into car_models (brand_id, model) value (13, 'CUORE IV');
insert into car_models (brand_id, model) value (13, 'CUORE V');
insert into car_models (brand_id, model) value (13, 'CUORE VI');
insert into car_models (brand_id, model) value (13, 'CUORE VII');
insert into car_models (brand_id, model) value (13, 'DELTA');
insert into car_models (brand_id, model) value (13, 'DELTA WIDE');
insert into car_models (brand_id, model) value (13, 'DEVAN');
insert into car_models (brand_id, model) value (13, 'DOMINO II');
insert into car_models (brand_id, model) value (13, 'ESPRI');
insert into car_models (brand_id, model) value (13, 'ESSE');
insert into car_models (brand_id, model) value (13, 'EXTOL');
insert into car_models (brand_id, model) value (13, 'F50');
insert into car_models (brand_id, model) value (13, 'FEROZA');
insert into car_models (brand_id, model) value (13, 'FEROZA HARD TOP');
insert into car_models (brand_id, model) value (13, 'FOURTRAK');
insert into car_models (brand_id, model) value (13, 'GRAN MAX');
insert into car_models (brand_id, model) value (13, 'GRAN MOVE');
insert into car_models (brand_id, model) value (13, 'HANDI');
insert into car_models (brand_id, model) value (13, 'HANDIVAN II');
insert into car_models (brand_id, model) value (13, 'HIJET');
insert into car_models (brand_id, model) value (13, 'LEEZA');
insert into car_models (brand_id, model) value (13, 'LUXIO');
insert into car_models (brand_id, model) value (13, 'MATERIA');
insert into car_models (brand_id, model) value (13, 'MAX');
insert into car_models (brand_id, model) value (13, 'MEBIUS');
insert into car_models (brand_id, model) value (13, 'MIDGET');
insert into car_models (brand_id, model) value (13, 'MIRA COCOA');
insert into car_models (brand_id, model) value (13, 'MIRA ES');
insert into car_models (brand_id, model) value (13, 'MIRA GINO I');
insert into car_models (brand_id, model) value (13, 'MIRA GINO II');
insert into car_models (brand_id, model) value (13, 'MIRA II');
insert into car_models (brand_id, model) value (13, 'MIRA III');
insert into car_models (brand_id, model) value (13, 'MIRA IV');
insert into car_models (brand_id, model) value (13, 'MIRA V');
insert into car_models (brand_id, model) value (13, 'MIRA VII');
insert into car_models (brand_id, model) value (13, 'MOVE');
insert into car_models (brand_id, model) value (13, 'MOVE CONTE');
insert into car_models (brand_id, model) value (13, 'NAKED');
insert into car_models (brand_id, model) value (13, 'OPTI I');
insert into car_models (brand_id, model) value (13, 'OPTI II');
insert into car_models (brand_id, model) value (13, 'PYZAR');
insert into car_models (brand_id, model) value (13, 'ROCKY');
insert into car_models (brand_id, model) value (13, 'ROCKY HARD TOP');
insert into car_models (brand_id, model) value (13, 'RUGGER');
insert into car_models (brand_id, model) value (13, 'RUSH');
insert into car_models (brand_id, model) value (13, 'SIRION');
insert into car_models (brand_id, model) value (13, 'SKYWING');
insert into car_models (brand_id, model) value (13, 'SOCIAL');
insert into car_models (brand_id, model) value (13, 'SONICA');
insert into car_models (brand_id, model) value (13, 'SPORTRAK');
insert into car_models (brand_id, model) value (13, 'STORIA');
insert into car_models (brand_id, model) value (13, 'TAFT');
insert into car_models (brand_id, model) value (13, 'TANTO');
insert into car_models (brand_id, model) value (13, 'TARUNA');
insert into car_models (brand_id, model) value (13, 'TERIOS');
insert into car_models (brand_id, model) value (13, 'TERIOS KID');
insert into car_models (brand_id, model) value (13, 'TREVIS');
insert into car_models (brand_id, model) value (13, 'VALERA IV');
insert into car_models (brand_id, model) value (13, 'WAKE');
insert into car_models (brand_id, model) value (13, 'XENIA');
insert into car_models (brand_id, model) value (13, 'YRV');
insert into car_models (brand_id, model) value (13, 'ZEBRA');





insert into car_models (brand_id, model) value (14, 'GO');
insert into car_models (brand_id, model) value (14, 'MI-DO');
insert into car_models (brand_id, model) value (14, 'ON-DO');




insert into car_models (brand_id, model) value (15, 'Avenger');
insert into car_models (brand_id, model) value (15, 'Caliber');
insert into car_models (brand_id, model) value (15, 'Caravan');
insert into car_models (brand_id, model) value (15, 'Challenger');
insert into car_models (brand_id, model) value (15, 'Charger');
insert into car_models (brand_id, model) value (15, 'Dakota');
insert into car_models (brand_id, model) value (15, 'Dart');
insert into car_models (brand_id, model) value (15, 'Daytona');
insert into car_models (brand_id, model) value (15, 'Durango');
insert into car_models (brand_id, model) value (15, 'Grand Caravan');
insert into car_models (brand_id, model) value (15, 'Intrepid');
insert into car_models (brand_id, model) value (15, 'Journey');
insert into car_models (brand_id, model) value (15, 'Magnum');
insert into car_models (brand_id, model) value (15, 'Monaco');
insert into car_models (brand_id, model) value (15, 'Neon');
insert into car_models (brand_id, model) value (15, 'Nitro');
insert into car_models (brand_id, model) value (15, 'Ram');
insert into car_models (brand_id, model) value (15, 'Ramcharger');
insert into car_models (brand_id, model) value (15, 'Shadow');
insert into car_models (brand_id, model) value (15, 'Spirit');
insert into car_models (brand_id, model) value (15, 'Stealth');
insert into car_models (brand_id, model) value (15, 'Stratus');
insert into car_models (brand_id, model) value (15, 'Viper');



insert into car_models (brand_id, model) value (16, '126');
insert into car_models (brand_id, model) value (16, '500');
insert into car_models (brand_id, model) value (16, '500L');
insert into car_models (brand_id, model) value (16, '600');
insert into car_models (brand_id, model) value (16, 'Albea');
insert into car_models (brand_id, model) value (16, 'Barchetta');
insert into car_models (brand_id, model) value (16, 'Brava');
insert into car_models (brand_id, model) value (16, 'Bravo');
insert into car_models (brand_id, model) value (16, 'Cinquecento');
insert into car_models (brand_id, model) value (16, 'Coupe');
insert into car_models (brand_id, model) value (16, 'Croma');
insert into car_models (brand_id, model) value (16, 'Doblo');
insert into car_models (brand_id, model) value (16, 'Ducato');
insert into car_models (brand_id, model) value (16, 'Fiorino');
insert into car_models (brand_id, model) value (16, 'Freemont');
insert into car_models (brand_id, model) value (16, 'Fullback');
insert into car_models (brand_id, model) value (16, 'Idea');
insert into car_models (brand_id, model) value (16, 'Linea');
insert into car_models (brand_id, model) value (16, 'Marea');
insert into car_models (brand_id, model) value (16, 'Multipla');
insert into car_models (brand_id, model) value (16, 'Palio');
insert into car_models (brand_id, model) value (16, 'Panda');
insert into car_models (brand_id, model) value (16, 'Punto');
insert into car_models (brand_id, model) value (16, 'Qubo');
insert into car_models (brand_id, model) value (16, 'Regata');
insert into car_models (brand_id, model) value (16, 'Scudo');
insert into car_models (brand_id, model) value (16, 'Sedici');
insert into car_models (brand_id, model) value (16, 'Seicento');
insert into car_models (brand_id, model) value (16, 'Siena');
insert into car_models (brand_id, model) value (16, 'Stilo');
insert into car_models (brand_id, model) value (16, 'Tempra');
insert into car_models (brand_id, model) value (16, 'Tipo');
insert into car_models (brand_id, model) value (16, 'Ulysse');
insert into car_models (brand_id, model) value (16, 'UNO');



insert into car_models (brand_id, model) value (17, 'Aerostar');
insert into car_models (brand_id, model) value (17, 'Aspire');
insert into car_models (brand_id, model) value (17, 'B-Max');
insert into car_models (brand_id, model) value (17, 'Bronco');
insert into car_models (brand_id, model) value (17, 'C-Max');
insert into car_models (brand_id, model) value (17, 'Capri');
insert into car_models (brand_id, model) value (17, 'Contour');
insert into car_models (brand_id, model) value (17, 'Cougar');
insert into car_models (brand_id, model) value (17, 'Courier');
insert into car_models (brand_id, model) value (17, 'Crown Victoria');
insert into car_models (brand_id, model) value (17, 'E-series');
insert into car_models (brand_id, model) value (17, 'Econovan');
insert into car_models (brand_id, model) value (17, 'EcoSport');
insert into car_models (brand_id, model) value (17, 'Edge');
insert into car_models (brand_id, model) value (17, 'Escape');
insert into car_models (brand_id, model) value (17, 'Escort');
insert into car_models (brand_id, model) value (17, 'Excursion');
insert into car_models (brand_id, model) value (17, 'Expedition');
insert into car_models (brand_id, model) value (17, 'Explorer');
insert into car_models (brand_id, model) value (17, 'Explorer Sport Trac');
insert into car_models (brand_id, model) value (17, 'F-150');
insert into car_models (brand_id, model) value (17, 'Festiva');
insert into car_models (brand_id, model) value (17, 'Fiesta');
insert into car_models (brand_id, model) value (17, 'Five Hundred');
insert into car_models (brand_id, model) value (17, 'Flex');
insert into car_models (brand_id, model) value (17, 'Focus');
insert into car_models (brand_id, model) value (17, 'Freestyle');
insert into car_models (brand_id, model) value (17, 'Fusion');
insert into car_models (brand_id, model) value (17, 'Galaxy');
insert into car_models (brand_id, model) value (17, 'Granada');
insert into car_models (brand_id, model) value (17, 'Grand C-Max');
insert into car_models (brand_id, model) value (17, 'GT');
insert into car_models (brand_id, model) value (17, 'KA');
insert into car_models (brand_id, model) value (17, 'Kuga');
insert into car_models (brand_id, model) value (17, 'Maverick');
insert into car_models (brand_id, model) value (17, 'Mondeo');
insert into car_models (brand_id, model) value (17, 'Mustang');
insert into car_models (brand_id, model) value (17, 'Orion');
insert into car_models (brand_id, model) value (17, 'Probe');
insert into car_models (brand_id, model) value (17, 'Puma');
insert into car_models (brand_id, model) value (17, 'Ranger');
insert into car_models (brand_id, model) value (17, 'S-Max');
insert into car_models (brand_id, model) value (17, 'Scorpio');
insert into car_models (brand_id, model) value (17, 'Shelby');
insert into car_models (brand_id, model) value (17, 'Sierra');
insert into car_models (brand_id, model) value (17, 'Taunus');
insert into car_models (brand_id, model) value (17, 'Taurus');
insert into car_models (brand_id, model) value (17, 'Tempo');
insert into car_models (brand_id, model) value (17, 'Thunderbird');
insert into car_models (brand_id, model) value (17, 'Torino');
insert into car_models (brand_id, model) value (17, 'Tourneo Connect');
insert into car_models (brand_id, model) value (17, 'Tourneo Custom');
insert into car_models (brand_id, model) value (17, 'Transit');
insert into car_models (brand_id, model) value (17, 'Transit Connect');
insert into car_models (brand_id, model) value (17, 'Windstar');



insert into car_models (brand_id, model) value (18, 'GAZELLE');
insert into car_models (brand_id, model) value (18, 'GAZELLE NEXT');
insert into car_models (brand_id, model) value (18, 'GAZON');
insert into car_models (brand_id, model) value (18, 'SADKO');
insert into car_models (brand_id, model) value (18, 'SOBOL');
insert into car_models (brand_id, model) value (18, 'TIGR');
insert into car_models (brand_id, model) value (18, 'VALDAI');
insert into car_models (brand_id, model) value (18, 'VALDAJ');
insert into car_models (brand_id, model) value (18, 'VOLGA');
insert into car_models (brand_id, model) value (18, 'VOLGA SIBER');



insert into car_models (brand_id, model) value (19, 'Atlas');
insert into car_models (brand_id, model) value (19, 'Beauty Leopard');
insert into car_models (brand_id, model) value (19, 'CK (Otaka)');
insert into car_models (brand_id, model) value (19, 'Emgrand');
insert into car_models (brand_id, model) value (19, 'Emgrand 7');
insert into car_models (brand_id, model) value (19, 'Emgrand GT');
insert into car_models (brand_id, model) value (19, 'Emgrand X7');
insert into car_models (brand_id, model) value (19, 'LC Cross');
insert into car_models (brand_id, model) value (19, 'MK');
insert into car_models (brand_id, model) value (19, 'SC7');
insert into car_models (brand_id, model) value (19, 'Vision');



insert into car_models (brand_id, model) value (20, 'ACADIA');
insert into car_models (brand_id, model) value (20, 'C1500');
insert into car_models (brand_id, model) value (20, 'C2500');
insert into car_models (brand_id, model) value (20, 'C3500');
insert into car_models (brand_id, model) value (20, 'CANYON');
insert into car_models (brand_id, model) value (20, 'ENVOY');
insert into car_models (brand_id, model) value (20, 'G1500');
insert into car_models (brand_id, model) value (20, 'G1500 EXTENDED');
insert into car_models (brand_id, model) value (20, 'G1500 STANDARD');
insert into car_models (brand_id, model) value (20, 'G2500');
insert into car_models (brand_id, model) value (20, 'G2500 EXTENDED');
insert into car_models (brand_id, model) value (20, 'G2500 STANDARD');
insert into car_models (brand_id, model) value (20, 'G3500');
insert into car_models (brand_id, model) value (20, 'G3500 EXTENDED');
insert into car_models (brand_id, model) value (20, 'G3500 STANDARD');
insert into car_models (brand_id, model) value (20, 'JIMMY');
insert into car_models (brand_id, model) value (20, 'K1500');
insert into car_models (brand_id, model) value (20, 'K2500');
insert into car_models (brand_id, model) value (20, 'K3500');
insert into car_models (brand_id, model) value (20, 'P3500');
insert into car_models (brand_id, model) value (20, 'P3500 STANDARD');
insert into car_models (brand_id, model) value (20, 'R1500');
insert into car_models (brand_id, model) value (20, 'R2500');
insert into car_models (brand_id, model) value (20, 'R3500');
insert into car_models (brand_id, model) value (20, 'S15');
insert into car_models (brand_id, model) value (20, 'S15 JIMMY');
insert into car_models (brand_id, model) value (20, 'SAFARI');
insert into car_models (brand_id, model) value (20, 'SAFARI EXTENDED');
insert into car_models (brand_id, model) value (20, 'SAFARI STANDARD');
insert into car_models (brand_id, model) value (20, 'SAVANA');
insert into car_models (brand_id, model) value (20, 'SAVANA 1500');
insert into car_models (brand_id, model) value (20, 'SAVANA 1500 STANDARD');
insert into car_models (brand_id, model) value (20, 'SAVANA 2500');
insert into car_models (brand_id, model) value (20, 'SAVANA 2500 EXTENDED');
insert into car_models (brand_id, model) value (20, 'SAVANA 2500 STANDARD');
insert into car_models (brand_id, model) value (20, 'SAVANA 3500');
insert into car_models (brand_id, model) value (20, 'SAVANA 3500 EXTENDED');
insert into car_models (brand_id, model) value (20, 'SAVANA 3500 STANDARD');
insert into car_models (brand_id, model) value (20, 'SAVANA 4500');
insert into car_models (brand_id, model) value (20, 'SIERRA');
insert into car_models (brand_id, model) value (20, 'SIERRA 1500');
insert into car_models (brand_id, model) value (20, 'SIERRA 1500 HD');
insert into car_models (brand_id, model) value (20, 'SIERRA 2500');
insert into car_models (brand_id, model) value (20, 'SIERRA 2500 HD');
insert into car_models (brand_id, model) value (20, 'SIERRA 3500');
insert into car_models (brand_id, model) value (20, 'SIERRA 3500 HD');
insert into car_models (brand_id, model) value (20, 'SIERRA DENALI');
insert into car_models (brand_id, model) value (20, 'SONOMA');
insert into car_models (brand_id, model) value (20, 'SUBURBAN');
insert into car_models (brand_id, model) value (20, 'SYCLONE');
insert into car_models (brand_id, model) value (20, 'TERRAIN');
insert into car_models (brand_id, model) value (20, 'TRACKER');
insert into car_models (brand_id, model) value (20, 'TYPHOON');
insert into car_models (brand_id, model) value (20, 'V1500');
insert into car_models (brand_id, model) value (20, 'V2500');
insert into car_models (brand_id, model) value (20, 'V3500');
insert into car_models (brand_id, model) value (20, 'YUKON');
insert into car_models (brand_id, model) value (20, 'YUKON XL 1500');
insert into car_models (brand_id, model) value (20, 'YUKON XL 2500');



insert into car_models (brand_id, model) value (21, 'Coolbear');
insert into car_models (brand_id, model) value (21, 'Deer');
insert into car_models (brand_id, model) value (21, 'H3');
insert into car_models (brand_id, model) value (21, 'H5');
insert into car_models (brand_id, model) value (21, 'H6');
insert into car_models (brand_id, model) value (21, 'Hover');
insert into car_models (brand_id, model) value (21, 'M4');
insert into car_models (brand_id, model) value (21, 'Peri');
insert into car_models (brand_id, model) value (21, 'Safe');
insert into car_models (brand_id, model) value (21, 'Wingle 3');
insert into car_models (brand_id, model) value (21, 'Wingle 5');
insert into car_models (brand_id, model) value (21, 'Wingle 6');


insert into car_models (brand_id, model) value (22, 'Accord');
insert into car_models (brand_id, model) value (22, 'Airwave');
insert into car_models (brand_id, model) value (22, 'ASCOT');
insert into car_models (brand_id, model) value (22, 'Avancier');
insert into car_models (brand_id, model) value (22, 'Capa');
insert into car_models (brand_id, model) value (22, 'City');
insert into car_models (brand_id, model) value (22, 'Civic');
insert into car_models (brand_id, model) value (22, 'Concerto');
insert into car_models (brand_id, model) value (22, 'CR-V');
insert into car_models (brand_id, model) value (22, 'CR-Z');
insert into car_models (brand_id, model) value (22, 'Crossroad');
insert into car_models (brand_id, model) value (22, 'Crosstour');
insert into car_models (brand_id, model) value (22, 'CRX');
insert into car_models (brand_id, model) value (22, 'Domani');
insert into car_models (brand_id, model) value (22, 'Element');
insert into car_models (brand_id, model) value (22, 'Elysion');
insert into car_models (brand_id, model) value (22, 'Fit');
insert into car_models (brand_id, model) value (22, 'Fit Aria');
insert into car_models (brand_id, model) value (22, 'FR-V');
insert into car_models (brand_id, model) value (22, 'Hr-V');
insert into car_models (brand_id, model) value (22, 'Insight');
insert into car_models (brand_id, model) value (22, 'Integra');
insert into car_models (brand_id, model) value (22, 'Jazz');
insert into car_models (brand_id, model) value (22, 'Legend');
insert into car_models (brand_id, model) value (22, 'Life');
insert into car_models (brand_id, model) value (22, 'Logo');
insert into car_models (brand_id, model) value (22, 'Mobilio');
insert into car_models (brand_id, model) value (22, 'NSX');
insert into car_models (brand_id, model) value (22, 'Odyssey');
insert into car_models (brand_id, model) value (22, 'Orthia');
insert into car_models (brand_id, model) value (22, 'Passport');
insert into car_models (brand_id, model) value (22, 'Pilot');
insert into car_models (brand_id, model) value (22, 'Prelude');
insert into car_models (brand_id, model) value (22, 'Rafaga');
insert into car_models (brand_id, model) value (22, 'Ridgeline');
insert into car_models (brand_id, model) value (22, 'S-MX');
insert into car_models (brand_id, model) value (22, 'S2000');
insert into car_models (brand_id, model) value (22, 'Saber');
insert into car_models (brand_id, model) value (22, 'Shuttle');
insert into car_models (brand_id, model) value (22, 'Stepwgn');
insert into car_models (brand_id, model) value (22, 'Stream');
insert into car_models (brand_id, model) value (22, 'Thats');
insert into car_models (brand_id, model) value (22, 'Today');
insert into car_models (brand_id, model) value (22, 'Vamos');
insert into car_models (brand_id, model) value (22, 'Vigor');
insert into car_models (brand_id, model) value (22, 'Z');



insert into car_models (brand_id, model) value (23, 'H1');
insert into car_models (brand_id, model) value (23, 'H2');
insert into car_models (brand_id, model) value (23, 'H3');




insert into car_models (brand_id, model) value (24, 'Accent');
insert into car_models (brand_id, model) value (24, 'Amica');
insert into car_models (brand_id, model) value (24, 'Atos');
insert into car_models (brand_id, model) value (24, 'Atos Prime');
insert into car_models (brand_id, model) value (24, 'Atoz');
insert into car_models (brand_id, model) value (24, 'Avante');
insert into car_models (brand_id, model) value (24, 'Azera');
insert into car_models (brand_id, model) value (24, 'Centennial');
insert into car_models (brand_id, model) value (24, 'Click');
insert into car_models (brand_id, model) value (24, 'Coupe');
insert into car_models (brand_id, model) value (24, 'Creta');
insert into car_models (brand_id, model) value (24, 'Dynasty');
insert into car_models (brand_id, model) value (24, 'Elantra');
insert into car_models (brand_id, model) value (24, 'Elantra GT');
insert into car_models (brand_id, model) value (24, 'Elantra Touring');
insert into car_models (brand_id, model) value (24, 'Entourage');
insert into car_models (brand_id, model) value (24, 'Eon');
insert into car_models (brand_id, model) value (24, 'Equus');
insert into car_models (brand_id, model) value (24, 'Excel');
insert into car_models (brand_id, model) value (24, 'Galloper');
insert into car_models (brand_id, model) value (24, 'Genesis');
insert into car_models (brand_id, model) value (24, 'Getz');
insert into car_models (brand_id, model) value (24, 'Grand Avega');
insert into car_models (brand_id, model) value (24, 'Grandeur');
insert into car_models (brand_id, model) value (24, 'H-1');
insert into car_models (brand_id, model) value (24, 'H-100');
insert into car_models (brand_id, model) value (24, 'HB20');
insert into car_models (brand_id, model) value (24, 'i10');
insert into car_models (brand_id, model) value (24, 'i20');
insert into car_models (brand_id, model) value (24, 'i30');
insert into car_models (brand_id, model) value (24, 'i40');
insert into car_models (brand_id, model) value (24, 'i45');
insert into car_models (brand_id, model) value (24, 'i800');
insert into car_models (brand_id, model) value (24, 'iLoad');
insert into car_models (brand_id, model) value (24, 'iMax');
insert into car_models (brand_id, model) value (24, 'ix20');
insert into car_models (brand_id, model) value (24, 'ix35');
insert into car_models (brand_id, model) value (24, 'ix55');
insert into car_models (brand_id, model) value (24, 'Lantra');
insert into car_models (brand_id, model) value (24, 'Lavita');
insert into car_models (brand_id, model) value (24, 'Marcia');
insert into car_models (brand_id, model) value (24, 'Matrix');
insert into car_models (brand_id, model) value (24, 'NF');
insert into car_models (brand_id, model) value (24, 'Pony');
insert into car_models (brand_id, model) value (24, 'Porter');
insert into car_models (brand_id, model) value (24, 'Presto');
insert into car_models (brand_id, model) value (24, 'Rohens');
insert into car_models (brand_id, model) value (24, 'S-Coupe');
insert into car_models (brand_id, model) value (24, 'Santa Fe');
insert into car_models (brand_id, model) value (24, 'Santamo');
insert into car_models (brand_id, model) value (24, 'Satellite');
insert into car_models (brand_id, model) value (24, 'Solaris');
insert into car_models (brand_id, model) value (24, 'Sonata');
insert into car_models (brand_id, model) value (24, 'Stellar');
insert into car_models (brand_id, model) value (24, 'TB');
insert into car_models (brand_id, model) value (24, 'Terracan');
insert into car_models (brand_id, model) value (24, 'Tiburon');
insert into car_models (brand_id, model) value (24, 'Trajet');
insert into car_models (brand_id, model) value (24, 'Trajet XG');
insert into car_models (brand_id, model) value (24, 'Tucson');
insert into car_models (brand_id, model) value (24, 'Tucson ix');
insert into car_models (brand_id, model) value (24, 'Tuscani');
insert into car_models (brand_id, model) value (24, 'Veloster');
insert into car_models (brand_id, model) value (24, 'Veracruz');
insert into car_models (brand_id, model) value (24, 'Verna');
insert into car_models (brand_id, model) value (24, 'XG');




insert into car_models (brand_id, model) value (25, 'EX-Series');
insert into car_models (brand_id, model) value (25, 'FX-Series');
insert into car_models (brand_id, model) value (25, 'G-Series');
insert into car_models (brand_id, model) value (25, 'I-Series');
insert into car_models (brand_id, model) value (25, 'J30');
insert into car_models (brand_id, model) value (25, 'JX-Series');
insert into car_models (brand_id, model) value (25, 'M-Series');
insert into car_models (brand_id, model) value (25, 'Q45');
insert into car_models (brand_id, model) value (25, 'Q50');
insert into car_models (brand_id, model) value (25, 'Q70');
insert into car_models (brand_id, model) value (25, 'QX-Series');
insert into car_models (brand_id, model) value (25, 'QX50');
insert into car_models (brand_id, model) value (25, 'QX60');
insert into car_models (brand_id, model) value (25, 'QX70');
insert into car_models (brand_id, model) value (25, 'QX80');



insert into car_models (brand_id, model) value (26, 'AMIGO');
insert into car_models (brand_id, model) value (26, 'ASCENDER');
insert into car_models (brand_id, model) value (26, 'ASKA II');
insert into car_models (brand_id, model) value (26, 'ASKA III');
insert into car_models (brand_id, model) value (26, 'AXIOM');
insert into car_models (brand_id, model) value (26, 'BIGHORN');
insert into car_models (brand_id, model) value (26, 'BISON');
insert into car_models (brand_id, model) value (26, 'CAMPO');
insert into car_models (brand_id, model) value (26, 'CROSSWIND');
insert into car_models (brand_id, model) value (26, 'D-MAX');
insert into car_models (brand_id, model) value (26, 'ELF');
insert into car_models (brand_id, model) value (26, 'F FORWARD');
insert into car_models (brand_id, model) value (26, 'FASTER');
insert into car_models (brand_id, model) value (26, 'FILLY');
insert into car_models (brand_id, model) value (26, 'FRONTIER');
insert into car_models (brand_id, model) value (26, 'GEMINI');
insert into car_models (brand_id, model) value (26, 'GRAFTER');
insert into car_models (brand_id, model) value (26, 'HOMBRE');
insert into car_models (brand_id, model) value (26, 'I');
insert into car_models (brand_id, model) value (26, 'IMPULSE');
insert into car_models (brand_id, model) value (26, 'INVADER');
insert into car_models (brand_id, model) value (26, 'KB');
insert into car_models (brand_id, model) value (26, 'LUV');
insert into car_models (brand_id, model) value (26, 'MIDI');
insert into car_models (brand_id, model) value (26, 'MU');
insert into car_models (brand_id, model) value (26, 'MU 7');
insert into car_models (brand_id, model) value (26, 'MU-X');
insert into car_models (brand_id, model) value (26, 'N');
insert into car_models (brand_id, model) value (26, 'OASIS');
insert into car_models (brand_id, model) value (26, 'PANTHER');
insert into car_models (brand_id, model) value (26, 'PIAZZA');
insert into car_models (brand_id, model) value (26, 'PICKUP');
insert into car_models (brand_id, model) value (26, 'RODEO');
insert into car_models (brand_id, model) value (26, 'RODEO SPORT');
insert into car_models (brand_id, model) value (26, 'SPACECAB');
insert into car_models (brand_id, model) value (26, 'TF');
insert into car_models (brand_id, model) value (26, 'TFR/TFS');
insert into car_models (brand_id, model) value (26, 'TROOPER');
insert into car_models (brand_id, model) value (26, 'VEGA');
insert into car_models (brand_id, model) value (26, 'VEHICROSS');
insert into car_models (brand_id, model) value (26, 'WIZARD');



insert into car_models (brand_id, model) value (27, '370');
insert into car_models (brand_id, model) value (27, 'CITY CLASS');
insert into car_models (brand_id, model) value (27, 'DAILY CITYS');
insert into car_models (brand_id, model) value (27, 'DAILY II');
insert into car_models (brand_id, model) value (27, 'DAILY III');
insert into car_models (brand_id, model) value (27, 'DAILY IV');
insert into car_models (brand_id, model) value (27, 'DAILY LINE');
insert into car_models (brand_id, model) value (27, 'DAILY V');
insert into car_models (brand_id, model) value (27, 'DAILY VI');
insert into car_models (brand_id, model) value (27, 'DAILY YS');
insert into car_models (brand_id, model) value (27, 'EURO');
insert into car_models (brand_id, model) value (27, 'EUROFIRE');
insert into car_models (brand_id, model) value (27, 'EUROSTAR');
insert into car_models (brand_id, model) value (27, 'EUROTECH MH');
insert into car_models (brand_id, model) value (27, 'EUROTECH MP');
insert into car_models (brand_id, model) value (27, 'EUROTECH MT');
insert into car_models (brand_id, model) value (27, 'EUROTRAKKER');
insert into car_models (brand_id, model) value (27, 'M');
insert into car_models (brand_id, model) value (27, 'MAGELYS');
insert into car_models (brand_id, model) value (27, 'MASSIF');
insert into car_models (brand_id, model) value (27, 'MASSIF SINGLE CAB');
insert into car_models (brand_id, model) value (27, 'MK');
insert into car_models (brand_id, model) value (27, 'P/PA');
insert into car_models (brand_id, model) value (27, 'P/PA-HAUBENFAHRZEUGE');
insert into car_models (brand_id, model) value (27, 'POWERSTAR');
insert into car_models (brand_id, model) value (27, 'STRALIS');
insert into car_models (brand_id, model) value (27, 'STRATOR');
insert into car_models (brand_id, model) value (27, 'TECTOR');
insert into car_models (brand_id, model) value (27, 'TRAKKER');
insert into car_models (brand_id, model) value (27, 'TURBOCITY');
insert into car_models (brand_id, model) value (27, 'TURBOSTAR');
insert into car_models (brand_id, model) value (27, 'TURBOTECH');
insert into car_models (brand_id, model) value (27, 'URBANWAY');
insert into car_models (brand_id, model) value (27, 'VERTIS');
insert into car_models (brand_id, model) value (27, 'ZETA');




insert into car_models (brand_id, model) value (28, 'F-PACE');
insert into car_models (brand_id, model) value (28, 'F-TYPE');
insert into car_models (brand_id, model) value (28, 'S-TYPE');
insert into car_models (brand_id, model) value (28, 'VANDEN PLAS');
insert into car_models (brand_id, model) value (28, 'XE');
insert into car_models (brand_id, model) value (28, 'XF');
insert into car_models (brand_id, model) value (28, 'XF SPORTBRAKE');
insert into car_models (brand_id, model) value (28, 'XJ');
insert into car_models (brand_id, model) value (28, 'XJ220');
insert into car_models (brand_id, model) value (28, 'XJS');
insert into car_models (brand_id, model) value (28, 'XJSC');
insert into car_models (brand_id, model) value (28, 'XK');
insert into car_models (brand_id, model) value (28, 'XK 8');
insert into car_models (brand_id, model) value (28, 'X-TYPE');



insert into car_models (brand_id, model) value (29, 'Cherokee');
insert into car_models (brand_id, model) value (29, 'Commander');
insert into car_models (brand_id, model) value (29, 'Compass');
insert into car_models (brand_id, model) value (29, 'Grand Cherokee');
insert into car_models (brand_id, model) value (29, 'Liberty');
insert into car_models (brand_id, model) value (29, 'Patriot');
insert into car_models (brand_id, model) value (29, 'Renegade');
insert into car_models (brand_id, model) value (29, 'Wrangler');


insert into car_models (brand_id, model) value (30, 'Amanti');
insert into car_models (brand_id, model) value (30, 'Avella');
insert into car_models (brand_id, model) value (30, 'Besta');
insert into car_models (brand_id, model) value (30, 'Bongo');
insert into car_models (brand_id, model) value (30, 'Borrego');
insert into car_models (brand_id, model) value (30, 'Brisa');
insert into car_models (brand_id, model) value (30, 'Cadenza');
insert into car_models (brand_id, model) value (30, 'Capital');
insert into car_models (brand_id, model) value (30, 'Carens');
insert into car_models (brand_id, model) value (30, 'Carnival');
insert into car_models (brand_id, model) value (30, 'Carstar');
insert into car_models (brand_id, model) value (30, 'Ceed');
insert into car_models (brand_id, model) value (30, 'Cerato');
insert into car_models (brand_id, model) value (30, 'Clarus');
insert into car_models (brand_id, model) value (30, 'Concord');
insert into car_models (brand_id, model) value (30, 'Credos');
insert into car_models (brand_id, model) value (30, 'Elan');
insert into car_models (brand_id, model) value (30, 'Enterprise');
insert into car_models (brand_id, model) value (30, 'Forte');
insert into car_models (brand_id, model) value (30, 'Joice');
insert into car_models (brand_id, model) value (30, 'K2');
insert into car_models (brand_id, model) value (30, 'K3');
insert into car_models (brand_id, model) value (30, 'K5');
insert into car_models (brand_id, model) value (30, 'K7');
insert into car_models (brand_id, model) value (30, 'K9');
insert into car_models (brand_id, model) value (30, 'Lotze');
insert into car_models (brand_id, model) value (30, 'Magentis');
insert into car_models (brand_id, model) value (30, 'Mohave');
insert into car_models (brand_id, model) value (30, 'Morning');
insert into car_models (brand_id, model) value (30, 'Opirus');
insert into car_models (brand_id, model) value (30, 'Optima');
insert into car_models (brand_id, model) value (30, 'Parktown');
insert into car_models (brand_id, model) value (30, 'Picanto');
insert into car_models (brand_id, model) value (30, 'Potentia');
insert into car_models (brand_id, model) value (30, 'Pregio');
insert into car_models (brand_id, model) value (30, 'Pride');
insert into car_models (brand_id, model) value (30, 'Quoris');
insert into car_models (brand_id, model) value (30, 'Ray');
insert into car_models (brand_id, model) value (30, 'Retona');
insert into car_models (brand_id, model) value (30, 'Rio');
insert into car_models (brand_id, model) value (30, 'Rondo');
insert into car_models (brand_id, model) value (30, 'Sedona');
insert into car_models (brand_id, model) value (30, 'Sephia');
insert into car_models (brand_id, model) value (30, 'Shuma');
insert into car_models (brand_id, model) value (30, 'Sorento');
insert into car_models (brand_id, model) value (30, 'Soul');
insert into car_models (brand_id, model) value (30, 'Spectra');
insert into car_models (brand_id, model) value (30, 'Sportage');
insert into car_models (brand_id, model) value (30, 'Stinger');
insert into car_models (brand_id, model) value (30, 'Towner');
insert into car_models (brand_id, model) value (30, 'Venga');
insert into car_models (brand_id, model) value (30, 'Visto');
insert into car_models (brand_id, model) value (30, 'X-Trek');




insert into car_models (brand_id, model) value (31, '1111 Ока');
insert into car_models (brand_id, model) value (31, '2101');
insert into car_models (brand_id, model) value (31, '2102');
insert into car_models (brand_id, model) value (31, '2103');
insert into car_models (brand_id, model) value (31, '2104');
insert into car_models (brand_id, model) value (31, '2105');
insert into car_models (brand_id, model) value (31, '2106');
insert into car_models (brand_id, model) value (31, '2107');
insert into car_models (brand_id, model) value (31, '2108');
insert into car_models (brand_id, model) value (31, '2109');
insert into car_models (brand_id, model) value (31, '21099');
insert into car_models (brand_id, model) value (31, '2110');
insert into car_models (brand_id, model) value (31, '2111');
insert into car_models (brand_id, model) value (31, '2112');
insert into car_models (brand_id, model) value (31, '2113');
insert into car_models (brand_id, model) value (31, '2114');
insert into car_models (brand_id, model) value (31, '2115');
insert into car_models (brand_id, model) value (31, '2120 Надежда');
insert into car_models (brand_id, model) value (31, '2123');
insert into car_models (brand_id, model) value (31, '4x4');
insert into car_models (brand_id, model) value (31, 'Granta');
insert into car_models (brand_id, model) value (31, 'Kalina');
insert into car_models (brand_id, model) value (31, 'Largus');
insert into car_models (brand_id, model) value (31, 'Priora');
insert into car_models (brand_id, model) value (31, 'Vesta');
insert into car_models (brand_id, model) value (31, 'XRAY');



insert into car_models (brand_id, model) value (32, 'DEDRA');
insert into car_models (brand_id, model) value (32, 'DEDRA SW');
insert into car_models (brand_id, model) value (32, 'DELTA');
insert into car_models (brand_id, model) value (32, 'DELTA I');
insert into car_models (brand_id, model) value (32, 'DELTA II');
insert into car_models (brand_id, model) value (32, 'DELTA III');
insert into car_models (brand_id, model) value (32, 'FLAVIA');
insert into car_models (brand_id, model) value (32, 'KAPPA');
insert into car_models (brand_id, model) value (32, 'KAPPA SW');
insert into car_models (brand_id, model) value (32, 'LYBRA');
insert into car_models (brand_id, model) value (32, 'LYBRA SW');
insert into car_models (brand_id, model) value (32, 'MUSA');
insert into car_models (brand_id, model) value (32, 'PHEDRA');
insert into car_models (brand_id, model) value (32, 'PRISMA');
insert into car_models (brand_id, model) value (32, 'THEMA');
insert into car_models (brand_id, model) value (32, 'THEMA SW');
insert into car_models (brand_id, model) value (32, 'THESIS');
insert into car_models (brand_id, model) value (32, 'VOYAGER');
insert into car_models (brand_id, model) value (32, 'Y');
insert into car_models (brand_id, model) value (32, 'Y10');
insert into car_models (brand_id, model) value (32, 'YPSILON');
insert into car_models (brand_id, model) value (32, 'ZETA');



insert into car_models (brand_id, model) value (33, 'Defender');
insert into car_models (brand_id, model) value (33, 'Discovery');
insert into car_models (brand_id, model) value (33, 'Discovery Sport');
insert into car_models (brand_id, model) value (33, 'Freelander');
insert into car_models (brand_id, model) value (33, 'Range Rover');
insert into car_models (brand_id, model) value (33, 'Range Rover Evoque');
insert into car_models (brand_id, model) value (33, 'Range Rover Sport');
insert into car_models (brand_id, model) value (33, 'Range Rover Velar');



insert into car_models (brand_id, model) value (34, 'CT');
insert into car_models (brand_id, model) value (34, 'ES');
insert into car_models (brand_id, model) value (34, 'GS');
insert into car_models (brand_id, model) value (34, 'GX');
insert into car_models (brand_id, model) value (34, 'HS');
insert into car_models (brand_id, model) value (34, 'IS');
insert into car_models (brand_id, model) value (34, 'LC');
insert into car_models (brand_id, model) value (34, 'LFA');
insert into car_models (brand_id, model) value (34, 'LS');
insert into car_models (brand_id, model) value (34, 'LX');
insert into car_models (brand_id, model) value (34, 'NX');
insert into car_models (brand_id, model) value (34, 'RC');
insert into car_models (brand_id, model) value (34, 'RX');
insert into car_models (brand_id, model) value (34, 'SC');
insert into car_models (brand_id, model) value (34, 'UX');



insert into car_models (brand_id, model) value (35, 'Breez');
insert into car_models (brand_id, model) value (35, 'Cebrium');
insert into car_models (brand_id, model) value (35, 'Celliya');
insert into car_models (brand_id, model) value (35, 'Murman');
insert into car_models (brand_id, model) value (35, 'Myway');
insert into car_models (brand_id, model) value (35, 'Smily');
insert into car_models (brand_id, model) value (35, 'Solano');
insert into car_models (brand_id, model) value (35, 'X50');
insert into car_models (brand_id, model) value (35, 'X60');




insert into car_models (brand_id, model) value (36, 'AVIATOR');
insert into car_models (brand_id, model) value (36, 'BLACKWOOD');
insert into car_models (brand_id, model) value (36, 'CONTINENTAL');
insert into car_models (brand_id, model) value (36, 'CONTINENTAL TOWN CAR');
insert into car_models (brand_id, model) value (36, 'LS');
insert into car_models (brand_id, model) value (36, 'MARK LT');
insert into car_models (brand_id, model) value (36, 'MARK VII');
insert into car_models (brand_id, model) value (36, 'MARK VIII');
insert into car_models (brand_id, model) value (36, 'MKC');
insert into car_models (brand_id, model) value (36, 'MKS');
insert into car_models (brand_id, model) value (36, 'MKT');
insert into car_models (brand_id, model) value (36, 'MKX');
insert into car_models (brand_id, model) value (36, 'MKZ');
insert into car_models (brand_id, model) value (36, 'NAVIGATOR');
insert into car_models (brand_id, model) value (36, 'TOWN CAR');
insert into car_models (brand_id, model) value (36, 'TOWN CAR II');
insert into car_models (brand_id, model) value (36, 'TOWN CAR III');
insert into car_models (brand_id, model) value (36, 'ZEPHYR/MKZ');



insert into car_models (brand_id, model) value (37, '57');
insert into car_models (brand_id, model) value (37, 'MAYBACH');
insert into car_models (brand_id, model) value (37, '62');




insert into car_models (brand_id, model) value (38, '121');
insert into car_models (brand_id, model) value (38, '2');
insert into car_models (brand_id, model) value (38, '3');
insert into car_models (brand_id, model) value (38, '323');
insert into car_models (brand_id, model) value (38, '5');
insert into car_models (brand_id, model) value (38, '6');
insert into car_models (brand_id, model) value (38, '616');
insert into car_models (brand_id, model) value (38, '626');
insert into car_models (brand_id, model) value (38, '929');
insert into car_models (brand_id, model) value (38, 'Atenza');
insert into car_models (brand_id, model) value (38, 'Axela');
insert into car_models (brand_id, model) value (38, 'Az-1');
insert into car_models (brand_id, model) value (38, 'Az-offroad');
insert into car_models (brand_id, model) value (38, 'Az-wagon');
insert into car_models (brand_id, model) value (38, 'Biante');
insert into car_models (brand_id, model) value (38, 'Bongo');
insert into car_models (brand_id, model) value (38, 'Bongo Friendee');
insert into car_models (brand_id, model) value (38, 'BT-50');
insert into car_models (brand_id, model) value (38, 'Capella');
insert into car_models (brand_id, model) value (38, 'Carol');
insert into car_models (brand_id, model) value (38, 'Cronos');
insert into car_models (brand_id, model) value (38, 'CX-5');
insert into car_models (brand_id, model) value (38, 'CX-7');
insert into car_models (brand_id, model) value (38, 'CX-9');
insert into car_models (brand_id, model) value (38, 'Demio');
insert into car_models (brand_id, model) value (38, 'Eunos 500');
insert into car_models (brand_id, model) value (38, 'Eunos Cosmo');
insert into car_models (brand_id, model) value (38, 'Lantis');
insert into car_models (brand_id, model) value (38, 'Laputa');
insert into car_models (brand_id, model) value (38, 'Levante');
insert into car_models (brand_id, model) value (38, 'Millenia');
insert into car_models (brand_id, model) value (38, 'MPV');
insert into car_models (brand_id, model) value (38, 'Mx-3');
insert into car_models (brand_id, model) value (38, 'MX-5');
insert into car_models (brand_id, model) value (38, 'Mx-6');
insert into car_models (brand_id, model) value (38, 'Navajo');
insert into car_models (brand_id, model) value (38, 'Premacy');
insert into car_models (brand_id, model) value (38, 'Protege');
insert into car_models (brand_id, model) value (38, 'RX-7');
insert into car_models (brand_id, model) value (38, 'RX-8');
insert into car_models (brand_id, model) value (38, 'Scrum');
insert into car_models (brand_id, model) value (38, 'Spiano');
insert into car_models (brand_id, model) value (38, 'Tribute');
insert into car_models (brand_id, model) value (38, 'Verisa');
insert into car_models (brand_id, model) value (38, 'Xedos 6');
insert into car_models (brand_id, model) value (38, 'Xedos 9');




insert into car_models (brand_id, model) value (39, '540C');
insert into car_models (brand_id, model) value (39, '570S');
insert into car_models (brand_id, model) value (39, '625C');
insert into car_models (brand_id, model) value (39, '650S');
insert into car_models (brand_id, model) value (39, '675LT');
insert into car_models (brand_id, model) value (39, 'F1');
insert into car_models (brand_id, model) value (39, 'LM');
insert into car_models (brand_id, model) value (39, 'MP4');
insert into car_models (brand_id, model) value (39, 'P1');



insert into car_models (brand_id, model) value (40, 'A-Класс');
insert into car_models (brand_id, model) value (40, 'Actros');
insert into car_models (brand_id, model) value (40, 'Atego');
insert into car_models (brand_id, model) value (40, 'Axor');
insert into car_models (brand_id, model) value (40, 'B-Класс');
insert into car_models (brand_id, model) value (40, 'C-Класс');
insert into car_models (brand_id, model) value (40, 'CL');
insert into car_models (brand_id, model) value (40, 'CLA');
insert into car_models (brand_id, model) value (40, 'CLC');
insert into car_models (brand_id, model) value (40, 'CLK');
insert into car_models (brand_id, model) value (40, 'CLS');
insert into car_models (brand_id, model) value (40, 'E-Класс');
insert into car_models (brand_id, model) value (40, 'G-Класс');
insert into car_models (brand_id, model) value (40, 'GL');
insert into car_models (brand_id, model) value (40, 'GLA');
insert into car_models (brand_id, model) value (40, 'GLC');
insert into car_models (brand_id, model) value (40, 'GLE');
insert into car_models (brand_id, model) value (40, 'GLK');
insert into car_models (brand_id, model) value (40, 'GLS');
insert into car_models (brand_id, model) value (40, 'L206');
insert into car_models (brand_id, model) value (40, 'LK');
insert into car_models (brand_id, model) value (40, 'M-Класс');
insert into car_models (brand_id, model) value (40, 'Maybach');
insert into car_models (brand_id, model) value (40, 'MB');
insert into car_models (brand_id, model) value (40, 'R-Класс');
insert into car_models (brand_id, model) value (40, 'S-Класс');
insert into car_models (brand_id, model) value (40, 'SL');
insert into car_models (brand_id, model) value (40, 'SLK');
insert into car_models (brand_id, model) value (40, 'SLR');
insert into car_models (brand_id, model) value (40, 'SLS AMG');
insert into car_models (brand_id, model) value (40, 'Sprinter');
insert into car_models (brand_id, model) value (40, 'T1 (207D-410D)');
insert into car_models (brand_id, model) value (40, 'T2 (407D-814D)');
insert into car_models (brand_id, model) value (40, 'V-Класс');
insert into car_models (brand_id, model) value (40, 'Vaneo');
insert into car_models (brand_id, model) value (40, 'Vario');
insert into car_models (brand_id, model) value (40, 'Viano');
insert into car_models (brand_id, model) value (40, 'Vito');




insert into car_models (brand_id, model) value (41, 'CROSSOVER');
insert into car_models (brand_id, model) value (41, 'MINI');
insert into car_models (brand_id, model) value (41, 'MINI CLUBMAN');
insert into car_models (brand_id, model) value (41, 'MINI CLUBVAN');
insert into car_models (brand_id, model) value (41, 'MINI COUNTRYMAN');
insert into car_models (brand_id, model) value (41, 'MINI PACEMAN');


insert into car_models (brand_id, model) value (42, '3000 GT');
insert into car_models (brand_id, model) value (42, 'ASX');
insert into car_models (brand_id, model) value (42, 'Carisma');
insert into car_models (brand_id, model) value (42, 'Chariot');
insert into car_models (brand_id, model) value (42, 'Colt');
insert into car_models (brand_id, model) value (42, 'Debonair');
insert into car_models (brand_id, model) value (42, 'Delica');
insert into car_models (brand_id, model) value (42, 'Diamante');
insert into car_models (brand_id, model) value (42, 'Dingo');
insert into car_models (brand_id, model) value (42, 'Dion');
insert into car_models (brand_id, model) value (42, 'Eclipse');
insert into car_models (brand_id, model) value (42, 'Eclipse Cross');
insert into car_models (brand_id, model) value (42, 'EK Wagon');
insert into car_models (brand_id, model) value (42, 'Endeavor');
insert into car_models (brand_id, model) value (42, 'FTO');
insert into car_models (brand_id, model) value (42, 'Galant');
insert into car_models (brand_id, model) value (42, 'Grandis');
insert into car_models (brand_id, model) value (42, 'i');
insert into car_models (brand_id, model) value (42, 'i-MiEV');
insert into car_models (brand_id, model) value (42, 'L200');
insert into car_models (brand_id, model) value (42, 'L300');
insert into car_models (brand_id, model) value (42, 'L400');
insert into car_models (brand_id, model) value (42, 'Lancer');
insert into car_models (brand_id, model) value (42, 'Legnum');
insert into car_models (brand_id, model) value (42, 'Libero');
insert into car_models (brand_id, model) value (42, 'Minica');
insert into car_models (brand_id, model) value (42, 'Mirage');
insert into car_models (brand_id, model) value (42, 'Montero');
insert into car_models (brand_id, model) value (42, 'Outlander');
insert into car_models (brand_id, model) value (42, 'Pajero');
insert into car_models (brand_id, model) value (42, 'Pajero Junior');
insert into car_models (brand_id, model) value (42, 'Pajero Mini');
insert into car_models (brand_id, model) value (42, 'Pajero Pinin');
insert into car_models (brand_id, model) value (42, 'Pajero Sport');
insert into car_models (brand_id, model) value (42, 'Proudia');
insert into car_models (brand_id, model) value (42, 'RVR');
insert into car_models (brand_id, model) value (42, 'Sigma');
insert into car_models (brand_id, model) value (42, 'Space Gear');
insert into car_models (brand_id, model) value (42, 'Space Runner');
insert into car_models (brand_id, model) value (42, 'Space Star');
insert into car_models (brand_id, model) value (42, 'Space Wagon');
insert into car_models (brand_id, model) value (42, 'Toppo');
insert into car_models (brand_id, model) value (42, 'Town BOX');




insert into car_models (brand_id, model) value (43, '403');
insert into car_models (brand_id, model) value (43, '407');
insert into car_models (brand_id, model) value (43, '412');
insert into car_models (brand_id, model) value (43, '423');
insert into car_models (brand_id, model) value (43, '427');
insert into car_models (brand_id, model) value (43, '2141');
insert into car_models (brand_id, model) value (43, '2142');
insert into car_models (brand_id, model) value (43, '2335');
insert into car_models (brand_id, model) value (43, '2901');
insert into car_models (brand_id, model) value (43, 'ASLK 2137');
insert into car_models (brand_id, model) value (43, 'ASLK 2140');



insert into car_models (brand_id, model) value (44, '100NX');
insert into car_models (brand_id, model) value (44, '180SX');
insert into car_models (brand_id, model) value (44, '200SX');
insert into car_models (brand_id, model) value (44, '240SX');
insert into car_models (brand_id, model) value (44, '300ZX');
insert into car_models (brand_id, model) value (44, '350Z');
insert into car_models (brand_id, model) value (44, '370Z');
insert into car_models (brand_id, model) value (44, 'Almera');
insert into car_models (brand_id, model) value (44, 'Almera Classic');
insert into car_models (brand_id, model) value (44, 'Almera Tino');
insert into car_models (brand_id, model) value (44, 'Altima');
insert into car_models (brand_id, model) value (44, 'Aprio');
insert into car_models (brand_id, model) value (44, 'Armada');
insert into car_models (brand_id, model) value (44, 'Auster');
insert into car_models (brand_id, model) value (44, 'Avenir');
insert into car_models (brand_id, model) value (44, 'Bassara');
insert into car_models (brand_id, model) value (44, 'Be-1');
insert into car_models (brand_id, model) value (44, 'Bluebird');
insert into car_models (brand_id, model) value (44, 'Bluebird Sylphy');
insert into car_models (brand_id, model) value (44, 'Caball');
insert into car_models (brand_id, model) value (44, 'Cabstar');
insert into car_models (brand_id, model) value (44, 'Caravan');
insert into car_models (brand_id, model) value (44, 'Cedric');
insert into car_models (brand_id, model) value (44, 'Cefiro');
insert into car_models (brand_id, model) value (44, 'Cherry');
insert into car_models (brand_id, model) value (44, 'Cima');
insert into car_models (brand_id, model) value (44, 'Clipper');
insert into car_models (brand_id, model) value (44, 'Crew');
insert into car_models (brand_id, model) value (44, 'Cube');
insert into car_models (brand_id, model) value (44, 'Datsun');
insert into car_models (brand_id, model) value (44, 'Dualis');
insert into car_models (brand_id, model) value (44, 'Dualis+2');
insert into car_models (brand_id, model) value (44, 'Elgrand');
insert into car_models (brand_id, model) value (44, 'Expert');
insert into car_models (brand_id, model) value (44, 'Fairlady Z');
insert into car_models (brand_id, model) value (44, 'Figaro');
insert into car_models (brand_id, model) value (44, 'Frontier');
insert into car_models (brand_id, model) value (44, 'Fuga');
insert into car_models (brand_id, model) value (44, 'Gazelle');
insert into car_models (brand_id, model) value (44, 'Gloria');
insert into car_models (brand_id, model) value (44, 'GT-R');
insert into car_models (brand_id, model) value (44, 'Homy');
insert into car_models (brand_id, model) value (44, 'Interstar');
insert into car_models (brand_id, model) value (44, 'Juke');
insert into car_models (brand_id, model) value (44, 'Kix');
insert into car_models (brand_id, model) value (44, 'Kubistar');
insert into car_models (brand_id, model) value (44, 'Lafesta');
insert into car_models (brand_id, model) value (44, 'Langley');
insert into car_models (brand_id, model) value (44, 'Largo');
insert into car_models (brand_id, model) value (44, 'Latio');
insert into car_models (brand_id, model) value (44, 'Laurel');
insert into car_models (brand_id, model) value (44, 'Leaf');
insert into car_models (brand_id, model) value (44, 'Leopard');
insert into car_models (brand_id, model) value (44, 'Liberty');
insert into car_models (brand_id, model) value (44, 'Livina');
insert into car_models (brand_id, model) value (44, 'Lucino');
insert into car_models (brand_id, model) value (44, 'March');
insert into car_models (brand_id, model) value (44, 'Maxima');
insert into car_models (brand_id, model) value (44, 'Micra');
insert into car_models (brand_id, model) value (44, 'Mistral');
insert into car_models (brand_id, model) value (44, 'Moco');
insert into car_models (brand_id, model) value (44, 'Murano');
insert into car_models (brand_id, model) value (44, 'Navara');
insert into car_models (brand_id, model) value (44, 'Note');
insert into car_models (brand_id, model) value (44, 'NP200');
insert into car_models (brand_id, model) value (44, 'NP300');
insert into car_models (brand_id, model) value (44, 'NV');
insert into car_models (brand_id, model) value (44, 'NV200');
insert into car_models (brand_id, model) value (44, 'NV350');
insert into car_models (brand_id, model) value (44, 'NV400');
insert into car_models (brand_id, model) value (44, 'Otti');
insert into car_models (brand_id, model) value (44, 'Paladin');
insert into car_models (brand_id, model) value (44, 'Pao');
insert into car_models (brand_id, model) value (44, 'Pathfinder');
insert into car_models (brand_id, model) value (44, 'Patrol');
insert into car_models (brand_id, model) value (44, 'Pick UP');
insert into car_models (brand_id, model) value (44, 'Pino');
insert into car_models (brand_id, model) value (44, 'Pixo');
insert into car_models (brand_id, model) value (44, 'Platina');
insert into car_models (brand_id, model) value (44, 'Prairie');
insert into car_models (brand_id, model) value (44, 'Presage');
insert into car_models (brand_id, model) value (44, 'Presea');
insert into car_models (brand_id, model) value (44, 'President');
insert into car_models (brand_id, model) value (44, 'Primastar');
insert into car_models (brand_id, model) value (44, 'Primera');
insert into car_models (brand_id, model) value (44, 'Pulsar');
insert into car_models (brand_id, model) value (44, 'Qashqai');
insert into car_models (brand_id, model) value (44, 'Qashqai+2');
insert into car_models (brand_id, model) value (44, 'Quest');
insert into car_models (brand_id, model) value (44, 'Rnessa');
insert into car_models (brand_id, model) value (44, 'Rasheen');
insert into car_models (brand_id, model) value (44, 'Rogue');
insert into car_models (brand_id, model) value (44, 'Roox');
insert into car_models (brand_id, model) value (44, 'S-Cargo');
insert into car_models (brand_id, model) value (44, 'Safari');
insert into car_models (brand_id, model) value (44, 'Sentra');
insert into car_models (brand_id, model) value (44, 'Serena');
insert into car_models (brand_id, model) value (44, 'Silvia');
insert into car_models (brand_id, model) value (44, 'Skyline');
insert into car_models (brand_id, model) value (44, 'Skyline Crossover');
insert into car_models (brand_id, model) value (44, 'Stagea');
insert into car_models (brand_id, model) value (44, 'Stanza');
insert into car_models (brand_id, model) value (44, 'Sunny');
insert into car_models (brand_id, model) value (44, 'Teana');
insert into car_models (brand_id, model) value (44, 'Terrano');
insert into car_models (brand_id, model) value (44, 'Tiida');
insert into car_models (brand_id, model) value (44, 'Tino');
insert into car_models (brand_id, model) value (44, 'Titan');
insert into car_models (brand_id, model) value (44, 'Urvan');
insert into car_models (brand_id, model) value (44, 'Vanette');
insert into car_models (brand_id, model) value (44, 'Versa');
insert into car_models (brand_id, model) value (44, 'Violet');
insert into car_models (brand_id, model) value (44, 'Violet Liberta');
insert into car_models (brand_id, model) value (44, 'Wingroad');
insert into car_models (brand_id, model) value (44, 'X-Trail');
insert into car_models (brand_id, model) value (44, 'Xterra');



insert into car_models (brand_id, model) value (45, 'Adam');
insert into car_models (brand_id, model) value (45, 'Agila');
insert into car_models (brand_id, model) value (45, 'Ampera');
insert into car_models (brand_id, model) value (45, 'Antara');
insert into car_models (brand_id, model) value (45, 'Arena');
insert into car_models (brand_id, model) value (45, 'Ascona');
insert into car_models (brand_id, model) value (45, 'Astra');
insert into car_models (brand_id, model) value (45, 'Calibra');
insert into car_models (brand_id, model) value (45, 'Campo');
insert into car_models (brand_id, model) value (45, 'Cascada');
insert into car_models (brand_id, model) value (45, 'Combo');
insert into car_models (brand_id, model) value (45, 'Commodore');
insert into car_models (brand_id, model) value (45, 'Corsa');
insert into car_models (brand_id, model) value (45, 'Frontera');
insert into car_models (brand_id, model) value (45, 'GT');
insert into car_models (brand_id, model) value (45, 'Insignia');
insert into car_models (brand_id, model) value (45, 'Kadett');
insert into car_models (brand_id, model) value (45, 'Manta');
insert into car_models (brand_id, model) value (45, 'Meriva');
insert into car_models (brand_id, model) value (45, 'Mokka');
insert into car_models (brand_id, model) value (45, 'Monterey');
insert into car_models (brand_id, model) value (45, 'Movano');
insert into car_models (brand_id, model) value (45, 'Olympia');
insert into car_models (brand_id, model) value (45, 'Omega');
insert into car_models (brand_id, model) value (45, 'Rekord');
insert into car_models (brand_id, model) value (45, 'Senator');
insert into car_models (brand_id, model) value (45, 'Signum');
insert into car_models (brand_id, model) value (45, 'Sintra');
insert into car_models (brand_id, model) value (45, 'Speedster');
insert into car_models (brand_id, model) value (45, 'Tigra');
insert into car_models (brand_id, model) value (45, 'Vectra');
insert into car_models (brand_id, model) value (45, 'Vivaro');
insert into car_models (brand_id, model) value (45, 'Zafira');



insert into car_models (brand_id, model) value (46, '1007');
insert into car_models (brand_id, model) value (46, '106');
insert into car_models (brand_id, model) value (46, '107');
insert into car_models (brand_id, model) value (46, '2008');
insert into car_models (brand_id, model) value (46, '205');
insert into car_models (brand_id, model) value (46, '206');
insert into car_models (brand_id, model) value (46, '207');
insert into car_models (brand_id, model) value (46, '208');
insert into car_models (brand_id, model) value (46, '3008');
insert into car_models (brand_id, model) value (46, '301');
insert into car_models (brand_id, model) value (46, '305');
insert into car_models (brand_id, model) value (46, '306');
insert into car_models (brand_id, model) value (46, '307');
insert into car_models (brand_id, model) value (46, '308');
insert into car_models (brand_id, model) value (46, '309');
insert into car_models (brand_id, model) value (46, '4007');
insert into car_models (brand_id, model) value (46, '4008');
insert into car_models (brand_id, model) value (46, '405');
insert into car_models (brand_id, model) value (46, '406');
insert into car_models (brand_id, model) value (46, '407');
insert into car_models (brand_id, model) value (46, '408');
insert into car_models (brand_id, model) value (46, '5008');
insert into car_models (brand_id, model) value (46, '505');
insert into car_models (brand_id, model) value (46, '508');
insert into car_models (brand_id, model) value (46, '605');
insert into car_models (brand_id, model) value (46, '607');
insert into car_models (brand_id, model) value (46, '806');
insert into car_models (brand_id, model) value (46, '807');
insert into car_models (brand_id, model) value (46, 'Bipper');
insert into car_models (brand_id, model) value (46, 'Boxer');
insert into car_models (brand_id, model) value (46, 'Expert');
insert into car_models (brand_id, model) value (46, 'J5');
insert into car_models (brand_id, model) value (46, 'Partner');
insert into car_models (brand_id, model) value (46, 'RCZ');



insert into car_models (brand_id, model) value (47, '6000');
insert into car_models (brand_id, model) value (47, 'AZTEK');
insert into car_models (brand_id, model) value (47, 'BONNEVILLE');
insert into car_models (brand_id, model) value (47, 'FIREBIRD');
insert into car_models (brand_id, model) value (47, 'FIREFLY');
insert into car_models (brand_id, model) value (47, 'G3');
insert into car_models (brand_id, model) value (47, 'G4');
insert into car_models (brand_id, model) value (47, 'G5');
insert into car_models (brand_id, model) value (47, 'G6');
insert into car_models (brand_id, model) value (47, 'G8');
insert into car_models (brand_id, model) value (47, 'GRAND AM');
insert into car_models (brand_id, model) value (47, 'GRAND PRIX');
insert into car_models (brand_id, model) value (47, 'GRAND PRIX V');
insert into car_models (brand_id, model) value (47, 'GRAND PRIX VI');
insert into car_models (brand_id, model) value (47, 'GTO');
insert into car_models (brand_id, model) value (47, 'J2000 SUNBIRD');
insert into car_models (brand_id, model) value (47, 'LEMANS');
insert into car_models (brand_id, model) value (47, 'MATIZ');
insert into car_models (brand_id, model) value (47, 'MATIZ G2');
insert into car_models (brand_id, model) value (47, 'MONTANA');
insert into car_models (brand_id, model) value (47, 'OPTIMA');
insert into car_models (brand_id, model) value (47, 'PURSUIT');
insert into car_models (brand_id, model) value (47, 'SOLSTICE');
insert into car_models (brand_id, model) value (47, 'SUNFIRE');
insert into car_models (brand_id, model) value (47, 'SUNNRUNNER');
insert into car_models (brand_id, model) value (47, 'TEMPEST');
insert into car_models (brand_id, model) value (47, 'TORRENT');
insert into car_models (brand_id, model) value (47, 'TRANS SPORT');
insert into car_models (brand_id, model) value (47, 'VIBE');
insert into car_models (brand_id, model) value (47, 'WAVE');
insert into car_models (brand_id, model) value (47, 'WAVE5');



insert into car_models (brand_id, model) value (48, '911');
insert into car_models (brand_id, model) value (48, '928');
insert into car_models (brand_id, model) value (48, '968');
insert into car_models (brand_id, model) value (48, 'Boxster');
insert into car_models (brand_id, model) value (48, 'Carrera GT');
insert into car_models (brand_id, model) value (48, 'Cayenne');
insert into car_models (brand_id, model) value (48, 'Cayman');
insert into car_models (brand_id, model) value (48, 'Macan');
insert into car_models (brand_id, model) value (48, 'Panamera');



insert into car_models (brand_id, model) value (49, 'AEROBACK');
insert into car_models (brand_id, model) value (49, 'ARENA');
insert into car_models (brand_id, model) value (49, 'COMPACT');
insert into car_models (brand_id, model) value (49, 'EXORA');
insert into car_models (brand_id, model) value (49, 'GEN 2');
insert into car_models (brand_id, model) value (49, 'GEN-2 PERSONA');
insert into car_models (brand_id, model) value (49, 'IMPIAN');
insert into car_models (brand_id, model) value (49, 'INSPIRA');
insert into car_models (brand_id, model) value (49, 'IPIAN - WAJA');
insert into car_models (brand_id, model) value (49, 'IRIZ');
insert into car_models (brand_id, model) value (49, 'ISWARA');
insert into car_models (brand_id, model) value (49, 'JUMBUCK');
insert into car_models (brand_id, model) value (49, 'M21');
insert into car_models (brand_id, model) value (49, 'MPI');
insert into car_models (brand_id, model) value (49, 'PERDANA');
insert into car_models (brand_id, model) value (49, 'PERSONA');
insert into car_models (brand_id, model) value (49, 'PERSONA 300');
insert into car_models (brand_id, model) value (49, 'PERSONA 400');
insert into car_models (brand_id, model) value (49, 'PREV');
insert into car_models (brand_id, model) value (49, 'PUTRA');
insert into car_models (brand_id, model) value (49, 'S16 II');
insert into car_models (brand_id, model) value (49, 'SAGA');
insert into car_models (brand_id, model) value (49, 'SAGA II');
insert into car_models (brand_id, model) value (49, 'SALOON');
insert into car_models (brand_id, model) value (49, 'SATRIA');
insert into car_models (brand_id, model) value (49, 'SATRIA NEO');
insert into car_models (brand_id, model) value (49, 'SAVVY');
insert into car_models (brand_id, model) value (49, 'SUPRIMA');
insert into car_models (brand_id, model) value (49, 'SUPRIMA S');
insert into car_models (brand_id, model) value (49, 'WAJA');
insert into car_models (brand_id, model) value (49, 'WIRA');



insert into car_models (brand_id, model) value (50, 'Avantime');
insert into car_models (brand_id, model) value (50, 'Captur');
insert into car_models (brand_id, model) value (50, 'Clio');
insert into car_models (brand_id, model) value (50, 'Dokker');
insert into car_models (brand_id, model) value (50, 'Duster');
insert into car_models (brand_id, model) value (50, 'Espace');
insert into car_models (brand_id, model) value (50, 'Express');
insert into car_models (brand_id, model) value (50, 'Fluence');
insert into car_models (brand_id, model) value (50, 'Fluence Z.E.');
insert into car_models (brand_id, model) value (50, 'Fregate');
insert into car_models (brand_id, model) value (50, 'Fuego');
insert into car_models (brand_id, model) value (50, 'Kadjar');
insert into car_models (brand_id, model) value (50, 'Kangoo');
insert into car_models (brand_id, model) value (50, 'Kaptur');
insert into car_models (brand_id, model) value (50, 'Koleos');
insert into car_models (brand_id, model) value (50, 'Laguna');
insert into car_models (brand_id, model) value (50, 'Latitude');
insert into car_models (brand_id, model) value (50, 'Lodgy');
insert into car_models (brand_id, model) value (50, 'Logan');
insert into car_models (brand_id, model) value (50, 'Mascott');
insert into car_models (brand_id, model) value (50, 'Master');
insert into car_models (brand_id, model) value (50, 'Megane');
insert into car_models (brand_id, model) value (50, 'Modus');
insert into car_models (brand_id, model) value (50, 'Safrane');
insert into car_models (brand_id, model) value (50, 'Sandero');
insert into car_models (brand_id, model) value (50, 'Sandero Stepway');
insert into car_models (brand_id, model) value (50, 'Scenic');
insert into car_models (brand_id, model) value (50, 'Sport Spider');
insert into car_models (brand_id, model) value (50, 'Symbol');
insert into car_models (brand_id, model) value (50, 'Talisman');
insert into car_models (brand_id, model) value (50, 'Thalia');
insert into car_models (brand_id, model) value (50, 'Torino');
insert into car_models (brand_id, model) value (50, 'Trafic');
insert into car_models (brand_id, model) value (50, 'Twingo');
insert into car_models (brand_id, model) value (50, 'Twizy');
insert into car_models (brand_id, model) value (50, 'Vel Satis');
insert into car_models (brand_id, model) value (50, 'Wind');
insert into car_models (brand_id, model) value (50, 'Zoe');



insert into car_models (brand_id, model) value (51, 'CORNICHE III');
insert into car_models (brand_id, model) value (51, 'CORNICHE IV');
insert into car_models (brand_id, model) value (51, 'CORNICHE V');
insert into car_models (brand_id, model) value (51, 'DAWN');
insert into car_models (brand_id, model) value (51, 'FLYING SPUR');
insert into car_models (brand_id, model) value (51, 'GHOST');
insert into car_models (brand_id, model) value (51, 'ING');
insert into car_models (brand_id, model) value (51, 'PARK WARD');
insert into car_models (brand_id, model) value (51, 'PHANTOM');
insert into car_models (brand_id, model) value (51, 'PHANTOM DROPHEAD');
insert into car_models (brand_id, model) value (51, 'SILVER DAWN');
insert into car_models (brand_id, model) value (51, 'SILVER SERAPH');
insert into car_models (brand_id, model) value (51, 'SILVER SPIRIT');
insert into car_models (brand_id, model) value (51, 'SILVER SPIRIT II');
insert into car_models (brand_id, model) value (51, 'SILVER SPIRIT III');
insert into car_models (brand_id, model) value (51, 'SILVER SPIRIT IV');
insert into car_models (brand_id, model) value (51, 'SILVER SPUR');
insert into car_models (brand_id, model) value (51, 'WRAITH');





insert into car_models (brand_id, model) value (52, '25');
insert into car_models (brand_id, model) value (52, '45');
insert into car_models (brand_id, model) value (52, '75');
insert into car_models (brand_id, model) value (52, '100');
insert into car_models (brand_id, model) value (52, '200');
insert into car_models (brand_id, model) value (52, '400');
insert into car_models (brand_id, model) value (52, '600');
insert into car_models (brand_id, model) value (52, '800');
insert into car_models (brand_id, model) value (52, '400 ER');
insert into car_models (brand_id, model) value (52, 'CABRIOLET');
insert into car_models (brand_id, model) value (52, 'CITYROVER');
insert into car_models (brand_id, model) value (52, 'COUPE');
insert into car_models (brand_id, model) value (52, 'MAESTRO');
insert into car_models (brand_id, model) value (52, 'MINI');
insert into car_models (brand_id, model) value (52, 'MINI-MOKE');
insert into car_models (brand_id, model) value (52, 'MONTEGO');
insert into car_models (brand_id, model) value (52, 'RV8');
insert into car_models (brand_id, model) value (52, 'STREETWISE');



insert into car_models (brand_id, model) value (53, '9-2X');
insert into car_models (brand_id, model) value (53, '43533');
insert into car_models (brand_id, model) value (53, '9-3X');
insert into car_models (brand_id, model) value (53, '9-4X');
insert into car_models (brand_id, model) value (53, '43594');
insert into car_models (brand_id, model) value (53, '9-7X');
insert into car_models (brand_id, model) value (53, '900');
insert into car_models (brand_id, model) value (53, '900 I');
insert into car_models (brand_id, model) value (53, '900 II');
insert into car_models (brand_id, model) value (53, '9000');


insert into car_models (brand_id, model) value (54, 'Alhambra');
insert into car_models (brand_id, model) value (54, 'Altea');
insert into car_models (brand_id, model) value (54, 'Altea XL');
insert into car_models (brand_id, model) value (54, 'Arosa');
insert into car_models (brand_id, model) value (54, 'Cordoba');
insert into car_models (brand_id, model) value (54, 'Exeo');
insert into car_models (brand_id, model) value (54, 'Ibiza');
insert into car_models (brand_id, model) value (54, 'Inca');
insert into car_models (brand_id, model) value (54, 'Leon');
insert into car_models (brand_id, model) value (54, 'Malaga');
insert into car_models (brand_id, model) value (54, 'Marbella');
insert into car_models (brand_id, model) value (54, 'Terra');
insert into car_models (brand_id, model) value (54, 'Toledo');



insert into car_models (brand_id, model) value (55, 'Citigo');
insert into car_models (brand_id, model) value (55, 'Fabia');
insert into car_models (brand_id, model) value (55, 'Favorit');
insert into car_models (brand_id, model) value (55, 'Felicia');
insert into car_models (brand_id, model) value (55, 'Forman');
insert into car_models (brand_id, model) value (55, 'Kodiaq');
insert into car_models (brand_id, model) value (55, 'Octavia');
insert into car_models (brand_id, model) value (55, 'Praktik');
insert into car_models (brand_id, model) value (55, 'Rapid');
insert into car_models (brand_id, model) value (55, 'Roomster');
insert into car_models (brand_id, model) value (55, 'Superb');
insert into car_models (brand_id, model) value (55, 'Yeti');




insert into car_models (brand_id, model) value (56, 'CABRIO');
insert into car_models (brand_id, model) value (56, 'CITY-COUPE');
insert into car_models (brand_id, model) value (56, 'CROSSBLADE');
insert into car_models (brand_id, model) value (56, 'FORFOUR');
insert into car_models (brand_id, model) value (56, 'FORTWO');
insert into car_models (brand_id, model) value (56, 'ROADSTER');




insert into car_models (brand_id, model) value (57, 'ACTYON I');
insert into car_models (brand_id, model) value (57, 'ACTYON II');
insert into car_models (brand_id, model) value (57, 'ACTYON SPORTS I');
insert into car_models (brand_id, model) value (57, 'ACTYON SPORTS II');
insert into car_models (brand_id, model) value (57, 'CHAIRMAN');
insert into car_models (brand_id, model) value (57, 'ISTANA');
insert into car_models (brand_id, model) value (57, 'KORANDO');
insert into car_models (brand_id, model) value (57, 'KORANDO SPORTS II');
insert into car_models (brand_id, model) value (57, 'KYRON');
insert into car_models (brand_id, model) value (57, 'MUSSO');
insert into car_models (brand_id, model) value (57, 'MUSSO SPORTS');
insert into car_models (brand_id, model) value (57, 'REXTON');
insert into car_models (brand_id, model) value (57, 'REXTON W');
insert into car_models (brand_id, model) value (57, 'RODIUS');
insert into car_models (brand_id, model) value (57, 'RODIUS II');
insert into car_models (brand_id, model) value (57, 'STAVIC');
insert into car_models (brand_id, model) value (57, 'STAVIC II');
insert into car_models (brand_id, model) value (57, 'TIVOLAN');
insert into car_models (brand_id, model) value (57, 'TIVOLI');
insert into car_models (brand_id, model) value (57, 'TURISMO');
insert into car_models (brand_id, model) value (57, 'TURISMO II');



insert into car_models (brand_id, model) value (58, 'Baja');
insert into car_models (brand_id, model) value (58, 'BRZ');
insert into car_models (brand_id, model) value (58, 'Forester');
insert into car_models (brand_id, model) value (58, 'Impreza');
insert into car_models (brand_id, model) value (58, 'Justy');
insert into car_models (brand_id, model) value (58, 'Legacy');
insert into car_models (brand_id, model) value (58, 'Leone');
insert into car_models (brand_id, model) value (58, 'Libero');
insert into car_models (brand_id, model) value (58, 'Outback');
insert into car_models (brand_id, model) value (58, 'Pleo');
insert into car_models (brand_id, model) value (58, 'R1');
insert into car_models (brand_id, model) value (58, 'R2');
insert into car_models (brand_id, model) value (58, 'SVX');
insert into car_models (brand_id, model) value (58, 'Traviq');
insert into car_models (brand_id, model) value (58, 'Trezia');
insert into car_models (brand_id, model) value (58, 'Tribeca');
insert into car_models (brand_id, model) value (58, 'Vivio');
insert into car_models (brand_id, model) value (58, 'WRX');
insert into car_models (brand_id, model) value (58, 'XT');
insert into car_models (brand_id, model) value (58, 'XV');




insert into car_models (brand_id, model) value (59, 'Aerio');
insert into car_models (brand_id, model) value (59, 'Alto');
insert into car_models (brand_id, model) value (59, 'Baleno');
insert into car_models (brand_id, model) value (59, 'Cappuccino');
insert into car_models (brand_id, model) value (59, 'Cultus');
insert into car_models (brand_id, model) value (59, 'Every');
insert into car_models (brand_id, model) value (59, 'Forenza');
insert into car_models (brand_id, model) value (59, 'Grand Vitara');
insert into car_models (brand_id, model) value (59, 'Ignis');
insert into car_models (brand_id, model) value (59, 'Jimny');
insert into car_models (brand_id, model) value (59, 'Kei');
insert into car_models (brand_id, model) value (59, 'Kizashi');
insert into car_models (brand_id, model) value (59, 'Liana');
insert into car_models (brand_id, model) value (59, 'MR Wagon');
insert into car_models (brand_id, model) value (59, 'Splash');
insert into car_models (brand_id, model) value (59, 'Swift');
insert into car_models (brand_id, model) value (59, 'SX4');
insert into car_models (brand_id, model) value (59, 'Verona');
insert into car_models (brand_id, model) value (59, 'Vitara');
insert into car_models (brand_id, model) value (59, 'Wagon R');
insert into car_models (brand_id, model) value (59, 'X-90');
insert into car_models (brand_id, model) value (59, 'XL7');



insert into car_models (brand_id, model) value (60, 'MODEL S');
insert into car_models (brand_id, model) value (60, 'MODEL X');
insert into car_models (brand_id, model) value (60, 'ROADSTER');




insert into car_models (brand_id, model) value (61, '4runner');
insert into car_models (brand_id, model) value (61, 'Allex');
insert into car_models (brand_id, model) value (61, 'Allion');
insert into car_models (brand_id, model) value (61, 'Alphard');
insert into car_models (brand_id, model) value (61, 'Altezza');
insert into car_models (brand_id, model) value (61, 'Aristo');
insert into car_models (brand_id, model) value (61, 'Aurion');
insert into car_models (brand_id, model) value (61, 'Auris');
insert into car_models (brand_id, model) value (61, 'Avalon');
insert into car_models (brand_id, model) value (61, 'Avanza');
insert into car_models (brand_id, model) value (61, 'Avensis');
insert into car_models (brand_id, model) value (61, 'Avensis Verso');
insert into car_models (brand_id, model) value (61, 'Aygo');
insert into car_models (brand_id, model) value (61, 'BB');
insert into car_models (brand_id, model) value (61, 'Belta');
insert into car_models (brand_id, model) value (61, 'Blade');
insert into car_models (brand_id, model) value (61, 'Blizzard');
insert into car_models (brand_id, model) value (61, 'Brevis');
insert into car_models (brand_id, model) value (61, 'C-HR');
insert into car_models (brand_id, model) value (61, 'Caldina');
insert into car_models (brand_id, model) value (61, 'Cami');
insert into car_models (brand_id, model) value (61, 'Camry');
insert into car_models (brand_id, model) value (61, 'Camry Solara');
insert into car_models (brand_id, model) value (61, 'Carina');
insert into car_models (brand_id, model) value (61, 'Carina E');
insert into car_models (brand_id, model) value (61, 'Cavalier');
insert into car_models (brand_id, model) value (61, 'Celica');
insert into car_models (brand_id, model) value (61, 'Celsior');
insert into car_models (brand_id, model) value (61, 'Century');
insert into car_models (brand_id, model) value (61, 'Chaser');
insert into car_models (brand_id, model) value (61, 'Corolla');
insert into car_models (brand_id, model) value (61, 'Corolla Axio');
insert into car_models (brand_id, model) value (61, 'Corolla Ceres');
insert into car_models (brand_id, model) value (61, 'Corolla Rumion');
insert into car_models (brand_id, model) value (61, 'Corolla Spacio');
insert into car_models (brand_id, model) value (61, 'Corolla Verso');
insert into car_models (brand_id, model) value (61, 'Corona');
insert into car_models (brand_id, model) value (61, 'Corsa');
insert into car_models (brand_id, model) value (61, 'Cressida');
insert into car_models (brand_id, model) value (61, 'Cresta');
insert into car_models (brand_id, model) value (61, 'Crown');
insert into car_models (brand_id, model) value (61, 'Crown Majesta');
insert into car_models (brand_id, model) value (61, 'Curren');
insert into car_models (brand_id, model) value (61, 'Cynos');
insert into car_models (brand_id, model) value (61, 'Duet');
insert into car_models (brand_id, model) value (61, 'Echo');
insert into car_models (brand_id, model) value (61, 'Estima');
insert into car_models (brand_id, model) value (61, 'FJ Cruiser');
insert into car_models (brand_id, model) value (61, 'Fortuner');
insert into car_models (brand_id, model) value (61, 'Fun Cargo');
insert into car_models (brand_id, model) value (61, 'Granvia');
insert into car_models (brand_id, model) value (61, 'GT 86');
insert into car_models (brand_id, model) value (61, 'Harrier');
insert into car_models (brand_id, model) value (61, 'Hiace');
insert into car_models (brand_id, model) value (61, 'Highlander');
insert into car_models (brand_id, model) value (61, 'Hilux');
insert into car_models (brand_id, model) value (61, 'Hilux Surf');
insert into car_models (brand_id, model) value (61, 'Innova');
insert into car_models (brand_id, model) value (61, 'Ipsum');
insert into car_models (brand_id, model) value (61, 'ISis');
insert into car_models (brand_id, model) value (61, 'Ist');
insert into car_models (brand_id, model) value (61, 'iQ');
insert into car_models (brand_id, model) value (61, 'Kluger');
insert into car_models (brand_id, model) value (61, 'Land Cruiser');
insert into car_models (brand_id, model) value (61, 'Land Cruiser Prado');
insert into car_models (brand_id, model) value (61, 'Lite Ace');
insert into car_models (brand_id, model) value (61, 'Mark II');
insert into car_models (brand_id, model) value (61, 'Mark X');
insert into car_models (brand_id, model) value (61, 'Matrix');
insert into car_models (brand_id, model) value (61, 'Mega Cruiser');
insert into car_models (brand_id, model) value (61, 'MR-S');
insert into car_models (brand_id, model) value (61, 'MR2');
insert into car_models (brand_id, model) value (61, 'Nadia');
insert into car_models (brand_id, model) value (61, 'Noah');
insert into car_models (brand_id, model) value (61, 'Opa');
insert into car_models (brand_id, model) value (61, 'Paseo');
insert into car_models (brand_id, model) value (61, 'Passo');
insert into car_models (brand_id, model) value (61, 'Passo Sette');
insert into car_models (brand_id, model) value (61, 'Picnic');
insert into car_models (brand_id, model) value (61, 'Platz');
insert into car_models (brand_id, model) value (61, 'Porte');
insert into car_models (brand_id, model) value (61, 'Premio');
insert into car_models (brand_id, model) value (61, 'Previa');
insert into car_models (brand_id, model) value (61, 'Prius');
insert into car_models (brand_id, model) value (61, 'Prius C');
insert into car_models (brand_id, model) value (61, 'Prius Plus');
insert into car_models (brand_id, model) value (61, 'Prius V');
insert into car_models (brand_id, model) value (61, 'Probox');
insert into car_models (brand_id, model) value (61, 'Progres');
insert into car_models (brand_id, model) value (61, 'Pronard');
insert into car_models (brand_id, model) value (61, 'Ractis');
insert into car_models (brand_id, model) value (61, 'Raum');
insert into car_models (brand_id, model) value (61, 'RAV4');
insert into car_models (brand_id, model) value (61, 'Regius');
insert into car_models (brand_id, model) value (61, 'Rukus');
insert into car_models (brand_id, model) value (61, 'Scepter');
insert into car_models (brand_id, model) value (61, 'Sequoia');
insert into car_models (brand_id, model) value (61, 'Sera');
insert into car_models (brand_id, model) value (61, 'Sienna');
insert into car_models (brand_id, model) value (61, 'Sienta');
insert into car_models (brand_id, model) value (61, 'Soarer');
insert into car_models (brand_id, model) value (61, 'Sparky');
insert into car_models (brand_id, model) value (61, 'Sprinter');
insert into car_models (brand_id, model) value (61, 'Sprinter Carib');
insert into car_models (brand_id, model) value (61, 'Sprinter Marino');
insert into car_models (brand_id, model) value (61, 'Sprinter Trueno');
insert into car_models (brand_id, model) value (61, 'Starlet');
insert into car_models (brand_id, model) value (61, 'Succeed');
insert into car_models (brand_id, model) value (61, 'Supra');
insert into car_models (brand_id, model) value (61, 'Tacoma');
insert into car_models (brand_id, model) value (61, 'Tercel');
insert into car_models (brand_id, model) value (61, 'Town Ace');
insert into car_models (brand_id, model) value (61, 'Tundra');
insert into car_models (brand_id, model) value (61, 'Urban Cruiser');
insert into car_models (brand_id, model) value (61, 'Venza');
insert into car_models (brand_id, model) value (61, 'Verossa');
insert into car_models (brand_id, model) value (61, 'Verso');
insert into car_models (brand_id, model) value (61, 'Verso-S');
insert into car_models (brand_id, model) value (61, 'Vios');
insert into car_models (brand_id, model) value (61, 'Vista');
insert into car_models (brand_id, model) value (61, 'Vitz');
insert into car_models (brand_id, model) value (61, 'Voltz');
insert into car_models (brand_id, model) value (61, 'Voxy');
insert into car_models (brand_id, model) value (61, 'Windom');
insert into car_models (brand_id, model) value (61, 'Wish');
insert into car_models (brand_id, model) value (61, 'Yaris');
insert into car_models (brand_id, model) value (61, 'Yaris Verso');



insert into car_models (brand_id, model) value (62, '3151');
insert into car_models (brand_id, model) value (62, '3159 Барс');
insert into car_models (brand_id, model) value (62, '3160');
insert into car_models (brand_id, model) value (62, '3162 Simbir');
insert into car_models (brand_id, model) value (62, '3303');
insert into car_models (brand_id, model) value (62, '3909');
insert into car_models (brand_id, model) value (62, '39094');
insert into car_models (brand_id, model) value (62, '452');
insert into car_models (brand_id, model) value (62, '469');
insert into car_models (brand_id, model) value (62, 'Cargo');
insert into car_models (brand_id, model) value (62, 'Hunter');
insert into car_models (brand_id, model) value (62, 'Patriot');
insert into car_models (brand_id, model) value (62, 'Pickup');




insert into car_models (brand_id, model) value (63, 'Amarok');
insert into car_models (brand_id, model) value (63, 'Beetle');
insert into car_models (brand_id, model) value (63, 'Bora');
insert into car_models (brand_id, model) value (63, 'Brasilia');
insert into car_models (brand_id, model) value (63, 'Caddy');
insert into car_models (brand_id, model) value (63, 'California');
insert into car_models (brand_id, model) value (63, 'Caravelle');
insert into car_models (brand_id, model) value (63, 'Clasico');
insert into car_models (brand_id, model) value (63, 'Corrado');
insert into car_models (brand_id, model) value (63, 'Crafter');
insert into car_models (brand_id, model) value (63, 'Derby');
insert into car_models (brand_id, model) value (63, 'Eos');
insert into car_models (brand_id, model) value (63, 'Fox');
insert into car_models (brand_id, model) value (63, 'Gol');
insert into car_models (brand_id, model) value (63, 'Golf');
insert into car_models (brand_id, model) value (63, 'Golf Plus');
insert into car_models (brand_id, model) value (63, 'Iltis');
insert into car_models (brand_id, model) value (63, 'Jetta');
insert into car_models (brand_id, model) value (63, 'Karmann Ghia');
insert into car_models (brand_id, model) value (63, 'Lavida');
insert into car_models (brand_id, model) value (63, 'LT');
insert into car_models (brand_id, model) value (63, 'Lupo');
insert into car_models (brand_id, model) value (63, 'Magotan');
insert into car_models (brand_id, model) value (63, 'Multivan');
insert into car_models (brand_id, model) value (63, 'New Beetle');
insert into car_models (brand_id, model) value (63, 'Parati');
insert into car_models (brand_id, model) value (63, 'Passat');
insert into car_models (brand_id, model) value (63, 'Passat CC');
insert into car_models (brand_id, model) value (63, 'Phaeton');
insert into car_models (brand_id, model) value (63, 'Polo');
insert into car_models (brand_id, model) value (63, 'Quantum');
insert into car_models (brand_id, model) value (63, 'Rabbit');
insert into car_models (brand_id, model) value (63, 'Routan');
insert into car_models (brand_id, model) value (63, 'Sagitar');
insert into car_models (brand_id, model) value (63, 'Santana');
insert into car_models (brand_id, model) value (63, 'Saveiro');
insert into car_models (brand_id, model) value (63, 'Scirocco');
insert into car_models (brand_id, model) value (63, 'Sharan');
insert into car_models (brand_id, model) value (63, 'SP2');
insert into car_models (brand_id, model) value (63, 'Suran');
insert into car_models (brand_id, model) value (63, 'Teramont');
insert into car_models (brand_id, model) value (63, 'Tiguan');
insert into car_models (brand_id, model) value (63, 'Touareg');
insert into car_models (brand_id, model) value (63, 'Touran');
insert into car_models (brand_id, model) value (63, 'Transporter');
insert into car_models (brand_id, model) value (63, 'Up!');
insert into car_models (brand_id, model) value (63, 'Vento');
insert into car_models (brand_id, model) value (63, 'Voyage');



insert into car_models (brand_id, model) value (64, '240 series');
insert into car_models (brand_id, model) value (64, '260 series');
insert into car_models (brand_id, model) value (64, '300 series');
insert into car_models (brand_id, model) value (64, '440');
insert into car_models (brand_id, model) value (64, '460');
insert into car_models (brand_id, model) value (64, '740');
insert into car_models (brand_id, model) value (64, '760');
insert into car_models (brand_id, model) value (64, '850');
insert into car_models (brand_id, model) value (64, '940');
insert into car_models (brand_id, model) value (64, '960');
insert into car_models (brand_id, model) value (64, 'C30');
insert into car_models (brand_id, model) value (64, 'C30');
insert into car_models (brand_id, model) value (64, 'C70');
insert into car_models (brand_id, model) value (64, 'S40');
insert into car_models (brand_id, model) value (64, 'S60');
insert into car_models (brand_id, model) value (64, 'S70');
insert into car_models (brand_id, model) value (64, 'S80');
insert into car_models (brand_id, model) value (64, 'S90');
insert into car_models (brand_id, model) value (64, 'V40');
insert into car_models (brand_id, model) value (64, 'V50');
insert into car_models (brand_id, model) value (64, 'V60');
insert into car_models (brand_id, model) value (64, 'V70');
insert into car_models (brand_id, model) value (64, 'V90');
insert into car_models (brand_id, model) value (64, 'XC40');
insert into car_models (brand_id, model) value (64, 'XC60');
insert into car_models (brand_id, model) value (64, 'XC70');
insert into car_models (brand_id, model) value (64, 'XC90');




/*Countries:*/
insert into countries (country_name) value ('Беларусь');
insert into countries (country_name) value ('Россия');
insert into countries (country_name) value ('Польша');
insert into countries (country_name) value ('Украина');
insert into countries (country_name) value ('Литва');
insert into countries (country_name) value ('Латвия');


/*Беларусь*/
insert into regions (country_id, region_name) value (1, 'Минская ');
insert into regions (country_id, region_name) value (1, 'Брестская ');
insert into regions (country_id, region_name) value (1, 'Витебская ');
insert into regions (country_id, region_name) value (1, 'Гродненская ');
insert into regions (country_id, region_name) value (1, 'Гомельская ');
insert into regions (country_id, region_name) value (1, 'Могилевская ');




insert into cities (region_id, city_name) value (1, 'Вязынка ');
insert into cities (region_id, city_name) value (1, 'Беница ');
insert into cities (region_id, city_name) value (1, 'Малиновщина ');
insert into cities (region_id, city_name) value (1, 'Холхлово ');
insert into cities (region_id, city_name) value (1, 'Марково ');
insert into cities (region_id, city_name) value (1, 'Молодечно ');
insert into cities (region_id, city_name) value (1, 'Красное ');
insert into cities (region_id, city_name) value (1, 'Лебедево ');
insert into cities (region_id, city_name) value (1, 'Плебань ');
insert into cities (region_id, city_name) value (1, 'Радошковичи ');
insert into cities (region_id, city_name) value (1, 'Будслав ');
insert into cities (region_id, city_name) value (1, 'Мядель ');
insert into cities (region_id, city_name) value (1, 'Комарово ');
insert into cities (region_id, city_name) value (1, 'Кривичи ');
insert into cities (region_id, city_name) value (1, 'Свирь ');
insert into cities (region_id, city_name) value (1, 'Нарочь (Кобыльник) ');
insert into cities (region_id, city_name) value (1, 'Засвирь ');
insert into cities (region_id, city_name) value (1, 'Константиново ');
insert into cities (region_id, city_name) value (1, 'Княгинин ');
insert into cities (region_id, city_name) value (1, 'Шеметово ');
insert into cities (region_id, city_name) value (1, 'Лущики ');
insert into cities (region_id, city_name) value (1, 'Рубежевичи ');
insert into cities (region_id, city_name) value (1, 'Столбцы ');
insert into cities (region_id, city_name) value (1, 'Деревная ');
insert into cities (region_id, city_name) value (1, 'Новый Свержень ');
insert into cities (region_id, city_name) value (1, 'Налибоки ');
insert into cities (region_id, city_name) value (1, 'Вишневец ');
insert into cities (region_id, city_name) value (1, 'Слободка ');
insert into cities (region_id, city_name) value (1, 'Великий Двор ');
insert into cities (region_id, city_name) value (1, 'Жатерево ');
insert into cities (region_id, city_name) value (1, 'Школьный ');
insert into cities (region_id, city_name) value (1, 'Вилейка ');
insert into cities (region_id, city_name) value (1, 'Илья ');
insert into cities (region_id, city_name) value (1, 'Вязынь ');
insert into cities (region_id, city_name) value (1, 'Долгиново ');
insert into cities (region_id, city_name) value (1, 'Клесино ');
insert into cities (region_id, city_name) value (1, 'Речки ');
insert into cities (region_id, city_name) value (1, 'Старинки ');
insert into cities (region_id, city_name) value (1, 'Куренец ');
insert into cities (region_id, city_name) value (1, 'Любань ');
insert into cities (region_id, city_name) value (1, 'Стайки ');
insert into cities (region_id, city_name) value (1, 'Латыголь ');
insert into cities (region_id, city_name) value (1, 'Костеневичи ');
insert into cities (region_id, city_name) value (1, 'Ольковичи ');
insert into cities (region_id, city_name) value (1, 'Стешицы ');
insert into cities (region_id, city_name) value (1, 'Матьковцы ');
insert into cities (region_id, city_name) value (1, 'Ободовцы ');
insert into cities (region_id, city_name) value (1, 'Ивенец ');
insert into cities (region_id, city_name) value (1, 'Раков ');
insert into cities (region_id, city_name) value (1, 'Воложин ');
insert into cities (region_id, city_name) value (1, 'Вишнево ');
insert into cities (region_id, city_name) value (1, 'Першаи ');
insert into cities (region_id, city_name) value (1, 'Богданов ');
insert into cities (region_id, city_name) value (1, 'Десятники ');
insert into cities (region_id, city_name) value (1, 'Минск ');





insert into cities (region_id, city_name) value (2, 'Ишкольдь ');
insert into cities (region_id, city_name) value (2, 'Вольно ');
insert into cities (region_id, city_name) value (2, 'Столовичи ');
insert into cities (region_id, city_name) value (2, 'Барановичи ');
insert into cities (region_id, city_name) value (2, 'Заосье ');
insert into cities (region_id, city_name) value (2, 'Полонечка ');
insert into cities (region_id, city_name) value (2, 'Ястрембель ');
insert into cities (region_id, city_name) value (2, 'Большая Своротва ');
insert into cities (region_id, city_name) value (2, 'Почапово ');
insert into cities (region_id, city_name) value (2, 'Берёза ');
insert into cities (region_id, city_name) value (2, 'Пески ');
insert into cities (region_id, city_name) value (2, 'Брест ');
insert into cities (region_id, city_name) value (2, 'Скоки ');
insert into cities (region_id, city_name) value (2, 'Збироги ');
insert into cities (region_id, city_name) value (2, 'Вистычи ');
insert into cities (region_id, city_name) value (2, 'Чернавчицы ');
insert into cities (region_id, city_name) value (2, 'Котельня-Боярская ');
insert into cities (region_id, city_name) value (2, 'Раковица ');
insert into cities (region_id, city_name) value (2, 'Малые Щитники ');
insert into cities (region_id, city_name) value (2, 'Шебрин ');
insert into cities (region_id, city_name) value (2, 'Бездеж ');
insert into cities (region_id, city_name) value (2, 'Закозель ');
insert into cities (region_id, city_name) value (2, 'Перковичи ');
insert into cities (region_id, city_name) value (2, 'Воловель ');
insert into cities (region_id, city_name) value (2, 'Антополь ');
insert into cities (region_id, city_name) value (2, 'Чудин ');
insert into cities (region_id, city_name) value (2, 'Иваново ');
insert into cities (region_id, city_name) value (2, 'Достоево ');
insert into cities (region_id, city_name) value (2, 'Молодово ');
insert into cities (region_id, city_name) value (2, 'Вороцевичи ');
insert into cities (region_id, city_name) value (2, 'Коссово ');
insert into cities (region_id, city_name) value (2, 'Бытень ');
insert into cities (region_id, city_name) value (2, 'Бусяж ');
insert into cities (region_id, city_name) value (2, 'Каменец ');
insert into cities (region_id, city_name) value (2, 'Высокое ');
insert into cities (region_id, city_name) value (2, 'Каменюки ');
insert into cities (region_id, city_name) value (2, 'Волчин ');
insert into cities (region_id, city_name) value (2, 'Паниквы ');
insert into cities (region_id, city_name) value (2, 'Кобрин ');
insert into cities (region_id, city_name) value (2, 'Буховичи ');
insert into cities (region_id, city_name) value (2, 'Лунинец ');
insert into cities (region_id, city_name) value (2, 'Микашевичи ');
insert into cities (region_id, city_name) value (2, 'Кожан-Городок ');
insert into cities (region_id, city_name) value (2, 'Грушевка ');
insert into cities (region_id, city_name) value (2, 'Совейки ');
insert into cities (region_id, city_name) value (2, 'Кривошин ');
insert into cities (region_id, city_name) value (2, 'Остров ');
insert into cities (region_id, city_name) value (2, 'Нача ');
insert into cities (region_id, city_name) value (2, 'Флерьяново ');
insert into cities (region_id, city_name) value (2, 'Медведичи ');
insert into cities (region_id, city_name) value (2, 'Своятичи ');
insert into cities (region_id, city_name) value (2, 'Хотислав ');
insert into cities (region_id, city_name) value (2, 'Пинск ');
insert into cities (region_id, city_name) value (2, 'Дубой ');
insert into cities (region_id, city_name) value (2, 'Логишин ');
insert into cities (region_id, city_name) value (2, 'Пинковичи ');
insert into cities (region_id, city_name) value (2, 'Заполье ');
insert into cities (region_id, city_name) value (2, 'Погост-Загородск ');
insert into cities (region_id, city_name) value (2, 'Пружаны ');
insert into cities (region_id, city_name) value (2, 'Ружаны ');
insert into cities (region_id, city_name) value (2, 'Лысково ');
insert into cities (region_id, city_name) value (2, 'Шерешево ');
insert into cities (region_id, city_name) value (2, 'Могилёвцы ');
insert into cities (region_id, city_name) value (2, 'Столин ');
insert into cities (region_id, city_name) value (2, 'Давид-Городок ');
insert into cities (region_id, city_name) value (2, 'Дубенец ');
insert into cities (region_id, city_name) value (2, 'Жабинка ');
insert into cities (region_id, city_name) value (2, 'Здитово ');




insert into cities (region_id, city_name) value (3, 'Бешенковичи ');
insert into cities (region_id, city_name) value (3, 'Улла ');
insert into cities (region_id, city_name) value (3, 'Видзы ');
insert into cities (region_id, city_name) value (3, 'Браслав ');
insert into cities (region_id, city_name) value (3, 'Опса ');
insert into cities (region_id, city_name) value (3, 'Друя ');
insert into cities (region_id, city_name) value (3, 'Слободка ');
insert into cities (region_id, city_name) value (3, 'Иказнь ');
insert into cities (region_id, city_name) value (3, 'Дрисвяты ');
insert into cities (region_id, city_name) value (3, 'Ахремовцы ');
insert into cities (region_id, city_name) value (3, 'Белая Церковь ');
insert into cities (region_id, city_name) value (3, 'Чашники ');
insert into cities (region_id, city_name) value (3, 'Черея ');
insert into cities (region_id, city_name) value (3, 'Докшицы ');
insert into cities (region_id, city_name) value (3, 'Ситцы ');
insert into cities (region_id, city_name) value (3, 'Парафьяново ');
insert into cities (region_id, city_name) value (3, 'Порплище ');
insert into cities (region_id, city_name) value (3, 'Волколата ');
insert into cities (region_id, city_name) value (3, 'Дубровно ');
insert into cities (region_id, city_name) value (3, 'Мосар ');
insert into cities (region_id, city_name) value (3, 'Глубокое ');
insert into cities (region_id, city_name) value (3, 'Удело ');
insert into cities (region_id, city_name) value (3, 'Прозороки ');
insert into cities (region_id, city_name) value (3, 'Мнюто ');
insert into cities (region_id, city_name) value (3, 'Ковали ');
insert into cities (region_id, city_name) value (3, 'Мамаи ');
insert into cities (region_id, city_name) value (3, 'Городок ');
insert into cities (region_id, city_name) value (3, 'Лепель ');
insert into cities (region_id, city_name) value (3, 'Лиозно ');
insert into cities (region_id, city_name) value (3, 'Миоры ');
insert into cities (region_id, city_name) value (3, 'Дисна ');
insert into cities (region_id, city_name) value (3, 'Леонполь ');
insert into cities (region_id, city_name) value (3, 'Идолта ');
insert into cities (region_id, city_name) value (3, 'Орша ');
insert into cities (region_id, city_name) value (3, 'Смоляны ');
insert into cities (region_id, city_name) value (3, 'Полоцк ');
insert into cities (region_id, city_name) value (3, 'Поставы ');
insert into cities (region_id, city_name) value (3, 'Камаи ');
insert into cities (region_id, city_name) value (3, 'Лынтупы ');
insert into cities (region_id, city_name) value (3, 'Дуниловичи ');
insert into cities (region_id, city_name) value (3, 'Лучай ');
insert into cities (region_id, city_name) value (3, 'Россоны ');
insert into cities (region_id, city_name) value (3, 'Сенно ');
insert into cities (region_id, city_name) value (3, 'Шарковщина ');
insert into cities (region_id, city_name) value (3, 'Лужки ');
insert into cities (region_id, city_name) value (3, 'Городец ');
insert into cities (region_id, city_name) value (3, 'Германовичи ');
insert into cities (region_id, city_name) value (3, 'Оболь ');
insert into cities (region_id, city_name) value (3, 'Лесковичи ');
insert into cities (region_id, city_name) value (3, 'Толочин ');
insert into cities (region_id, city_name) value (3, 'Голошево ');
insert into cities (region_id, city_name) value (3, 'Селище ');
insert into cities (region_id, city_name) value (3, 'Ушачи ');
insert into cities (region_id, city_name) value (3, 'Бычки ');
insert into cities (region_id, city_name) value (3, 'Ореховно ');
insert into cities (region_id, city_name) value (3, 'Сарья ');
insert into cities (region_id, city_name) value (3, 'Росица ');
insert into cities (region_id, city_name) value (3, 'Освея ');
insert into cities (region_id, city_name) value (3, 'Верхнедвинск ');
insert into cities (region_id, city_name) value (3, 'Витебск ');
insert into cities (region_id, city_name) value (3, 'Койтово (Здравнево) ');





insert into cities (region_id, city_name) value (4, 'Большая Берестовица ');
insert into cities (region_id, city_name) value (4, 'Малая Берестовица ');
insert into cities (region_id, city_name) value (4, 'Массоляны ');
insert into cities (region_id, city_name) value (4, 'Лишки ');
insert into cities (region_id, city_name) value (4, 'Большие Эйсмонты ');
insert into cities (region_id, city_name) value (4, 'Олекшицы ');
insert into cities (region_id, city_name) value (4, 'Глебовичи ');
insert into cities (region_id, city_name) value (4, 'Гольни ');
insert into cities (region_id, city_name) value (4, 'Горбачи ');
insert into cities (region_id, city_name) value (4, 'Дятлово ');
insert into cities (region_id, city_name) value (4, 'Гродно ');
insert into cities (region_id, city_name) value (4, 'Скидель ');
insert into cities (region_id, city_name) value (4, 'Свислочь ');
insert into cities (region_id, city_name) value (4, 'Индура ');
insert into cities (region_id, city_name) value (4, 'Святск ');
insert into cities (region_id, city_name) value (4, 'Поречье ');
insert into cities (region_id, city_name) value (4, 'Сопоцкин ');
insert into cities (region_id, city_name) value (4, 'Мильковщина ');
insert into cities (region_id, city_name) value (4, 'Коптёвка ');
insert into cities (region_id, city_name) value (4, 'Ивье ');
insert into cities (region_id, city_name) value (4, 'Трабы ');
insert into cities (region_id, city_name) value (4, 'Липнишки ');
insert into cities (region_id, city_name) value (4, 'Жемыславль ');
insert into cities (region_id, city_name) value (4, 'Юратишки ');
insert into cities (region_id, city_name) value (4, 'Субботники ');
insert into cities (region_id, city_name) value (4, 'Геранёны ');
insert into cities (region_id, city_name) value (4, 'Дуды ');
insert into cities (region_id, city_name) value (4, 'Лаздуны ');
insert into cities (region_id, city_name) value (4, 'Мир ');
insert into cities (region_id, city_name) value (4, 'Райца ');
insert into cities (region_id, city_name) value (4, 'Медвядка ');
insert into cities (region_id, city_name) value (4, 'Воронча ');
insert into cities (region_id, city_name) value (4, 'Лида ');
insert into cities (region_id, city_name) value (4, 'Белогруда ');
insert into cities (region_id, city_name) value (4, 'Тарново ');
insert into cities (region_id, city_name) value (4, 'Вавёрка ');
insert into cities (region_id, city_name) value (4, 'Можейково ');
insert into cities (region_id, city_name) value (4, 'Дубно ');
insert into cities (region_id, city_name) value (4, 'Новогрудок ');
insert into cities (region_id, city_name) value (4, 'Любча ');
insert into cities (region_id, city_name) value (4, 'Вселюб ');
insert into cities (region_id, city_name) value (4, 'Щорсы ');
insert into cities (region_id, city_name) value (4, 'Валевка ');
insert into cities (region_id, city_name) value (4, 'Лавришево ');
insert into cities (region_id, city_name) value (4, 'Гольшаны ');
insert into cities (region_id, city_name) value (4, 'Ошмяны ');
insert into cities (region_id, city_name) value (4, 'Боруны ');
insert into cities (region_id, city_name) value (4, 'Жупраны ');
insert into cities (region_id, city_name) value (4, 'Гервяты ');
insert into cities (region_id, city_name) value (4, 'Островец ');
insert into cities (region_id, city_name) value (4, 'Ворняны ');
insert into cities (region_id, city_name) value (4, 'Михалишки ');
insert into cities (region_id, city_name) value (4, 'Гудогай ');
insert into cities (region_id, city_name) value (4, 'Ворона ');
insert into cities (region_id, city_name) value (4, 'Кемелишки ');
insert into cities (region_id, city_name) value (4, 'Быстрица ');
insert into cities (region_id, city_name) value (4, 'Клющаны ');
insert into cities (region_id, city_name) value (4, 'Трокеники ');
insert into cities (region_id, city_name) value (4, 'Жукойни-Желядские ');
insert into cities (region_id, city_name) value (4, 'Соколойти ');
insert into cities (region_id, city_name) value (4, 'Большие Свиряны ');
insert into cities (region_id, city_name) value (4, 'Большие Свирянки ');
insert into cities (region_id, city_name) value (4, 'Щучин ');
insert into cities (region_id, city_name) value (4, 'Мурованка ');
insert into cities (region_id, city_name) value (4, 'Желудок ');
insert into cities (region_id, city_name) value (4, 'Рожанка ');
insert into cities (region_id, city_name) value (4, 'Головичполье ');
insert into cities (region_id, city_name) value (4, 'Старые Василишки ');
insert into cities (region_id, city_name) value (4, 'Василишки ');
insert into cities (region_id, city_name) value (4, 'Ищелно ');
insert into cities (region_id, city_name) value (4, 'Слоним ');
insert into cities (region_id, city_name) value (4, 'Жировичи ');
insert into cities (region_id, city_name) value (4, 'Новодевятковичи ');
insert into cities (region_id, city_name) value (4, 'Крево ');
insert into cities (region_id, city_name) value (4, 'Сморгонь ');
insert into cities (region_id, city_name) value (4, 'Солы ');
insert into cities (region_id, city_name) value (4, 'Залесье ');
insert into cities (region_id, city_name) value (4, 'Кушляны ');
insert into cities (region_id, city_name) value (4, 'Нестанишки ');
insert into cities (region_id, city_name) value (4, 'Порозово ');
insert into cities (region_id, city_name) value (4, 'Коревичи ');
insert into cities (region_id, city_name) value (4, 'Россь ');
insert into cities (region_id, city_name) value (4, 'Волковыск ');
insert into cities (region_id, city_name) value (4, 'Гнезно ');
insert into cities (region_id, city_name) value (4, 'Подороск ');
insert into cities (region_id, city_name) value (4, 'Верейки ');
insert into cities (region_id, city_name) value (4, 'Красносельский ');
insert into cities (region_id, city_name) value (4, 'Шиловичи ');
insert into cities (region_id, city_name) value (4, 'Волпа ');
insert into cities (region_id, city_name) value (4, 'Краски ');
insert into cities (region_id, city_name) value (4, 'Репля ');
insert into cities (region_id, city_name) value (4, 'Изабелин ');
insert into cities (region_id, city_name) value (4, 'Мстибово ');
insert into cities (region_id, city_name) value (4, 'Теолин ');
insert into cities (region_id, city_name) value (4, 'Личицы ');
insert into cities (region_id, city_name) value (4, 'Седельники ');
insert into cities (region_id, city_name) value (4, 'Трокели ');
insert into cities (region_id, city_name) value (4, 'Бенякони ');
insert into cities (region_id, city_name) value (4, 'Радунь ');
insert into cities (region_id, city_name) value (4, 'Нача ');
insert into cities (region_id, city_name) value (4, 'Жирмуны ');
insert into cities (region_id, city_name) value (4, 'Гайтюнишки ');
insert into cities (region_id, city_name) value (4, 'Оссова ');
insert into cities (region_id, city_name) value (4, 'Больтеники ');
insert into cities (region_id, city_name) value (4, 'Конвелишки ');
insert into cities (region_id, city_name) value (4, 'Германишки ');
insert into cities (region_id, city_name) value (4, 'Подворишки ');
insert into cities (region_id, city_name) value (4, 'Сынковичи ');
insert into cities (region_id, city_name) value (4, 'Зельва ');
insert into cities (region_id, city_name) value (4, 'Деречин ');
insert into cities (region_id, city_name) value (4, 'Александровщина ');
insert into cities (region_id, city_name) value (4, 'Словатичи ');





insert into cities (region_id, city_name) value (5, 'Буда-Кошелёво ');
insert into cities (region_id, city_name) value (5, 'Василевичи ');
insert into cities (region_id, city_name) value (5, 'Ветка');
insert into cities (region_id, city_name) value (5, 'Гомель ');
insert into cities (region_id, city_name) value (5, 'Добруш ');
insert into cities (region_id, city_name) value (5, 'Ельск ');
insert into cities (region_id, city_name) value (5, 'Житковичи ');
insert into cities (region_id, city_name) value (5, 'Жлобин ');
insert into cities (region_id, city_name) value (5, 'Калинковичи ');
insert into cities (region_id, city_name) value (5, 'Мозырь ');
insert into cities (region_id, city_name) value (5, 'Наровля ');
insert into cities (region_id, city_name) value (5, 'Петриков ');
insert into cities (region_id, city_name) value (5, 'Речица ');
insert into cities (region_id, city_name) value (5, 'Рогачёв ');
insert into cities (region_id, city_name) value (5, 'Светлогорск ');
insert into cities (region_id, city_name) value (5, 'Туров ');
insert into cities (region_id, city_name) value (5, 'Хойники ');
insert into cities (region_id, city_name) value (5, 'Чечерск ');






insert into cities (region_id, city_name) value (6, 'Белыничи ');
insert into cities (region_id, city_name) value (6, 'Бобруйск ');
insert into cities (region_id, city_name) value (6, 'Быхов ');
insert into cities (region_id, city_name) value (6, 'Горки ');
insert into cities (region_id, city_name) value (6, 'Кировск ');
insert into cities (region_id, city_name) value (6, 'Климовичи ');
insert into cities (region_id, city_name) value (6, 'Кличев ');
insert into cities (region_id, city_name) value (6, 'Костюковичи ');
insert into cities (region_id, city_name) value (6, 'Кричев ');
insert into cities (region_id, city_name) value (6, 'Круглое  ');
insert into cities (region_id, city_name) value (6, 'Могилёв ');
insert into cities (region_id, city_name) value (6, 'Мстиславль ');
insert into cities (region_id, city_name) value (6, 'Осиповичи ');
insert into cities (region_id, city_name) value (6, 'Славгород ');
insert into cities (region_id, city_name) value (6, 'Чаусы ');
insert into cities (region_id, city_name) value (6, 'Чериков ');
insert into cities (region_id, city_name) value (6, 'Шклов ');

