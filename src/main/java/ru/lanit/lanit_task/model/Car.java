package ru.lanit.lanit_task.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "car")
public class Car extends BaseEntity {

    @NotBlank
    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "model")
    private String model;

    @NotNull
    @Column(name = "horsepower")
    private Integer horsepower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

}
