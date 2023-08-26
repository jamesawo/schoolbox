
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.repository;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentPassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPassportRepository extends JpaRepository<StudentPassport, Long> {

//    @Query("update StudentPassport s set s.data =:data  where s.id=:id")
//    StudentPassport updateStudentPassportById(@Param("data") byte[] data, @Param("id") long id);
}
