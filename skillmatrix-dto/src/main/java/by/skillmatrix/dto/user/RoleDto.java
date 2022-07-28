package by.skillmatrix.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "RoleDto")
public class RoleDto {

    @Schema(description = "Role id")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Role name")
    @JsonProperty("name")
    private String name;
}
