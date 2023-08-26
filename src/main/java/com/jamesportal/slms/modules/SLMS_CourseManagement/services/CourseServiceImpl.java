
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_CourseManagement.services;

import com.jamesportal.slms.modules.SLMS_CourseManagement.entity.Course;
import com.jamesportal.slms.modules.SLMS_CourseManagement.repository.CourseRepository;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionLevel;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSemester;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public boolean activateCourse(long courseId) {
        boolean res = false;
        try{
            courseRepository.activateCourseById(courseId);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean deactivateCourse(long courseId) {
        boolean res = false;
        try{
            courseRepository.deactivateCourseById(courseId);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Course findCourseById(long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public List<Course> findCourseByTitle(String courseTitle) {
        return courseRepository.findByCourseTitle(courseTitle);
    }

    @Override
    public List<Course> findCourseByInstitutionStructure(InstitutionStructure institutionStructure) {
        return courseRepository.findByInstitutionStructure(institutionStructure);
    }

    @Override
    public List<Course> findCourseByLevel(InstitutionLevel level) {
        return courseRepository.findByLevel(level);
    }

    @Override
    public List<Course> findCourseBySemester(InstitutionSemester semester) {
        return courseRepository.findBySemester(semester);
    }
}
