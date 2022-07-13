package by.skillmatrix.dto.assessment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillAssessmentDto")
public class SkillAssessmentDto {

    @JsonProperty("assessment")
    private Long assessment;

    @JsonProperty("comment")
    private String comment;
}
