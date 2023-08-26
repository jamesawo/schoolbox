
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.dto;

import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.FeeCategory;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.FeeInstallment;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class SpecificFeeDTO {

    private long id;

    private InstitutionProgrammeType programmeType;

    private InstitutionSession session;

    private InstitutionLevel level;

    private InstitutionStructure institutionStructure;

    private InstitutionSemester semester;

    private FeeCategory feeCategory;

    private String dependentFee;

    private BigDecimal amount;

    private boolean status = true;

    private String itemCode;

    private String merchantId;

    private boolean globalFeeBool = false;

    private String command;

    private String programmeTypeDescription;

    private String sessionDescription;

    private String levelDescription;

    private String institutionStructureDescription;

    private String semesterDescription;

    private String feeCategoryDescription;

    private String dateModified;

    private String feeType;

    private String feeCategoryType;

    private boolean allowPartPayment;

    private BigDecimal firstInstalment;

    private BigDecimal secondInstalment;

    private FeeInstallment feeInstallment;

    private long jsAllowPartPayment;

    private long institutionStructureValue;


}
