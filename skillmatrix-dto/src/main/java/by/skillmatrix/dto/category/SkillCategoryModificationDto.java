package by.skillmatrix.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "SkillCategoryModificationDto")
public class SkillCategoryModificationDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("position")
    private Long position;
}
