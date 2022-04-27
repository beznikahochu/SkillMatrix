package by.skillmatrix.entity;

import javax.persistence.*;
import java.util.List;

//TODO: Изменить cascade
@Entity
@Table(name = "skill_matrix_schemes")
public class SkillMatrixSchemeEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany (fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "skill_matrix_scheme_id")
    private List<SkillCategoryEntity> skillCategories;

    @OneToMany (fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "skill_matrix_scheme_id")
    private List<SkillEntity> skills;
}
