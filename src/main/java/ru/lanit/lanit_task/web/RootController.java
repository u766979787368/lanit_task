package ru.lanit.lanit_task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.lanit_task.model.Statistics;
import ru.lanit.lanit_task.repository.CarRepository;
import ru.lanit.lanit_task.repository.PersonRepository;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/statistics")
    @ResponseStatus(HttpStatus.OK)
    public Statistics getStatistics() {

        Statistics statistics = new Statistics();
        statistics.setPersoncount(personRepository.count());
        statistics.setCarcount(carRepository.count());
        statistics.setUniquevendorcount(carRepository.countModel());

        return statistics;
    }

    @GetMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clear() {
        personRepository.deleteAll();
        carRepository.deleteAll();
    }

}
