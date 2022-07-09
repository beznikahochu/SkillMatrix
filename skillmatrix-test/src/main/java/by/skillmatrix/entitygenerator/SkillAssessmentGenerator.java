package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.SkillAssessmentEntity;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class SkillAssessmentGenerator {

    private Random random;

    public SkillAssessmentGenerator() {
        random = new Random();
    }

    public SkillAssessmentEntity generateAssessment(Long skillMatrixId, Long skillId) {
        SkillAssessmentEntity skillAssessmentEntity = new SkillAssessmentEntity();
        skillAssessmentEntity.setSkillId(skillId);
        skillAssessmentEntity.setSkillMatrixId(skillMatrixId);
        skillAssessmentEntity.setAssessment((long) random.nextInt(4));
        if(random.nextBoolean()) {
            skillAssessmentEntity.setComment("comment" + skillMatrixId + "" + skillId);
        }
        return skillAssessmentEntity;
    }
}
