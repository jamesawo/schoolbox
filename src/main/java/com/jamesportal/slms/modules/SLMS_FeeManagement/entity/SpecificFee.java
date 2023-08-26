
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.entity;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SLMS_SpecificFee_Record")
public class SpecificFee  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long id;

    @OneToOne
    private InstitutionProgrammeType programmeType;

    @OneToOne
    private InstitutionSession session;

    @OneToOne
    private InstitutionLevel level;

    @OneToOne
    private InstitutionStructure institutionStructure;

    @OneToOne
    private InstitutionSemester semester;

    @OneToOne
    private FeeCategory feeCategory;

    private String dependentFee;

    private BigDecimal amount;

    private boolean status = true;

    private String itemCode;

    private String merchant;

    private String type;

    private boolean allowPartPayment = false;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    public SpecificFee(){}

    public SpecificFee(long id){
        this.id = id;
    }


}
