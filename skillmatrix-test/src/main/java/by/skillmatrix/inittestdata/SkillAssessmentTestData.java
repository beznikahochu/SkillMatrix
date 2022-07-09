package by.skillmatrix.inittestdata;

import by.skillmatrix.entity.SkillAssessmentEntity;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.entitygenerator.SkillAssessmentGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class SkillAssessmentTestData implements InitTestDataController<SkillAssessmentEntity>{
    private final SkillAssessmentGenerator assessmentGenerator;
    private List<SkillAssessmentEntity> assessments;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void initTestData(int count) {
        Random random = new Random();
        assessments = new ArrayList<>();

        List<SkillMatrixEntity> matrices =  entityManager
                .createQuery("FROM SkillMatrixEntity", SkillMatrixEntity.class).getResultList();
        List<SkillEntity> skills =  entityManager
                .createQuery("FROM SkillEntity", SkillEntity.class).getResultList();


        for (int i = 0; i < count; i++) {
            for (SkillMatrixEntity matrix: matrices) {
                for (SkillEntity skill: skills) {
                    SkillAssessmentEntity assessment = assessmentGenerator
                            .generateAssessment(matrix.getId(), skill.getId());
                    entityManager.merge(assessment);
                    assessments.add(assessment);
                }
            }
        }
    }

    @Override
    public List<SkillAssessmentEntity> getInitTestData() {
        return assessments;
    }

    @Override
    @Transactional
    public void clear() {
        entityManager.createQuery("DELETE FROM SkillAssessmentEntity").executeUpdate();
    }
}
