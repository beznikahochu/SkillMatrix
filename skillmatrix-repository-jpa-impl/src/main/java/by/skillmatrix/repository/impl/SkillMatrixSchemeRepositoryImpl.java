package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SkillMatrixSchemeRepositoryImpl
        extends AbstractRepository<SkillMatrixSchemeEntity, Long>
        implements SkillMatrixSchemeRepository
{
    @Override
    @Transactional
    public SkillMatrixSchemeEntity save(SkillMatrixSchemeEntity schemeEntity) {
        if (schemeEntity.getId() == null) {
            return create(schemeEntity);
        }
        return update(schemeEntity);
    }
}
