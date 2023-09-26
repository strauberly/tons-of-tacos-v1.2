-- Sample customers
--INSERT INTO customer ( name, email, phone_number, customer_uid)
--VALUES( 'John Johnson', 'john@johnson.com', '555.555.5552', 'jk34-h5j0');
--INSERT INTO customer ( name, email, phone_number, customer_uid)
--VALUES('Tim Timson', 'tim@timson.com',  '555.555.5553', 'gd34-igjr' );
--INSERT INTO customer ( name, email, phone_number, customer_uid)
--VALUES('Bob Bobson', 'bob@bobson.com', '555.555.5551', '09t8-g093');

-- Sample items

-- menu-item taco
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('taco', 'nom nom', 'pound', NULL, 'TBD', 2.25);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('taco', 'nom nom', 'golden pound', NULL, 'TBD', 5.30);

-- menu-item drink
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('drink', 'refreshing', 'cola', '16 oz.bottle', 'TBD', 1.00);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('drink', 'refreshing', 'orange soda', '16 oz.bottle', 'TBD', 1.25);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('drink', 'refreshing', 'grape soda', '16 oz.bottle', 'TBD', 1.25);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('drink', 'refreshing', 'pineapple soda', '16 oz.bottle', 'TBD', 1.25);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('drink', 'refreshing', 'horchata', 's', 'TBD', 1.00);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('drink', 'refreshing', 'horchata', 'm', 'TBD', 1.50);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('drink', 'refreshing', 'horchata', 'l', 'TBD', 2.00);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('drink', 'refreshing', 'iced tea', 's', 'TBD', 1.00);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('drink', 'refreshing', 'iced tea', 'm', 'TBD', 1.50);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('drink', 'refreshing', 'iced tea', 'l', 'TBD', 2.00);

-- menu-item  side
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('side', 'a wonderful addition', 'street corn', NULL, 'TBD', 1.00);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('side', 'a wonderful addition', 'pico de gallo', NULL, 'TBD', 1.50);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('side', 'a wonderful addition', 'slaw de mexicana', NULL, 'TBD', 1.00);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('side', 'a wonderful addition', 'papas', NULL, 'TBD', 2.15);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('side', 'a wonderful addition', 'frijoles', NULL, 'TBD', 1.50);

-- menu-item  topping
INSERT INTO menu_item (category, description, item_name, item_size, img_url,
unit_price)
VALUES('topping', 'a little extra', 'lettuce', NULL, 'TBD', .50);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('topping', 'a little extra', 'cabbage', NULL, 'TBD', .50);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('topping', 'a little extra', 'cilantro', NULL, 'TBD', .50);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('topping', 'a little extra', 'pickled jalepenos and onions', NULL,
'TBD', 1.00);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('topping', 'a little extra', 'sour cream', NULL, 'TBD', 1.50);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('topping', 'a little extra', 'avocado', NULL, 'TBD', 1.50);
INSERT INTO menu_item (category, description , item_name, item_size, img_url,
unit_price)
VALUES('topping', 'a little extra', 'lime', NULL, 'TBD', 1.50);

--
------ Sample orders
--INSERT INTO orders (customer_fk, order_total, order_uid)
--VALUES(2, 30.55, '654654-465465-555');
--INSERT INTO orders (customer_fk, order_total, order_uid)
--VALUES(1, 25.55, '654654-4655-555');
--INSERT INTO orders (customer_fk, order_total, order_uid)
--VALUES(1, 10.00, '654654-4657-555');
----
------ Sample order items
--INSERT INTO order_items (item_fk, quantity, total, order_fk)
--VALUES(1, 3, 3.00, 2);
--INSERT INTO order_items (item_fk, quantity, total, order_fk)
--VALUES(2, 4, 4.00, 2);
--INSERT INTO order_items (item_fk, quantity, total, order_fk)
--VALUES(3, 3, 1.50, 1);
--INSERT INTO order_items (item_fk, quantity, total, order_fk)
--VALUES(3, 3, 1.50, 3);

-- Sample owners
INSERT INTO owners (name, username, psswrd, contact, role)
VALUES('Jim Castillo', 'jcast22', 
'$2a$10$MIGqdrc1JzTC1NWCfsWnsuY9L3OpAO4.gWmGiFKzUrq26Q1ejmjS2', 
'jim@tonsoftacos.com', 'ADMIN');
INSERT INTO owners (name, username, psswrd, contact, role)
VALUES('Jenny Castillo', 'jcast33', 
'$2a$10$oJU3y8przJfPudPzXAH8FOVNSeNl3YEZdckyPJzia0fq/lYIvJhWC', 
'jenny@tonsoftacos.com', 'ADMIN');