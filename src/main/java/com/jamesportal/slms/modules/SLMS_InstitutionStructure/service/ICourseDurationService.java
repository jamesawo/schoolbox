
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCourseDuration;

import java.util.List;

public interface ICourseDurationService {

    InstitutionCourseDuration getInstitutionCourseDuration(long id);

    InstitutionCourseDuration createInstitutionCourseDuration(InstitutionCourseDuration institutionCourseDuration);

    List<InstitutionCourseDuration> getAllInstitutionCourseDuration();

    InstitutionCourseDuration updateInstitutionCourseDuration(InstitutionCourseDuration institutionCourseDuration);

    void deleteInstitutionCourseDuration(long id);

    void createInstitutionCourseDurationByList(List<InstitutionCourseDuration> institutionCourseDurationList);


}
