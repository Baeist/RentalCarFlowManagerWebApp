INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, '123frt123frg123fr', 'blue', 'Nissan', 'stationcar', 'NX2020', 2650);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, '456frt123frg123fr', 'red', 'Nissan', 'stationcar', 'NX2019', 2650);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, '789frt123frg123fr', 'black', 'Nissan', 'cabriolet', 'NT2018', 2800);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, '481265t23frg123fr', 'white', 'Volvo', 'varevogn', 'VO2020', 3300);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, 'abcdrt123frg123fr', 'silver', 'Volvo', 'sedan', 'VO2022', 3650);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, 'efghrt123frg123fr', 'blue', 'Volvo', 'mini', 'Vo2019', 2450);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, 'ihjlrt123frg123fr', 'orange', 'Toyota', 'stationcar', 'TREXI', 2550);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, 'iokl45123frg123fr', 'green', 'Toyota', 'stationcar', 'KANT', 2950);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, 'py3frt123frg123fr', 'silver', 'Toyota', 'varevogn', 'RUBES', 3650);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, '4uilrt123frg123fr', 'black', 'Toyota', 'mini', 'DARKO', 2300);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, 'hjkdet123frg123fr', 'blue', 'Honda', 'cabriolet', 'F2010', 3100);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, 'we341t123frg123fr', 'red', 'Honda', 'sedan', 'ROISE', 2890);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, 'uio56t123frg123fr', 'white', 'Honda', 'cabriolet', 'PORPO', 2760);
INSERT INTO car(car_id, chassis_number, color, car_manufactorer, car_type, car_name, car_rental_price_per_month_dkk) Values (default, 'cxe32t123frg123fr', 'grey', 'Honda', 'stationcar', 'RED10', 2290);

INSERT INTO employee(employee_id, employee_first_name, employee_last_name, employee_username, employee_password, employee_type) VALUES (default, 'Jen', 'Hansen', 'jen', '1', 'admin');
INSERT INTO employee(employee_id, employee_first_name, employee_last_name, employee_username, employee_password, employee_type) VALUES (default, 'Dan', 'Jensen', 'dan', '2', 'dataregistrering');
INSERT INTO employee(employee_id, employee_first_name, employee_last_name, employee_username, employee_password, employee_type) VALUES (default, 'Tim', 'Prosen', 'tim', '3', 'forretningsudvikler');
INSERT INTO employee(employee_id, employee_first_name, employee_last_name, employee_username, employee_password, employee_type) VALUES (default, 'Pil', 'Tisen', 'pil', '4', 'skade- og mangler');

INSERT INTO contract(employee_id, customer_id) VALUES (1, 1);
INSERT INTO contract(employee_id, customer_id) VALUES (1, 2);
INSERT INTO contract(employee_id, customer_id) VALUES (1, 3);
INSERT INTO contract(employee_id, customer_id) VALUES (1, 4);
INSERT INTO contract(employee_id, customer_id) VALUES (1, 5);
INSERT INTO contract(employee_id, customer_id) VALUES (1, 6);
INSERT INTO contract(employee_id, customer_id) VALUES (1, 7);

INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) VALUES (1, 1, "2022-05-16", 90);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) VALUES (2, 2, "2021-05-16", 365);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) VALUES (3, 3, "2022-09-16", 120);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) VALUES (4, 4, "2021-05-16", 730);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) VALUES (5, 5, "2022-01-16", 90);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) VALUES (6, 6, "2022-04-16", 90);