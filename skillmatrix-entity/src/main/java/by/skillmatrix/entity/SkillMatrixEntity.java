package by.skillmatrix.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//TODO: Изменить cascade
@Getter
@Setter
@FieldNameConstants
@Entity
@Table(name = "skill_matrices")
@ToString
@NoArgsConstructor
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
