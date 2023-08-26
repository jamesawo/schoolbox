
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_AutoComplete.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_AutoComplete.dto.SuggestionDTO;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceImpl;
import com.jamesportal.slms.modules.SLMS_StudentManagement.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    InstitutionStructureServiceImpl institutionStructureServiceImpl;

    @Autowired
    StudentServiceImpl studentServiceImpl;


    public List<SuggestionDTO> autoCompleteSearchStudentByRegNumber(String regNumber){
        return studentServiceImpl.autoCompleteSearchStudentByStudentRegNumber(regNumber);
    }

    public List<SuggestionDTO> autoCompleteSearchInstitutionStructureByDescription(String description){
        return institutionStructureServiceImpl.autoCompleteSearchInstitutionStructureByDescription(description);
    }


}
