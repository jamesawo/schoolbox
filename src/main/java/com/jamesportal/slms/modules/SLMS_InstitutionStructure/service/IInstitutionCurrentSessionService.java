
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCurrentSession;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;

public interface IInstitutionCurrentSessionService {

    InstitutionCurrentSession getInstitutionCurrentSessionById(long id);

    InstitutionCurrentSession createInstitutionCurrentSession(InstitutionCurrentSession institutionCurrentSession);

    InstitutionCurrentSession updateInstitutionCurrentSession(InstitutionCurrentSession institutionCurrentSession);

    InstitutionCurrentSession getInstitutionCurrentSessionByProgrammeType(InstitutionProgrammeType institutionProgrammeType);

    InstitutionCurrentSession setInstitutionCurrentSemester(InstitutionCurrentSession institutionCurrentSession);

    InstitutionCurrentSession setInstitutionCurrentSession(InstitutionCurrentSession institutionCurrentSession);

    InstitutionCurrentSession saveOrUpdateInstitutionCurrentSession(InstitutionCurrentSession institutionCurrentSession);


}
