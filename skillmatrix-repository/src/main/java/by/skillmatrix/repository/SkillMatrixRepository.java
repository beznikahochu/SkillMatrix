package by.skillmatrix.repository;

import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;

import java.util.List;
import java.util.Optional;

public interface SkillMatrixRepository {
    SkillMatrixEntity create(SkillMatrixEntity schemeEntity);
    SkillMatrixEntity update(SkillMatrixEntity schemeEntity);
    void delete(Long id);
    List<SkillMatrixEntity> findAll();
    Optional<SkillMatrixEntity> findById(Long id);
    Optional<SkillMatrixEntity> findFullInfoById(Long id);
}
