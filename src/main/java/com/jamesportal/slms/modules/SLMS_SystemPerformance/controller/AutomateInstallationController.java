
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_SystemPerformance.controller;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/SLMS/SystemPerformance/AutomateInstallation/")
public class AutomateInstallationController {

    @GetMapping(value = "StartProcess")
    public ModelAndView startProcess(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("modules/backend/views/SystemPerformance/AutomateInstallation");
        return modelAndView;
    }



}
