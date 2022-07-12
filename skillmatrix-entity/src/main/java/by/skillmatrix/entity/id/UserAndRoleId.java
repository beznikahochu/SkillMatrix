package by.skillmatrix.entity.id;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAndRoleId implements Serializable {
    private Long userId;
    private Long roleId;
}
