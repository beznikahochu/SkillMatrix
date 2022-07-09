package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.EmployeeEntity;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class EmployeeGenerator {

    private Faker faker;

    public EmployeeGenerator() {
        faker = new Faker();
    }

    public EmployeeEntity generateEmployee() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setFirstName(faker.name().firstName());
        employee.setLastName(faker.name().lastName());
        return employee;
    }
}
