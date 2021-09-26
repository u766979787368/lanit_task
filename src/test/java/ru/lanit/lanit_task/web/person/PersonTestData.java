package ru.lanit.lanit_task.web.person;

import ru.lanit.lanit_task.model.Person;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.lanit.lanit_task.web.car.CarTestData.*;

public class PersonTestData {

    // Persons as in populateDB.sql file
    public static final Person person_1 = new Person(1L, "person_1", LocalDate.of(1995, Month.JANUARY, 30));
    public static final Person person_2 = new Person(2L, "person_2", LocalDate.of(1994, Month.JANUARY, 31));
    public static final Person person_3 = new Person(3L, "person_3", LocalDate.of(2020, Month.JANUARY, 31));

    public static Person getPersonWithCars() {
        Person person = person_1;
        person.setCars(Arrays.asList(
                car_1,
                car_2,
                car_3
        ));
        return person;
    }

    public static List<Person> getAllPerson() {
        return Arrays.asList(
                person_1,
                person_2,
                person_3
        );
    }
}
