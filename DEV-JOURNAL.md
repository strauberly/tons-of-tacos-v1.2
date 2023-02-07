## This Journal tracks development progress, ideas and thoughts.

---

-- 6 Feb 2023 --

- Refactored order item controllers and services.
- Refactored packages for organizational purposes.
- Began working out logic for updating a menu item.
- Updated documentation for menu item and order item controllers.
- Will be refactoring data structure in future. Order items will become cart items as this is more descriptive of what is actually happening.
- Order item endpoints test in browser and with postman for desired functionality. Tests will be written with JUnit following establishment of remaining endpoints for crud functionality of cart items.

---

-- 3 Feb 2023 --

- Refactored menu item controllers and created services that now provides desired functionality of shorthand or jpa search.
  -Refactored Controller naming convention .
- Working on functionality of order/cart item in regards to crud operations and then transferring to an order with making these end points open for queries. Will resume next week.

---

-- 2 Feb 2023 --

- added jira project - need to research issue keys.
- updated read me to include purpose of application and link to dev journal.
- created endpoint for creating orders and order items.
- created test for 201 response upon successful creation of order item.
- tested endpoint with valid data in postman.
- cleaned up tests.
- created controller and documentation for order endpoint.
- cleaned up config file.

Tomorrow's goals

- Refactor Controllers.
- Continue Crud Operations for order items.

---

-- 1 Feb 2023 --

- Built global error handler for future implementation.
- Began fleshing out functionality to be associated with adding items to a cart.
  - In summary:
    - click on item >> order uuid is generated >> relevant information is written to DB for the purpose of populating a cart for customer to alter and observe.
    - On checkout >> uuid and cart will clear after using the data in the cart to create an order and write to DB.
- Created order item controllers and repository classes.
- Refactored database based on needs new phase will bring.
- Update project on GitHub.

Tomorrow's goals

- Start project management with Jira.
- Update README description.
- Begin work on controllers and and tests to facilitate writing order items to DB.

---

-- 31 Jan 2023 --

- Test built for creating a new menu item for when that feature is implemented for the owners.
- Test built for getting menu items by category.
- Sprint concluded on target.

Tomorrow's goals

- Build error handler.

---

-- 30 Jan 2023 --

- Re-organizing menu item test for http responses.
- Organizing ideas on migrating menu item to order item.

---

-- 27 Jan 2023 --
Not as far as I wanted to get today but I'll take the small victory.

- Created test for retrieving data and comparing it against an expected set of data.
- Also tested for proper response code from the query.

---

-- 26 Jan 2023 --

- Created a configuration that works for testing in memory as well as against the actual database.
- Successfully created a test to create a new item in the database. I am not completely pleased with it however and intend to do some more work on it. Right now we create an entity to send through a constructor but I would like for the data to come from a builder instead.

Tomorrow's goals

- Try and complete means for full crud testing with in memory database.

---

-- 25 Jan 2023 --

- Revealed entity ids in order to aid in ease of working with data with method constructed in rest config
- Refined test for CRUD operations against the data base.
- Calling items by category will remain as simply a query on our menu-item endpoint.
- Considerable research into configuring H2 in memory database for testing purposes. Have possibly locked on to a configuration that will yield desired results.

Tomorrow's goals

- Try and complete means for full crud testing with in memory database.

---

-- 24 Jan 2023 --

Starting to get a bit more dialed with my procedure and what needs to come next. The pieces are falling together. I do find that I am prone to losing time because I worry about best practice and simply questioning if I'm doing this right in order to save work down the road. But sometimes you just gotta fool about and find out ^\_-.

- Created resources for searching menu items by id and category.
- Refined swagger document a bit more
- Created test file working with menu items by category
- Refined menu item path a bit so that a bit more precision is required to call an item and all items do not just become available upon endpoint entry.
- Updated DB with columns for descriptions and image urls.
- Added more DB entries to work with.

Tomorrow's goals

- More work on menu item category endpoint.
- Work on menu item test and error handling.
- Possibly work on logging.

---

-- 23 Jan 2023 --

- Created successful route test for a menu item.
- Created API documentation with swagger for menu-item end point.
- Added as project on github and linked to repository. Don't know how well I'll be able to keep up with project tracking while simultaneously developing application solo but going to give it a try.

Tomorrow's goals

- Create and Test menu-item endpoint for returning items by category.
- Add documentation for end point.
- Update DB to add columns 'description' and 'img-url' to menu-item table.
- Begin work on error handling.

---

-- 19 Jan 2023 --

- Inserted test data to local instance db.
- Created connection configuration.
- Created base entity classes.
- Created Menu Item repository as part of dao.
- Created Data Rest Config and limited access to read only.
- Tested endpoint in browser and with Postman.
- Created basic test classes.
- Updated README with a rough phase list.

Next tasks will include fleshing out the test classes. Project management with github and jira.

---

-- 18 Jan 2023 --

- Created script based on an updated EDR.
- Established local database for testing.
- Initialized SpringBoot application.
- Created base packages for further development.

---

-- 17 Jan 2023 --

Created initial EDR for consideration of database layout. This diagram will be updated as improvements are made.

&nbsp;

<p align="center">
  <img src="./back-end/supporting-files/TonsOfTacosErd.drawio2.svg"/>
</p>

&nbsp;

---

-- 16 Jan 2023 --

Created repositories, updated readme and pushed all to readme-update branch.

---

© Adam Straub 2023
