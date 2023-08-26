
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSemester;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository.InstitutionSemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterServiceImpl implements ISemesterService {
    @Autowired
    InstitutionSemesterRepository institutionSemesterRepository;

    @Override
    public InstitutionSemester createInstitutionSemester(InstitutionSemester institutionSemester) {
        return institutionSemesterRepository.save(institutionSemester);
    }

    @Override
    public void createInstitutionSemesterFromList(List<InstitutionSemester> institutionSemesterList) {
        institutionSemesterRepository.saveAll(institutionSemesterList);
    }

    @Override
    public InstitutionSemester updateInstitutionSemester(InstitutionSemester institutionSemester) {
        return institutionSemesterRepository.save(institutionSemester);

    }

    @Override
    public List<InstitutionSemester> getAllInstitutionSemester() {
        return institutionSemesterRepository.findAll();
    }

    @Override
    public void deleteInstitutionSemester(long id) {

    }

    @Override
    public InstitutionSemester getInstitutionSemesterById(long id) {
        return institutionSemesterRepository.getOne(id);
    }
}
