package by.skillmatrix.dto.user;

import by.skillmatrix.validation.annotation.LoginConstraint;
import by.skillmatrix.validation.annotation.PasswordConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "UserCreationDto")
public class UserCreationDto {

    @Schema(description = "User login", defaultValue = "login")
    @LoginConstraint
    @JsonProperty("login")
    private String login;

    @Schema(description = "User login", defaultValue = "password")
    @PasswordConstraint
    @JsonProperty("password")
    private String password;
}
