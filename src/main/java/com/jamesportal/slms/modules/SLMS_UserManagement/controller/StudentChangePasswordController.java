
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_UserManagement.controller;

import com.jamesportal.slms.entity.User;
import com.jamesportal.slms.modules.SLMS_UserManagement.dto.UserRequest;
import com.jamesportal.slms.modules.SLMS_UserManagement.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StudentChangePasswordController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value="/StudentFacing/UserManagement/ChangePassword")
    public ModelAndView studentProfileViewGet()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDto", new UserRequest());
        modelAndView.setViewName("modules/frontend/views/UserManagement/ChangePassword");
        return modelAndView;
    }

    @PostMapping(value = "/StudentFacing/UserManagement/ChangePassword")
    public String UserPasswordPost(@ModelAttribute("userRequest") UserRequest userRequest, Authentication authentication, BindingResult result,
                                   RedirectAttributes redirectAttributes, HttpServletRequest request)
    {
        User user = userService.getUserByUsername(authentication.getName());

        if(!userRequest.getOldPassword().isEmpty() && !userRequest.getPassword().isEmpty() && !userRequest.getConfirmPassword().isEmpty()){
            if (userRequest.getPassword().equals(userRequest.getConfirmPassword())){
                if (userService.isPasswordMatch(userRequest.getOldPassword(), user.getPassword())){
                    user.setPassword(userRequest.getPassword());
                    userService.createUser(user);
                    redirectAttributes.addFlashAttribute("success", "Password Updated Successfully.");
                    return  "redirect:/StudentFacing/UserManagement/ChangePassword";
                }else {
                    redirectAttributes.addFlashAttribute("message","Old Password Did Not Match" );
                    String referer = request.getHeader("Referer");
                    return "redirect:"+ referer;
                }
            }else{
                redirectAttributes.addFlashAttribute("message","New Password & Confirm Password Did Not Match" );
            }
        }

        return  "redirect:/StudentFacing/UserManagement/ChangePassword";
    }

}
