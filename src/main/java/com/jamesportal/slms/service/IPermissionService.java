
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.service;

import com.jamesportal.slms.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPermissionService  {
    List<Permission> getAllPermissions();

}
