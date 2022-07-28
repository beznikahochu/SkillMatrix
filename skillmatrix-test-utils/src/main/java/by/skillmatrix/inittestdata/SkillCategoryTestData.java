package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.SkillCategory;
import by.skillmatrix.entity.SkillMatrixScheme;
import by.skillmatrix.entitygenerator.SkillCategoryGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SkillCategoryTestData extends InitTestDataController<SkillCategory>{

    private final SkillCategoryGenerator categoryGenerator;

    @Override
    @Transactional
    public void initTestData(int count) {;
        Random random = new Random();
        List<SkillMatrixScheme> schemes =  entityManager
                .createQuery("FROM SkillMatrixScheme", SkillMatrixScheme.class).getResultList();

        for (int i = 0; i < count; i++) {
            int schemeIndex = random.nextInt(schemes.size());
            SkillCategory category = categoryGenerator.generateSkillCategory(schemes.get(schemeIndex));
            entityManager.persist(category);
        }
    }
}
