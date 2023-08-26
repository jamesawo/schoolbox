
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.entity;

import com.jamesportal.slms.entity.SlmsBaseEntity;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity.PaymentMethod;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity.PaymentPin;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "SLMS_FeePayment_Record")
@Data
@NoArgsConstructor @AllArgsConstructor @ToString
public class FeePayment  {

    @OneToOne
    private SpecificFee specificFee;

    @OneToOne
    private Student student;

    private boolean isInstallment;

    private BigDecimal amount;

    private String installmentType;

    private Date transactionDate;

    @OneToOne
    private PaymentMethod paymentMethod;

    private String merchantId;

    private String TransactionRef;

    private String payer;

    @OneToOne
    private PaymentPin pin;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long id;

    private boolean status = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;


}
