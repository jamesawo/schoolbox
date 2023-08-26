
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_UserManagement.controller;

import com.jamesportal.slms.entity.Role;
import com.jamesportal.slms.entity.User;
import com.jamesportal.slms.modules.SLMS_UserManagement.dto.UserRequest;
import com.jamesportal.slms.modules.SLMS_UserManagement.service.UserServiceImpl;
import com.jamesportal.slms.modules.SLMS_UserManagement.service.UserServiceWorker;
import com.jamesportal.slms.service.RolesServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping(value = "/SLMS/UserManagement")
public class UsersController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RolesServicesImpl rolesServicesImpl;

    @Value("${app.user.default.password}")
    private String password;

    @Autowired
    UserServiceWorker userServiceWorker;

   /*
    VERB: GET
    RETURN USERS LIST TO USERS PAGE
    */
    @RequestMapping( value = "/Users", method = RequestMethod.GET)
    public ModelAndView UserIndex()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userServiceImpl.getAllUsers();

        modelAndView.addObject("users", users);
        modelAndView.setViewName("modules/backend/views/user_management/users/index");
        return modelAndView;
    }

    /*
        VERB: GET
        DISPLAY ADD A NEW USER PAGE
    */

    @RequestMapping(value = "/Users/Create", method = RequestMethod.GET)
    public ModelAndView UserCreateGet()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = rolesServicesImpl.getAllRoles();
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("userRequest", new UserRequest());
        modelAndView.setViewName("modules/backend/views/user_management/users/createUser");
        return  modelAndView;
    }

    /*

        CREATE A NEW USER
        USERS ACCOUNT WILL BE DEACTIVATED ON CREATE
        ADMIN SHOULD MANUALLY CALL UP THE NEW CREATED USER AND ENABLE ACCOUNT
        TODO::ADD VALIDATION
        TODO::RESOLVE BUG USER WITH ROLE BUT NO PERMISSION CANT LOGIN
   */
    @PostMapping(value = "/Users/Create")
    public String UserCreatePost(@ModelAttribute("userRequest") UserRequest userRequest,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes)
    {


        userServiceWorker.createUser(userRequest);
        redirectAttributes.addFlashAttribute("message", "User Saved Successfully.");
        return "redirect:/SLMS/UserManagement/Users";
    }

    /*
        GET REQUEST
        DISPLAY USER EDIT PAGE
        SET FORM FIELDS WITH USER DETAIL
        TODO::VALIDATE USER ID IN PATH VARIABLE (IF NOT EXIST HANDLE PROPERLY)
     */
    @GetMapping("/Users/Edit/{id}")
    public ModelAndView UserEditGet(@PathVariable("id") Long id)
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = rolesServicesImpl.getAllRoles();
        User user = userServiceImpl.getUserById(id);
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("user", user);
        modelAndView.addObject("userRequest", new UserRequest());
        modelAndView.setViewName("modules/backend/views/user_management/users/editUser");
        return  modelAndView;
    }


    /*
           POST REQUEST
           UPDATE USER DETAILS
        */
    @RequestMapping(value = "/Users/Edit", method = RequestMethod.POST)
    public String UserEditPost(@ModelAttribute("userRequest") UserRequest userRequest,
                               BindingResult result, RedirectAttributes redirectAttributes)
    {
        User userToUpdate = userServiceImpl.getUserById(userRequest.getId());
        userToUpdate.setRoles(userRequest.getRoles());
        userToUpdate.setEmail(userRequest.getEmail());
        userToUpdate.setUsername(userRequest.getUsername());
//        if(!userRequest.getPassword().isEmpty()){
//            userToUpdate.setPassword(userRequest.getPassword());
//        }
        userServiceImpl.updateUser(userToUpdate);
        redirectAttributes.addFlashAttribute("message", "User Updated Successfully.");

        return "redirect:/SLMS/UserManagement/Users";
    }


    /*
        UPDATE USER ACCOUNT STATUS
        CHECK IF ENABLED THEN DISABLE
     */

    @RequestMapping(value = "/Users/Status/{id}", method = RequestMethod.GET)
    public String SetUserAccountStatus(@PathVariable("id") Long Id, RedirectAttributes redirectAttributes)
    {
        User user = userServiceImpl.getUserById(Id);
        if (user.getStatus() == true ) {
            user.setStatus(false);
            redirectAttributes.addFlashAttribute("message", "User Status Toggled (Disable).");
        }else if (user.getStatus() == false) {
            user.setStatus(true);
            redirectAttributes.addFlashAttribute("message", "User Status Toggled (Enable).");

        }
        userServiceImpl.updateUser(user);

        return "redirect:/SLMS/UserManagement/Users";
    }

}
