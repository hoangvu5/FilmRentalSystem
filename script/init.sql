CREATE DATABASE mvms;
USE mvms;

SELECT * FROM actor;
SELECT * FROM film;
SELECT * FROM category;
SELECT * FROM language;
SELECT * FROM film_actor;
SELECT * FROM film_category;
SELECT * FROM inventory;

SELECT * FROM user;
SELECT * FROM customer;

-- DROP TABLE film_actor;
-- DROP TABLE film_category;
-- DROP TABLE actor;
-- DROP TABLE category;
-- DROP TABLE film;
-- DROP TABLE language;

-- DELETE FROM customer WHERE customer_id = 3;
-- DELETE FROM user WHERE user_id = 3;

INSERT INTO language (last_update, name) VALUES
(current_timestamp(), 'English'),
(current_timestamp(), 'Spanish'),
(current_timestamp(), 'Vietnamese');

INSERT INTO actor (last_update, first_name, last_name) VALUES
(current_timestamp(), 'Hoang', 'Vu'),
(current_timestamp(), 'Kien', 'Le'),
(current_timestamp(), 'Vinh', 'Do'),
(current_timestamp(), 'Minh', 'Nguyen'),
(current_timestamp(), 'Phuong', 'Nguyen');

INSERT INTO category (last_update, name) VALUES
(current_timestamp(), 'Comedy'),
(current_timestamp(), 'Documentary'),
(current_timestamp(), 'Sci-fi'),
(current_timestamp(), 'Romance');

INSERT INTO film (language_id, last_update, length, rating, release_year, rental_duration, rental_rate, replacement_cost, description, full_text, special_features, title) VALUES
(1, current_timestamp(), 60, 5, 2024, 30, 10, 10, 'A movie...', '', '', 'Minion'),
(2, current_timestamp(), 60, 5, 2024, 30, 10, 10, 'Romantic...', '', '', 'Despacito'),
(3, current_timestamp(), 60, 5, 2024, 30, 10, 10, 'By Tran Thanh', '', '', 'Bo Gia');

INSERT INTO film_actor (film_id, actor_id, last_update) VALUES
(1, 1, current_timestamp()),
(1, 2, current_timestamp()),
(2, 2, current_timestamp()),
(2, 3, current_timestamp()),
(2, 4, current_timestamp()),
(3, 1, current_timestamp()),
(3, 4, current_timestamp()),
(3, 5, current_timestamp());

INSERT INTO film_category (film_id, category_id, last_update) VALUES
(1, 1, current_timestamp()),
(1, 3, current_timestamp()),
(2, 2, current_timestamp()),
(2, 4, current_timestamp()),
(3, 1, current_timestamp()),
(3, 4, current_timestamp());

INSERT INTO inventory (film_id, last_update) VALUES
(1, current_timestamp()),
(2, current_timestamp()),
(2, current_timestamp()),
(3, current_timestamp()),
(3, current_timestamp()),
(3, current_timestamp());

INSERT INTO customer (first_name, last_name, created_date, last_update, active) VALUES
('Hoang', 'Vu', current_timestamp(), current_timestamp(), 1),
('Kien', 'Le', current_timestamp(), current_timestamp(), 1),
('Vinh', 'Do', current_timestamp(), current_timestamp(), 1),
('Minh', 'Nguyen', current_timestamp(), current_timestamp(), 1),
('Phuong', 'Nguyen', current_timestamp(), current_timestamp(), 1);

INSERT INTO rental (customer_id, inventory_id, rental_date, return_date, last_update) VALUES
(1, 1, current_timestamp(), current_timestamp(), current_timestamp()),
(1, 2, current_timestamp(), current_timestamp(), current_timestamp()),
(2, 2, current_timestamp(), current_timestamp(), current_timestamp()),
(3, 3, current_timestamp(), current_timestamp(), current_timestamp()),
(3, 4, current_timestamp(), current_timestamp(), current_timestamp()),
(4, 5, current_timestamp(), current_timestamp(), current_timestamp());