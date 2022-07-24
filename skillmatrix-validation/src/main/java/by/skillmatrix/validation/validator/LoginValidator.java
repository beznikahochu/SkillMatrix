package by.skillmatrix.validation.validator;

import by.skillmatrix.validation.annotation.LoginConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<LoginConstraint, String> {

    private static final String LOGIN_PATTERN = "[a-zA-Z0-9]{5,20}";

    @Override
    public void initialize(LoginConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        if (login == null) {
            return true;
        }
        return login.matches(LOGIN_PATTERN);
    }
}
