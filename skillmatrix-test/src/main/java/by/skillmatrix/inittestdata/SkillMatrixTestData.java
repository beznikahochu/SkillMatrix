package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.Employee;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.entity.SkillMatrixScheme;
import by.skillmatrix.entitygenerator.SkillMatrixGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SkillMatrixTestData extends InitTestDataController<SkillMatrix>{
    private final SkillMatrixGenerator matrixGenerator;

    @Override
    @Transactional
    public void initTestData(int count) {
        Random random = new Random();
        List<SkillMatrixScheme> schemes =  entityManager
                .createQuery("FROM SkillMatrixSchemeEntity", SkillMatrixScheme.class).getResultList();
        List<Employee> employees =  entityManager
                .createQuery("FROM EmployeeEntity", Employee.class).getResultList();

        for (int i = 0; i < count; i++) {
            int schemeIndex = random.nextInt(schemes.size());
            int employeeIndex = random.nextInt(employees.size());

            SkillMatrix skillMatrix = matrixGenerator
                    .generateSkillMatrix(employees.get(employeeIndex),schemes.get(schemeIndex));
            entityManager.persist(skillMatrix);
        }
    }
}
