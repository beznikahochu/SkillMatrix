package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillMatrixSchemeSpringDataRepository extends JpaRepository<SkillMatrixSchemeEntity, Long> {
    @EntityGraph("scheme-with-categories-and-skill")
    Optional<SkillMatrixSchemeEntity> findFullInfoById(Long id);
}
