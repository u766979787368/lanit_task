База данных - H2 in memory. Инициализирована следующими данными:

```
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
```

### CURL:

#### create Person
`curl -X POST -d '{"id":15,"name": "new","birthdate":"20.03.1990"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/person`

#### create Person With Invalidation - Future Birthdate
`curl -X POST -d '{"id":16,"name": "new","birthdate":"20.03.2033"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/person`

#### create Person With Invalidation - Birthdate Format
`curl -X POST -d '{"id":17,"name": "new","birthdate":"20-03-1990"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/person`

#### create Person With Invalidation - Person Exist
`curl -X POST -d '{"id":1,"name": "new","birthdate":"20.03.1990"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/person`

#### create Car
`curl -X POST -d '{"id": 17,"model": "BMW-X5","horsepower": 100,"ownerid":2}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/car`

#### create Car With Invalidation - Horsepower Less Null
`curl -X POST -d '{"id": 18,"model": "BMW-X5","horsepower": -100,"ownerid":2}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/car`

#### create Car With Invalidation - Person Under 18
`curl -X POST -d '{"id": 19,"model": "BMW-X5","horsepower": 100,"ownerid":3}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/car`

#### create Car With Invalidation - Person Not Exist
`curl -X POST -d '{"id": 20,"model": "BMW-X5","horsepower": 100,"ownerid":999}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/car`

#### create Car With Invalidation - Car Exist
`curl -X POST -d '{"id": 1,"model": "BMW-X5","horsepower": 100,"ownerid":2}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/car`

#### create Car With Invalidation - Vendor
`curl -X POST -d '{"id": 21,"model": "-X5","horsepower": 100,"ownerid":2}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/car`

#### create Car With Invalidation - Vendor Model
`curl -X POST -d '{"id": 22,"model": "BMW-","horsepower": 100,"ownerid":2}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/car`

#### get Person with cars
`curl http://localhost:8080/personwithcars?personid=2`

#### get Person with cars - Null Id
`curl http://localhost:8080/personwithcars?personid=`

#### get Person with cars - Not Found
`curl http://localhost:8080/personwithcars?personid=1000`

#### get Statistics
`curl http://localhost:8080/statistics`

#### clear
`curl http://localhost:8080/clear`
