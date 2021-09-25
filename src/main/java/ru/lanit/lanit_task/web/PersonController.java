package ru.lanit.lanit_task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.lanit.lanit_task.model.Person;
import ru.lanit.lanit_task.repository.PersonRepository;

import javax.validation.Valid;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private PersonRepository repository;


    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void create(@Valid @RequestBody Person person) {
        repository.save(person);
    }

    @GetMapping("/personwithcars")
    public Person getWithCars(@RequestParam Long personid) {
        Person person = repository.getWithCars(personid);
        return person;
    }
}
