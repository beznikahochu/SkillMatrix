package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.Employee;
import by.skillmatrix.entitygenerator.EmployeeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class EmployeeTestData extends InitTestDataController<Employee>{

    private final EmployeeGenerator employeeGenerator;

    @Override
    @Transactional
    public void initTestData(int count) {
        Stream.generate(employeeGenerator::generateEmployee).limit(count)
                .forEach(employee -> {
                    entityManager.persist(employee);
                });
    }
}
