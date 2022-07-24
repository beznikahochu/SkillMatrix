package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillAssessment;
import by.skillmatrix.repository.SkillAssessmentRepository;
import by.skillmatrix.repository.impl.springdata.SkillAssessmentSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class SkillAssessmentRepositoryImpl implements SkillAssessmentRepository {

    private final SkillAssessmentSpringDataRepository repository;

    @Override
    public SkillAssessment save(SkillAssessment skillAssessment) {
        return repository.save(skillAssessment);
    }
}
