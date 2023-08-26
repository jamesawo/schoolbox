
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_UserManagement.service;

import com.jamesportal.slms.entity.User;
import com.jamesportal.slms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUsersService {

    @Qualifier("userRepository")
    @Autowired
    UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getUserById(Long Id) { return userRepository.getOne(Id); }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void enableUserAccount(Long Id)
    {
        userRepository.getOne(Id).setStatus(true);
    }

    @Override
    public void disableUserAccount(Long Id)
    {
        userRepository.getOne(Id).setStatus(false);
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String dbPassword) {
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, dbPassword);
    }

    @Override
    public User getUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }


}
