
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_UserManagement.service;

import com.jamesportal.slms.entity.Role;
import com.jamesportal.slms.entity.User;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentDTO;
import com.jamesportal.slms.modules.SLMS_UserManagement.dto.UserRequest;
import com.jamesportal.slms.service.RolesServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceWorker {
    public static final String STUDENT_DEFAULT_PASSWORD = "password";
    private final UserServiceImpl userServiceImpl;
    private final RolesServicesImpl rolesServicesImpl;

    public void createUser(UserRequest userRequest)
    {
        User user = new User();

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setStatus(userRequest.isStatus());
        user.setPassword(user.getPassword());
        Set<Role> roles = userRequest.getRoles();
        user.setRoles(roles);

        userServiceImpl.createUser(user);
    }

    public User createUserForNewStudent(StudentDTO studentDTO)
    {
        Role role = rolesServicesImpl.findByName("ROLE_STUDENT");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setEmail(studentDTO.getEmail());
        user.setUsername(studentDTO.getRegNumber());
        user.setStatus(true);
        user.setPassword(STUDENT_DEFAULT_PASSWORD);
        user.setRoles(roles);
        return userServiceImpl.createUser(user);
    }

    public User getUserByUsername(String username)
    {
        return userServiceImpl.getUserByUsername(username);
    }

    public Long getUserIdByUsername(String username)
    {
        User user = getUserByUsername(username);
        return user.getId();
    }
}
