
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.service;

import com.jamesportal.slms.entity.Role;

import java.util.List;
import java.util.Set;

public interface IRolesService {

    List<Role> getAllRoles();

    Role saveRole(Role role);

    Role updateRole(Role role);

    void deleteRole(Role role);

    Role getRoleById(Long Id);

    Role findByName(String name);


}
