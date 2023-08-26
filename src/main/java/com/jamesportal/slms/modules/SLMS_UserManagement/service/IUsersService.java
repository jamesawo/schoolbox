
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_UserManagement.service;

import com.jamesportal.slms.entity.User;

import java.util.List;

public interface IUsersService {

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    User getUserById(Long Id);

    List<User> getAllUsers();

    User getUserByUsername(String username);

    void enableUserAccount(Long Id);

    void disableUserAccount(Long Id);

    boolean isPasswordMatch(String newPassword, String oldPassword);


}
