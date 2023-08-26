
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.controller;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto.LevelDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LevelHistoryController {
    @GetMapping(value = "/SLMS/InstitutionStructure/LevelHistory")
    public ModelAndView LevelHistoryIndexGet()
    {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/modules/backend/views/InstitutionStructure/LevelHistory");
        return modelAndView;
    }


}
