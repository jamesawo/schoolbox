
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.entity;

import com.jamesportal.slms.entity.SlmsBaseEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "SLMS_SpecificFee_Installment_Record")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeeInstallment extends SlmsBaseEntity {

    @OneToOne
    private SpecificFee specificFee;

    private BigDecimal firstInstalment;

    private BigDecimal secondInstalment;
}
