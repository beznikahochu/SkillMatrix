package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.SkillCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillCategorySpringDataRepository extends JpaRepository<SkillCategoryEntity, Long> {
    @Query(
            "SELECT DISTINCT category FROM SkillCategoryEntity category " +
            "LEFT JOIN FETCH category.skillMatrixScheme scheme " +
            "LEFT JOIN FETCH category.skills skill " +
            "WHERE scheme.id = :id"
    )
    List<SkillCategoryEntity> findFullSkillCategoryBySchemeId(Long id);
}
