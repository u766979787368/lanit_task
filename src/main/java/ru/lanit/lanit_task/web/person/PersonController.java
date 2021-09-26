package ru.lanit.lanit_task.web.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.lanit.lanit_task.model.Person;
import ru.lanit.lanit_task.repository.PersonRepository;
import ru.lanit.lanit_task.util.exception.ExistException;
import ru.lanit.lanit_task.util.exception.NotFoundException;

import javax.validation.Valid;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private PersonRepository repository;


    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void create(@Valid @RequestBody Person person) {
        if (repository.existsById(person.getId())) {
            throw new ExistException("Person with id " + person.getId() + " already exist");
        } else {
            repository.save(person);
        }
    }

    @GetMapping("/personwithcars")
    public Person getWithCars(@RequestParam Long personid) {
        if (repository.existsById(personid)) {
            return repository.getWithCars(personid);
        } else {
            throw new NotFoundException("Person with id " + personid + " doesn't exist");
        }
    }
}
