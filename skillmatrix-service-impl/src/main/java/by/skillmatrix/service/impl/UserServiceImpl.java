package by.skillmatrix.service.impl;

import by.skillmatrix.dto.user.UserCreationDto;
import by.skillmatrix.dto.user.UserDto;
import by.skillmatrix.entity.UserEntity;
import by.skillmatrix.mapper.UserMapper;
import by.skillmatrix.repository.UserRepository;
import by.skillmatrix.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto create(UserCreationDto creationDto) {
        log.debug("Trying to create new User: {}", creationDto);

        UserEntity userEntity = userMapper.toUserEntity(creationDto);
        UserEntity createdUser = userRepository.save(userEntity);
        UserDto createdUserDto = userMapper.toUserDto(createdUser);

        log.debug("Return created User: {}", createdUserDto);
        return createdUserDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
