
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.controller;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto.LevelDTO;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionLevel;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LevelSetupController {

    @Autowired
    InstitutionStructureServiceX institutionStructureServiceX;

    @GetMapping( value = "/SLMS/InstitutionStructure/LevelSetup")
    public ModelAndView LevelSetupIndexGet()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("levelDTO", new LevelDTO());
        modelAndView.addObject("institutionLevels", institutionStructureServiceX.getAllInstitutionLevel());
        modelAndView.setViewName("/modules/backend/views/InstitutionStructure/LevelSetup");
        return modelAndView;
    }

    @RequestMapping(value = "/SLMS/InstitutionStructure/LevelSetup",method = RequestMethod.POST)
    public String LevelSetupCreatePost(@ModelAttribute("levelDTO")LevelDTO levelDTO , RedirectAttributes redirectAttributes)
    {

        institutionStructureServiceX.createInstitutionLevel(levelDTO);
        redirectAttributes.addFlashAttribute("message", "Created Successfully.");

        return "redirect:/SLMS/InstitutionStructure/LevelSetup";
    }

    @RequestMapping(value = "/SLMS/InstitutionStructure/LevelSetup/Edit",method = RequestMethod.POST)
    public String LevelSetupUpdatePost(@ModelAttribute("levelDTO")LevelDTO levelDTO , RedirectAttributes redirectAttributes)
    {

        institutionStructureServiceX.updateInstitutionLevel(levelDTO);
        redirectAttributes.addFlashAttribute("message", "Updated Successfully.");

        return "redirect:/SLMS/InstitutionStructure/LevelSetup";
    }

}
