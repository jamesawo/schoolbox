
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.service;

import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.SpecificFeeDTO;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.FeeInstallment;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.SpecificFee;
import com.jamesportal.slms.modules.SLMS_FeeManagement.repository.FeeInstalmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeInstalmentServiceImpl implements IFeeInstalmentService {
    @Autowired
    FeeInstalmentRepository feeInstalmentRepository;

    @Override
    public FeeInstallment createFeeInstalment(SpecificFeeDTO specificFeeDTO) {
        FeeInstallment feeInstallment = new FeeInstallment();
        feeInstallment.setFirstInstalment(specificFeeDTO.getFirstInstalment());
        feeInstallment.setSecondInstalment(specificFeeDTO.getSecondInstalment());
        feeInstallment.setSpecificFee(new SpecificFee(specificFeeDTO.getId()));
        return feeInstalmentRepository.save(feeInstallment);
    }

    @Override
    public FeeInstallment updateFeeInstalment(SpecificFeeDTO specificFeeDTO) {
        return null;
    }

    @Override
    public FeeInstallment getFeeInstalmentById(long id) {
        return feeInstalmentRepository.getOne(id);

    }

    @Override
    public FeeInstallment getFeeInstalmentBySpecificFee(SpecificFee specificFee) {
        return feeInstalmentRepository.findBySpecificFee(specificFee);
    }
}
