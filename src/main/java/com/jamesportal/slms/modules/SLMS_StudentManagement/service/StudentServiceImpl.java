
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
import com.jamesportal.slms.modules.SLMS_StudentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentSearchService studentSearchService;

    @Autowired
    EntityManager entityManager;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deactivateStudent(long id) {
        studentRepository.deactivateStudentById(id);
    }

    @Override
    public void activateStudent(long id) {
        studentRepository.activateStudentById(id);
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.getOne(id);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public List<SuggestionDTO> autoCompleteSearchStudentByStudentRegNumber(String searchPhrase) {
       return studentRepository.findAllByRegNumberLike(searchPhrase);

    }
    @Override
    public List<Student> searchStudentSingleCriteriaApi(StudentSearchParamDTO searchParamDTO){
        return null;
    }

    @Override
    public List<Student> searchStudentMultipleCriteriaApi(StudentSearchParamDTO searchParamDTO) {
        return null;
    }

    @Override // Get Complete Result Set From Users Search Criteria (Useful For Report Export)
    public List<Student> getStudentReportParameter(StudentSearchParamDTO searchParamDTO) {
      return  studentSearchService.searchStudentReportData(searchParamDTO);
    }

    @Override //Get Pageable Student Result From Users Search Criteria (Useful for Student Search and Table Display)
    public Page<Student> StudentSearchCriteria(StudentSearchParamDTO searchParamDto) {
        return studentSearchService.searchStudents(searchParamDto);
    }

    @Override
    public Student getStudentByRegNumber(String regNumber) {
        return studentRepository.getStudentByRegNumber(regNumber);
    }


}
