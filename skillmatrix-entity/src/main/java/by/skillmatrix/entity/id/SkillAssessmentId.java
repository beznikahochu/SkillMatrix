package by.skillmatrix.entity.id;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkillAssessmentId implements Serializable {
    private Long skillMatrixId;
    private Long skillId;
}
