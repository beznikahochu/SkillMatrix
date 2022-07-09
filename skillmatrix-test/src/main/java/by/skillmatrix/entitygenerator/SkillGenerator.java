package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillEntity;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class SkillGenerator {

    private Faker faker;

    public SkillGenerator() {
        faker = new Faker();
    }

    public SkillEntity generateSkill(SkillCategoryEntity skillCategory) {
        SkillEntity skill = new SkillEntity();
        skill.setName(faker.job().keySkills());
        skill.setSkillCategory(skillCategory);
        return  skill;
    }
}
