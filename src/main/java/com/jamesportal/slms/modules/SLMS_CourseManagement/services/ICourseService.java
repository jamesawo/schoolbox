
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_CourseManagement.services;

import com.jamesportal.slms.modules.SLMS_CourseManagement.dto.CourseDto;
import com.jamesportal.slms.modules.SLMS_CourseManagement.entity.Course;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionLevel;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSemester;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;

import java.util.List;

public interface ICourseService {

    Course createCourse(Course course);

    Course updateCourse(Course course);

    boolean activateCourse(long courseId);

    boolean deactivateCourse(long courseId);

    Course findCourseById(long courseId);

    List<Course> findCourseByTitle(String courseTitle);

    List<Course> findCourseByInstitutionStructure(InstitutionStructure institutionStructure);

    List<Course> findCourseByLevel(InstitutionLevel level);

    List<Course> findCourseBySemester(InstitutionSemester semester);


}
