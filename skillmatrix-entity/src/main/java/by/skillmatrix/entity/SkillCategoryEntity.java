package by.skillmatrix.entity;

import javax.persistence.*;

@Entity
@Table(name = "skill_categories")
public class SkillCategoryEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;
}
