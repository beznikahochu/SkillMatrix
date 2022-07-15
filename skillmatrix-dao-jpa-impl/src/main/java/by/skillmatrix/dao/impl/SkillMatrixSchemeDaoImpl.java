package by.skillmatrix.dao.impl;

import by.skillmatrix.dao.SkillMatrixSchemeDao;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import org.springframework.stereotype.Repository;

@Repository
public class SkillMatrixSchemeDaoImpl
        extends AbstractDao<SkillMatrixSchemeEntity, Long>
        implements SkillMatrixSchemeDao
{
    @Override
    public SkillMatrixSchemeEntity save(SkillMatrixSchemeEntity schemeEntity) {
        if (schemeEntity.getId() == null) {
            return create(schemeEntity);
        }
        return update(schemeEntity);
    }
}
