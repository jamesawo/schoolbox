
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseManagementWorker {

    @Autowired
    CourseServiceImpl courseService;

    /*
        **************************************
        Course Creation Block
        ****************************************

     */

    public Course createCourse(CourseDto courseDto){
        Course course = new Course();
        course.setCourseTitle(courseDto.getCourseTitle());
        course.setCourseUnit(courseDto.getCourseUnit());
        course.setInstitutionStructure(courseDto.getInstitutionStructure());
        course.setLevel(courseDto.getLevel());
        course.setSemester(courseDto.getSemester());
        return courseService.createCourse(course);
    }

    public Course updateCourse(CourseDto courseDto){
        Course course = courseService.findCourseById(courseDto.getId());
        course.setCourseTitle(courseDto.getCourseTitle());
        course.setCourseUnit(courseDto.getCourseUnit());
        course.setInstitutionStructure(courseDto.getInstitutionStructure());
        course.setLevel(courseDto.getLevel());
        course.setSemester(courseDto.getSemester());
        return courseService.createCourse(course);
    }

    public boolean activateCourse(long courseId){
        return courseService.activateCourse(courseId);
    }

    public boolean deactivateCourse(long courseId){
        return courseService.deactivateCourse(courseId);
    }

    public Course findCourseById(long id){
        return  courseService.findCourseById(id);
    }

    public List<Course> findCourseByTitle(CourseDto courseDto){
        return courseService.findCourseByTitle(courseDto.getCourseTitle());
    }

    public List<Course> findCourseByInstitutionStructure(CourseDto courseDto){
        return courseService.findCourseByInstitutionStructure(courseDto.getInstitutionStructure());
    }

    public List<Course> findCourseByLevel(CourseDto courseDto){
        return courseService.findCourseByLevel(courseDto.getLevel());
    }

    public List<Course> findCourseBySemester(CourseDto courseDto){
        return courseService.findCourseBySemester(courseDto.getSemester());
    }

}

