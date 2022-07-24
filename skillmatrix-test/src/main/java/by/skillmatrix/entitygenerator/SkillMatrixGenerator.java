package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.Employee;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.entity.SkillMatrixScheme;
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

    public SkillMatrix generateSkillMatrix(Employee employee, SkillMatrixScheme scheme) {
        SkillMatrix skillMatrix = new SkillMatrix();
        skillMatrix.setName(faker.funnyName().name());
        skillMatrix.setEmployee(employee);
        skillMatrix.setSkillMatrixScheme(scheme);

        Date creationDate = faker.date().past(5, TimeUnit.DAYS);
        LocalDateTime creationLocalDateTime = LocalDateTime.ofInstant(creationDate.toInstant(), ZoneId.systemDefault());

        skillMatrix.setCreationDate(creationLocalDateTime.toLocalDate());
        skillMatrix.setCreationTime(creationLocalDateTime.toLocalTime());

        return skillMatrix;
    }
}
