package by.skillmatrix.controller;

import by.skillmatrix.dto.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "1. User Controller", description = "works with users")
@RequestMapping(value = "api/users")
public interface UserController {

    @PostMapping
    @Operation(summary = "Register a new users")
    UserDto registerNewUser(@RequestBody UserCreationDto creationDto);

    @PutMapping("/{id}/roles")
    @Operation(summary = "Add role for user")
    UserFullInfoDto addRole(@PathVariable Long id, @RequestBody UserRoleSettingDto settingDto);

    @DeleteMapping("/{id}/roles")
    @Operation(summary = "Remove role from user")
    UserFullInfoDto removeRole(@PathVariable Long id, @RequestBody UserRoleSettingDto settingDto);

    @PutMapping("/{id}/employee")
    @Operation(summary = "Set employee for user")
    UserFullInfoDto setEmployee(@PathVariable Long id, @RequestBody UserEmployeeSettingDto settingDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    void delete(@PathVariable Long id);
}
