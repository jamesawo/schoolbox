
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
import java.util.List;

@Controller
@RequestMapping(value = "/SLMS/UserManagement")
public class PasswordController  {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping( value = "/Password", method = RequestMethod.GET)
    public ModelAndView PasswordIndexGet()
    {
        List<User> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("modules/backend/views/user_management/password/index");

        return modelAndView;
    }

    @RequestMapping( value = "/Password/{id}", method = RequestMethod.GET)
    public ModelAndView UserPasswordGet(@PathVariable("id") Long id)
    {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("userRequest", new UserRequest());
        modelAndView.setViewName("modules/backend/views/user_management/password/editPassword");
        return  modelAndView;
    }

    @RequestMapping( value = "/Password", method = RequestMethod.POST)
    public String UserPasswordPost(@ModelAttribute("userRequest") UserRequest userRequest, BindingResult result,
                                   RedirectAttributes redirectAttributes, HttpServletRequest request)
    {
        User user = userService.getUserById(userRequest.getId());

        if(!userRequest.getPassword().isEmpty() && !userRequest.getConfirmPassword().isEmpty()){

            if (userRequest.getPassword().toString().equals(userRequest.getConfirmPassword().toString())){

                user.setPassword(userRequest.getPassword().toString());
                userService.createUser(user);
                redirectAttributes.addFlashAttribute("message", "Password Updated Successfully.");
                return  "redirect:/SLMS/UserManagement/Password";
            }else {
                String referer = request.getHeader("Referer");
                return "redirect:"+ referer;
            }

        }
        return  "redirect:/SLMS/UserManagement/Password";
    }

    @GetMapping(value = "/Password/ResetPassword")
    public ModelAndView userResetPassword(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDto", new UserRequest());
        modelAndView.setViewName("modules/backend/views/user_management/password/ResetPassword");
        return modelAndView;
    }

    @PostMapping( value = "/Password/ResetPassword")
    public String userResetPasswordPost(@ModelAttribute("userRequest") UserRequest userRequest, Authentication authentication, BindingResult result,
                                        RedirectAttributes redirectAttributes, HttpServletRequest request){
        User user = userService.getUserByUsername(authentication.getName());

        if(!userRequest.getOldPassword().isEmpty() && !userRequest.getPassword().isEmpty() && !userRequest.getConfirmPassword().isEmpty()){
            if (userRequest.getPassword().equals(userRequest.getConfirmPassword())){
                if (userService.isPasswordMatch(userRequest.getOldPassword(), user.getPassword())){
                    user.setPassword(userRequest.getPassword());
                    userService.createUser(user);
                    redirectAttributes.addFlashAttribute("success", "Password Updated Successfully.");
                    return  "redirect:/SLMS/UserManagement/Password/ResetPassword";
                }else {
                    redirectAttributes.addFlashAttribute("message","Old Password Did Not Match" );
                    String referer = request.getHeader("Referer");
                    return "redirect:"+ referer;
                }
            }else{
                redirectAttributes.addFlashAttribute("message","New Password & Confirm Password Did Not Match" );
            }
        }

        return  "redirect:/SLMS/UserManagement/Password/ResetPassword";
    }

}
