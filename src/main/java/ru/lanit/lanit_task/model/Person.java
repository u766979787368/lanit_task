package ru.lanit.lanit_task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;
import ru.lanit.lanit_task.util.validation.NotFutureBirthdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "person")
public class Person extends BaseEntity {

    @NotBlank
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthdate", nullable = false)
    @NotNull
    @NotFutureBirthdate
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthdate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    @JsonManagedReference
    private List<Car> cars;

    public Person () {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public List<Car> getCars() {
        return cars;
    }
}
