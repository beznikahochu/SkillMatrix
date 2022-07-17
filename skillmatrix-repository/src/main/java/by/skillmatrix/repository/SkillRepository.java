package by.skillmatrix.repository;

import by.skillmatrix.entity.SkillEntity;

import java.util.Optional;

public interface SkillRepository {
    SkillEntity save(SkillEntity skillEntity);
    void delete(Long id);
    Optional<SkillEntity> findById(Long id);
}
