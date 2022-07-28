package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.Skill;
import by.skillmatrix.entity.SkillCategory;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SkillGenerator {

    private Faker faker;
    private Random random;

    public SkillGenerator() {
        faker = new Faker();
        random = new Random();
    }

    public Skill generateSkill(SkillCategory skillCategory) {
        Skill skill = new Skill();
        skill.setName(faker.job().keySkills());
        skill.setSkillCategory(skillCategory);
        skill.setPosition((long) random.nextInt(4));
        return  skill;
    }
}
