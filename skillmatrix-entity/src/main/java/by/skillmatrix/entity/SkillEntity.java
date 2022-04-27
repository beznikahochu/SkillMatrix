package by.skillmatrix.entity;

import javax.persistence.*;

//TODO: Изменить optional и cascade
@Entity
@Table(name = "skills")
public class SkillEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "skill_category_id")
    private SkillCategoryEntity skillCategory;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "skill_matrix_scheme_id")
    private SkillMatrixSchemeEntity skillMatrixScheme;
}
