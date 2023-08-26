
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentPassport;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.repository.StudentPassportRepository;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentPassportServiceImpl implements IStudentPassportService {
    @Autowired
    StudentPassportRepository passportRepository;

    @Override
    public StudentPassport createStudentPassport(StudentPassport passport) {
        return passportRepository.save(passport);
    }

    @Override
    public StudentPassport getStudentPassportById(Long id) {
       return passportRepository.getOne(id);
    }

    @Override
    public StudentPassport updateStudentPassport(StudentPassport passport) {
        return passportRepository.save(passport);
    }
}
