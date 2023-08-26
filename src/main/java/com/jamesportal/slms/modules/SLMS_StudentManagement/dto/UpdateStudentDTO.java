
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.dto;

import lombok.Data;

@Data
public class UpdateStudentDTO {
    private boolean status;

    private String maritalStatus;

    private String phone;

    private String height;

    private String weight;

    private String country;

    private String state;

    private String lga;

    private String town;

    private String facialMarks;

    private String contactAddress;

    private String postalAddress;

    private String guardianFullName;

    private String guardianOccupation;

    private String guardianRelationship;

    private String guardianPhone;

    private String guardianAddress;

    private String guardianEmail;


}
