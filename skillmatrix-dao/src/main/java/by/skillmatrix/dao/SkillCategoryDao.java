package by.skillmatrix.dao;

import by.skillmatrix.entity.SkillCategoryEntity;

import java.util.List;
import java.util.Optional;

public interface SkillCategoryDao {
    SkillCategoryEntity save(SkillCategoryEntity schemeEntity);
    void delete(Long id);
    Optional<SkillCategoryEntity> findById(Long id);
    List<SkillCategoryEntity> findFullSkillCategoryBySchemeId(Long id);
}
