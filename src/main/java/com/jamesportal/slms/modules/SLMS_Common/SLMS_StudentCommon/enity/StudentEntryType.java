
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

import javax.persistence.*;

@Data
@Entity
@Table(name="SLMS_StudentEntryTypeRecord")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentEntryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String description;

    public StudentEntryType(String description) {
        this.description = description;
    }

    public StudentEntryType(long id ) {
        this.id = id;
    }

}

