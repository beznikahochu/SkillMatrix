package by.skillmatrix.validation.annotation;

import by.skillmatrix.validation.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Invalid password format, password should has one upper/lower case letter and one number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
