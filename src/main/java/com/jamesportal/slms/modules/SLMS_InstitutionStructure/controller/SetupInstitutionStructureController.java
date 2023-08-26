
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.controller;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto.InstitutionStructureDTO;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SetupInstitutionStructureController {

    @Autowired
    InstitutionStructureServiceX institutionStructureService;

    @GetMapping(value = "/SLMS/InstitutionStructure/Setup_Institution_Structure")
    public ModelAndView SetupInsStrIndexGet()
    {
        ModelAndView modelAndView = new ModelAndView();
        InstitutionCategory leastHierarchy =  institutionStructureService.getInstitutionCategoryByLeastHierarchy();


        modelAndView.addObject("parentCategories", institutionStructureService.getAllCategoryNames() );
        modelAndView.addObject("institutionStructureDTO", new InstitutionStructureDTO());
        modelAndView.addObject("instStrListByLeastHieCategory", institutionStructureService.getInstitutionStructureListByCategory(leastHierarchy)); //List of institution structure by first hierarchy


        modelAndView.setViewName("/modules/backend/views/InstitutionStructure/SetupInstitutionStructure");
        return modelAndView;
    }

    @RequestMapping( value = "/SLMS/InstitutionStructure/Setup_Institution_Structure/Create", method = RequestMethod.POST)
    public String SetupInsStrIndexPost(@ModelAttribute("InstitutionStructureDTO") InstitutionStructureDTO institutionStructureDTO, RedirectAttributes redirectAttributes)
    {
        institutionStructureService.createNewInstitutionStructure(institutionStructureDTO);
        redirectAttributes.addFlashAttribute("message", "Added Successfully.");
        return "redirect:/SLMS/InstitutionStructure/Setup_Institution_Structure";
    }

    @RequestMapping( value = "/SLMS/InstitutionStructure/FindInstitutionStructures/Category/{id}")
    @ResponseBody
    public List<InstitutionStructure> FindInstitutionStructuresByCategory(@PathVariable("id")long id )
    {
        InstitutionCategory institutionCategory = new InstitutionCategory();
        institutionCategory.setId(id);
        return institutionStructureService.getInstitutionStructureListByCategory(institutionCategory);

    }

    @RequestMapping( value = "/SLMS/InstitutionStructure/FindInstitutionStructures/Category/Parent/{id}")
    @ResponseBody
    public List<InstitutionStructure> FindInstitutionStructuresByParent(@PathVariable("id")long id )
    {
        return institutionStructureService.getInstitutionStructureByParent(id);

    }


}
