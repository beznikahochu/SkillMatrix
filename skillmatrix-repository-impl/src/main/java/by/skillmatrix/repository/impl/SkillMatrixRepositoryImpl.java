package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.impl.springdata.SkillMatrixSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SkillMatrixRepositoryImpl implements SkillMatrixRepository {

    private final SkillMatrixSpringDataRepository skillMatrixRepository;

    @Override
    public SkillMatrixEntity save(SkillMatrixEntity skillMatrixEntity) {
        return skillMatrixRepository.save(skillMatrixEntity);
    }

    @Override
    public void delete(Long id) {
        skillMatrixRepository.deleteById(id);
    }

    @Override
    public List<SkillMatrixEntity> findAll() {
        return skillMatrixRepository.findAll();
    }

    @Override
    public Optional<SkillMatrixEntity> findById(Long id) {
        return skillMatrixRepository.findById(id);
    }

    @Override
    public Optional<SkillMatrixEntity> findWithAssessmentsById(Long id) {
        return skillMatrixRepository.findWithAssessmentsById(id);
    }
}
