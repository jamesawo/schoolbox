
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.dto;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity.PaymentMethod;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.SpecificFee;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class FeePaymentDto {
    private BigDecimal amount;

    private SpecificFee specificFee;

    private Student student;

    private PaymentMethod paymentMethod;

    private boolean isInstallment;

    private String installmentType;

    private String pin;

    private String serial;


}
