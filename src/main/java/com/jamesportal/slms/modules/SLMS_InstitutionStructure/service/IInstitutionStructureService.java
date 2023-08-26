
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

import java.util.List;

public interface IInstitutionStructureService {

    List<InstitutionStructure> getAllInstitutionStructure();

    InstitutionStructure saveInstitutionStructure(InstitutionStructure institutionStructure );

    List<InstitutionStructure> getInstitutionStructureByCategory(InstitutionCategory categoryName);

    List<InstitutionStructure> getInstitutionStructureByParent(long id);

    List<SuggestionDTO> autoCompleteSearchInstitutionStructureByDescription(String description);

    InstitutionStructure getInstitutionStructureById(long id);

    List<InstitutionStructure> getInstitutionStructureListByCategoryAndParent(InstitutionCategory category, InstitutionStructure parent );

    InstitutionStructure getInstitutionStructureByDescription(String description);


}
