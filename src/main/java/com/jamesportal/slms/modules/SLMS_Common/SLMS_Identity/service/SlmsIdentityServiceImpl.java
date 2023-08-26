
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.service;

import com.jamesportal.slms.entity.User;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.entity.SlmsIdentity;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.repository.SlmsIdentityRepository;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlmsIdentityServiceImpl implements ISlmsIdentityService {
    @Autowired
    SlmsIdentityRepository slmsIdentityRepository;

    @Override
    public SlmsIdentity createSlmsIdentity( SlmsIdentity slmsIdentity ) {
        return slmsIdentityRepository.save(slmsIdentity);
    }


    @Override
    public SlmsIdentity getSlmsIdentityByUser(User user) {
        return slmsIdentityRepository.findByUser(user);
    }

    @Override
    public SlmsIdentity getSlmsIdentityByStudent(Student student) {
        return slmsIdentityRepository.findByStudent(student);
    }
}
