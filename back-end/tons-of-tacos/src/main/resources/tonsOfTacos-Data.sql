USE tonsOfTacos;

#sample customers
INSERT INTO customer (email, name, phone_number) 
VALUES('bob@bobson.com', 'Bob Bobson', '555.555.5551');
INSERT INTO customer (email, name, phone_number) 
VALUES('john@johnson.com', 'John Johnson', '555.555.5552');
INSERT INTO customer (email, name, phone_number) 
VALUES('tim@timson.com', 'Tim Timson', '555.555.5553');

# menu-item taco
INSERT INTO menu_item (category, description , item_name, item_size, img_url, 
unit_price) 
VALUES('taco', 'nom nom', 'pound', NULL, 'TBD', 2.25);
INSERT INTO menu_item (category, description , item_name, item_size, img_url, 
unit_price) 
VALUES('taco', 'nom nom', 'golden pound', NULL, 'TBD', 5.30);

# menu-item drink
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
VALUES('drink', 'refreshing', 'small horchata', 's', 'TBD', 1.00);
INSERT INTO menu_item (category, description , item_name, item_size, img_url, 
unit_price) 
VALUES('drink', 'refreshing', 'medium horchata', 'm', 'TBD', 1.50);
INSERT INTO menu_item (category, description , item_name, item_size, img_url, 
unit_price) 
VALUES('drink', 'refreshing', 'large horchata', 'l', 'TBD', 2.00);
INSERT INTO menu_item (category, description , item_name, item_size, img_url, 
unit_price) 
VALUES('drink', 'refreshing', 'small iced tea', 's', 'TBD', 1.00);
INSERT INTO menu_item (category, description , item_name, item_size, img_url, 
unit_price) 
VALUES('drink', 'refreshing', 'medium iced tea', 'm', 'TBD', 1.50);
INSERT INTO menu_item (category, description , item_name, item_size, img_url, 
unit_price) 
VALUES('drink', 'refreshing', 'large iced tea', 'l', 'TBD', 2.00);

# menu-item  side
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

# menu-item  topping
INSERT INTO menu_item (category, description , item_name, item_size, img_url, 
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


