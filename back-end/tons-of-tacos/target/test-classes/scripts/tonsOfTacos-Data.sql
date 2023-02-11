
INSERT INTO customer ( name, email, phone_number) 
VALUES('Bob Bobson', 'bob@bobson.com', '555.555.5551');

INSERT INTO menu_item (category, description , item_name, item_size, img_url, 
unit_price) 
VALUES('taco', 'nom nom', 'pound', NULL, 'TBD', 2.25);

INSERT INTO orders (customer_fk, order_total, order_uuid) 
VALUES(1, 25.55, '654654-465465-555');

INSERT INTO order_item (item_fk, order_fk, order_uuid_fk, quantity, total) 
VALUES(1, 1,'654654-465465-555', 3, 3.00);



