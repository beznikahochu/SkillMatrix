package by.skillmatrix.dao.impl;

import by.skillmatrix.entity.SkillAssessmentEntity;
import by.skillmatrix.dao.SkillAssessmentDao;
import by.skillmatrix.dao.impl.springdata.SkillAssessmentSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class SkillAssessmentDaoImpl implements SkillAssessmentDao {

    private final SkillAssessmentSpringDataRepository repository;

    @Override
    public SkillAssessmentEntity save(SkillAssessmentEntity skillAssessment) {
        return repository.save(skillAssessment);
    }
}
