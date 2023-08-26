
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSemester;

import java.util.List;

public interface ISemesterService {

    InstitutionSemester createInstitutionSemester(InstitutionSemester institutionSemester);

    void createInstitutionSemesterFromList(List<InstitutionSemester> institutionSemesterList);

    InstitutionSemester updateInstitutionSemester(InstitutionSemester institutionSemester);

    List<InstitutionSemester> getAllInstitutionSemester();

    void deleteInstitutionSemester(long id);

    InstitutionSemester getInstitutionSemesterById(long id);
}
