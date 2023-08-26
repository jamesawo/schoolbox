
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.service;

import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.SFeeSearchParamDto;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.SpecificFee;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISpecificFeeService {

    SpecificFee createSpecificFee(SpecificFee specificFee);

    SpecificFee updateSpecificFee(SpecificFee specificFee);

    void deactivateSpecificFee(SpecificFee specificFee);

    void activateSpecificFee(SpecificFee specificFee);

    SpecificFee getSpecificFeeById(long id);

    List<SpecificFee> getAllSpecificFee();

    List<SpecificFee> getSpecificFeeByProgrammeType( InstitutionProgrammeType institutionProgrammeType );

    List<SpecificFee> getAllSpecificFeeByInstitutionStructure( InstitutionStructure institutionStructure );

    Page<SpecificFee> specificFeeSearchCriteria(SFeeSearchParamDto sFeeSearchParamDto);

    List<SpecificFee> getStudentFeesCriteria(SFeeSearchParamDto sFeeSearchParamDto);


}
