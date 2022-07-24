package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.SkillAssessment;
import by.skillmatrix.entity.id.SkillAssessmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillAssessmentSpringDataRepository extends JpaRepository<SkillAssessment, SkillAssessmentId> {

}
