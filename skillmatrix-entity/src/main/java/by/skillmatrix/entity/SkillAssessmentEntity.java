package by.skillmatrix.entity;

import by.skillmatrix.entity.id.SkillAssessmentId;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Data
@Entity
@Table(name = "skill_assessments")
@IdClass(SkillAssessmentId.class)
public class SkillAssessmentEntity {

    @Id
    @Column(name = "skill_matrix_id")
    private Long skillMatrixId;

    @Id
    @Column(name = "skill_id")
    private Long skillId;

    @ManyToOne
    @JoinColumn(name = "skill_matrix_id", insertable = false, updatable = false)
    private SkillMatrixEntity skillMatrix;

    @ManyToOne
    @JoinColumn(name = "skill_id", insertable = false, updatable = false)
    private SkillEntity skill;

    @Column(name = "assessment")
    private Long assessment;

    @Column(name = "comment")
    private String comment;
}
