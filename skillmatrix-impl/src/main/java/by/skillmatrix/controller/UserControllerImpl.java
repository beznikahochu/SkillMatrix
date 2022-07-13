package by.skillmatrix.controller;

import by.skillmatrix.dto.user.*;
import by.skillmatrix.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/users")
@Tag(name = "1. User Controller", description = "works with users")
public class UserControllerImpl {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Register a new users")
    public UserDto registerNewUser(UserCreationDto creationDto) {
        log.info("Try to create new user: {}", creationDto.getLogin());

        UserDto createdUser = userService.create(creationDto);

        log.info("Return created user: {}", createdUser);
        return createdUser;
    }

    @PutMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add role for user")
    public UserFullInfoDto addRole(Long id, UserRoleSettingDto settingDto) {
        log.info("Try to add role for user : {}", id);

        UserFullInfoDto userFullInfoDto = userService.addRole(id, settingDto);

        log.info("Return user: {}", userFullInfoDto);
        return userFullInfoDto;
    }

    @DeleteMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Remove role from user")
    public UserFullInfoDto removeRole(Long id, UserRoleSettingDto settingDto) {
        log.info("Try to remove role from user : {}", id);

        UserFullInfoDto userFullInfoDto = userService.removeRole(id, settingDto);

        log.info("Role removed. User: {}", userFullInfoDto);
        return userFullInfoDto;
    }

    @PutMapping("/{id}/employee")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Set employee for user")
    public UserFullInfoDto setEmployee(Long id, UserEmployeeSettingDto settingDto) {
        log.info("Try to set employee for user : {}", id);

        UserFullInfoDto userFullInfoDto = userService.setEmployee(id, settingDto);

        log.info("Return user: {}", userFullInfoDto);
        return userFullInfoDto;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete user")
    public void delete(Long id) {
        log.info("Try to delete user : {}", id);

        userService.delete(id);

        log.info("User with id {} was deleted", id);
    }
}
