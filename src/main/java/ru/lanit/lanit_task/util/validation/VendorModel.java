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
@Constraint(validatedBy = VendorModelValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface VendorModel {
    String message() default "model must be vendor-model form";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
