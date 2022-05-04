package by.skillmatrix.entity;

import lombok.*;
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
@Data
@Entity
@Table(name = "skill_matrix_schemes")
public class SkillMatrixSchemeEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany (fetch=FetchType.LAZY, cascade=CascadeType.REMOVE, mappedBy = "skillMatrixScheme")
    private List<SkillCategoryEntity> skillCategories;
}
