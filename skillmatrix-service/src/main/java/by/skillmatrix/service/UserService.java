package by.skillmatrix.service;

import by.skillmatrix.dto.user.*;
import by.skillmatrix.param.PageParams;

import java.util.List;

/**
 * Service for working with Users.
 */
public interface UserService {

    /**
     * Create new User.
     *
     * @param creationDto new User
     * @return created user
     */
    UserDto create(UserCreationDto creationDto);

    /**
     * Find User by id.
     *
     * @param id id of User
     * @return found SkillMatrixScheme
     */
    UserFullInfoDto findById(Long id);

    /**
     * Find User page.
     *
     * @param page page params
     * @param sort sort type
     * @return found users
     */
    List<UserFullInfoDto> findAll(PageParams page, String sort);

    /**
     * Add role for user.
     *
     * @param id user id
     * @param settingDto dto with role id
     * @return updated user
     */
    UserFullInfoDto addRole(Long id, UserRoleSettingDto settingDto);

    /**
     * Remove user's role
     *
     * @param id user id
     * @param settingDto dto with role id
     * @return updated user
     */
    UserFullInfoDto removeRole(Long id, UserRoleSettingDto settingDto);

    /**
     * Set person for user
     *
     * @param id user id
     * @param settingDto dto with role id
     * @return updated user
     */
    UserFullInfoDto setPerson(Long id, UserPeopleSettingDto settingDto);

    /**
     * Delete user by id
     *
     * @param id user id
     */
    void delete(Long id);
}
