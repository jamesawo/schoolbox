
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_PaymentOption.entity;

import com.jamesportal.slms.entity.SlmsBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SLMS_PaymentMethod_Record")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentMethod  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long id;

    private String description;

    private long hierarchy;

    private String type;

    private String displayFormat;

    private String callbackUrl;

    private String webHook;

    private String publicKey;

    private String privateKey;

    private String verifyUrl;


    private boolean status = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    public PaymentMethod(String description, String type, String verifyUrl) {
        this.description = description;
        this.type = type;
        this.verifyUrl = verifyUrl;
    }
}
