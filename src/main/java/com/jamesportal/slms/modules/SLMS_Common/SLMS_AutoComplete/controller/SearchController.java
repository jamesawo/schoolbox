
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_AutoComplete.controller;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_AutoComplete.dto.SuggestionDTO;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_AutoComplete.service.SearchService;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/API/SLMS/Common/Search/StudentManagement/NameSearch")
    public List<SuggestionDTO> searchStudentNames(@RequestParam(defaultValue = "", required = false, name = "phrase") String phrase){
        List<SuggestionDTO> suggestionDTOList = new ArrayList<>();
        if (phrase.length()>5){
           suggestionDTOList = searchService.autoCompleteSearchStudentByRegNumber(phrase);
        }
        return suggestionDTOList;
    }


    @GetMapping("/API/SLMS/Common/Search/InstitutionStructure/DescriptionSearch")
    public List<SuggestionDTO> searchInstitutionStructure(@RequestParam(defaultValue = "", required = false, name = "phrase") String phrase){
        List<SuggestionDTO> suggestionDTOList = new ArrayList<>();
        if (phrase.length()>3){
            suggestionDTOList = searchService.autoCompleteSearchInstitutionStructureByDescription(phrase);
        }
        return suggestionDTOList;
    }



}
