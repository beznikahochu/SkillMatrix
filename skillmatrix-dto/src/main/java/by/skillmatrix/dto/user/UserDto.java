package by.skillmatrix.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "UserDto")
public class UserDto {

    @Schema(description = "User id")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "User login")
    @JsonProperty("login")
    private String login;
}
