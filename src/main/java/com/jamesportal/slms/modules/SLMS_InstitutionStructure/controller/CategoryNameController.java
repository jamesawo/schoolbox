
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.controller;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto.CategoryNameDTO;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryNameController {


    @Autowired
    InstitutionStructureServiceX institutionStructureService;


    //CATEGORY NAME INDEX VIEW
    @RequestMapping( value = "/SLMS/InstitutionStructure/CategoryNameSetup")
    public ModelAndView CategoryNameIndexGet()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("catNameDTO", new CategoryNameDTO() );
        modelAndView.addObject("categoryNames", institutionStructureService.getAllCategoryNames());
        modelAndView.setViewName("/modules/backend/views/InstitutionStructure/CategoryNameSetup");
        return modelAndView;
    }



    @RequestMapping( value = "/SLMS/InstitutionStructure/CategoryNameSetup", method = RequestMethod.POST)
    public String CreateCategoryNamePost(@ModelAttribute("catNameDTO") CategoryNameDTO categoryNameDTO, RedirectAttributes redirectAttributes){
//        institutionStructureService.createNewCategoryName(categoryNameDTO);
        institutionStructureService.createCategoryNameFromList(categoryNameDTO);
        redirectAttributes.addFlashAttribute("message", "Category Created Successfully.");
        return "redirect:/SLMS/InstitutionStructure/CategoryNameSetup";

    }


    @RequestMapping(value = "/SLMS/InstitutionStructure/CategoryNameSetup/Edit")
    public String UpdateCategoryNamePost(@ModelAttribute("catNameDTO") CategoryNameDTO catNameDTO, RedirectAttributes redirectAttributes )
    {
        institutionStructureService.editCategoryName(catNameDTO);
        redirectAttributes.addFlashAttribute("message", "Category Updated Successfully.");
        return "redirect:/SLMS/InstitutionStructure/CategoryNameSetup";
    }
}
