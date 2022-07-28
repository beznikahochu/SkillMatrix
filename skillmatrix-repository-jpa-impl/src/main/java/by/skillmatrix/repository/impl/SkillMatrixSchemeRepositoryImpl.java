package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillMatrixScheme;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SkillMatrixSchemeRepositoryImpl
        extends AbstractRepository<SkillMatrixScheme, Long>
        implements SkillMatrixSchemeRepository
{
    @Override
    @Transactional
    public SkillMatrixScheme save(SkillMatrixScheme schemeEntity) {
        if (schemeEntity.getId() == null) {
            return create(schemeEntity);
        }
        return update(schemeEntity);
    }
}
