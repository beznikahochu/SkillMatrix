package by.skillmatrix.impl.controller;

import by.skillmatrix.controller.UserController;
import by.skillmatrix.dto.user.*;
import by.skillmatrix.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

/**
 * User Controller Implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto registerNewUser(UserCreationDto creationDto) {
        log.info("Try to create new user: {}", creationDto.getLogin());

        UserDto createdUser = userService.create(creationDto);

        log.info("Return created user: {}", createdUser);
        return createdUser;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserFullInfoDto addRole(Long id, UserRoleSettingDto settingDto) {
        log.info("Try to add role for user : {}", id);

        UserFullInfoDto userFullInfoDto = userService.addRole(id, settingDto);

        log.info("Return user: {}", userFullInfoDto);
        return userFullInfoDto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserFullInfoDto removeRole(Long id, UserRoleSettingDto settingDto) {
        log.info("Try to remove role from user : {}", id);

        UserFullInfoDto userFullInfoDto = userService.removeRole(id, settingDto);

        log.info("Role removed. User: {}", userFullInfoDto);
        return userFullInfoDto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserFullInfoDto setEmployee(Long id, UserEmployeeSettingDto settingDto) {
        log.info("Try to set employee for user : {}", id);

        UserFullInfoDto userFullInfoDto = userService.setEmployee(id, settingDto);

        log.info("Return user: {}", userFullInfoDto);
        return userFullInfoDto;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        log.info("Try to delete user : {}", id);

        userService.delete(id);

        log.info("User with id {} was deleted", id);
    }
}
