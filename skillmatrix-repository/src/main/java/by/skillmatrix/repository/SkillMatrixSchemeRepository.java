package by.skillmatrix.repository;

import by.skillmatrix.entity.SkillMatrixSchemeEntity;

import java.util.List;
import java.util.Optional;

public interface SkillMatrixSchemeRepository {
    SkillMatrixSchemeEntity save(SkillMatrixSchemeEntity schemeEntity);
    void delete(Long id);
    List<SkillMatrixSchemeEntity> findAll();
    Optional<SkillMatrixSchemeEntity> findById(Long id);
}
