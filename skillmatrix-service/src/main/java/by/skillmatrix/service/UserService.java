package by.skillmatrix.service;

import by.skillmatrix.dto.user.UserCreationDto;
import by.skillmatrix.dto.user.UserDto;

import java.util.List;

/**
 * Service for working with Users.
 */
public interface UserService {

    /**
     * Registration new user.
     *
     * @param user     new user
     * @return created User
     */
    UserDto registrationUser(UserCreationDto user);
}
