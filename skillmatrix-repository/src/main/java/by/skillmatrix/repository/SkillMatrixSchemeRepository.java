package by.skillmatrix.repository;

import by.skillmatrix.entity.SkillMatrixSchemeEntity;

import java.util.List;
import java.util.Optional;

public interface SkillMatrixSchemeRepository {
    SkillMatrixSchemeEntity create(SkillMatrixSchemeEntity schemeEntity);
    SkillMatrixSchemeEntity update(SkillMatrixSchemeEntity schemeEntity);
    void delete(Long id);
    List<SkillMatrixSchemeEntity> findAll();
    Optional<SkillMatrixSchemeEntity> findById(Long id);
    Optional<SkillMatrixSchemeEntity> findWithCategoriesById(Long id);
}
