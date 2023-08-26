
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.controller;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto.SemesterDTO;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto.SessionDTO;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCurrentSession;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSession;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SessionSetupController {

    @Autowired
    InstitutionStructureServiceX institutionStructureServiceX;

    @GetMapping( value = "/SLMS/InstitutionStructure/SessionSetup")
    public ModelAndView SessionSetupIndexGet()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("programmeTypes", institutionStructureServiceX.getAllProgrammeType());
        modelAndView.addObject("semesters", institutionStructureServiceX.getAllInstitutionSemester());
        modelAndView.addObject("sessions", institutionStructureServiceX.getAllInstitutionSession());
        modelAndView.addObject("sessionDTO", new SessionDTO());

        modelAndView.setViewName("/modules/backend/views/InstitutionStructure/SessionSetup");
        return modelAndView;
    }

    @PostMapping( value = "SLMS/InstitutionStructure/SessionCreate" )
    public String createInstitutionSessionPost(@ModelAttribute SessionDTO sessionDTO, RedirectAttributes redirectAttributes)
    {
        switch (sessionDTO.getCommand()) {
            case ("Create Session"):
                institutionStructureServiceX.createInstitutionSession(sessionDTO);
                redirectAttributes.addFlashAttribute("message", "Session Created Successfully." );
                break;

            case ("Set Session"):
                institutionStructureServiceX.setInstitutionCurrentSession(sessionDTO);
                redirectAttributes.addFlashAttribute("message", "Application Session Configured Successfully." );
                break;
            case ("Set Semester"):
                institutionStructureServiceX.setInstitutionCurrentSemester(sessionDTO);
                redirectAttributes.addFlashAttribute("message", "Application Semester Configured Successfully." );
                break;
            default:
                redirectAttributes.addFlashAttribute("message", "Invalid Command Message." );
                break;
        }
        return "redirect:/SLMS/InstitutionStructure/SessionSetup";
    }

    @RequestMapping( value = "/SLMS/InstitutionStructure/Active/SessionByProgrammeType/{id}", method = RequestMethod.GET)
    @ResponseBody
    public InstitutionCurrentSession getInstitutionCurrentSessionByProgrammeType(@PathVariable long id)
    {
        return institutionStructureServiceX.getInstitutionCurrentSessionByProgrammeType(id);
    }

    @GetMapping("/API/SLMS/InstitutionStructure/SessionByProgrammeType/{id}")
    @ResponseBody
    public List<SessionDTO> getAllInstitutionSessionByProgrammeType(@PathVariable long id){
        return institutionStructureServiceX.getAllInstitutionSessionByProgrammeType(id);
    }
}
