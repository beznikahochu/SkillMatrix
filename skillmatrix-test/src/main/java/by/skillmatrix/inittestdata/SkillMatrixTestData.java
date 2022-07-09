package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.entitygenerator.SkillMatrixGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SkillMatrixTestData implements InitTestDataController<SkillMatrixEntity>{
    private final SkillMatrixGenerator matrixGenerator;
    private List<SkillMatrixEntity> matrices;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void initTestData(int count) {
        Random random = new Random();
        List<SkillMatrixSchemeEntity> schemes =  entityManager
                .createQuery("FROM SkillMatrixSchemeEntity", SkillMatrixSchemeEntity.class).getResultList();
        List<EmployeeEntity> employees =  entityManager
                .createQuery("FROM EmployeeEntity", EmployeeEntity.class).getResultList();
        matrices = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int schemeIndex = random.nextInt(schemes.size());
            int employeeIndex = random.nextInt(employees.size());

            SkillMatrixEntity skillMatrix = matrixGenerator
                    .generateSkillMatrix(employees.get(employeeIndex),schemes.get(schemeIndex));
            entityManager.persist(skillMatrix);
            matrices.add(skillMatrix);
        }
    }

    @Override
    public List<SkillMatrixEntity> getInitTestData() {
        return matrices;
    }

    @Override
    @Transactional
    public void clear() {
        entityManager.createQuery("DELETE FROM SkillMatrixEntity").executeUpdate();
    }
}
