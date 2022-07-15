package by.skillmatrix.dao.impl;

import by.skillmatrix.dao.UserAndRoleDao;
import by.skillmatrix.entity.SkillAssessmentEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.entity.UserAndRoleEntity;
import by.skillmatrix.entity.id.UserAndRoleId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserAndRoleDaoImpl
        extends AbstractDao<UserAndRoleEntity, UserAndRoleId>
        implements UserAndRoleDao
{
    @Override
    public UserAndRoleEntity save(UserAndRoleEntity entity) {
        UserAndRoleId id = new UserAndRoleId();
        id.setRoleId(entity.getRoleId());
        id.setUserId(entity.getUserId());
        UserAndRoleEntity entity1 = entityManager.find(UserAndRoleEntity.class, id);
        if (entity1 == null) {
            return create(entity);
        }
        return update(entity);//TODO НАДО ХОРОШЕНЬКО ЭТО ПРОВЕРИТЬ
    }
}
