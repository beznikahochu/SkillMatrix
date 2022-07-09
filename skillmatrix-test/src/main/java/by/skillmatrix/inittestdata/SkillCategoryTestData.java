package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.entitygenerator.SkillCategoryGenerator;
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
public class SkillCategoryTestData implements InitTestDataController<SkillCategoryEntity>{

    private final SkillCategoryGenerator categoryGenerator;
    private List<SkillCategoryEntity> skillCategoryEntities;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void initTestData(int count) {;
        Random random = new Random();
        List<SkillMatrixSchemeEntity> schemes =  entityManager
                .createQuery("FROM SkillMatrixSchemeEntity", SkillMatrixSchemeEntity.class).getResultList();
        skillCategoryEntities = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int schemeIndex = random.nextInt(schemes.size());
            SkillCategoryEntity category = categoryGenerator.generateSkillCategory(schemes.get(schemeIndex));
            entityManager.persist(category);
            skillCategoryEntities.add(category);
        }
    }

    @Override
    public List<SkillCategoryEntity> getInitTestData() {
        return skillCategoryEntities;
    }

    @Override
    @Transactional
    public void clear() {
        entityManager.createQuery("DELETE FROM SkillCategoryEntity").executeUpdate();
    }
}
