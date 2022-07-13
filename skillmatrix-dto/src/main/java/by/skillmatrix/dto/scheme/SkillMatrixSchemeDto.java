package by.skillmatrix.dto.scheme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillMatrixSchemeDto")
public class SkillMatrixSchemeDto {

    @Schema(description = "Skill matrix scheme id", example = "1", defaultValue = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Skill matrix scheme name", example = "Spring", defaultValue = "Spring")
    @JsonProperty("name")
    private String name;
}
