package by.skillmatrix.repository;

import by.skillmatrix.entity.Skill;

import java.util.Optional;

public interface SkillRepository {
    Skill save(Skill skill);
    void delete(Long id);
    Optional<Skill> findById(Long id);
}
