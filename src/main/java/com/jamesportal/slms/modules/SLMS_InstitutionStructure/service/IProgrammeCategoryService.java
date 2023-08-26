
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeCategory;

import java.util.List;

public interface IProgrammeCategoryService {

    InstitutionProgrammeCategory createProgrammeCategory(InstitutionProgrammeCategory institutionProgrammeCategory);

    InstitutionProgrammeCategory updateProgrammeCategory(InstitutionProgrammeCategory institutionProgrammeCategory);

    InstitutionProgrammeCategory getProgrammeCategory(long id);

    List<InstitutionProgrammeCategory> getAllProgrammeCategory();

    void disableProgrammeCategory(long id);

    InstitutionProgrammeCategory getInstitutionProgrammeCategoryByObj(InstitutionProgrammeCategory institutionProgrammeCategory);



}
