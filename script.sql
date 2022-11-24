DROP DATABASE IF EXISTS medicine;
CREATE DATABASE medicine;
\c medicine;

CREATE TABLE medicament(
    id serial NOT NULL,
    name varchar(255) NOT NULL,
    type varchar(255) NOT NULL,
    form varchar(255) NOT NULL,
    price real NOT NULL,
    count integer NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO medicament VALUES
(1, 'A', 'A', 'Pills', 150.5, 54),
(2, 'B', 'B', 'Liquid', 36, 150);