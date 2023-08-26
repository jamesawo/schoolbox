
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.repository;

import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.SpecificFee;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificFeeRepository extends JpaRepository<SpecificFee, Long>, JpaSpecificationExecutor<SpecificFee> {

    @Query("update SpecificFee s set s.status = true where s.id=:id")
    void activateSpecificFeeById(@Param("id") long id);

    @Query("update SpecificFee s set s.status = false where s.id=:id")
    void deactivateSpecificFeeById(@Param("id") long id);

    @Query("update SpecificFee s set s.itemCode =:itemCode, s.merchant =:merchant where s.id=:id")
    SpecificFee updateSpecificDetails(@Param("itemCode") String itemCode, @Param("merchant") String merchant);

    List<SpecificFee> getAllByProgrammeType(InstitutionProgrammeType programmeType);

    List<SpecificFee> getAllByInstitutionStructure(InstitutionStructure institutionStructure);

//    Page<SpecificFee> findAll(Specification<SpecificFee> byCriteria, Pageable pageable);

}
