package by.skillmatrix.repository;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;

import java.util.Optional;

public interface SkillCategoryRepository {
    SkillCategoryEntity save(SkillCategoryEntity schemeEntity);
    void delete(Long id);
    Optional<SkillCategoryEntity> findById(Long id);
}
