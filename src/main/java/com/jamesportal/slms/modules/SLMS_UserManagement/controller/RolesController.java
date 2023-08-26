
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_UserManagement.controller;

import com.jamesportal.slms.entity.Permission;
import com.jamesportal.slms.entity.Role;
import com.jamesportal.slms.modules.SLMS_UserManagement.dto.RoleRequest;
import com.jamesportal.slms.repository.RoleRepository;
import com.jamesportal.slms.service.PermissionServiceImpl;
import com.jamesportal.slms.service.RolesServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/SLMS/UserManagement")
public class RolesController {
    @Qualifier("roleRepository")
    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    RolesServicesImpl rolesServicesImpl;

    @Autowired
    PermissionServiceImpl permissionServiceImpl;


    /*
        @GET REQUEST
        DISPLAY ROLES LIST PAGE
        @MODELVIEW(RETURN LIST OF ROLES)

     */
    @GetMapping("/Roles")
    public ModelAndView GetRoles()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = rolesServicesImpl.getAllRoles();
        modelAndView.addObject("roles", roles);
        modelAndView.setViewName("modules/backend/views/user_management/roles/index");
        return modelAndView;
    }

    /*
        @GET
        CREATE ROLE PAGE
        @RETURN MAP<LIST OF PERMISSION GROUPED BY MODULE NAME
     */
    @GetMapping("/Role/Create")
    public ModelAndView CreateRole()
    {
        ModelAndView modelAndView = new ModelAndView();

        List<Permission> permissions = permissionServiceImpl.getAllPermissions();

        Map<String,List<Permission>> permissionGroupByModule = new HashMap<>();
        permissionGroupByModule =  permissions.stream()
                .collect(Collectors.groupingBy(Permission::getGroup));

        modelAndView.addObject("permissionGroupByModule", permissionGroupByModule);
        modelAndView.addObject("roleRequest", new RoleRequest() );
        modelAndView.setViewName("modules/backend/views/user_management/roles/createRole");
        return modelAndView;
    }

    /*
        @POST
        CREATE NEW ROLE
     */
    @RequestMapping(value = "/Role/Create", method = RequestMethod.POST)
    public String CreateRolePost(@ModelAttribute("roleRequest") RoleRequest roleRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        Role role = new Role();
        role.setName(roleRequest.getName().toUpperCase());
        role.setDescription(roleRequest.getDescription().toUpperCase());
        role.setPermissions( (Set<Permission>) roleRequest.getPermissions());
        rolesServicesImpl.saveRole(role);
        redirectAttributes.addFlashAttribute("message", "Saved Successfully.");

        return "redirect:/SLMS/UserManagement/Roles";
    }

    /*
        @GET
        EDIT ROLE PAGE
        TODO::VALIDATE REQUEST (ID)
     */
    @GetMapping("/Role/Edit/{id}")
    public ModelAndView EditRoleGet(@PathVariable("id") Long id)
    {
        ModelAndView modelAndView = new ModelAndView();

        Role role = rolesServicesImpl.getRoleById(id);

        List<Permission> permissions = permissionServiceImpl.getAllPermissions();
        Map<String,List<Permission>> permissionGroupByModule = new HashMap<>();
        permissionGroupByModule =  permissions.stream()
                .collect(Collectors.groupingBy(Permission::getGroup));

        modelAndView.addObject("role", role);
        modelAndView.addObject("permissionGroupByModule", permissionGroupByModule);
        modelAndView.addObject("roleRequest", new RoleRequest() );

        modelAndView.setViewName("modules/backend/views/user_management/roles/editRole");
        return modelAndView;
    }

    @RequestMapping(value = "/Role/Edit", method = RequestMethod.POST)
    public String EditRolePost(@ModelAttribute("roleRequest") RoleRequest roleRequest,
                               BindingResult result, RedirectAttributes redirectAttributes)
    {
        Role roleToUpdate = rolesServicesImpl.getRoleById(roleRequest.getId());
        roleToUpdate.setPermissions(roleRequest.getPermissions());
        roleToUpdate.setDescription(roleRequest.getDescription().toUpperCase());
        roleToUpdate.setName(roleRequest.getName().toUpperCase());
        rolesServicesImpl.saveRole(roleToUpdate);
        redirectAttributes.addFlashAttribute("message", "Updated Successfully.");

        return "redirect:/SLMS/UserManagement/Roles";
    }

    @GetMapping("/Role/Delete/{id}")
    public String DeleteRoleGet(@PathVariable("id") Long id){
        //disable role - delete is not allowed.
        return "redirect:/SLMS/UserManagement/Roles";
    }

}
