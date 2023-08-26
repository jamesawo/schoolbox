
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_CourseManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("SLMS/CourseManagement")
public class LecturerApprovedCourseListController {

    @GetMapping("/LecturerApprovedCourseList")
    public ModelAndView lecturerApprovedCourseList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/modules/backend/views/CourseManagement/LecturerApprovedCourseList");
        return modelAndView;
    }
}
