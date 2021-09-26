package ru.lanit.lanit_task.web.person;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.lanit.lanit_task.model.Person;
import ru.lanit.lanit_task.web.AbstractControllerTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PersonControllerTest extends AbstractControllerTest {

    private static final String PERSON_URL = "/person";
    private static final String PERSON_WITH_CAR_URL = "/personwithcars?personid=";

    @Test
    void create() throws Exception {
        Person newPerson = new Person();
        newPerson.setId(15L);
        newPerson.setName("newPerson");
        newPerson.setBirthdate(LocalDate.of(1995, Month.JANUARY, 15));

        perform(MockMvcRequestBuilders.post(PERSON_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPerson)))
                .andDo(print())
                .andExpect(status().isOk());

        BiConsumer<Person, Person> assertion = (a, e) -> assertThat(a).usingRecursiveComparison().isEqualTo(e);
        assertion.accept(personRepository.findById(newPerson.getId()).get(), newPerson);
    }

    @Test
    void createWithInvalidationFutureBirthdate() throws Exception {
        Person newPerson = new Person();
        newPerson.setId(15L);
        newPerson.setName("newPerson");
        newPerson.setBirthdate(LocalDate.of(2033, Month.JANUARY, 15));

        perform(MockMvcRequestBuilders.post(PERSON_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPerson)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWithInvalidationBirthdateFormat() throws Exception {
        perform(MockMvcRequestBuilders.post(PERSON_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":15,\"name\":\"newPerson\",\"birthdate\":\"15-01-1995\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    void createWithInvalidationPersonExist() throws Exception {
        Person newPerson = new Person();
        newPerson.setId(1L); // Person with id=1 already exist in db
        newPerson.setName("newPerson");
        newPerson.setBirthdate(LocalDate.of(1995, Month.JANUARY, 15));

        perform(MockMvcRequestBuilders.post(PERSON_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPerson)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }



    @Test
    void getWithCars() throws Exception {
        BiConsumer<Person, Person> assertion = (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("cars.person").isEqualTo(e);

        perform(MockMvcRequestBuilders.get(PERSON_WITH_CAR_URL + 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertion.accept(objectMapper.readValue(result.getResponse().getContentAsString(), Person.class), PersonTestData.getPersonWithCars()));
    }

    @Test
    void getWithCarsNullId() throws Exception {
        perform(MockMvcRequestBuilders.get(PERSON_WITH_CAR_URL))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void getWithCarsNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(PERSON_WITH_CAR_URL + 15))  // Person with id=15 doesn't exist in db
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}