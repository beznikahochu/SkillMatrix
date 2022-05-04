package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.SkillMatrixEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillMatrixSpringDataRepository extends JpaRepository<SkillMatrixEntity, Long> {
    @EntityGraph("skill-matrix-with-assessments")
    Optional<SkillMatrixEntity> findWithAssessmentsById(Long id);
}
