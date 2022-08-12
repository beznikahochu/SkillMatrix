package by.skillmatrix.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@NamedEntityGraph(
        name = "user-with-roles",
        attributeNodes = {
                @NamedAttributeNode("roles")
        }
)
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true, updatable = false)
    private String login;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "password", nullable = false)
    private String password;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="person_id", unique = true, updatable = false, insertable = false)
    private Person person;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name="person_id", unique = true)
    private Long personId;
}
