DELETE FROM car;
DELETE FROM person;

INSERT INTO person (id, name, birthdate)
VALUES (1, 'person_1', '2020-01-30'),
       (2, 'person_2', '2020-01-31'),
       (3, 'person_3', '2020-01-31');

INSERT INTO car (id, model, horsepower, person_id)
VALUES (1, 'car_1_person_1', 100, 1),
       (2, 'car_2_person_1', 100, 1),
       (3, 'car_3_person_1', 100, 1),

       (4, 'car_1_person_2', 100, 2),
       (5, 'car_2_person_2', 100, 2),
       (6, 'car_3_person_2', 100, 2),

       (7, 'car_1_person_3', 100, 3),
       (8, 'car_2_person_3', 100, 3),
       (9, 'car_3_person_3', 100, 3);
