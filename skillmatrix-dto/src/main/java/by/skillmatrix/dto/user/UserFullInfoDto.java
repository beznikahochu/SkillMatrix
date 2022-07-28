package by.skillmatrix.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "UserDto")
public class UserFullInfoDto {

    @Schema(description = "User id")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "User login")
    @JsonProperty("login")
    private String login;

    @Schema(description = "Person id")
    @JsonProperty("personId")
    private Long personId;

    @Schema(description = "Roles")
    @JsonProperty("roles")
    private List<RoleDto> roles;
}
