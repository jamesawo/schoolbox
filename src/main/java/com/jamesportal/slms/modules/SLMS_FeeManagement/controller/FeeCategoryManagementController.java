
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.controller;

import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.FeeCategoryDTO;
import com.jamesportal.slms.modules.SLMS_FeeManagement.service.FeeCategoryServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FeeCategoryManagementController {

    @Autowired
    FeeCategoryServiceWorker feeCategoryServiceWorker;

    @GetMapping( value = "SLMS/FeeManagement/FeeCategorySetup" )
    public ModelAndView feeCategoryManagementIndexGet()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("feeCategoryDTO", new FeeCategoryDTO());
        modelAndView.addObject("feeCategories", feeCategoryServiceWorker.getAllFeeCategory());
        modelAndView.setViewName("/modules/backend/views/FeesManagement/FeeCategorySetup");
        return modelAndView;
    }

    @PostMapping( value = "SLMS/FeeManagement/FeeCategorySetup/Create")
    public String createFeeCategory(@ModelAttribute FeeCategoryDTO feeCategoryDTO, RedirectAttributes redirectAttributes)
    {
        feeCategoryServiceWorker.createFeeCategory(feeCategoryDTO);
        redirectAttributes.addFlashAttribute("message", "Fee Category Created Successfully.");
        return "redirect:/SLMS/FeeManagement/FeeCategorySetup";
    }

    @PostMapping( value = "SLMS/FeeManagement/FeeCategorySetup/Edit")
    public String updateFeeCategory(@ModelAttribute FeeCategoryDTO feeCategoryDTO, RedirectAttributes redirectAttributes)
    {
        feeCategoryServiceWorker.updateFeeCategory(feeCategoryDTO);
        redirectAttributes.addFlashAttribute("message", "Fee Category Updated Successfully.");
        return "redirect:/SLMS/FeeManagement/FeeCategorySetup";
    }
}
