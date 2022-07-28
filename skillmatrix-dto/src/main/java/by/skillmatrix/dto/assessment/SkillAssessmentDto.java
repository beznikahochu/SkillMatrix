package by.skillmatrix.dto.assessment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "SkillAssessmentDto")
public class SkillAssessmentDto {

    @JsonProperty("assessment")
    private Long assessment;

    @JsonProperty("comment")
    private String comment;
}
