package by.skillmatrix.service;

import by.skillmatrix.dto.user.UserCreationDto;
import by.skillmatrix.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto create(UserCreationDto creationDto);
    void delete(Long id);
}
