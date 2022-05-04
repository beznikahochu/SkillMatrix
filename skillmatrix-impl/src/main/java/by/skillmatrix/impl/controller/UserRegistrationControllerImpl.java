package by.skillmatrix.impl.controller;

import by.skillmatrix.controller.UserRegistrationController;
import by.skillmatrix.dto.user.UserCreationDto;
import by.skillmatrix.dto.user.UserDto;
import by.skillmatrix.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Register user controller Implementation.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserRegistrationControllerImpl implements UserRegistrationController {

    private final UserService userService;

    @Override
    public UserDto createUser(UserCreationDto userCreationDto) {
        log.info("Try to create new user with login: {}", userCreationDto.getLogin());

        UserDto createdUser = userService.registrationUser(userCreationDto);

        log.info("Return created user: {}", createdUser);
        return createdUser;
    }
}
