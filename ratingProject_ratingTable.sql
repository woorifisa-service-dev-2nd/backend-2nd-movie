CREATE TABLE rating (
    rating_id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    film_id SMALLINT UNSIGNED,
    grade SMALLINT NOT NULL,
    FOREIGN KEY (film_id) REFERENCES film (film_id)
);

INSERT INTO rating (film_id, rating) VALUES (1, 5);
INSERT INTO rating (film_id, rating) VALUES (9, 3);
INSERT INTO rating (film_id, rating) VALUES (14, 4);