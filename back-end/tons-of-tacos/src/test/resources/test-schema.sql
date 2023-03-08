DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS menu_item;
DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
customer_pk INT unsigned NOT NULL AUTO_INCREMENT,
name VARCHAR(40) NOT NULL,
email VARCHAR(40) NOT NULL,
phone_number VARCHAR(12) NOT NULL,
PRIMARY KEY (customer_pk)
);

CREATE TABLE menu_item(
item_pk INT unsigned NOT NULL AUTO_INCREMENT,
category VARCHAR(30) NOT NUll,
description VARCHAR(255) NOT NUll,
item_name VARCHAR(30) NOT NUll,
item_size VARCHAR(100) DEFAULT NULL,
img_url VARCHAR(255) NOT NUll,
unit_price DECIMAL(19, 2) NOT NUll,
PRIMARY KEY (item_pk)
);

CREATE TABLE orders(
order_pk INT unsigned NOT NULL AUTO_INCREMENT,
customer_fk INT unsigned NOT NULL,
order_total DECIMAL(19, 2) NOT NULL,
order_uid VARCHAR(255) NOT NULL,
created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (order_pk),
FOREIGN KEY (customer_fk) REFERENCES customer(customer_pk) ON DELETE CASCADE
);

-- CREATE INDEX CART_UID ON orders(cart_uid);

CREATE TABLE order_items(
order_item_pk INT unsigned NOT NULL AUTO_INCREMENT,
item_fk INT unsigned NOT NULL,
quantity INT(2) NOT NULL,
total DECIMAL(19, 2) DEFAULT 0.00 NOT NULL,
order_fk INT unsigned NOT NULL,
PRIMARY KEY (order_item_pk),
FOREIGN KEY (item_fk) REFERENCES menu_item(item_pk) ON DELETE CASCADE,
FOREIGN KEY (order_fk) REFERENCES orders(order_pk) ON DELETE CASCADE
);