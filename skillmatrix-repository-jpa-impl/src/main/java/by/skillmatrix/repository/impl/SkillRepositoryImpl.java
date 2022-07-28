package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.Skill;
import by.skillmatrix.repository.SkillRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class SkillRepositoryImpl extends AbstractRepository<Skill, Long> implements SkillRepository {

    @Override
    @Transactional
    public Skill save(Skill skillEntity) {
        if (skillEntity.getId() == null) {
            return create(skillEntity);
        }
        return update(skillEntity);
    }
}
