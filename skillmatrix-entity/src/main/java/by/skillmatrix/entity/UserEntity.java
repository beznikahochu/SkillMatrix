package by.skillmatrix.entity;

import by.skillmatrix.entity.enumeration.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Getter
@Setter
@FieldNameConstants
@Entity
@Table(name = "users")
@ToString
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role") //TODO Изменить роли
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;
}
