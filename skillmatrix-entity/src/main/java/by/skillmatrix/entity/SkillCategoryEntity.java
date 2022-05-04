package by.skillmatrix.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;

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
    @OneToMany (fetch=FetchType.LAZY, mappedBy = "skillCategory")
    private List<SkillEntity> skills;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn (name="skill_matrix_scheme_id")
    private SkillMatrixSchemeEntity skillMatrixScheme;
}
