package by.skillmatrix.dao.impl;

import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.dao.SkillMatrixSchemeDao;
import by.skillmatrix.dao.impl.springdata.SkillMatrixSchemeSpringDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SkillMatrixSchemeDaoImpl implements SkillMatrixSchemeDao {

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
}
