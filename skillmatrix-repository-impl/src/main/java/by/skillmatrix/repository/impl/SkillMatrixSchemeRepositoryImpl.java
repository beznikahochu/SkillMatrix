package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.repository.impl.springdata.SkillMatrixSchemeSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SkillMatrixSchemeRepositoryImpl implements SkillMatrixSchemeRepository {

    private final SkillMatrixSchemeSpringDataRepository schemeRepository;

    @Override
    public SkillMatrixSchemeEntity save(SkillMatrixSchemeEntity schemeEntity) {
        return schemeRepository.save(schemeEntity);
    }

    @Override
    public void delete(Long id) {
        schemeRepository.deleteById(id);
    }

    @Override
    public List<SkillMatrixSchemeEntity> findAll() {
        return schemeRepository.findAll();
    }

    @Override
    public Optional<SkillMatrixSchemeEntity> findById(Long id) {
        return schemeRepository.findById(id);
    }

    @Override
    public Optional<SkillMatrixSchemeEntity> findWithCategoriesById(Long id) {
        return schemeRepository.findWithCategoriesById(id);
    }
}
