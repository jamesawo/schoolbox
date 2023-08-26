
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository.ProgrammeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammeTypeServiceImpl implements IProgrammeTypeService {

    @Autowired
    ProgrammeTypeRepository programmeTypeRepository;

    @Override
    public InstitutionProgrammeType getInstitutionProgrammeTypeById(long id) {
        return programmeTypeRepository.getOne(id);
    }

    @Override
    public InstitutionProgrammeType createInstitutionProgrammeType(InstitutionProgrammeType institutionProgrammeType) {
        return programmeTypeRepository.save(institutionProgrammeType);
    }

    @Override
    public InstitutionProgrammeType updateInstitutionProgrammeType(InstitutionProgrammeType institutionProgrammeType) {
        return programmeTypeRepository.save(institutionProgrammeType);
    }

    @Override
    public void deleteInstitutionProgrammeType(InstitutionProgrammeType institutionProgrammeType) {
        programmeTypeRepository.delete(institutionProgrammeType);
    }

    @Override
    public List<InstitutionProgrammeType> getAllInstitutionProgrammeType() {
        return programmeTypeRepository.findAll();
    }

    @Override
    public List<InstitutionProgrammeType> getInstitutionProgrammeTypeByProgrammeCategory(InstitutionProgrammeCategory institutionProgrammeCategory) {
        return programmeTypeRepository.findByInstitutionProgrammeCategory(institutionProgrammeCategory);
    }



}
