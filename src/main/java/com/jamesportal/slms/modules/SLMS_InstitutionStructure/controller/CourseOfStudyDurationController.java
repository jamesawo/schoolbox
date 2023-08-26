
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.controller;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto.CourseOfStudyDTO;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CourseOfStudyDurationController {

    @Autowired
    InstitutionStructureServiceX institutionStructureServiceX;

    @GetMapping(value = "/SLMS/InstitutionStructure/CourseOfStudyDuration")
    public ModelAndView CourseOfStudyDurationIndexGet()
    {
        ModelAndView modelAndView = new ModelAndView();

        InstitutionCategory leastHierarchy =  institutionStructureServiceX.getInstitutionCategoryByLeastHierarchy();

        modelAndView.addObject("categories", institutionStructureServiceX.getAllCategoryNames()); //List of category names
        modelAndView.addObject("instStrListByLeastHieCategory", institutionStructureServiceX.getInstitutionStructureListByCategory(leastHierarchy)); //List of institution structure by first hierarchy
        modelAndView.addObject("programmeCategories", institutionStructureServiceX.getAllProgrammeCategory()); //List of institution structure by first hierarchy
        modelAndView.addObject("courseDurationDTO", new CourseOfStudyDTO());
        modelAndView.setViewName("/modules/backend/views/InstitutionStructure/CourseOfStudyDuration");

        return modelAndView;
    }

    @PostMapping(value = "/SLMS/InstitutionStructure/CourseOfStudyDuration")
    public String CourseOfStudyDurationPost(@ModelAttribute CourseOfStudyDTO courseOfStudyDTO, RedirectAttributes redirectAttributes)
    {
        institutionStructureServiceX.createInstitutionCourseDurationByList(courseOfStudyDTO);
        redirectAttributes.addFlashAttribute("message", "Added Successfully.");

        return "redirect:/SLMS/InstitutionStructure/CourseOfStudyDuration";
    }
}
