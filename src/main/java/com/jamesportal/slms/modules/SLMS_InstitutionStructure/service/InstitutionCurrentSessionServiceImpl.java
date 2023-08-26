
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
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository.InstitutionCurrentSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionCurrentSessionServiceImpl implements IInstitutionCurrentSessionService {
    @Autowired
    InstitutionCurrentSessionRepository institutionCurrentSessionRepository;

    @Override
    public InstitutionCurrentSession getInstitutionCurrentSessionById(long id) {
        return institutionCurrentSessionRepository.getOne(id);
    }

    @Override
    public InstitutionCurrentSession createInstitutionCurrentSession(InstitutionCurrentSession institutionCurrentSession) {
        return institutionCurrentSessionRepository.save(institutionCurrentSession);
    }

    @Override
    public InstitutionCurrentSession saveOrUpdateInstitutionCurrentSession(InstitutionCurrentSession institutionCurrentSession) {
        return institutionCurrentSessionRepository.save(institutionCurrentSession);
    }



    @Override
    public InstitutionCurrentSession updateInstitutionCurrentSession(InstitutionCurrentSession institutionCurrentSession) {
        return institutionCurrentSessionRepository.save(institutionCurrentSession);
    }

    @Override
    public InstitutionCurrentSession getInstitutionCurrentSessionByProgrammeType(InstitutionProgrammeType institutionProgrammeType) {
        return institutionCurrentSessionRepository.findByInstitutionProgrammeType(institutionProgrammeType);

    }

    @Override
    public InstitutionCurrentSession setInstitutionCurrentSemester(InstitutionCurrentSession institutionCurrentSession) {
        return institutionCurrentSessionRepository.save(institutionCurrentSession);
    }

    @Override
    public InstitutionCurrentSession setInstitutionCurrentSession(InstitutionCurrentSession institutionCurrentSession) {
        return null;
    }
}
