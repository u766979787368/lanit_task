package ru.lanit.lanit_task.web.car;

import ru.lanit.lanit_task.model.Car;

import java.util.Arrays;
import java.util.List;

public class CarTestData {

    // Cars as in populateDB.sql file
    public static final Car car_1 = new Car(1L, "car_1-person_1", 100, 1L);
    public static final Car car_2 = new Car(2L, "car_2-person_1", 100, 1L);
    public static final Car car_3 = new Car(3L, "car_3-person_1", 100, 1L);

    public static final Car car_4 = new Car(4L, "cAr_1-person_2", 100, 2L);
    public static final Car car_5 = new Car(5L, "cAr_2-person_2", 100, 2L);
    public static final Car car_6 = new Car(6L, "cAr_3-person_2", 100, 2L);

    public static List<Car> getAllCars() {
        return Arrays.asList(
                car_1,
                car_2,
                car_3,
                car_4,
                car_5,
                car_6
        );
    }

    public static Long getUniqueVendorCount() {
        return getAllCars().stream().map(car -> car.getModel().substring(0, car.getModel().indexOf('-')).toUpperCase()).distinct().count();
    }


}
