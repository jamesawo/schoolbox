
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionLevel;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository.InstitutionLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionLevelServiceImpl implements IInstitutionLevelService {

    @Autowired
    InstitutionLevelRepository institutionLevelRepository;

    @Override
    public InstitutionLevel saveInstitutionLevel(InstitutionLevel institutionLevel) { return institutionLevelRepository.save(institutionLevel); }

    @Override
    public List<InstitutionLevel> getAllInstitutionLevel() {
        return institutionLevelRepository.findAll();
    }

    public InstitutionLevel getInstitutionLevel( long id ){ return institutionLevelRepository.getOne(id); }

}
