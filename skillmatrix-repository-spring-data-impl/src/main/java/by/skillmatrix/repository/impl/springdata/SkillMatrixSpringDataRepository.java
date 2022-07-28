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

    @EntityGraph("skill-matrix-with-scheme-and-person")
    Optional<SkillMatrix> findById(Long id);
    @EntityGraph("skill-matrix-with-scheme-and-person")
    Page<SkillMatrix> findAll(
            Specification<SkillMatrix> specification,
            Pageable pageable
    );
    @EntityGraph("skill-matrix-with-assessments")
    Optional<SkillMatrix> findWithAssessmentsById(Long id);

    @Modifying
    @Query(
            value = "UPDATE SkillMatrix SET avgAssessment = " +
                    "(SELECT AVG(assessment) FROM SkillAssessment WHERE skillMatrixId = :id) " +
                    "WHERE id = :id"
    )
    int calkAvgAssessment(Long id);
}
