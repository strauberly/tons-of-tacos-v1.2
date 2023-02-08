CREATE DATABASE IF NOT EXISTS tonsOfTacos;

USE tonsOfTacos;



DROP TABLES IF EXISTS order_item;

DROP TABLES IF EXISTS orders;

DROP TABLES IF EXISTS menu_item;

DROP TABLES IF EXISTS customer;


CREATE TABLE customer(
id INT(12) NOT NULL AUTO_INCREMENT,
email VARCHAR(40) NOT NULL,
name VARCHAR(40) NOT NULL,
orders JSON,
phone_number VARCHAR(12) NOT NULL,
PRIMARY KEY(id)
);


CREATE TABLE menu_item(
id INT(12) NOT NULL AUTO_INCREMENT,
category VARCHAR(30) NOT NUll,
description VARCHAR(255) NOT NUll,
item_name VARCHAR(30) NOT NUll,
item_size VARCHAR(100),
img_url VARCHAR(255) NOT NUll,
unit_price DECIMAL(19, 2) NOT NUll,
PRIMARY KEY(id)
);

CREATE INDEX ITEM_NAMEX ON menu_item(item_name);
CREATE INDEX ITEM_IDX ON menu_item(id);



CREATE TABLE orders(
id INT(12) NOT NULL AUTO_INCREMENT,
created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
customer_id INT(12) NOT NULL,
order_data JSON,
order_total DECIMAL(19, 2) NOT NULL,
order_uuid VARCHAR(255) NOT NULL,
status VARCHAR(6) DEFAULT "open",
PRIMARY KEY(id),
FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE INDEX ORDER_UUIDX ON orders(order_uuid);


CREATE TABLE order_item(
id INT(12) NOT NULL AUTO_INCREMENT,
menu_item_id INT(12) NOT NULL,
menu_item_name VARCHAR(30) NOT NULL,
order_uuid VARCHAR(255) NOT NULL, 
quantity INT(2) NOT NULL,
total DECIMAL(19, 2)NOT NULL,
PRIMARY KEY(id),
CONSTRAINT fk_menuItemName_orderItemName FOREIGN KEY (menu_item_name) 
REFERENCES menu_item(item_name),
CONSTRAINT fk_menuItemId_cartItemId FOREIGN KEY (menu_item_id) REFERENCES 
menu_item(id),
CONSTRAINT fk_OrdersOrderItemOrderUuid FOREIGN KEY (order_uuid) 
REFERENCES 
orders(order_uuid)
);




