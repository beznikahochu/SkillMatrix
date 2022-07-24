package by.skillmatrix.entity;

import by.skillmatrix.entity.id.SkillAssessmentId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "skill_assessments")
@IdClass(SkillAssessmentId.class)
public class SkillAssessment {

    @Id
    @Column(name = "skill_matrix_id", updatable = false)
    private Long skillMatrixId;

    @Id
    @Column(name = "skill_id", updatable = false)
    private Long skillId;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "skill_matrix_id", insertable = false, updatable = false)
    private SkillMatrix skillMatrix;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "skill_id", insertable = false, updatable = false)
    private Skill skill;

    @Column(name = "assessment", nullable = false)
    private Long assessment;

    @Column(name = "comment")
    private String comment;
}
