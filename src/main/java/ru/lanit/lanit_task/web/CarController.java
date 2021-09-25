package ru.lanit.lanit_task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.lanit.lanit_task.model.Car;
import ru.lanit.lanit_task.repository.CarRepository;
import ru.lanit.lanit_task.repository.PersonRepository;
import ru.lanit.lanit_task.util.exception.ExistException;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    @Autowired
    private CarRepository repository;

    @Autowired
    private PersonRepository personRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void create(@Valid @RequestBody Car car) {
        if (repository.existsById(car.getId())) {
            throw new ExistException("Car with id " + car.getId() + " already exist");
        }
        if (personRepository.existsPersonByBirthdateBeforeAndId(LocalDate.now().minusYears(18), car.getPerson().getId())) {
            repository.save(car);
        } else {
            throw new ExistException("Person with id " + car.getPerson().getId() + " doesn't exist or he is under the age of 18");
        }
    }
}
