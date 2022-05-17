-- Cars
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

-- Employee
INSERT INTO employee(employee_id, employee_first_name, employee_last_name, employee_username, employee_password, employee_password_salt, employee_type, is_user_active) VALUES (default, 'Jen', 'Hansen', 'jen', 'fWAKZlbEDJTBc8ZSIz4RQ0LYFrOvz8dWgOSMasmSHk8=', 'CgY7nQUteSi1qyFoWgM1sytGK3oasA', 'admin', true);
INSERT INTO employee(employee_id, employee_first_name, employee_last_name, employee_username, employee_password, employee_password_salt, employee_type, is_user_active) VALUES (default, 'Dan', 'Jensen', 'dan', 'ZjrSv8TSYhn73K0KVzmTlSWHDSbRyjve8BB1M9sIei8=', '9raKccpXzZXbk5srACOxHzxrCW948K', 'dataregistrering', true);
INSERT INTO employee(employee_id, employee_first_name, employee_last_name, employee_username, employee_password, employee_password_salt, employee_type, is_user_active) VALUES (default, 'Tim', 'Prosen', 'tim', 'TopCy0fiEANdpffhyinBRm7hDr9YX+4usgupUJdd0tc=', 'rm4jvpM66yrjeoGFTNWyYpcR1xgE8v', 'forretningsudvikler', true);
INSERT INTO employee(employee_id, employee_first_name, employee_last_name, employee_username, employee_password, employee_password_salt, employee_type, is_user_active) VALUES (default, 'Pil', 'Tisen', 'pil', '8K4pc/e8XFDwMjTgzGJyVhrIRsHAHZEZyaUwwqQ5kdI=', 'CqzI920fdPCPOwtRDDpvDA0Sx1HUz9', 'skade- og mangler', true);
INSERT INTO employee(employee_id, employee_first_name, employee_last_name, employee_username, employee_password, employee_password_salt, employee_type, is_user_active) VALUES (default, 'Mik', 'Dyring', 'mik', '8kLVU7O4Agt91dgw4g8A8YuOfFxO39w3TQCcr7ASO8A=', '567lrcVICZvFJnRqUgMn8cefQ153A8', 'skade- og mangler', false);

-- Contracts
INSERT INTO contract(employee_id, customer_id) VALUES (1, 1);
INSERT INTO contract(employee_id, customer_id) VALUES (1, 2);
INSERT INTO contract(employee_id, customer_id) VALUES (2, 3);
INSERT INTO contract(employee_id, customer_id) VALUES (2, 4);
INSERT INTO contract(employee_id, customer_id) VALUES (2, 5);
INSERT INTO contract(employee_id, customer_id) VALUES (3, 6);
INSERT INTO contract(employee_id, customer_id) VALUES (4, 7);
INSERT INTO contract(employee_id, customer_id) VALUES (4, 8);
INSERT INTO contract(employee_id, customer_id) VALUES (4, 9);

-- Lease
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) Values (1, 1, '2022-12-17', 120);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) Values (1, 2, '2022-12-17', 120);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) Values (2, 3, '2022-12-17', 150);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) Values (2, 4, '2022-12-17', 120);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) Values (2, 5, '2022-12-17', 120);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) Values (3, 6, '2022-12-17', 120);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) Values (3, 7, '2022-12-17', 120);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) Values (4, 8, '2022-12-17', 120);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) Values (5, 9, '2022-12-17', 120);
INSERT INTO lease(contract_id, car_id, lease_start_date, lease_period_number_of_days) Values (6, 10, '2022-12-17', 120);
