package by.skillmatrix.repository.impl;

import by.skillmatrix.entity.UserAndRole;
import by.skillmatrix.entity.id.UserAndRoleId;
import by.skillmatrix.repository.UserAndRoleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserAndRoleRepositoryImpl
        extends AbstractRepository<UserAndRole, UserAndRoleId>
        implements UserAndRoleRepository
{
    @Override
    @Transactional
    public UserAndRole save(UserAndRole entity) {
        UserAndRoleId id = new UserAndRoleId();
        id.setRoleId(entity.getRoleId());
        id.setUserId(entity.getUserId());
        UserAndRole entity1 = entityManager.find(UserAndRole.class, id);
        if (entity1 == null) {
            return create(entity);
        }
        return update(entity);
    }
}
