
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentPassport;

public interface IStudentPassportService {

    StudentPassport createStudentPassport(StudentPassport passport);

    StudentPassport getStudentPassportById(Long id);

    StudentPassport updateStudentPassport(StudentPassport passport);

}
