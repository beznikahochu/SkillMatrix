package by.skillmatrix.dto.assessment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static by.skillmatrix.config.OpenApiConstants.DESCRIPTION_TOKEN;
import static by.skillmatrix.config.OpenApiConstants.EXAMPLE_TOKEN;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillAssessmentDto")
public class SkillAssessmentDto {

    @JsonProperty("assessment")
    private Byte assessment;

    @JsonProperty("comment")
    private String comment;
}
