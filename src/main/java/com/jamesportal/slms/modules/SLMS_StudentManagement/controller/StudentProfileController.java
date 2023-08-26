
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.controller;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.service.LocationServiceWorker;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.service.GlobalSettingServiceWorker;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.service.StudentServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "")
public class StudentProfileController {


    @Autowired
    StudentServiceWorker studentServiceWorker;

    @Autowired
    InstitutionStructureServiceX institutionStructureServiceX;

    @Autowired
    LocationServiceWorker locationServiceWorker;

    @Autowired
    GlobalSettingServiceWorker globalSettingServiceWorker;

    @GetMapping( value = "/StudentFacing/StudentManagement/StudentProfileView")
    //Todo::refactor entire class use one common studentProfileDto
    public ModelAndView studentProfileViewGet(Authentication authentication)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", studentServiceWorker.getAuthUserStudentDetails(authentication.getName()));
        modelAndView.addObject("institutionCategories", institutionStructureServiceX.getAllCategoryNames());
        modelAndView.addObject( "studentDTO", new StudentDTO());
        modelAndView.addObject("states", locationServiceWorker.getAllLocationState());
        modelAndView.addObject("countries", locationServiceWorker.getAllLocationCountry());
        modelAndView.addObject("studentConfigMap", globalSettingServiceWorker.getStudentSpecificConfigsKeyValue());

        modelAndView.setViewName("modules/frontend/views/StudentManagement/StudentProfileView");
        return modelAndView;
    }

    @PostMapping(value = "/StudentFacing/StudentManagement/StudentProfileView")
    public String studentUpdateStudentProfilePost(@ModelAttribute StudentDTO studentDTO, RedirectAttributes redirectAttributes){
        try{
            studentServiceWorker.updateStudent(studentDTO);
            redirectAttributes.addFlashAttribute("success","Profile Updated Successfully.");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/StudentFacing/StudentManagement/StudentProfileView";
    }

}
