package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SkillCategoryGenerator {

    private Faker faker;
    private Random random;

    public SkillCategoryGenerator() {
        faker = new Faker();
        random = new Random();
    }

    public SkillCategoryEntity generateSkillCategory(SkillMatrixSchemeEntity scheme) {
        SkillCategoryEntity skillCategory = new SkillCategoryEntity();
        skillCategory.setName(faker.funnyName().name());
        skillCategory.setSkillMatrixScheme(scheme);
        skillCategory.setPosition((long) random.nextInt(4));
        return skillCategory;
    }
}
