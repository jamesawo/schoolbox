
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.dto;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.Country;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.Lga;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.State;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentEntryType;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentPassport;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionLevel;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSession;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class StudentDTO {

    private long id;

    private String fullName;

    private String firstName;

    private String surname;

    private Date dateOfBirth;

    private String gender;

    private String lastName;

    private StudentEntryType entryType;

    private String email;

    private String regNumber;

    private InstitutionLevel level;

    private InstitutionSession session;

    private InstitutionStructure institutionStructure;

    private InstitutionProgrammeType programmeType;

    private boolean status;

    private String maritalStatus;

    private String phone;

    private String height;

    private String weight;

    private Country country;

    private State state;

    private Lga lga;

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

    private MultipartFile passport;

}
