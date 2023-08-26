
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_AutoComplete.dto.SuggestionDTO;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository.InstitutionStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionStructureServiceImpl implements IInstitutionStructureService {

    @Autowired
    InstitutionStructureRepository institutionStructureRepository;

    @Override
    public InstitutionStructure saveInstitutionStructure(InstitutionStructure institutionStructure) {
        return institutionStructureRepository.save(institutionStructure);
    }

    @Override
    public List<InstitutionStructure> getInstitutionStructureByCategory(InstitutionCategory category) {
        return institutionStructureRepository.findByCategory(category);
    }

    @Override
    public List<InstitutionStructure> getInstitutionStructureByParent(long id) {
        return institutionStructureRepository.findAllByParent( new InstitutionStructure(id));
    }

    @Override
    public List<InstitutionStructure> getAllInstitutionStructure(){
        return institutionStructureRepository.findAll();
    }

    @Override
    public List<SuggestionDTO> autoCompleteSearchInstitutionStructureByDescription(String description) {
        return institutionStructureRepository.findAllByDescriptionLike(description);
    }


    @Override
    public InstitutionStructure getInstitutionStructureById(long id){
        return institutionStructureRepository.getOne(id);
    }

    @Override
    public List<InstitutionStructure> getInstitutionStructureListByCategoryAndParent(InstitutionCategory category, InstitutionStructure parent) {
        return institutionStructureRepository.findByCategoryAndParent(category, parent);
    }

    @Override
    public InstitutionStructure getInstitutionStructureByDescription(String description) {
        return institutionStructureRepository.findByDescription(description);
    }
}
