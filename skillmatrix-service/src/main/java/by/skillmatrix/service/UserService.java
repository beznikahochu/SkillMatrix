package by.skillmatrix.service;

import by.skillmatrix.dto.user.*;

public interface UserService {
    UserDto create(UserCreationDto creationDto);
    UserFullInfoDto findById(Long id);
    UserFullInfoDto findByLogin(String login);
    UserFullInfoDto addRole(Long id, UserRoleSettingDto settingDto);
    UserFullInfoDto removeRole(Long id, UserRoleSettingDto settingDto);
    UserFullInfoDto setEmployee(Long id, UserEmployeeSettingDto settingDto);
    void delete(Long id);

}
