
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.controller;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto.SemesterDTO;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SemesterSetupController {

    @Autowired
    InstitutionStructureServiceX institutionStructureServiceX;

    @GetMapping(value = "/SLMS/InstitutionStructure/SemesterSetup")
    public ModelAndView SemesterSetupIndexGet()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("semesterDTO", new SemesterDTO());
        modelAndView.addObject("semesters", institutionStructureServiceX.getAllInstitutionSemester());
        modelAndView.setViewName("/modules/backend/views/InstitutionStructure/SemesterSetup");
        return modelAndView;
    }

   @PostMapping(value = "/SLMS/InstitutionStructure/SemesterSetup")
    public String createSemesterPost(@ModelAttribute SemesterDTO semesterDTO, RedirectAttributes redirectAttributes)
    {
        institutionStructureServiceX.createInstitutionSemesterFromList(semesterDTO);
        redirectAttributes.addFlashAttribute("message", "Created Successfully.");
        return "redirect:/SLMS/InstitutionStructure/SemesterSetup";
    }

    @PostMapping(value = "/SLMS/InstitutionStructure/SemesterSetup/Edit")
    public String updateSemesterPost(@ModelAttribute SemesterDTO semesterDTO, RedirectAttributes redirectAttributes)
    {
        institutionStructureServiceX.updateInstitutionSemester(semesterDTO);
        redirectAttributes.addFlashAttribute("message", "Updated Successfully.");
        return "redirect:/SLMS/InstitutionStructure/SemesterSetup";
    }
}
