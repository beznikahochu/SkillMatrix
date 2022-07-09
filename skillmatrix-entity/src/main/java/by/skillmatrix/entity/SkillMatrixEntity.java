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
import java.time.LocalDateTime;
import java.util.List;


@NamedEntityGraph(
        name = "skill-matrix-with-assessments",
        attributeNodes = {
                @NamedAttributeNode("employee"),
                @NamedAttributeNode("skillAssessments"),
                @NamedAttributeNode("skillMatrixScheme")
        }
)
@NamedEntityGraph(
        name = "skill-matrix-with-scheme-and-user",
        attributeNodes = {
                @NamedAttributeNode("employee"),
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
    @JoinColumn (name="skill_matrix_scheme_id", updatable = false, nullable = false)
    private SkillMatrixSchemeEntity skillMatrixScheme;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany (cascade={CascadeType.REMOVE, CascadeType.DETACH}, mappedBy = "skillMatrix")
    private List<SkillAssessmentEntity> skillAssessments;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn (name="employee_id", updatable = false)
    private EmployeeEntity employee;
}
