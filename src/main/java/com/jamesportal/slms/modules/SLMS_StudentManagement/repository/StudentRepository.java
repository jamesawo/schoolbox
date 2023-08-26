
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.repository;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_AutoComplete.dto.SuggestionDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {



    @Transactional
    @Modifying
    @Query("update Student s set s.status = false where s.id=:id")
    void deactivateStudentById(@Param("id") long id);

    @Transactional
    @Modifying
    @Query("update Student s set s.status = true where s.id=:id")
    void activateStudentById(@Param("id") long id);

    List<Student> findAllByRegNumberContains(String regNumber);


    @Query(value = "select  new com.jamesportal.slms.modules.SLMS_Common.SLMS_AutoComplete.dto.SuggestionDTO(s.id, s.regNumber, s.surname, s.firstName, s.lastName) from Student s where lower(s.regNumber) like lower( concat( '%',:regNumber,'%' ) )" )
    List<SuggestionDTO> findAllByRegNumberLike(@Param("regNumber") String regNumber );

    Student getStudentByRegNumber(String regNumber);
}
