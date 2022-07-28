package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.SkillMatrixScheme;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class SkillMatrixSchemeGenerator {

    private Faker faker;

    public SkillMatrixSchemeGenerator() {
        faker = new Faker();
    }

    public SkillMatrixScheme generateScheme() {
        SkillMatrixScheme scheme = new SkillMatrixScheme();
        scheme.setName(faker.job().position());
        return scheme;
    }
}
