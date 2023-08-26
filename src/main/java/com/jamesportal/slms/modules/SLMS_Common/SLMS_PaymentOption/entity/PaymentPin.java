
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity;

import com.jamesportal.slms.entity.SlmsBaseEntity;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.entity.SlmsIdentity;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.SpecificFee;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "SLMS_PaymentPin_Record")
@NoArgsConstructor @AllArgsConstructor @ToString
public class PaymentPin extends SlmsBaseEntity {

    private String pin;

    private String serial;

    private int attempt;

    @OneToOne
    private PaymentMethod paymentMethod;

    private Date usedDate;

    @OneToOne
    private Student student;

    @OneToOne
    private SlmsIdentity slmsIdentity;

    @OneToOne
    private SpecificFee specificFee;

    private String ref;


}
