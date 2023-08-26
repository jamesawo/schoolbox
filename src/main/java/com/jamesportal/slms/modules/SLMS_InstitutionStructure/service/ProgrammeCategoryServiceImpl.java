
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository.ProgrammeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammeCategoryServiceImpl implements IProgrammeCategoryService {

    @Autowired
    ProgrammeCategoryRepository programmeCategoryRepository;

    @Override
    public InstitutionProgrammeCategory createProgrammeCategory(InstitutionProgrammeCategory institutionProgrammeCategory) {
        return programmeCategoryRepository.save(institutionProgrammeCategory);
    }

    @Override
    public InstitutionProgrammeCategory updateProgrammeCategory(InstitutionProgrammeCategory institutionProgrammeCategory) {
        return programmeCategoryRepository.save(institutionProgrammeCategory);

    }

    @Override
    public InstitutionProgrammeCategory getProgrammeCategory(long id){
        return programmeCategoryRepository.getOne(id);
    }

    @Override
    public List<InstitutionProgrammeCategory> getAllProgrammeCategory() {
        return programmeCategoryRepository.findAll();
    }

    @Override
    public void disableProgrammeCategory(long id) {

    }

    @Override
    public InstitutionProgrammeCategory getInstitutionProgrammeCategoryByObj(InstitutionProgrammeCategory institutionProgrammeCategory) {
        return null;
    }


}
