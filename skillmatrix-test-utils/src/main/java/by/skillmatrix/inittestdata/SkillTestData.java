package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.Skill;
import by.skillmatrix.entity.SkillCategory;
import by.skillmatrix.entitygenerator.SkillGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SkillTestData extends InitTestDataController<Skill>{

    private final SkillGenerator skillGenerator;

    @Override
    @Transactional
    public void initTestData(int count) {
        Random random = new Random();
        List<SkillCategory> categories =  entityManager
                .createQuery("FROM SkillCategory", SkillCategory.class).getResultList();

        for (int i = 0; i < count; i++) {
            int categoryIndex = random.nextInt(categories.size());
            Skill skill = skillGenerator.generateSkill(categories.get(categoryIndex));
            entityManager.persist(skill);
        }
    }
}
