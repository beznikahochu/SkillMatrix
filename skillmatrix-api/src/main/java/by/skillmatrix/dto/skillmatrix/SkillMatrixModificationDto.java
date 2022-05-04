package by.skillmatrix.dto.skillmatrix;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static by.skillmatrix.config.OpenApiConstants.EXAMPLE_LONG;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillMatrixModificationDto")
public class SkillMatrixModificationDto {

    @Schema(description = "Skill matrix id", example = EXAMPLE_LONG, defaultValue = EXAMPLE_LONG)
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Skill matrix name", example = "Spring", defaultValue = "Spring")
    @JsonProperty("name")
    private String name;
}
