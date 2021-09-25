package ru.lanit.lanit_task.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = NotFutureBirthdateValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface NotFutureBirthdate {
    String message() default "date must be from the past";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
