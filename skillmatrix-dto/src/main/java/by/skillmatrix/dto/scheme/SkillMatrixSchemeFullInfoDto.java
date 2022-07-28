package by.skillmatrix.dto.scheme;

import by.skillmatrix.dto.category.SkillCategoryFullInfoDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "SkillMatrixSchemeFullInfoDto")
public class SkillMatrixSchemeFullInfoDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("categories")
    private List<SkillCategoryFullInfoDto> categories;
}
