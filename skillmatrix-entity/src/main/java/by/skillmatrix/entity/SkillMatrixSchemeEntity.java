package by.skillmatrix.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.List;

//TODO: Изменить cascade
@NamedEntityGraph(
        name = "scheme-with-categories",
        attributeNodes = {
                @NamedAttributeNode("skillCategories")
        }
)
@Getter
@Setter
@FieldNameConstants
@Entity
@Table(name = "skill_matrix_schemes")
@ToString
@NoArgsConstructor
public class SkillMatrixSchemeEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany (fetch=FetchType.LAZY, cascade=CascadeType.REMOVE, mappedBy = "skillMatrixScheme")
    private List<SkillCategoryEntity> skillCategories;

    @OneToMany (fetch=FetchType.LAZY, cascade=CascadeType.REMOVE, mappedBy = "skillMatrixScheme")
    private List<SkillEntity> skills;
}
