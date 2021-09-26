DELETE FROM car;
DELETE FROM person;

INSERT INTO person (id, name, birthdate)
VALUES (1, 'person_1', '1995-01-30'),
       (2, 'person_2', '1994-01-31'),
       (3, 'person_3', '2020-01-31');

INSERT INTO car (id, model, horsepower, person_id)
VALUES (1, 'car_1-person_1', 100, 1),
       (2, 'car_2-person_1', 100, 1),
       (3, 'car_3-person_1', 100, 1),

       (4, 'cAr_1-person_2', 100, 2),
       (5, 'cAr_2-person_2', 100, 2),
       (6, 'cAr_3-person_2', 100, 2);
