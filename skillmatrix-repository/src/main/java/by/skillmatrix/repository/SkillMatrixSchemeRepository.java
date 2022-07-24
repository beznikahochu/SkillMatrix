package by.skillmatrix.repository;

import by.skillmatrix.entity.SkillMatrixScheme;

import java.util.List;
import java.util.Optional;

public interface SkillMatrixSchemeRepository {
    SkillMatrixScheme save(SkillMatrixScheme schemeEntity);
    void delete(Long id);
    List<SkillMatrixScheme> findAll();
    Optional<SkillMatrixScheme> findById(Long id);
}
