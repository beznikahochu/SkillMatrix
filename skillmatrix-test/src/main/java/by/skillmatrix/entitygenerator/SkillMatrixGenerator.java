package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class SkillMatrixGenerator {

    private Faker faker;

    public SkillMatrixGenerator() {
        faker = new Faker();
    }

    public SkillMatrixEntity generateSkillMatrix(EmployeeEntity employee, SkillMatrixSchemeEntity scheme) {
        SkillMatrixEntity skillMatrix = new SkillMatrixEntity();
        skillMatrix.setName(faker.funnyName().name());
        skillMatrix.setEmployee(employee);
        skillMatrix.setSkillMatrixScheme(scheme);

        Date creationDate = faker.date().past(5, TimeUnit.DAYS);
        LocalDateTime creationLocalDateTime = LocalDateTime.ofInstant(creationDate.toInstant(), ZoneId.systemDefault());

        skillMatrix.setCreationDate(creationLocalDateTime);

        return skillMatrix;
    }
}
