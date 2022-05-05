package by.skillmatrix.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "skill_categories")
public class SkillCategoryEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy = "skillCategory")
    private List<SkillEntity> skills;

    @ManyToOne
    @JoinColumn (name="skill_matrix_scheme_id")
    private SkillMatrixSchemeEntity skillMatrixScheme;
}
