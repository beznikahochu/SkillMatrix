package by.skillmatrix.dto.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillCategoryModificationDto")
public class SkillCategoryModificationDto {
    @JsonProperty("name")
    private String name;
}
