package by.skillmatrix.repository;

import by.skillmatrix.entity.SkillCategory;

import java.util.List;
import java.util.Optional;

public interface SkillCategoryRepository {
    SkillCategory save(SkillCategory schemeEntity);
    void delete(Long id);
    Optional<SkillCategory> findById(Long id);
    List<SkillCategory> findFullSkillCategoryBySchemeId(Long id);
}
