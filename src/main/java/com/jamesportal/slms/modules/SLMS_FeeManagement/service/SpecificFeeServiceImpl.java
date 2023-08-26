
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
import com.jamesportal.slms.modules.SLMS_FeeManagement.repository.SpecificFeeRepository;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificFeeServiceImpl implements ISpecificFeeService {

    @Autowired
    SpecificFeeRepository specificFeeRepository;

    @Autowired
    FeeSearchService feeSearchService;

    @Override
    public SpecificFee createSpecificFee(SpecificFee specificFee) {
        return specificFeeRepository.save(specificFee);
    }

    @Override
    public SpecificFee updateSpecificFee(SpecificFee specificFee) {
        return specificFeeRepository.updateSpecificDetails(specificFee.getItemCode(), specificFee.getMerchant() );
    }

    @Override
    public void deactivateSpecificFee(SpecificFee specificFee) {
        specificFeeRepository.deactivateSpecificFeeById(specificFee.getId());
    }

    @Override
    public void activateSpecificFee(SpecificFee specificFee) {
        specificFeeRepository.activateSpecificFeeById(specificFee.getId());
    }

    @Override
    public SpecificFee getSpecificFeeById(long id) {
        return specificFeeRepository.getOne(id);
    }

    @Override
    public List<SpecificFee> getAllSpecificFee() {
        return specificFeeRepository.findAll();
    }

    @Override
    public List<SpecificFee> getSpecificFeeByProgrammeType(InstitutionProgrammeType institutionProgrammeType) {
        return specificFeeRepository.getAllByProgrammeType(institutionProgrammeType);
    }

    @Override
    public List<SpecificFee> getAllSpecificFeeByInstitutionStructure(InstitutionStructure institutionStructure) {
        return specificFeeRepository.getAllByInstitutionStructure(institutionStructure);
    }

    @Override
    public Page<SpecificFee> specificFeeSearchCriteria(SFeeSearchParamDto sFeeSearchParamDto) {
        return feeSearchService.searchFee(sFeeSearchParamDto);
    }

    @Override
    public List<SpecificFee> getStudentFeesCriteria(SFeeSearchParamDto sFeeSearchParamDto) {
        return feeSearchService.searchFeeReportData(sFeeSearchParamDto);
    }


}
