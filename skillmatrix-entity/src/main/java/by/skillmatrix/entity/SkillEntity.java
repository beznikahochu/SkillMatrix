package by.skillmatrix.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

//TODO: Изменить optional и cascade
@Getter
@Setter
@FieldNameConstants
@Entity
@Table(name = "skills")
@ToString
@NoArgsConstructor
public class SkillEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne (optional=false)
    @JoinColumn(name = "skill_category_id")
    private SkillCategoryEntity skillCategory;

    @ManyToOne (optional=false)
    @JoinColumn(name = "skill_matrix_scheme_id")
    private SkillMatrixSchemeEntity skillMatrixScheme;
}
