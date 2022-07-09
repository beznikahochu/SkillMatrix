package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.entitygenerator.EmployeeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class EmployeeTestData implements InitTestDataController<EmployeeEntity>{

    private final EmployeeGenerator employeeGenerator;
    private List<EmployeeEntity> employeeEntities;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void initTestData(int count) {
        employeeEntities = Stream.generate(employeeGenerator::generateEmployee).limit(count)
                .peek(employee -> {
                    entityManager.persist(employee);
                }).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeEntity> getInitTestData() {
        return employeeEntities;
    }

    @Override
    @Transactional
    public void clear() {
        entityManager.createQuery("DELETE FROM EmployeeEntity").executeUpdate();
    }
}
