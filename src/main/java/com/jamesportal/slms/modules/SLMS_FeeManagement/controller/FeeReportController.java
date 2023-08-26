
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FeeReportController {

    @GetMapping( value = "/SLMS/FeeManagement/FeeReportView")
    public ModelAndView feeReportIndexGet()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/modules/backend/views/FeesManagement/FeeReport");
        return modelAndView;
    }

}

