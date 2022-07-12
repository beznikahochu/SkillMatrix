package by.skillmatrix.entity;

import by.skillmatrix.entity.id.UserAndRoleId;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users_roles")
@IdClass(UserAndRoleId.class)
public class UserAndRoleEntity {

    @Id
    @Column(name = "user_id", updatable = false)
    private Long userId;
    @Id
    @Column(name = "role_id", updatable = false)
    private Long roleId;
}
