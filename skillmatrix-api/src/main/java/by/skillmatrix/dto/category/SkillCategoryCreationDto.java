package by.skillmatrix.dto.category;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillCategoryCreationDto")
public class SkillCategoryCreationDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("position")
    private Long position;

    @JsonProperty("skillMatrixSchemeId")
    private Long skillMatrixSchemeId;
}
