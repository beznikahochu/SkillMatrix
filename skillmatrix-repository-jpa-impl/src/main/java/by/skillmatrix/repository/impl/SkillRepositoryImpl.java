package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.repository.SkillRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class SkillRepositoryImpl extends AbstractRepository<SkillEntity, Long> implements SkillRepository {

    @Override
    @Transactional
    public SkillEntity save(SkillEntity skillEntity) {
        if (skillEntity.getId() == null) {
            return create(skillEntity);
        }
        return update(skillEntity);
    }
}
