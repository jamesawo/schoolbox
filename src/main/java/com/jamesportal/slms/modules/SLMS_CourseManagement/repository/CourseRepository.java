
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_CourseManagement.repository;

import com.jamesportal.slms.modules.SLMS_CourseManagement.entity.Course;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionLevel;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSemester;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

    @Transactional
    @Modifying
    @Query("update Course s set s.status = false where s.id=:id")
    void deactivateCourseById(@Param("id") long id);

    @Transactional
    @Modifying
    @Query("update Course s set s.status = true where s.id=:id")
    void activateCourseById(@Param("id") long id);

    Course findById(long id);

    List<Course> findByCourseTitle(String courseTitle);

    List<Course> findByInstitutionStructure(InstitutionStructure institutionStructure);

    List<Course> findByLevel(InstitutionLevel level);

    List<Course> findBySemester(InstitutionSemester semester);



}
