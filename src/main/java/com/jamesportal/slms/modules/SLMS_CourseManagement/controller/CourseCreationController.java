
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_CourseManagement.controller;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Common.Service.CommonServiceWorker;
import com.jamesportal.slms.modules.SLMS_CourseManagement.dto.CourseDto;
import com.jamesportal.slms.modules.SLMS_CourseManagement.services.CourseManagementWorker;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("SLMS/CourseManagement")
public class CourseCreationController {

    @Autowired
    CourseManagementWorker courseManagementWorker;

    @Autowired
    InstitutionStructureServiceX institutionStructureServiceX;

    @Autowired
    CommonServiceWorker commonServiceWorker;

    @GetMapping("/CourseCreation")
    public ModelAndView courseCreationIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("courseDto", new CourseDto());
        modelAndView.addObject("categories", institutionStructureServiceX.getAllCategoryNames());
        modelAndView.addObject("instStrListByLeastHieCategory", institutionStructureServiceX
                .getInstitutionStructureListByCategory(institutionStructureServiceX
                        .getInstitutionCategoryByLeastHierarchy()));
        modelAndView.addObject("insStrFilterMap",  commonServiceWorker.getInstitutionStructureFilterMap());


        modelAndView.setViewName("/modules/backend/views/CourseManagement/CourseCreation");
        return modelAndView;
    }

    @PostMapping("/CourseCreation")
    public String createCourse(@ModelAttribute CourseDto courseDto, RedirectAttributes redirectAttributes,
                               HttpServletRequest request){
        String referer = request.getHeader("Referer");
        try{
            courseManagementWorker.createCourse(courseDto);
            redirectAttributes.addFlashAttribute("message", "Course Created Successfully.");
            return "redirect:"+ referer;

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed:" + e.toString());
            return "redirect:"+ referer;

        }

    }
}
