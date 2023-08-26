
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Common.Service;

import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.FeeCategory;
import com.jamesportal.slms.modules.SLMS_FeeManagement.service.FeeCategoryServiceWorker;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.*;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.service.InstitutionStructureServiceX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CommonServiceWorker {

    @Autowired
    InstitutionStructureServiceX institutionStructureService;

    @Autowired
    FeeCategoryServiceWorker feeCategoryService;

    public Map<String, Object> getInstitutionStructureFilterMap(){

        List<InstitutionProgrammeCategory> programmeCategories = institutionStructureService.getAllProgrammeCategory();
        List<InstitutionProgrammeType> programmeTypes = institutionStructureService.getAllProgrammeType();
        List<InstitutionSession> sessions = institutionStructureService.getAllInstitutionSession();
        List<InstitutionLevel> levels = institutionStructureService.getAllInstitutionLevel();
        List<InstitutionSemester> semesters = institutionStructureService.getAllInstitutionSemester();

        Map<String, Object> insFilterMap = new HashMap<>();
        insFilterMap.put("programmeCategories", programmeCategories);
        insFilterMap.put("programmeTypes", programmeTypes);
        insFilterMap.put("sessions", sessions);
        insFilterMap.put("levels", levels);
        insFilterMap.put("semesters", semesters);
        return insFilterMap;

    }

    public Map<String, Object> getFeeManagementFilterMap(){
        Map<String, Object> feeFilterMap = new HashMap<>();
        feeFilterMap.put("feeCategories", feeCategoryService.getAllFeeCategory());
        return feeFilterMap;
    }

}
