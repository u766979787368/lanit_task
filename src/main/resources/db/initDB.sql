DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS person;

CREATE TABLE person
(
    id        INTEGER PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    birthdate TIMESTAMP    NOT NULL
);

CREATE TABLE car
(
    id         INTEGER PRIMARY KEY,
    model      VARCHAR(255) NOT NULL,
    horsepower INTEGER      NOT NULL,
    person_id  INTEGER      NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE
);