package by.skillmatrix.controller;

import by.skillmatrix.dto.user.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for mapping Users.
 */
@Tag(name = "User Controller", description = "works with users")
@RequestMapping(value = "/users")
public interface UserController {

    @Operation(summary = "Get user by id", description = "get user information by id")
    @GetMapping("/{id}")
    UserDto findById(
            @Parameter(description = "user id", required = true)
            @PathVariable("id") Long id
    );
}
