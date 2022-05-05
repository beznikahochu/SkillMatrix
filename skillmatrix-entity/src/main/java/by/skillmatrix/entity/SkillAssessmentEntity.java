package by.skillmatrix.entity;

import by.skillmatrix.entity.id.SkillAssessmentId;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

//TODO: Ебаные id
@Data
@Entity
@Table(name = "skill_assessments")
@IdClass(SkillAssessmentId.class)
public class SkillAssessmentEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "skill_matrix_id")
    private SkillMatrixEntity skillMatrix;

    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private SkillEntity skill;

    @Column(name = "assessment")
    private Long assessment;

    @Column(name = "comment")
    private String comment;
}
