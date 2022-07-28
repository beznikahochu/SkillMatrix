package by.skillmatrix.dto.skillmatrix;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "SkillMatrixModificationDto")
public class SkillMatrixModificationDto {
    @Schema(description = "Skill matrix name", example = "Spring", defaultValue = "Spring")
    @JsonProperty("name")
    private String name;
}
