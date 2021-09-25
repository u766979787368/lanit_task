package ru.lanit.lanit_task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.lanit.lanit_task.model.Car;
import ru.lanit.lanit_task.repository.CarRepository;

@RestController
@RequestMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    @Autowired
    private CarRepository repository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody Car car) {
        repository.save(car);
    }

}
