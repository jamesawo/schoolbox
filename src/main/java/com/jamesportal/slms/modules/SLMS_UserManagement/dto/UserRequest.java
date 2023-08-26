
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_UserManagement.dto;

import com.jamesportal.slms.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserRequest {

    private Long id;

    private String oldPassword;

    private String username;

    private String password;

    private String confirmPassword;

    private String email;

    private Set<Role> roles;

    private boolean status = false;

}
