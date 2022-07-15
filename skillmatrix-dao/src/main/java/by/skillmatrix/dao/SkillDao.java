package by.skillmatrix.dao;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillEntity;

import java.util.List;
import java.util.Optional;

public interface SkillDao {
    SkillEntity save(SkillEntity skillEntity);
    void delete(Long id);
    Optional<SkillEntity> findById(Long id);
}
