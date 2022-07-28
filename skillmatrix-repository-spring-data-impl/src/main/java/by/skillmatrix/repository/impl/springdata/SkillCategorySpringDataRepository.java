package by.skillmatrix.repository.impl.springdata;

import by.skillmatrix.entity.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillCategorySpringDataRepository extends JpaRepository<SkillCategory, Long> {
    @Query(
            "SELECT DISTINCT category FROM SkillCategory category " +
            "LEFT JOIN FETCH category.skillMatrixScheme scheme " +
            "LEFT JOIN FETCH category.skills skill " +
            "WHERE scheme.id = :id " +
            "ORDER BY category.position ASC, skill.position ASC"
    )
    List<SkillCategory> findFullSkillCategoryBySchemeId(Long id);
}
