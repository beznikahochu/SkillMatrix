package by.skillmatrix.dto.skill;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "SkillModificationDto")
public class SkillModificationDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("position")
    private Long position;
}
