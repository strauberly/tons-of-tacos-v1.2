CREATE DATABASE IF NOT EXISTS tonsOfTacos;

USE tonsOfTacos;

DROP TABLES IF EXISTS menu_item;

DROP TABLES IF EXISTS order_item;

DROP TABLES IF EXISTS orders;

DROP TABLES IF EXISTS customer;


CREATE TABLE customer(
id INT(12) NOT NULL AUTO_INCREMENT,
name VARCHAR(40) NOT NULL,
phone_number VARCHAR(12) NOT NULL,
email VARCHAR(40) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE orders(
id INT(12) NOT NULL AUTO_INCREMENT,
UUID VARCHAR(255) NOT NULL,
customer_id INT(12) NOT NULL,
order_data JSON,
order_total DECIMAL(19, 2) NOT NULL,
status VARCHAR(6) DEFAULT "open",
created timestamp DEFAULT current_timestamp,
PRIMARY KEY(id),
FOREIGN KEY(customer_id) REFERENCES customer(id)
);

CREATE TABLE menu_item(
id INT(12) NOT NULL AUTO_INCREMENT,
item_name VARCHAR(30),
item_size VARCHAR(3),
category VARCHAR(30),
unit_price DECIMAL(19, 2),
PRIMARY KEY(id)
);


CREATE TABLE order_item(
id INT(12) NOT NULL AUTO_INCREMENT,
menu_item_id INT(12) NOT NULL,
order_id INT(12) NOT NULL,
quanity INT(2) NOT NULL,
total DECIMAL(19, 2),
PRIMARY KEY(id),
FOREIGN KEY(menu_item_id) REFERENCES menu_item(id),
FOREIGN KEY(order_id) REFERENCES orders(id)
);




