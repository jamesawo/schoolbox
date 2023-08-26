
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.controller;

import com.jamesportal.slms.entity.User;
import com.jamesportal.slms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LoginEventListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent>{
    @Qualifier("userRepository")
    @Autowired
    UserRepository userRepository;

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event)
    {
        UserDetails user = (UserDetails) event.getAuthentication().getPrincipal();
        User authUser = userRepository.findByUsername(user.getUsername());
        authUser.setLastLogin(LocalDateTime.now());
        userRepository.save(authUser);
    }

}
