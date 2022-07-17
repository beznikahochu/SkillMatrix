package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.repository.SkillRepository;
import by.skillmatrix.repository.impl.springdata.SkillSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class SkillRepositoryImpl implements SkillRepository {

    private final SkillSpringDataRepository repository;

    @Override
    public SkillEntity save(SkillEntity skillEntity) {
        return repository.save(skillEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<SkillEntity> findById(Long id) {
        return repository.findById(id);
    }
}
