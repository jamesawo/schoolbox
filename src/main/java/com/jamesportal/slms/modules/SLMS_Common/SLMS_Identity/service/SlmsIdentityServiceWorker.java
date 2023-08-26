
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.service;

import com.jamesportal.slms.entity.User;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.dto.SlmsIdentityDTO;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.entity.SlmsIdentity;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlmsIdentityServiceWorker {

    @Autowired
    SlmsIdentityServiceImpl slmsIdentityService;

    public SlmsIdentity createSlmsIdentity(SlmsIdentityDTO slmsIdentityDTO){
        SlmsIdentity slmsIdentity = new SlmsIdentity();

        slmsIdentity.setUser( new User(slmsIdentityDTO.getUserId() ) );
        slmsIdentity.setStudent( new Student( slmsIdentityDTO.getStudentId() ) );

        return  slmsIdentityService.createSlmsIdentity(slmsIdentity);
    }



    public SlmsIdentity getSlmsIdentityByUser(User user){
        return slmsIdentityService.getSlmsIdentityByUser(user);
    }

    public SlmsIdentity getSlmsIdentityByStudent(Student student){
        return slmsIdentityService.getSlmsIdentityByStudent(student);
    }


}
