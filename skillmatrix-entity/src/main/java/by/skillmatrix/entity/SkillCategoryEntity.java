package by.skillmatrix.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "skill_categories")
@ToString
@NoArgsConstructor
public class SkillCategoryEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany (fetch=FetchType.LAZY, mappedBy = "skillCategory")
    private List<SkillEntity> skills;

    @ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn (name="skill_matrix_scheme_id")
    private SkillMatrixSchemeEntity skillMatrixScheme;
}
