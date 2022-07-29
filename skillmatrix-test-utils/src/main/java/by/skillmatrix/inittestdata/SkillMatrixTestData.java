package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.Person;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.entity.SkillMatrixScheme;
import by.skillmatrix.entitygenerator.SkillMatrixGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Repository
@RequiredArgsConstructor
public class SkillMatrixTestData extends InitTestDataController<SkillMatrix> {
    private final SkillMatrixGenerator matrixGenerator;

    @Override
    @Transactional
    public void initTestData(int count) {
        Random random = new Random();
        List<SkillMatrixScheme> schemes =  entityManager
                .createQuery("FROM SkillMatrixScheme", SkillMatrixScheme.class).getResultList();
        List<Person> people =  entityManager
                .createQuery("FROM Person", Person.class).getResultList();

        for (int i = 0; i < count; i++) {
            int schemeIndex = random.nextInt(schemes.size());
            int personIndex = random.nextInt(people.size());

            SkillMatrix skillMatrix = matrixGenerator
                    .generateSkillMatrix(people.get(personIndex),schemes.get(schemeIndex));
            entityManager.persist(skillMatrix);
        }
    }
}
