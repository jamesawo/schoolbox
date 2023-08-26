
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
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository.InstitutionSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionSessionServiceImpl implements IInstitutionSessionService {
    @Autowired
    public InstitutionSessionRepository institutionSessionRepository;

    @Override
    public InstitutionSession getInstitutionSessionById(long id) {
        return institutionSessionRepository.getOne(id);
    }

    @Override
    public InstitutionSession createInstitutionSession(InstitutionSession institutionSession) {
        return institutionSessionRepository.save(institutionSession);
    }

    @Override
    public InstitutionSession updateInstitutionSession(InstitutionSession institutionSession) {
        return institutionSessionRepository.save(institutionSession);
    }

    @Override
    public void disableInstitutionSessionById(long id) {
        institutionSessionRepository.deactivateById(id);
    }

    @Override
    public void enableInstitutionSessionById(long id) {
        institutionSessionRepository.activateById(id);
    }

    @Override
    public void deleteInstitutionSessionById(long id) {
        institutionSessionRepository.delete(new InstitutionSession(id));
    }

    @Override
    public List<InstitutionSession> getAllInstitutionSession() {
        return institutionSessionRepository.findAll();
    }

    @Override
    public List<InstitutionSession> getAllInstitutionSessionByProgrammeType(InstitutionProgrammeType institutionProgrammeType) {
        return institutionSessionRepository.getAllByProgrammeType(institutionProgrammeType);
    }


}
