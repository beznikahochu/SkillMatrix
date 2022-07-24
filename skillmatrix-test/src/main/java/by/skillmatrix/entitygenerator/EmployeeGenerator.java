package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.Employee;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class EmployeeGenerator {

    private Faker faker;

    public EmployeeGenerator() {
        faker = new Faker();
    }

    public Employee generateEmployee() {
        Employee employee = new Employee();
        employee.setFirstName(faker.name().firstName());
        employee.setLastName(faker.name().lastName());
        return employee;
    }
}
