
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSession;

import java.util.List;

public interface IInstitutionSessionService {

    InstitutionSession getInstitutionSessionById(long id);

    InstitutionSession createInstitutionSession(InstitutionSession institutionSession);

    InstitutionSession updateInstitutionSession(InstitutionSession institutionSession);

    void disableInstitutionSessionById(long id);

    void enableInstitutionSessionById(long id);


    void deleteInstitutionSessionById(long id);

    List<InstitutionSession> getAllInstitutionSession();

    List<InstitutionSession> getAllInstitutionSessionByProgrammeType(InstitutionProgrammeType institutionProgrammeType);




}
