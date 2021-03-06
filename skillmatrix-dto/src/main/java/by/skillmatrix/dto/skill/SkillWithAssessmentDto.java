package by.skillmatrix.dto.skill;

import by.skillmatrix.dto.assessment.SkillAssessmentDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "SkillWithAssessmentsDto")
public class SkillWithAssessmentDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("position")
    private Long position;

    @JsonProperty("skillAssessment")
    private SkillAssessmentDto skillAssessment;
}
