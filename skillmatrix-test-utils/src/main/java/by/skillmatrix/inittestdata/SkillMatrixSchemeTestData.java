package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.SkillMatrixScheme;
import by.skillmatrix.entitygenerator.SkillMatrixSchemeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class SkillMatrixSchemeTestData extends InitTestDataController<SkillMatrixScheme> {

    private final SkillMatrixSchemeGenerator skillMatrixSchemeGenerator;

    @Override
    @Transactional
    public void initTestData(int count) {
        for (int i = 0; i < count; i++) {
            entityManager.persist(skillMatrixSchemeGenerator.generateScheme());
        }
    }
}
