
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.repository;

import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentSearchParamDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;

import java.util.List;

// not in use
public interface StudentRepositoryCustom {

    List<Student> searchStudentMultipleCriteriaApi(StudentSearchParamDTO searchParamDTO);

    List<Student> searchStudentSingleCriteriaApi(StudentSearchParamDTO searchParamDTO);



}
