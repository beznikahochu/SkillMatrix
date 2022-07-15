package by.skillmatrix.dao.impl.springdata;

import by.skillmatrix.entity.SkillAssessmentEntity;
import by.skillmatrix.entity.id.SkillAssessmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillAssessmentSpringDataRepository extends JpaRepository<SkillAssessmentEntity, SkillAssessmentId> {

}
