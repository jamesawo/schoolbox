
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionLevel;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;

import java.util.List;

public interface IInstitutionLevelService {

    InstitutionLevel saveInstitutionLevel(InstitutionLevel institutionLevel);

    List<InstitutionLevel> getAllInstitutionLevel();

    InstitutionLevel getInstitutionLevel(long id);



}
