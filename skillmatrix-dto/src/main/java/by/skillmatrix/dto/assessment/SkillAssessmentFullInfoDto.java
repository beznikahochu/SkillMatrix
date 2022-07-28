package by.skillmatrix.dto.assessment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "SkillAssessmentCreationDto")
public class SkillAssessmentFullInfoDto {

    @JsonProperty("skillMatrixId")
    private Long skillMatrixId;

    @JsonProperty("skillId")
    private Long skillId;

    @JsonProperty("assessment")
    private Long assessment;

    @JsonProperty("comment")
    private String comment;
}
