package ru.lanit.lanit_task.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Range;
import ru.lanit.lanit_task.util.validation.VendorModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "car")
public class Car extends BaseEntity {

    @NotBlank
    @NotNull
    @VendorModel
    @Column(name = "model")
    private String model;

    @NotNull
    @Range(min = 1)
    @Column(name = "horsepower")
    private Integer horsepower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    @JsonBackReference
    @NotNull
    private Person person;

    public Car() {
    }

    public Car(Long id, String model, Integer horsepower, Long ownerid) {
        super(id);
        this.model = model;
        this.horsepower = horsepower;
        setOwnerid(ownerid);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        if (id != null) {
            person = new Person();
            person.setId(id);
        }
    }
}
