package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.entitygenerator.SkillGenerator;
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
public class SkillTestData implements InitTestDataController<SkillEntity>{

    private final SkillGenerator skillGenerator;
    private List<SkillEntity> skillEntities;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void initTestData(int count) {
        Random random = new Random();
        List<SkillCategoryEntity> categories =  entityManager
                .createQuery("FROM SkillCategoryEntity", SkillCategoryEntity.class).getResultList();
        skillEntities = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int categoryIndex = random.nextInt(categories.size());
            SkillEntity skill = skillGenerator.generateSkill(categories.get(categoryIndex));
            entityManager.persist(skill);
            skillEntities.add(skill);
        }
    }

    @Override
    public List<SkillEntity> getInitTestData() {
        return skillEntities;
    }

    @Override
    @Transactional
    public void clear() {
        entityManager.createQuery("DELETE FROM SkillEntity").executeUpdate();
    }
}
