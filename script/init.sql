-- CREATE DATABASE mvms;
USE mvms;

SELECT * FROM actor;
SELECT * FROM film;
SELECT * FROM category;
SELECT * FROM language;
SELECT * FROM film_actor;
SELECT * FROM film_category;

-- DROP TABLE film_actor;
-- DROP TABLE film_category;
-- DROP TABLE actor;
-- DROP TABLE category;
-- DROP TABLE film;
-- DROP TABLE language;

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