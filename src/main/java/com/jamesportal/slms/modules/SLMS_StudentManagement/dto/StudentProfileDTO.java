
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
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionLevel;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class StudentProfileDTO {

    private long id;

    private String fullName;

    private String firstName;

    private String lastName;

    private String email;

    private String regNumber;

    private String surname;

    private boolean status;

    private String maritalStatus;

    private String phone;

    private String height;

    private String weight;

    private String country;

    private String state;

    private String lga;

    private long lgaValue;

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

    private String programmeType;

    private String level;

    private Map<Integer, String[]> programmeOfStudyStructure1  = new HashMap<>();

    private Map<Integer, Map<String, List<Object[]> > > institutionStructureMapData = new HashMap<>();

    private String courseOfStudy;

    private String gender;

    private String entryType;

    private String jambReg;

    private String applicationNumber;

    private String session;

    private String currentSession;

    private String dateOfBirth;

    private InstitutionProgrammeCategory programmeCategory;

    private InstitutionProgrammeType institutionProgrammeType;

    private List<InstitutionProgrammeType> institutionProgrammeTypeList;

    private List<InstitutionProgrammeCategory> programmeCategoryList;

    private List<StudentEntryType> entryTypeList;

    private List<InstitutionLevel> levelList;

    private List<Lga> lgaList;

    private MultipartFile passport;

    private StudentEntryType studentEntryType;

    private InstitutionLevel institutionLevel;

    private Country studentCountry;

    private State studentState;

    private Lga studentLga;

    private String passportData;

    private InstitutionStructure institutionStructure;

    private long levelValue;

}
