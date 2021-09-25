package ru.lanit.lanit_task.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @JsonBackReference
    private Person person;

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Long getOwnerid() {
        return person.getId();
    }

    public void setOwnerid(Long id) {
        person = new Person();
        person.setId(id);
    }

}
