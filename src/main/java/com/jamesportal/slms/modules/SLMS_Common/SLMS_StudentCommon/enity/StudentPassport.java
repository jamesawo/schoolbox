
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name="SLMS_StudentPassportRecord")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentPassport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] data;

    public StudentPassport(byte[] data) {
        this.data = data;
    }

}

