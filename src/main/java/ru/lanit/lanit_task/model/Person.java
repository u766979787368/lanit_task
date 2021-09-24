package ru.lanit.lanit_task.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
public class Person extends BaseEntity {

    @NotBlank
    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthdate", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthdate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Car> cars;

}
