package ru.lanit.lanit_task.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VendorModelValidator implements ConstraintValidator<VendorModel, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        String[] strings = value.split("-");
        if (strings.length < 2) return false;
        if (strings[0].equals("")) return false;
        return true;
    }
}
