package by.skillmatrix.entity;

import by.skillmatrix.entity.id.SkillAssessmentId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Data
@Entity
@Table(name = "skill_assessments")
@IdClass(SkillAssessmentId.class)
public class SkillAssessmentEntity {

    @Id
    @Column(name = "skill_matrix_id", updatable = false)
    private Long skillMatrixId;

    @Id
    @Column(name = "skill_id", updatable = false)
    private Long skillId;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "skill_matrix_id", insertable = false, updatable = false)
    private SkillMatrixEntity skillMatrix;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "skill_id", insertable = false, updatable = false)
    private SkillEntity skill;

    @Column(name = "assessment")
    private Long assessment;

    @Column(name = "comment")
    private String comment;
}
