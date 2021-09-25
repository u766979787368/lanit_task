package ru.lanit.lanit_task.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@MappedSuperclass
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = NONE, getterVisibility = ANY, isGetterVisibility = NONE, setterVisibility = ANY)
public abstract class BaseEntity {

    @Id
    @NotNull
    protected Long id;

    protected BaseEntity() {
    }

    protected BaseEntity(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : Math.toIntExact(id);
    }
}