package ru.lanit.lanit_task.web.car;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.lanit.lanit_task.model.Car;
import ru.lanit.lanit_task.web.AbstractControllerTest;

import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class CarControllerTest extends AbstractControllerTest {

    private static final String URL = "/car";

    @Test
    void create() throws Exception {
        Car newCar = new Car();
        newCar.setId(15L);
        newCar.setModel("BMW-X5");
        newCar.setHorsepower(300);
        newCar.setOwnerid(2L);

        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCar)))
                .andDo(print())
                .andExpect(status().isOk());

        BiConsumer<Car, Car> assertion = (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("person").isEqualTo(e);
        assertion.accept(carRepository.findById(newCar.getId()).get(), newCar);
    }

    @Test
    void createWithInvalidationHorsepowerLessNull() throws Exception {
        Car newCar = new Car();
        newCar.setId(15L);
        newCar.setModel("BMW-X5");
        newCar.setHorsepower(-300);
        newCar.setOwnerid(2L);

        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCar)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWithInvalidationPersonUnder18() throws Exception {
        Car newCar = new Car();
        newCar.setId(15L);
        newCar.setModel("BMW-X5");
        newCar.setHorsepower(300);
        newCar.setOwnerid(3L); // in db Person with id=3 birthdate is 2020-01-30

        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCar)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWithInvalidationPersonNotExist() throws Exception {
        Car newCar = new Car();
        newCar.setId(15L);
        newCar.setModel("BMW-X5");
        newCar.setHorsepower(300);
        newCar.setOwnerid(15L); // Person with id=15 doesn't exist in db

        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCar)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWithInvalidationCarExist() throws Exception {
        Car newCar = new Car();
        newCar.setId(1L); // Car with id=1 already exist in db
        newCar.setModel("BMW-X5");
        newCar.setHorsepower(300);
        newCar.setOwnerid(2L);

        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCar)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWithInvalidationVendor() throws Exception {
        Car newCar = new Car();
        newCar.setId(15L);
        newCar.setModel("-X5-m");
        newCar.setHorsepower(300);
        newCar.setOwnerid(2L);

        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCar)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWithInvalidationVendorModel() throws Exception {
        Car newCar = new Car();
        newCar.setId(15L);
        newCar.setModel("BMW-");
        newCar.setHorsepower(300);
        newCar.setOwnerid(2L);

        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCar)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}