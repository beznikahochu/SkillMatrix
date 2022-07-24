package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillMatrixScheme;
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
    public SkillMatrixScheme save(SkillMatrixScheme schemeEntity) {
        return schemeRepository.save(schemeEntity);
    }

    @Override
    public void delete(Long id) {
        schemeRepository.deleteById(id);
    }

    @Override
    public List<SkillMatrixScheme> findAll() {
        return schemeRepository.findAll();
    }

    @Override
    public Optional<SkillMatrixScheme> findById(Long id) {
        return schemeRepository.findById(id);
    }
}
