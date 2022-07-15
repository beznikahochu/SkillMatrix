package by.skillmatrix.dao.impl;

import by.skillmatrix.dao.SkillDao;
import by.skillmatrix.entity.SkillEntity;
import org.springframework.stereotype.Repository;


@Repository
public class SkillDaoImpl  extends AbstractDao<SkillEntity, Long> implements SkillDao {

    @Override
    public SkillEntity save(SkillEntity skillEntity) {
        if (skillEntity.getId() == null) {
            return create(skillEntity);
        }
        return update(skillEntity);
    }
}
