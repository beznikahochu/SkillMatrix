package by.skillmatrix.service.impl;

import by.skillmatrix.dto.user.UserCreationDto;
import by.skillmatrix.dto.user.UserDto;
import by.skillmatrix.entity.UserEntity;
import by.skillmatrix.mapper.UserMapper;
import by.skillmatrix.repository.UserRepository;
import by.skillmatrix.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public UserDto registrationUser(UserCreationDto user) {
        log.debug("Admit new user: {}", user);

        UserEntity userEntity = userMapper.toUserEntity(user);
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserEntity createdUser = userRepository.save(userEntity);
        UserDto result = userMapper.toUserDto(createdUser);

        log.debug("Admitted user: {}", user);
        return result;
    }
}
