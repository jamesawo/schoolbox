
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_UserManagement.dto;

import com.jamesportal.slms.entity.Permission;
import lombok.Data;

import java.util.Set;

@Data
public class RoleRequest {
    private Long id;
    private String name;
    private String description;
    private Set<Permission> permissions;

}
