package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class SkillMatrixSchemeGenerator {

    private Faker faker;

    public SkillMatrixSchemeGenerator() {
        faker = new Faker();
    }

    public SkillMatrixSchemeEntity generateScheme() {
        SkillMatrixSchemeEntity scheme = new SkillMatrixSchemeEntity();
        scheme.setName(faker.job().position());
        return scheme;
    }
}
