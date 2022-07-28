package by.skillmatrix.controller;

import by.skillmatrix.dto.user.*;
import by.skillmatrix.param.PageParams;
import by.skillmatrix.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public UserDto registerNewUser(@RequestBody @Valid UserCreationDto creationDto) {
        log.info("Try to create new user: {}", creationDto.getLogin());

        UserDto createdUser = userService.create(creationDto);

        log.info("Return created user: {}", createdUser);
        return createdUser;
    }

    @PutMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add role for user")
    public UserFullInfoDto addRole(@PathVariable Long id, @RequestBody UserRoleSettingDto settingDto) {
        log.info("Try to add role for user : {}", id);

        UserFullInfoDto userFullInfoDto = userService.addRole(id, settingDto);

        log.info("Return user: {}", userFullInfoDto);
        return userFullInfoDto;
    }

    @DeleteMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Remove role from user")
    public UserFullInfoDto removeRole(@PathVariable Long id, @RequestBody UserRoleSettingDto settingDto) {
        log.info("Try to remove role from user : {}", id);

        UserFullInfoDto userFullInfoDto = userService.removeRole(id, settingDto);

        log.info("Role removed. User: {}", userFullInfoDto);
        return userFullInfoDto;
    }

    @PatchMapping("/{id}/person")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Set person for user")
    public UserFullInfoDto setEmployee(@PathVariable Long id, @RequestBody UserPeopleSettingDto settingDto) {
        log.info("Try to set person for user : {}", id);

        UserFullInfoDto userFullInfoDto = userService.setPerson(id, settingDto);

        log.info("Return user: {}", userFullInfoDto);
        return userFullInfoDto;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete user")
    public void delete(@PathVariable Long id) {
        log.info("Try to delete user : {}", id);

        userService.delete(id);

        log.info("User with id {} was deleted", id);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Find users by params")
    public List<UserFullInfoDto> findByParams(
            @ParameterObject PageParams page,
            @RequestParam(required = false) String sort
    ) {
        log.info("Find users");

        List<UserFullInfoDto> result = userService.findAll(page, sort);

        log.info("Return size: {}", result.size());
        return result;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Find user by id")
    public UserFullInfoDto findById(@PathVariable Long id) {
        log.info("Find User by id: {}", id);

        UserFullInfoDto result = userService.findById(id);

        log.info("Return User: {}", result);
        return result;
    }
}
