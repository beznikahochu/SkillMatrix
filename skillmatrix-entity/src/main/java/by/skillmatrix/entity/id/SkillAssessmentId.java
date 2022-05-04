package by.skillmatrix.entity.id;

import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.entity.SkillMatrixEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class SkillAssessmentId implements Serializable {
    private SkillMatrixEntity skillMatrix;
    private SkillEntity skill;
}
