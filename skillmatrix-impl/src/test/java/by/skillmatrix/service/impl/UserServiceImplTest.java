package by.skillmatrix.service.impl;

import by.skillmatrix.dto.user.*;
import by.skillmatrix.entity.Person;
import by.skillmatrix.entity.Role;
import by.skillmatrix.entity.User;
import by.skillmatrix.entity.id.UserAndRoleId;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.UserMapperImpl;
import by.skillmatrix.repository.PersonRepository;
import by.skillmatrix.repository.RoleRepository;
import by.skillmatrix.repository.UserAndRoleRepository;
import by.skillmatrix.repository.UserRepository;
import by.skillmatrix.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UserServiceImplTest {

    private UserService userService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserAndRoleRepository userAndRoleRepository;
    private PersonRepository personRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void beforeEach() {
        userRepository = Mockito.mock(UserRepository.class);
        roleRepository = Mockito.mock(RoleRepository.class);
        personRepository = Mockito.mock(PersonRepository.class);
        userAndRoleRepository = Mockito.mock(UserAndRoleRepository.class);
        passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        userService = new UserServiceImpl(
                userRepository,
                roleRepository,
                userAndRoleRepository,
                personRepository,
                new UserMapperImpl(),
                passwordEncoder
        );
    }

    @Test
    void createTest() {
        UserCreationDto userCreationDto = new UserCreationDto();
        userCreationDto.setLogin("login");
        userCreationDto.setPassword("password");

        Mockito.when(passwordEncoder.encode(userCreationDto.getPassword()))
                .thenReturn("123");
        String password = passwordEncoder.encode(userCreationDto.getPassword());

        User user = new User();
        user.setLogin(userCreationDto.getLogin());
        user.setPassword(password);

        User createdUser = new User();
        createdUser.setId(1L);
        createdUser.setLogin(user.getLogin());
        createdUser.setPassword(password);

        Mockito.when(userRepository.save(user)).thenReturn(createdUser);

        UserDto expectedResult = new UserDto();
        expectedResult.setId(createdUser.getId());
        expectedResult.setLogin(createdUser.getLogin());

        UserDto result = userService.create(userCreationDto);

        assertEquals(expectedResult, result);
    }

    @Test
    void findByIdTest() {
        Long id = 1L;

        User user = new User();
        user.setId(id);
        user.setLogin("login");
        user.setPassword("123");
        user.setPersonId(1L);

        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("USER");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("MANAGER");

        Role role3 = new Role();
        role3.setId(3L);
        role3.setName("ADMIN");

        List<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);

        user.setRoles(roles);

        Mockito.when(userRepository.findUserWithRolesById(id)).thenReturn(Optional.of(user));

        UserFullInfoDto expectedResult = new UserFullInfoDto();
        expectedResult.setId(user.getId());
        expectedResult.setLogin(user.getLogin());
        expectedResult.setPersonId(user.getPersonId());

        RoleDto role1Dto = new RoleDto();
        role1Dto.setId(role1.getId());
        role1Dto.setName(role1.getName());

        RoleDto role2Dto = new RoleDto();
        role2Dto.setId(role2.getId());
        role2Dto.setName(role2.getName());

        RoleDto role3Dto = new RoleDto();
        role3Dto.setId(role3.getId());
        role3Dto.setName(role3.getName());

        List<RoleDto> rolesDto = new ArrayList<>();
        rolesDto.add(role1Dto);
        rolesDto.add(role2Dto);
        rolesDto.add(role3Dto);

        expectedResult.setRoles(rolesDto);

        UserFullInfoDto result = userService.findById(id);

        assertEquals(expectedResult, result);
    }

    @Test
    void whenFindByIdThrowsNotFoundExceptionTest() {
        Long id = 1L;
        Mockito.when(userRepository.findUserWithRolesById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.findById(id));
    }

    @Test
    void addRoleTest() {
        Long userId = 1L;
        UserRoleSettingDto settingDto = new UserRoleSettingDto();
        settingDto.setRoleId(1L);

        User user = new User();
        user.setId(userId);
        user.setLogin("login");
        user.setPassword("123");
        user.setPersonId(1L);
        user.setRoles(new ArrayList<>());

        Role role = new Role();
        role.setId(1L);
        role.setName("USER");

        Mockito.when(userRepository.findUserWithRolesById(userId))
                .thenReturn(Optional.of(user));
        Mockito.when(roleRepository.findById(settingDto.getRoleId()))
                .thenReturn(Optional.of(role));

        UserFullInfoDto expectedResult = new UserFullInfoDto();
        expectedResult.setId(user.getId());
        expectedResult.setLogin(user.getLogin());
        expectedResult.setPersonId((user.getPersonId()));

        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());

        expectedResult.setRoles(List.of(roleDto));

        UserFullInfoDto result = userService.addRole(userId, settingDto);

        assertEquals(expectedResult, result);
    }

    @Test
    void removeRoleTest() {
        Long userId = 1L;
        UserRoleSettingDto settingDto = new UserRoleSettingDto();
        settingDto.setRoleId(1L);
        ArgumentCaptor<UserAndRoleId> captor = ArgumentCaptor.forClass(UserAndRoleId.class);

        User user = new User();
        user.setId(userId);
        user.setLogin("login");
        user.setPassword("123");
        user.setPersonId(1L);
        user.setRoles(new ArrayList<>());

        Mockito.when(userRepository.findUserWithRolesById(userId))
                .thenReturn(Optional.of(user));

        UserFullInfoDto expectedDto = new UserFullInfoDto();
        expectedDto.setId(userId);
        expectedDto.setPersonId(user.getPersonId());
        expectedDto.setLogin(user.getLogin());
        expectedDto.setRoles(new ArrayList<>());

        UserFullInfoDto result = userService.removeRole(userId, settingDto);

        Mockito.verify(userAndRoleRepository).delete(captor.capture());

        UserAndRoleId capturedId = captor.getValue();

        UserAndRoleId expectedId = new UserAndRoleId();
        expectedId.setUserId(userId);
        expectedId.setRoleId(settingDto.getRoleId());

        assertEquals(expectedDto, result);
        assertEquals(expectedId, capturedId);
    }

    @Test
    void setEmployeeTest() {
        Long userId = 1L;
        UserPeopleSettingDto settingDto = new UserPeopleSettingDto();
        settingDto.setPeopleId(1L);

        User user = new User();
        user.setId(userId);
        user.setLogin("login");
        user.setPassword("password");
        user.setRoles(new ArrayList<>());

        Person person = new Person();
        person.setId(settingDto.getPeopleId());
        person.setLastName("name");
        person.setFirstName("name");

        Mockito.when(userRepository.findUserWithRolesById(userId))
                .thenReturn(Optional.of(user));
        Mockito.when(personRepository.findById(settingDto.getPeopleId()))
                .thenReturn(Optional.of(person));

        UserFullInfoDto expectedResult = new UserFullInfoDto();
        expectedResult.setId(userId);
        expectedResult.setLogin(user.getLogin());
        expectedResult.setRoles(new ArrayList<>());
        expectedResult.setPersonId(person.getId());

        UserFullInfoDto result = userService.setPerson(userId, settingDto);

        assertEquals(expectedResult, result);
    }

    @Test
    void deleteTest() {
        Long userId = 1L;
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);
        userService.delete(userId);
        Mockito.verify(userRepository).delete(longCaptor.capture());
        Long deleted = longCaptor.getValue();
        assertEquals(userId, deleted);
    }
}
