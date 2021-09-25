package ru.lanit.lanit_task.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lanit.lanit_task.model.Person;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @EntityGraph(attributePaths = {"cars"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT p FROM Person p WHERE p.id=?1")
    Person getWithCars(Long id);

    boolean existsPersonByBirthdateBeforeAndId(@NotNull LocalDate birthdate, @NotNull Long id);
}
