package ru.lanit.lanit_task.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class NotFutureBirthdateValidator implements ConstraintValidator<NotFutureBirthdate, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.isBefore(LocalDate.now());
    }
}
