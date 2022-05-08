package by.skillmatrix.dto.skillmatrix;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillMatrixModificationDto")
public class SkillMatrixModificationDto {

    @Schema(description = "Skill matrix id", example = "1", defaultValue = "1")//TODO
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Skill matrix name", example = "Spring", defaultValue = "Spring")
    @JsonProperty("name")
    private String name;
}
