package by.skillmatrix.dto.skill;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillCreationDto")
public class SkillCreationDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("skillCategoryId")
    private Long skillCategoryId;

    @JsonProperty("skillMatrixSchemeId")
    private Long skillMatrixSchemeId;
}
