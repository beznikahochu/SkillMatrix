package by.skillmatrix.service.impl;

import by.skillmatrix.dto.user.*;
import by.skillmatrix.entity.Person;
import by.skillmatrix.entity.Role;
import by.skillmatrix.entity.User;
import by.skillmatrix.entity.id.UserAndRoleId;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.exception.RegistrationException;
import by.skillmatrix.mapper.UserMapper;
import by.skillmatrix.param.PageParams;
import by.skillmatrix.repository.PersonRepository;
import by.skillmatrix.repository.RoleRepository;
import by.skillmatrix.repository.UserAndRoleRepository;
import by.skillmatrix.repository.UserRepository;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.UserSortType;
import by.skillmatrix.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserAndRoleRepository userAndRoleRepository;
    private final PersonRepository personRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto create(UserCreationDto creationDto) {
        log.debug("Try to create new User: {}", creationDto);

        throwExcIfUserWithLoginAlreadyExists(creationDto.getLogin());
        User user = new User();
        user.setLogin(creationDto.getLogin());
        user.setPassword(passwordEncoder.encode(creationDto.getPassword()));
        User createdUser = userRepository.save(user);
        UserDto createdUserFullInfoDto = userMapper.toUserDto(createdUser);

        log.debug("Return created User: {}", createdUserFullInfoDto);
        return createdUserFullInfoDto;
    }

    @Override
    public UserFullInfoDto findById(Long id) {
        log.debug("Find User by id: {}", id);

        User user = userRepository.findUserWithRolesById(id)
                .orElseThrow(() -> new NotFoundException("User not found by id"));
        UserFullInfoDto result = userMapper.toUserFullInfoDto(user);

        log.debug("Return User: {}", result);
        return result;
    }

    @Override
    public List<UserFullInfoDto> findAll(PageParams page, String sort) {
        log.debug("Find users");

        UserSortType sortType = getUserSortTypeByString(sort);
        PageOptions pageOptions = new PageOptions(page.getPage(), page.getPageSize());
        List<User> foundList = userRepository.findAll(pageOptions, sortType);
        List<UserFullInfoDto> result = foundList.stream().map(userMapper::toUserFullInfoDto)
                .collect(Collectors.toList());

        log.debug("Result size: {}", result.size());
        return result;
    }

    @Override
    @Transactional
    public UserFullInfoDto addRole(Long id, UserRoleSettingDto settingDto) {
        log.debug("Try to add role for User with id {}", id);

        User user = userRepository.findUserWithRolesById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Role role = roleRepository.findById(settingDto.getRoleId())
                .orElseThrow(() -> new NotFoundException("Role not found"));

        user.getRoles().add(role);
        UserFullInfoDto userFullInfoDto = userMapper.toUserFullInfoDto(user);

        log.debug("Role was added for user: {}", userFullInfoDto);
        return userFullInfoDto;
    }

    @Override
    @Transactional
    public UserFullInfoDto removeRole(Long id, UserRoleSettingDto settingDto) {
        log.debug("Try to remove role from User with id {}", id);

        UserAndRoleId userAndRoleId = new UserAndRoleId();
        userAndRoleId.setUserId(id);
        userAndRoleId.setRoleId(settingDto.getRoleId());

        userAndRoleRepository.delete(userAndRoleId);
        User user = userRepository.findUserWithRolesById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        UserFullInfoDto userFullInfoDto = userMapper.toUserFullInfoDto(user);

        log.debug("Role was removed from user with id: {}", user.getId());
        return userFullInfoDto;
    }

    @Override
    @Transactional
    public UserFullInfoDto setPerson(Long id, UserPeopleSettingDto settingDto) {
        log.debug("Set employee for User with id {}", id);

        User user = userRepository.findUserWithRolesById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Person person = personRepository.findById(settingDto.getPeopleId())
                .orElseThrow(() -> new NotFoundException("Person not found"));

        user.setPersonId(person.getId());
        UserFullInfoDto userFullInfoDto = userMapper.toUserFullInfoDto(user);

        log.debug("Person for User was added: {}", userFullInfoDto);
        return userFullInfoDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Try to delete User by id: {}", id);

        userRepository.delete(id);

        log.debug("User with ID {} has been removed", id);
    }

    private void throwExcIfUserWithLoginAlreadyExists(String login) {
        userRepository.findByLogin(login).ifPresent(foundUser -> {
            throw new RegistrationException("User with login " + login + " already exists");
        });
    }

    private UserSortType getUserSortTypeByString(String type) {
        if (type == null) {
            return UserSortType.ID_DESC;
        }
        switch (type) {
            case "id.d":
                return UserSortType.ID_DESC;
            case "id.a":
                return UserSortType.ID_ASC;
        }
        return UserSortType.ID_DESC;
    }
}
