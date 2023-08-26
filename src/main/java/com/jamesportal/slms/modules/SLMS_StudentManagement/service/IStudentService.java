
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_AutoComplete.dto.SuggestionDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentSearchParamDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IStudentService {

    Student createStudent(Student student);

    void updateStudent(Student student);

    void deactivateStudent(long id);

    void activateStudent(long id);

    Student getStudentById(long id);

    List<Student> getAllStudent();

    List<SuggestionDTO> autoCompleteSearchStudentByStudentRegNumber(String name);

    List<Student> searchStudentSingleCriteriaApi(StudentSearchParamDTO searchParamDTO);

    List<Student> searchStudentMultipleCriteriaApi(StudentSearchParamDTO searchParamDTO);

    List<Student> getStudentReportParameter(StudentSearchParamDTO searchParamDTO);

    Page<Student> StudentSearchCriteria(StudentSearchParamDTO searchParamDto);

    Student getStudentByRegNumber(String regNumber);


}
