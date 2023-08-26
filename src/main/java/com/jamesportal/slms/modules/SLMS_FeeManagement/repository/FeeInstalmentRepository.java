
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.repository;

import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.FeeInstallment;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.SpecificFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeInstalmentRepository extends JpaRepository<FeeInstallment, Long> {
    FeeInstallment findBySpecificFee(SpecificFee specificFee);
}
