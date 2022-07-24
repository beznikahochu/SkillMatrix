package by.skillmatrix.entitygenerator;

import by.skillmatrix.entity.SkillAssessment;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SkillAssessmentGenerator {

    private Random random;

    public SkillAssessmentGenerator() {
        random = new Random();
    }

    public SkillAssessment generateAssessment(Long skillMatrixId, Long skillId) {
        SkillAssessment skillAssessment = new SkillAssessment();
        skillAssessment.setSkillId(skillId);
        skillAssessment.setSkillMatrixId(skillMatrixId);
        skillAssessment.setAssessment((long) random.nextInt(4));
        if(random.nextBoolean()) {
            skillAssessment.setComment("comment" + skillMatrixId + "" + skillId);
        }
        return skillAssessment;
    }
}
