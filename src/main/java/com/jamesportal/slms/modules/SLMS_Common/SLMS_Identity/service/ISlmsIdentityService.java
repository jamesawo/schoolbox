
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
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;

public interface ISlmsIdentityService {

    SlmsIdentity createSlmsIdentity( SlmsIdentity slmsIdentity );

    SlmsIdentity getSlmsIdentityByUser ( User user);

    SlmsIdentity getSlmsIdentityByStudent( Student student);

}
