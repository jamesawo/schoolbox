
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

public interface IFeeInstalmentService {

    FeeInstallment createFeeInstalment(SpecificFeeDTO specificFeeDTO);

    FeeInstallment updateFeeInstalment(SpecificFeeDTO specificFeeDTO);

    FeeInstallment getFeeInstalmentById(long id);

    FeeInstallment getFeeInstalmentBySpecificFee(SpecificFee specificFee);




}
