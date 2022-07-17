package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.UserAndRoleEntity;
import by.skillmatrix.entity.id.UserAndRoleId;
import by.skillmatrix.repository.UserAndRoleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserAndRoleRepositoryImpl
        extends AbstractRepository<UserAndRoleEntity, UserAndRoleId>
        implements UserAndRoleRepository
{
    @Override
    @Transactional
    public UserAndRoleEntity save(UserAndRoleEntity entity) {
        UserAndRoleId id = new UserAndRoleId();
        id.setRoleId(entity.getRoleId());
        id.setUserId(entity.getUserId());
        UserAndRoleEntity entity1 = entityManager.find(UserAndRoleEntity.class, id);
        if (entity1 == null) {
            return create(entity);
        }
        return update(entity);
    }
}
