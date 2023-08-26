
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.controller;

import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.FeePaymentDto;
import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.SFeeSearchParamDto;
import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.SpecificFeeDTO;
import com.jamesportal.slms.modules.SLMS_FeeManagement.service.FeeCategoryServiceWorker;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import com.jamesportal.slms.modules.SLMS_StudentManagement.service.StudentServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentFeesController {
    @Autowired
    StudentServiceWorker studentServiceWorker;

    @Autowired
    InstitutionStructureServiceX structureServiceX;

    @Autowired
    FeeCategoryServiceWorker feeCategoryServiceWorker;

    @GetMapping(value = "StudentFacing/FeeManagement/StudentFee")
    public ModelAndView studentFacingFeeIndex(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", studentServiceWorker.getAuthUserStudentDetails(authentication.getName()));
        modelAndView.addObject("sessions", structureServiceX.getAllInstitutionSession());
        modelAndView.addObject("payments", feeCategoryServiceWorker.getAllPaymentMethod());

        modelAndView.setViewName("/modules/frontend/views/FeesManagement/StudentFees");
        return  modelAndView;
    }

    @RequestMapping(value = "StudentFacing/FeeManagement/GetStudentApplicableFees", method = RequestMethod.POST)
    @ResponseBody
    public List<SpecificFeeDTO> getStudentApplicableFees(SFeeSearchParamDto sFeeSearchParamDto){
        return feeCategoryServiceWorker.getStudentApplicableFee(sFeeSearchParamDto);
    }

    public FeePaymentDto verifyPayment(FeePaymentDto feePaymentDto){
        return new FeePaymentDto();
    }

}
