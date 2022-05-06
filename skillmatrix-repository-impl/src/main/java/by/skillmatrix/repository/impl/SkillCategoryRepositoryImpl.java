package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.impl.springdata.SkillCategorySpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class SkillCategoryRepositoryImpl implements SkillCategoryRepository {

    private final SkillCategorySpringDataRepository repository;

    @Override
    public SkillCategoryEntity create(SkillCategoryEntity skillCategoryEntity) {
        if (skillCategoryEntity.getId() != null) {
            throw new RuntimeException(); //TODO: Изменить на более осмысленный
        }
        return repository.save(skillCategoryEntity);
    }

    @Override
    public SkillCategoryEntity update(SkillCategoryEntity skillCategoryEntity) {
        if (skillCategoryEntity.getId() == null) {
            throw new RuntimeException(); //TODO: Изменить на более осмысленный
        }
        return repository.save(skillCategoryEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<SkillCategoryEntity> findById(Long id) {
        return repository.findById(id);
    }
}
