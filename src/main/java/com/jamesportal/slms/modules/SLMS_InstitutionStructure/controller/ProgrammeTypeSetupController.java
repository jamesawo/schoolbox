
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.controller;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto.ProgrammeTypeDTO;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository.CategoryNameRepository;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProgrammeTypeSetupController {

    @Autowired
    InstitutionStructureServiceX institutionStructureServiceX;

    @GetMapping( value = {"/SLMS/InstitutionStructure/ProgrammeTypeSetup" } )
    public ModelAndView ProgrammeTypeSetupIndexGet()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("programmeTypeDTO", new ProgrammeTypeDTO());
        modelAndView.addObject("programmeCategories", institutionStructureServiceX.getAllProgrammeCategory());
        modelAndView.setViewName("/modules/backend/views/InstitutionStructure/ProgrammeTypeSetup");
        return modelAndView;

    }

    @RequestMapping( value = "/SLMS/InstitutionStructure/ProgrammeTypeCSetup/", method = RequestMethod.POST)
    public ModelAndView ProgrammeTypeSetupIndexGet(@ModelAttribute("programmeTypeDTO") ProgrammeTypeDTO programmeTypeDTO)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("programmeTypeDTO", new ProgrammeTypeDTO());
        modelAndView.addObject("programmeCategories", institutionStructureServiceX.getAllProgrammeCategory());
        if (programmeTypeDTO.getReportType() == 1){
            System.out.println("Getting Programme Category");
            modelAndView.addObject("programmeCategoryList", institutionStructureServiceX.getAllProgrammeCategory());
        }else if(programmeTypeDTO.getReportType() == 2){
            System.out.println("Getting Programme Types");
            modelAndView.addObject("programmeTypeList", institutionStructureServiceX.getAllProgrammeType());
        }
        modelAndView.setViewName("/modules/backend/views/InstitutionStructure/ProgrammeTypeSetup");
        return modelAndView;

    }


    @RequestMapping( value = "/SLMS/InstitutionStructure/ProgrammeTypeSetup/", method = RequestMethod.POST)
    public String createProgrammeTypePost(@ModelAttribute("programmeTypeDTO") ProgrammeTypeDTO programmeTypeDTO, ModelMap model, RedirectAttributes redirectAttributes )
    {

        if (programmeTypeDTO.getInstitutionProgrammeCategoryId() == 0 && programmeTypeDTO.getDescription() != null){
            institutionStructureServiceX.createProgrammeCategory(programmeTypeDTO);
            redirectAttributes.addFlashAttribute("message", "Created Programme Category Successfully.");

        }else if(programmeTypeDTO.getInstitutionProgrammeCategoryId() > 0 && programmeTypeDTO.getDescription() != null){
            institutionStructureServiceX.createProgrammeType(programmeTypeDTO);
            redirectAttributes.addFlashAttribute("message", "Created Programme Type Successfully.");
        }

        return "redirect:/SLMS/InstitutionStructure/ProgrammeTypeSetup/";
    }

    /*
        TODO::Move this method to common
     */
    @RequestMapping( value = "/SLMS/InstitutionStructure/ProgrammeTypeSetup/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ProgrammeTypeDTO> getProgrammeTypeListInProgrammeCategory(@PathVariable("id") Long id) {
        return institutionStructureServiceX.getInstitutionProgrammeTypeByProgrammeCategory(id);
    }

    @RequestMapping(value = "/SLMS/InstitutionStructure/ProgrammeType/Edit", method = RequestMethod.POST)
    public String updateProgrammeTypePost(@ModelAttribute ProgrammeTypeDTO programmeTypeDTO, RedirectAttributes redirectAttributes )
    {
        if ( programmeTypeDTO.getReportType() == 1 ){

            institutionStructureServiceX.updateProgrammeCategory(programmeTypeDTO);
            redirectAttributes.addFlashAttribute("message", "Updated Programme Category Successfully.");

        }else if( programmeTypeDTO.getReportType() == 2 ){

            institutionStructureServiceX.updateInstitutionProgrammeType(programmeTypeDTO);
            redirectAttributes.addFlashAttribute("message", "Updated Programme Type Successfully.");
        }

        return "redirect:/SLMS/InstitutionStructure/ProgrammeTypeSetup";
    }



}
