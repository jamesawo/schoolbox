
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentRegNoUploadController {

    @GetMapping(value = "/SLMS/StudentManagement/StudentRegNoUploadView")
    public ModelAndView studentManagementViewGet()
    {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/modules/backend/views/StudentManagement/StudentRegNoUploadView");
        return modelAndView;
    }
}
