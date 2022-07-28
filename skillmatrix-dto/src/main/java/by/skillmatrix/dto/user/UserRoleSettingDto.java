package by.skillmatrix.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "UserRoleSettingDto")
public class UserRoleSettingDto {

    @Schema(description = "Role id")
    @JsonProperty("roleId")
    private Long roleId;
}
