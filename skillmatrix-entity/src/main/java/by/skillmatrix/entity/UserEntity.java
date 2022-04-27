package by.skillmatrix.entity;

import by.skillmatrix.entity.enumeration.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "login")
    private String login;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;
}
