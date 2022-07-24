package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.SkillMatrix;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillMatrixSpringDataRepository extends JpaRepository<SkillMatrix, Long>,
        JpaSpecificationExecutor<SkillMatrix> {
    @EntityGraph("skill-matrix-with-scheme-and-user")
    Page<SkillMatrix> findAll(
            Specification<SkillMatrix> specification,
            Pageable pageable
    );
    @EntityGraph("skill-matrix-with-assessments")
    Optional<SkillMatrix> findWithAssessmentsById(Long id);

    @Modifying
    @Query(
            value = "UPDATE skill_matrices SET avg_assessment = " +
                    "(SELECT AVG(assessment) FROM skill_assessments WHERE skill_matrix_id = :id) " +
                    "WHERE id = :id",
            nativeQuery = true
    )
    int calkAvgAssessment(Long id);
}
