package by.skillmatrix.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedAttributeNode;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@NamedEntityGraph(
        name = "skill-matrix-with-assessments",
        attributeNodes = {
                @NamedAttributeNode("user"),
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn (name="skill_matrix_scheme_id", updatable = false)
    private SkillMatrixSchemeEntity skillMatrixScheme;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany (cascade={CascadeType.REMOVE, CascadeType.DETACH}, mappedBy = "skillMatrix")
    private List<SkillAssessmentEntity> skillAssessments;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "creation_time", nullable = false)
    private LocalTime creationTime;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn (name="user_id", updatable = false)
    private UserEntity user;
}
