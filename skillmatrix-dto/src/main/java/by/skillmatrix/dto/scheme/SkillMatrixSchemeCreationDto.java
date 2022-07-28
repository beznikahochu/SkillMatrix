package by.skillmatrix.dto.scheme;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "SkillMatrixSchemeCreationDto")
public class SkillMatrixSchemeCreationDto {

    @JsonProperty("name")
    private String name;
}
