package by.skillmatrix.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "RoleDto")
public class RoleDto {

    @Schema(description = "Role id")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Role name")
    @JsonProperty("name")
    private String name;
}
