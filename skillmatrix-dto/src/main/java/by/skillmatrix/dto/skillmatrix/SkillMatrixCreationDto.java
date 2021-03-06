package by.skillmatrix.dto.skillmatrix;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "SkillMatrixSchemeDto")
public class SkillMatrixCreationDto {

    @Schema(description = "Skill matrix name", example = "Spring", defaultValue = "Spring")
    @JsonProperty("name")
    private String name;

    @Schema(description = "Skill matrix scheme id", example = "1", defaultValue = "1")
    @JsonProperty("schemeId")
    private Long schemeId;

    @Schema(description = "Person id", example = "1", defaultValue = "1")
    @JsonProperty("personId")
    private Long personId;
}
