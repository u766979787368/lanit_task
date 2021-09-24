package ru.lanit.lanit_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lanit.lanit_task.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
