package ru.lanit.lanit_task.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.lanit.lanit_task.model.Statistics;
import ru.lanit.lanit_task.web.car.CarTestData;
import ru.lanit.lanit_task.web.person.PersonTestData;

import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RootControllerTest extends AbstractControllerTest {

    private static final String STATISTICS_URL = "/statistics";
    private static final String CLEAR_URL = "/clear";

    @Test
    void getStatistics() throws Exception {
            BiConsumer<Statistics, Statistics> assertion = (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("cars.person").isEqualTo(e);

            Statistics statistics = new Statistics();
            statistics.setPersoncount((long) PersonTestData.getAllPerson().size());
            statistics.setCarcount((long) CarTestData.getAllCars().size());
            statistics.setUniquevendorcount(CarTestData.getUniqueVendorCount());


            perform(MockMvcRequestBuilders.get(STATISTICS_URL))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(result -> assertion.accept(objectMapper.readValue(result.getResponse().getContentAsString(), Statistics.class), statistics));
    }

    @Test
    void clear() throws Exception  {
        perform(MockMvcRequestBuilders.get(CLEAR_URL))
                .andExpect(status().isOk())
                .andDo(print());

        assertEquals(0, personRepository.count());
        assertEquals(0, carRepository.count());
    }
}