package by.skillmatrix.dto.assessment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillAssessmentCreationDto")
public class SkillAssessmentFullInfoDto {

    @JsonProperty("assessment")
    private Long assessment;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("skillMatrixId")
    private Long skillMatrixId;

    @JsonProperty("skillId")
    private Long skillId;
}
