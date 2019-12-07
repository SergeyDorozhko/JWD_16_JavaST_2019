DROP DATABASE IF EXISTS `poputka_db`;



create table car_brands
(
    id    int auto_increment
        primary key,
    brand varchar(50) not null,
    constraint brand_UNIQUE
        unique (brand)
);

create table car_climate
(
    id           int auto_increment
        primary key,
    climate_type varchar(30) not null
);

create table car_models
(
    id       int auto_increment
        primary key,
    brand_id int         not null,
    model    varchar(50) not null,
    constraint FK_brand
        foreign key (brand_id) references car_brands (id)
            on update cascade
);

create table cars
(
    id                 int auto_increment
        primary key,
    brand_and_model_id int  null,
    year_of_produce    year not null,
    climate_type_id    int  not null,
    constraint FK_car_brand_and_model
        foreign key (brand_and_model_id) references car_models (id)
            on update cascade,
    constraint FK_climate
        foreign key (climate_type_id) references car_climate (id)
            on update cascade
);

create table countries
(
    id           int auto_increment
        primary key,
    country_name varchar(20) not null
);

create table currencies
(
    id       int auto_increment
        primary key,
    currency char(3) not null
);

create table gender
(
    id     int auto_increment
        primary key,
    gender varchar(10) not null
);

create table regions
(
    id          int auto_increment
        primary key,
    country_id  int         not null,
    region_name varchar(20) not null,
    constraint FK_country
        foreign key (country_id) references countries (id)
            on update cascade
);

create table cities
(
    id        int auto_increment
        primary key,
    region_id int         not null,
    city_name varchar(20) not null,
    constraint FK_region
        foreign key (region_id) references regions (id)
            on update cascade
);

create table users
(
    id       int auto_increment
        primary key,
    login    varchar(255)      not null,
    password char(128)         not null,
    role     tinyint default 1 not null,
    salt     char(128)         not null,
    constraint login
        unique (login)
);

create table user_info
(
    id                     int auto_increment
        primary key,
    user_id                int          null,
    surname                varchar(255) not null,
    name                   varchar(255) not null,
    birthday               date         not null,
    country_id             int          not null,
    passport_number        varchar(10)  not null,
    passport_date_of_issue date         not null,
    phone                  varchar(255) not null,
    email                  varchar(255) not null,
    car_id                 int          null,
    gender_id              int          null,
    constraint email
        unique (email),
    constraint passport_number
        unique (passport_number),
    constraint phone
        unique (phone),
    constraint FK_car
        foreign key (car_id) references cars (id)
            on update cascade on delete set null,
    constraint FK_country_id
        foreign key (country_id) references countries (id)
            on update cascade,
    constraint FK_gender
        foreign key (gender_id) references gender (id),
    constraint FK_user
        foreign key (user_id) references users (id)
            on update cascade on delete set null
);

create table comments
(
    commentator_id   int      null,
    comment_about_id int      null,
    comment          longtext null,
    constraint FK_comment_about
        foreign key (comment_about_id) references user_info (id)
            on update cascade on delete set null,
    constraint FK_commentator
        foreign key (commentator_id) references user_info (id)
            on update cascade on delete set null
);

create table journey
(
    id                     int auto_increment
        primary key,
    driver_id              int           not null,
    start_address_id       int           not null,
    destination_address_id int           not null,
    departure_time         time          null,
    departure_date         date          not null,
    cost                   double(20, 2) not null,
    currency_id            int           not null,
    number_of_passengers   int           not null,
    additional_information varchar(1000) null,
    constraint FK_currency
        foreign key (currency_id) references currencies (id)
            on update cascade,
    constraint FK_destination_address
        foreign key (destination_address_id) references cities (id)
            on update cascade on delete cascade,
    constraint FK_driver
        foreign key (driver_id) references user_info (id)
            on update cascade,
    constraint FK_start_address
        foreign key (start_address_id) references cities (id)
            on update cascade on delete cascade
);

create table passengers
(
    journey_id   int not null,
    passenger_id int null,
    constraint FK_journey
        foreign key (journey_id) references journey (id)
            on update cascade on delete cascade,
    constraint FK_passenger
        foreign key (passenger_id) references user_info (id)
            on update cascade on delete cascade
);

