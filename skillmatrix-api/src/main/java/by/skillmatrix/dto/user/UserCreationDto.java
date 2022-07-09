package by.skillmatrix.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "UserCreationDto")
public class UserCreationDto {
    @Schema(description = "User id", defaultValue = "1")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "User login", defaultValue = "login")
    @JsonProperty("login")
    private String login;

    @Schema(description = "User login", defaultValue = "password")
    @JsonProperty("password")
    private String password;

    @Schema(description = "Employee id", defaultValue = "1")
    @JsonProperty("employeeid")
    private Long employeeId;
}
