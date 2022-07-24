package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.SkillMatrixScheme;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillMatrixSchemeSpringDataRepository extends JpaRepository<SkillMatrixScheme, Long> {
    @EntityGraph("scheme-with-categories")
    Optional<SkillMatrixScheme> findWithCategoriesById(Long id);
}
