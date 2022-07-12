package by.skillmatrix.dto.category;

import by.skillmatrix.dto.skill.SkillDto;
import by.skillmatrix.dto.skill.SkillWithAssessmentsDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "SkillCategoryWithAssessmentsDto")
public class SkillCategoryWithAssessmentsDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("position")
    private Long position;

    @JsonProperty("skills")
    private List<SkillWithAssessmentsDto> skills;
}
