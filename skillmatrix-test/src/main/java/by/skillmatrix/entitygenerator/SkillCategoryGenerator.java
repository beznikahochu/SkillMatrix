package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.SkillCategory;
import by.skillmatrix.entity.SkillMatrixScheme;
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

    public SkillCategory generateSkillCategory(SkillMatrixScheme scheme) {
        SkillCategory skillCategory = new SkillCategory();
        skillCategory.setName(faker.funnyName().name());
        skillCategory.setSkillMatrixScheme(scheme);
        skillCategory.setPosition((long) random.nextInt(4));
        return skillCategory;
    }
}
