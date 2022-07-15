package by.skillmatrix.service.impl;

import by.skillmatrix.dto.user.*;
import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.entity.RoleEntity;
import by.skillmatrix.entity.UserAndRoleEntity;
import by.skillmatrix.entity.UserEntity;
import by.skillmatrix.entity.id.UserAndRoleId;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.UserMapper;
import by.skillmatrix.dao.EmployeeDao;
import by.skillmatrix.dao.RoleDao;
import by.skillmatrix.dao.UserAndRoleDao;
import by.skillmatrix.dao.UserDao;
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

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final UserAndRoleDao userAndRoleDao;
    private final EmployeeDao employeeDao;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto create(UserCreationDto creationDto) {
        log.debug("Try to create new User: {}", creationDto);

        UserEntity user = new UserEntity();
        user.setLogin(creationDto.getLogin());
        user.setPassword(passwordEncoder.encode(creationDto.getPassword()));
        UserEntity createdUser = userDao.save(user);
        UserDto createdUserFullInfoDto = userMapper.toUserDto(createdUser);

        log.debug("Return created User: {}", createdUserFullInfoDto);
        return createdUserFullInfoDto;
    }

    @Override
    public UserFullInfoDto findById(Long id) {
        log.debug("Find User by id: {}", id);

        UserEntity user = userDao.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found by id"));
        UserFullInfoDto result = userMapper.toUserFullInfoDto(user);

        log.debug("Return User: {}", result);
        return null;
    }

    @Override
    public UserFullInfoDto findByLogin(String login) {
        log.debug("Find User by login: {}", login);

        UserEntity user = userDao.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("User not found by login"));
        UserFullInfoDto result = userMapper.toUserFullInfoDto(user);

        log.debug("Return User: {}", result);
        return null;
    }

    @Override
    @Transactional
    public UserFullInfoDto addRole(Long id, UserRoleSettingDto settingDto) {
        log.debug("Try to add role for User with id {}", id);

        UserEntity user = userDao.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        RoleEntity role = roleDao.findById(settingDto.getRoleId())
                .orElseThrow(() -> new NotFoundException("Role not found"));

        UserAndRoleEntity userAndRole = new UserAndRoleEntity();
        userAndRole.setUserId(user.getId());
        userAndRole.setRoleId(role.getId());

        userAndRoleDao.save(userAndRole);
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

        userAndRoleDao.delete(userAndRoleId);
        UserEntity user = userDao.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        UserFullInfoDto userFullInfoDto = userMapper.toUserFullInfoDto(user);

        log.debug("Role was removed from user with id: {}", user.getId());
        return userFullInfoDto;
    }

    @Override
    @Transactional
    public UserFullInfoDto setEmployee(Long id, UserEmployeeSettingDto settingDto) {
        log.debug("Set employee for User with id {}", id);

        UserEntity user = userDao.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        EmployeeEntity employee = employeeDao.findById(settingDto.getEmployeeId())
                .orElseThrow(() -> new NotFoundException("Employee not found"));

        user.setEmployeeId(employee.getId());
        UserEntity updatedUser = userDao.save(user);
        UserFullInfoDto userFullInfoDto = userMapper.toUserFullInfoDto(updatedUser);

        log.debug("Employee for User was added: {}", userFullInfoDto);
        return userFullInfoDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Try to delete User by id: {}", id);

        userDao.delete(id);

        log.debug("User with ID {} has been removed", id);
    }
}
