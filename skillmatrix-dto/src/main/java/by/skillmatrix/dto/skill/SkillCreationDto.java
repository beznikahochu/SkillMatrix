package by.skillmatrix.dto.skill;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "SkillCreationDto")
public class SkillCreationDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("position")
    private Long position;

    @JsonProperty("skillCategoryId")
    private Long skillCategoryId;
}
