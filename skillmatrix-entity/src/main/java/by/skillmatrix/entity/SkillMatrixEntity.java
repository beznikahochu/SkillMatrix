package by.skillmatrix.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//TODO: Изменить cascade
@Entity
@Table(name = "skill_matrices")
public class SkillMatrixEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @ManyToOne (fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn (name="skill_matrix_scheme_id")
    private SkillMatrixSchemeEntity killMatrixScheme;

    @OneToMany (fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "skillMatrix")
    private List<SkillAssessmentEntity> skillAssessment;

    @Column(nullable = false, name = "end_time")
    private LocalDate creationDate;

    @Column(nullable = false, name = "end_time")
    private LocalTime creationTime;
}
