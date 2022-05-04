package by.skillmatrix.repository;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;

import java.util.Optional;

public interface SkillCategoryRepository {
    SkillCategoryEntity create(SkillCategoryEntity schemeEntity);
    SkillCategoryEntity update(SkillCategoryEntity schemeEntity);
    void delete(Long id);
    Optional<SkillCategoryEntity> findById(Long id);
}
