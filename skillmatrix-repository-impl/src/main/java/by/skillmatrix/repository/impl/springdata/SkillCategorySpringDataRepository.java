package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillCategorySpringDataRepository extends JpaRepository<SkillCategoryEntity, Long> {
}
