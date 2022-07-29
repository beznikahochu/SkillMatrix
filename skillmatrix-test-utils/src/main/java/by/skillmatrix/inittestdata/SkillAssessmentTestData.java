package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.SkillAssessment;
import by.skillmatrix.entity.Skill;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.entitygenerator.SkillAssessmentGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Repository
@RequiredArgsConstructor
public class SkillAssessmentTestData extends InitTestDataController<SkillAssessment> {
    private final SkillAssessmentGenerator assessmentGenerator;

    @Override
    @Transactional
    public void initTestData(int count) {
        Random random = new Random();

        List<SkillMatrix> matrices =  entityManager
                .createQuery("FROM SkillMatrix", SkillMatrix.class).getResultList();
        List<Skill> skills =  entityManager
                .createQuery("FROM Skill", Skill.class).getResultList();


        for (int i = 0; i < count; i++) {
            for (SkillMatrix matrix: matrices) {
                for (Skill skill: skills) {
                    SkillAssessment assessment = assessmentGenerator
                            .generateAssessment(matrix.getId(), skill.getId());
                    entityManager.merge(assessment);
                }
            }
        }
    }
}
