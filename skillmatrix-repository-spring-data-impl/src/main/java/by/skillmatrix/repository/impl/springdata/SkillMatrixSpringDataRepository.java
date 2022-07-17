package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.SkillMatrixEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SkillMatrixSpringDataRepository extends JpaRepository<SkillMatrixEntity, Long>,
        JpaSpecificationExecutor<SkillMatrixEntity> {
    @EntityGraph("skill-matrix-with-scheme-and-user")
    Page<SkillMatrixEntity> findAll(
            Specification<SkillMatrixEntity> specification,
            Pageable pageable
    );
    @EntityGraph("skill-matrix-with-assessments")
    Optional<SkillMatrixEntity> findWithAssessmentsById(Long id);
}
