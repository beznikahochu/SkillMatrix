package by.skillmatrix.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "UserDto")
public class UserDto {

    @Schema(description = "User id")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "User login")
    @JsonProperty("login")
    private String login;
}
