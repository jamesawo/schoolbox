
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.service;

import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.SFeeSearchParamDto;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.FeeCategory;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.SpecificFee;
import com.jamesportal.slms.modules.SLMS_FeeManagement.repository.SpecificFeeRepository;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeeSearchService {
    @Autowired
    SpecificFeeRepository specificFeeRepository;

    //Returns Pageable Result For View Display
    @Transactional
    public Page<SpecificFee> searchFee(final SFeeSearchParamDto sFeeSearchParamDto){
        Pageable pageable = PageRequest.of(sFeeSearchParamDto.getPageNumber(), sFeeSearchParamDto.getPageSize());
        return specificFeeRepository.findAll(FeeSearchSpecification.findByCriteria(sFeeSearchParamDto), pageable);
    }

    //Returns Total
    // EntrySet/Record For Report Download
    @Transactional
    public List<SpecificFee> searchFeeReportData(final SFeeSearchParamDto sFeeSearchParamDto){
        return specificFeeRepository.findAll(FeeSearchSpecification.findByCriteria(sFeeSearchParamDto) );
    }


    private  static  class FeeSearchSpecification {
        private static Specification<SpecificFee> findByCriteria(final SFeeSearchParamDto sFeeSearchParamDto){
            return new Specification<SpecificFee>() {
                @Override
                public Predicate toPredicate(Root<SpecificFee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicates = new ArrayList<>();

                    if(sFeeSearchParamDto.getProgrammeType() != 0){
                        Join<SpecificFee, InstitutionProgrammeType> institutionProgrammeTypeJoin = root.join("programmeType");
                        predicates.add(criteriaBuilder.equal(institutionProgrammeTypeJoin.get("id"), sFeeSearchParamDto.getProgrammeType()));
                    }

                    if(sFeeSearchParamDto.getSession() != 0){
                        Join<SpecificFee, InstitutionSession> institutionSessionJoin = root.join("session");
                        predicates.add(criteriaBuilder.equal(institutionSessionJoin.get("id"), sFeeSearchParamDto.getSession()));
                    }

                    if(sFeeSearchParamDto.getLevel() != 0){
                        Join<SpecificFee, InstitutionLevel> institutionLevelJoin = root.join("level");
                        predicates.add(criteriaBuilder.equal(institutionLevelJoin.get("id"), sFeeSearchParamDto.getLevel()));
                    }

                    if(sFeeSearchParamDto.getSemester() != 0){
                        Join<SpecificFee, InstitutionSemester> institutionSemesterJoin = root.join("semester");
                        predicates.add(criteriaBuilder.equal(institutionSemesterJoin.get("id"), sFeeSearchParamDto.getSemester()));
                    }

                    if(sFeeSearchParamDto.getStatus() != null){

                        if (sFeeSearchParamDto.getStatus().equals("true")) {
                            predicates.add(criteriaBuilder.isTrue(root.get("status").as(Boolean.class)));
                        }
                        if (sFeeSearchParamDto.getStatus().equals("false")){
                            predicates.add(criteriaBuilder.isFalse(root.get("status").as(Boolean.class)));
                        }
                    }

                    if(sFeeSearchParamDto.getFeeCategory() != 0){
                        Join<SpecificFee, FeeCategory> feeCategoryJoin = root.join("feeCategory");
                        predicates.add(criteriaBuilder.equal(feeCategoryJoin.get("id"), sFeeSearchParamDto.getFeeCategory()));
                    }

                    if (sFeeSearchParamDto.getInstitutionStructureIds() != null && !sFeeSearchParamDto.getInstitutionStructureIds().isEmpty() ){
                        Join<SpecificFee, InstitutionStructure> institutionStructureJoin = root.join("institutionStructure");
                        predicates.add(institutionStructureJoin.in(sFeeSearchParamDto.getInstitutionStructureIds()));
                    }

                    criteriaQuery.orderBy(criteriaBuilder.asc(root.join("programmeType").get("description")));
                    return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
                }
            };
        }
    }
}
