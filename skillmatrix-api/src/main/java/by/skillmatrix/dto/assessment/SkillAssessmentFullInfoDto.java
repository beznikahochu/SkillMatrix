package by.skillmatrix.dto.assessment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated //TODO ВАЛИДАЦИЯ ДЕТКА
@JsonIgnoreProperties(ignoreUnknown = true)
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
