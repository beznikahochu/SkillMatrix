package by.skillmatrix.dto.category;

import by.skillmatrix.dto.skill.SkillDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "SkillCategoryDto")
public class SkillCategoryFullInfoDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("position")
    private Long position;

    @JsonProperty("skills")
    private List<SkillDto> skills;
}
