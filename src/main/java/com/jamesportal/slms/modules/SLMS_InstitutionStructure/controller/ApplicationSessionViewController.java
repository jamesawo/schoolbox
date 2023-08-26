
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationSessionViewController {

    @RequestMapping( value = "/SLMS/InstitutionStructure/ApplicationSessionView")
    public ModelAndView ApplicationSessionViewIndexGet()
    {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/modules/backend/views/InstitutionStructure/ApplicationSessionView");

        return modelAndView;
    }
}

