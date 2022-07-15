package by.skillmatrix.dao;

import by.skillmatrix.entity.SkillMatrixSchemeEntity;

import java.util.List;
import java.util.Optional;

public interface SkillMatrixSchemeDao {
    SkillMatrixSchemeEntity save(SkillMatrixSchemeEntity schemeEntity);
    void delete(Long id);
    List<SkillMatrixSchemeEntity> findAll();
    Optional<SkillMatrixSchemeEntity> findById(Long id);
}
