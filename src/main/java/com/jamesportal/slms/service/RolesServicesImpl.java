
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.service;

import com.jamesportal.slms.entity.Role;
import com.jamesportal.slms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RolesServicesImpl implements IRolesService {
    @Qualifier("roleRepository")
    @Autowired
    public RoleRepository roleRepository;
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
            return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Role getRoleById(Long Id) {
        return roleRepository.getOne(Id);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
