CREATE DATABASE config_lesson ENCODING 'UTF-8';

CREATE TABLE IF NOT EXISTS Actors (
                                       first_name VARCHAR(25) NOT NULL,
                                       second_name VARCHAR(25) NOT NULL,
                                       film_id VARCHAR(25) NOT NULL,
                                       actor_id VARCHAR(25) primary key
);

CREATE TABLE IF NOT EXISTS Movies (
                                      imdb_id VARCHAR(20) primary key,
                                      title VARCHAR(250) NOT NULL,
                                      year int NOT NULL,
                                      plot VARCHAR(250) NOT NULL,
                                      poster_url VARCHAR(250) NOT NULL,
                                      award_wins int NOT NULL,
                                      award_nominations int NOT NULL,
                                      tomato_meter double precision
);


