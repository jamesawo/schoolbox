
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.controller;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Common.Service.CommonServiceWorker;
import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.SFeeSearchParamDto;
import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.SpecificFeeDTO;
import com.jamesportal.slms.modules.SLMS_FeeManagement.service.FeeCategoryServiceWorker;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class SpecificFeeController {

    @Autowired
    FeeCategoryServiceWorker feeCategoryServiceWorker;

    @Autowired
    InstitutionStructureServiceX institutionStructureServiceX;

    @Autowired
    CommonServiceWorker commonServiceWorker;

    @GetMapping( value = "SLMS/FeeManagement/SpecificFeeCreation")
    public ModelAndView specificFeeIndexGet()
    {
        ModelAndView modelAndView = new ModelAndView();
        InstitutionCategory leastHierarchy =  institutionStructureServiceX.getInstitutionCategoryByLeastHierarchy();
        modelAndView.addObject("instStrListByLeastHieCategory", institutionStructureServiceX.getInstitutionStructureListByCategory(leastHierarchy));
        modelAndView.addObject("categories", institutionStructureServiceX.getAllCategoryNames());
        modelAndView.addObject("insStrFilterMap",  commonServiceWorker.getInstitutionStructureFilterMap());
        modelAndView.addObject("feeCatFilterMap", commonServiceWorker.getFeeManagementFilterMap());
        modelAndView.addObject("specificFeeDTO", new SpecificFeeDTO());
        modelAndView.addObject("sFeeSearchParamDto", new SFeeSearchParamDto());

        modelAndView.setViewName("/modules/backend/views/FeesManagement/SpecificFeeCreation");
        return  modelAndView;
    }

    @PostMapping(value = "SLMS/FeeManagement/SpecificFeeCreation/Create")
    public String createSpecificAndGlobalFee(@ModelAttribute SpecificFeeDTO specificFeeDTO, RedirectAttributes redirectAttributes) {

        // TODO::VALIDATE CLIENT AND SERVER
        switch (specificFeeDTO.getCommand()){
            //General is same as specific fee
            case ("General"):
                    feeCategoryServiceWorker.createSpecificFee(specificFeeDTO);
                    redirectAttributes.addFlashAttribute("message", "Fee Created Successfully.");
                break;
            case ("Global"):
                    feeCategoryServiceWorker.createGlobalFee(specificFeeDTO);
                    redirectAttributes.addFlashAttribute("message", "Global Fee Created Successfully.");
                break;

            default:
                redirectAttributes.addFlashAttribute("error", "Nothing was created!");
        }
        return "redirect:/SLMS/FeeManagement/SpecificFeeCreation";
    }

    @RequestMapping("/SLMS/FeeManagement/SpecificFeeCreation/SearchParams")
    public RedirectView searchSpecificFee(SFeeSearchParamDto sFeeSearchParamDto, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("searchResult", feeCategoryServiceWorker.searchSpecificFee(sFeeSearchParamDto));
        redirectAttributes.addFlashAttribute("userSearchParams", sFeeSearchParamDto);
        return new RedirectView("/SLMS/FeeManagement/SpecificFeeCreation", true);

    }

}
