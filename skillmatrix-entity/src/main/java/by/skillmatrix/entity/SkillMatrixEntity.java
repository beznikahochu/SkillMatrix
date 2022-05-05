package by.skillmatrix.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@NamedEntityGraph(
        name = "skill-matrix-with-assessments",
        attributeNodes = {
                @NamedAttributeNode("skillAssessments"),
                @NamedAttributeNode("skillMatrixScheme")
        }
)
@Data
@Entity
@Table(name = "skill_matrices")
public class SkillMatrixEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn (name="skill_matrix_scheme_id")
    private SkillMatrixSchemeEntity skillMatrixScheme;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany (cascade=CascadeType.REMOVE, mappedBy = "skillMatrix")
    private List<SkillAssessmentEntity> skillAssessments;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "creation_time", nullable = false)
    private LocalTime creationTime;

    @ManyToOne
    @JoinColumn (name="user_id")
    private UserEntity user;
}
