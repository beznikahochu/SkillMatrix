package by.skillmatrix.controller;

import by.skillmatrix.dto.user.UserCreationDto;
import by.skillmatrix.dto.user.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * User registration controller.
 */
@Tag(name = "Register User Controller", description = "registration new users")
@RequestMapping(value = "/auth/registration")
public interface UserRegistrationController {

    @Operation(summary = "Create new user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserDto createUser(
            @Parameter(description = "user dto for creating", required = true)
            @Valid @RequestBody UserCreationDto userCreationDto
    );
}
