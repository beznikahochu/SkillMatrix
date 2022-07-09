package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class SkillCategoryGenerator {

    private Faker faker;

    public SkillCategoryGenerator() {
        faker = new Faker();
    }

    public SkillCategoryEntity generateSkillCategory(SkillMatrixSchemeEntity scheme) {
        SkillCategoryEntity skillCategory = new SkillCategoryEntity();
        skillCategory.setName(faker.funnyName().name());
        skillCategory.setSkillMatrixScheme(scheme);
        return skillCategory;
    }
}
