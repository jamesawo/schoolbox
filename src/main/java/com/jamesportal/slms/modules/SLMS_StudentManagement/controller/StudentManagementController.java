
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.controller;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Common.dto.AjaxResponseBodyDto;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.service.LocationServiceWorker;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.service.StudentEntryTypeServiceImpl;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentProfileDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentSearchParamDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentStatusDto;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import com.jamesportal.slms.modules.SLMS_StudentManagement.service.StudentReportService;
import com.jamesportal.slms.modules.SLMS_StudentManagement.service.StudentSearchService;
import com.jamesportal.slms.modules.SLMS_StudentManagement.service.StudentServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/SLMS/StudentManagement/")
public class StudentManagementController {
    @Autowired
    InstitutionStructureServiceX institutionStructureServiceX;

    @Autowired
    StudentServiceWorker studentServiceWorker;

    @Autowired
    StudentEntryTypeServiceImpl entryTypeService;

    @Autowired
    LocationServiceWorker locationServiceWorker;

    @Autowired
    StudentSearchService studentSearchService;
    
    @Autowired
    StudentReportService studentReportService;

    @GetMapping(value = "StudentManagementView")
    public ModelAndView studentManagementViewGet(HttpServletRequest request)
    {
        InstitutionCategory leastHierarchy =  institutionStructureServiceX.getInstitutionCategoryByLeastHierarchy();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("StudentDTO", new StudentDTO());
        modelAndView.addObject("levels", institutionStructureServiceX.getAllInstitutionLevel());
        modelAndView.addObject("searchParamDTO",  new StudentSearchParamDTO() );
        modelAndView.addObject("instStrListByLeastHieCategory", institutionStructureServiceX.getInstitutionStructureListByCategory(leastHierarchy)); //List of institution structure by first hierarchy
        modelAndView.addObject("categories", institutionStructureServiceX.getAllCategoryNames()); //List of institution category names
        modelAndView.addObject("programmeCategories", institutionStructureServiceX.getAllProgrammeCategory());
        modelAndView.addObject("programmeTypes", institutionStructureServiceX.getAllProgrammeType());
        modelAndView.addObject("sessions", institutionStructureServiceX.getAllInstitutionSession());
        modelAndView.addObject("entryTypes", entryTypeService.getAllStudentEntryType());
        modelAndView.addObject("countries", locationServiceWorker.getAllLocationCountry());

        modelAndView.setViewName("/modules/backend/views/StudentManagement/StudentManagementView");
        return modelAndView;
    }

    @PostMapping(value = "StudentManagementView")
    public String studentManagementCreatePost(@ModelAttribute("studentDTO") StudentDTO studentDTO, RedirectAttributes redirectAttributes)
    {
        Student student = studentServiceWorker.createStudent(studentDTO);
        redirectAttributes.addFlashAttribute("message","Student: " +student.getRegNumber()+ " Added Successfully." );
        return "redirect:/SLMS/StudentManagement/StudentManagementView";
    }


    @RequestMapping("StudentManagementSearch/Params")
    public RedirectView searchStudentParam(StudentSearchParamDTO searchParamDTO, HttpServletRequest request, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("searchResult", studentServiceWorker.searchStudentList(searchParamDTO));
        redirectAttributes.addFlashAttribute("flashSearchParamDTO", searchParamDTO);
        return new RedirectView("/SLMS/StudentManagement/StudentManagementView", true);

    }


    @GetMapping(value = "Student/Edit/{id}")
    public ModelAndView  studentUpdateDetails(@PathVariable long id ){
        ModelAndView modelAndView = new ModelAndView();
        StudentProfileDTO student = studentServiceWorker.getStudentProfileDetailsByStudentId(id);
        modelAndView.addObject("studentProfileDTO", student);
        modelAndView.addObject("states", locationServiceWorker.getAllLocationState());
        modelAndView.addObject("countries", locationServiceWorker.getAllLocationCountry());
        modelAndView.setViewName("/modules/backend/views/StudentManagement/StudentManagementEdit");
        return modelAndView;

    }

    @PostMapping(value = "Student/Edit/")
    public String studentUpdateDetailsChanges(@ModelAttribute StudentProfileDTO studentProfileDTO, RedirectAttributes redirectAttributes) throws IOException, ParseException {
        studentServiceWorker.updateStudent(studentProfileDTO);
        redirectAttributes.addFlashAttribute("message", "Successfully Updated.");
        return "redirect:/SLMS/StudentManagement/Student/Edit/" + studentProfileDTO.getId();
    }
    
    @PostMapping(value="StudentList/Update/Status/")
	public ResponseEntity<?> studentUpdateStatus(@Valid @RequestBody StudentStatusDto statusDto, Errors errors ){
    
        AjaxResponseBodyDto responseBody = new AjaxResponseBodyDto();
        
        if (errors.hasErrors()) {
        
            responseBody.setMessage(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));
        
            return ResponseEntity.badRequest().body(responseBody);
        }
        responseBody.setMessage("success");
        return  ResponseEntity.ok(responseBody);
	}
	
	@Transactional
	@GetMapping("Student/GenerateReport")
    public void studentGenerateReportData(@ModelAttribute StudentSearchParamDTO searchParamDTO,HttpServletResponse response) throws FileNotFoundException {
    	studentServiceWorker.prepareStudentReportData(searchParamDTO, response);
    }

}
