package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.impl.SkillMatrixApplication;
import by.skillmatrix.inittestdata.InitTestDataController;
import by.skillmatrix.repository.EmployeeRepository;
import by.skillmatrix.testcontainer.PostgresContainerInitializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SkillMatrixApplication.class)
@ContextConfiguration(initializers = PostgresContainerInitializer.class)
public class EmployeeRepositoryImplTest {

    private static final int EMPLOYEE_COUNT = 5;
    private static InitTestDataController<EmployeeEntity> employeeTestData;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeAll
    static void initData(@Autowired InitTestDataController<EmployeeEntity> employeeTestData) {
        EmployeeRepositoryImplTest.employeeTestData = employeeTestData;
        EmployeeRepositoryImplTest.employeeTestData.initTestData(EMPLOYEE_COUNT);
    }

    @AfterAll
    static void clear() {
        employeeTestData.clear();
    }

    @Test
    void findByIdTest() {
        Long id = employeeTestData.getInitTestData().get(1).getId();
        EmployeeEntity result = employeeRepository.findById(id).get();
        assertEquals(id, result.getId());
    }

    @Test
    void whenFindByIdReturnEmptyTest() {
        Long id = -1l;
        Optional<EmployeeEntity> result = employeeRepository.findById(id);
        assertTrue(result.isEmpty());
    }
}
