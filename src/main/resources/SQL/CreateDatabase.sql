use rentalcarflow;
SET foreign_key_checks = 0;
drop table if exists employee;
drop table if exists car;
drop table if exists contract;
drop table if exists lease;
drop table if exists damage_report;
SET foreign_key_checks = 1;
create table if not exists employee
(
    employee_id   int auto_increment
    primary key unique,
    employee_first_name varchar(45) not null,
    employee_last_name varchar(45) not null,
    employee_username varchar(45) not null unique,
    employee_password varchar(256) not null,
    employee_password_salt varchar(30) not null,
    employee_type varchar(45) not null,
    is_user_active boolean not null
    );
create table if not exists car
(
    car_id   int auto_increment unique
    primary key,
    chassis_number varchar(17) not null unique,
    color varchar(45) not null,
    car_manufactorer varchar(45) not null,
    car_type varchar(45) not null,
    car_name varchar(70) not null,
    car_gear_level int not null,
    car_steel_price_dkk double not null,
    car_registration_fee_dkk double not null,
    car_co2_emission_per_km double not null,
    car_rental_price_per_month_dkk double not null
    );
create table if not exists contract
(
    contract_id   int auto_increment
    primary key unique,
    employee_id int not null,
    customer_id int not null,
    foreign key (employee_id) references employee(employee_id)
    );
create table if not exists lease
(
    lease_id   int auto_increment
    primary key unique,
    contract_id int not null,
    car_id int not null,
    lease_start_date date not null,
    lease_period_number_of_days int not null,
    foreign key (contract_id) references contract(contract_id),
    foreign key (car_id) references car(car_id)
    );
create table if not exists damage_report
(
    damage_report_id   int auto_increment unique
    primary key,
    contract_id int not null,
    car_id int not null,
    description varchar(400),
    damage_price_dkk double not null,
    garage_name varchar(45),
    foreign key (contract_id) references contract(contract_id),
    foreign key (car_id) references car(car_id)
    );