
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCourseDuration;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository.CourseDurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDurationServiceImpl implements ICourseDurationService {
    @Autowired
    CourseDurationRepository courseDurationRepository;


    @Override
    public InstitutionCourseDuration getInstitutionCourseDuration(long id) {
        return courseDurationRepository.getOne(id);
    }

    @Override
    public InstitutionCourseDuration createInstitutionCourseDuration(InstitutionCourseDuration institutionCourseDuration) {
        return courseDurationRepository.save(institutionCourseDuration);
    }

    @Override
    public List<InstitutionCourseDuration> getAllInstitutionCourseDuration() {
        return courseDurationRepository.findAll();
    }

    @Override
    public InstitutionCourseDuration updateInstitutionCourseDuration(InstitutionCourseDuration institutionCourseDuration) {
        return courseDurationRepository.save(institutionCourseDuration);
    }

    @Override
    public void deleteInstitutionCourseDuration(long id) {
        InstitutionCourseDuration institutionCourseDuration = new InstitutionCourseDuration();
        institutionCourseDuration.setId(id);
        courseDurationRepository.delete(institutionCourseDuration);
    }

    @Override
    public void createInstitutionCourseDurationByList(List<InstitutionCourseDuration> institutionCourseDurationList) {
        courseDurationRepository.saveAll(institutionCourseDurationList);
    }
}
