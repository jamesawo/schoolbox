
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

import java.util.List;

public interface IProgrammeTypeService {

    InstitutionProgrammeType getInstitutionProgrammeTypeById(long id);

    InstitutionProgrammeType createInstitutionProgrammeType(InstitutionProgrammeType institutionProgrammeType);

    InstitutionProgrammeType updateInstitutionProgrammeType(InstitutionProgrammeType institutionProgrammeType);

    void deleteInstitutionProgrammeType(InstitutionProgrammeType institutionProgrammeType);

    List<InstitutionProgrammeType> getAllInstitutionProgrammeType();

    List<InstitutionProgrammeType> getInstitutionProgrammeTypeByProgrammeCategory(InstitutionProgrammeCategory institutionProgrammeCategory);
}
