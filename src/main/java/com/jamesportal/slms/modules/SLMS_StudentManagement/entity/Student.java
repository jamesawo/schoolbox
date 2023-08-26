
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;


//TODO::Add Unique Validation to model
@Data
@Entity
@Table(name = "SLMS_StudentPart_Record")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long id;

    private String firstName;

    private String surname;

    private String lastName;

    private Date dateOfBirth;

    private String gender;

    private String email;

//    @Column(unique = true)

    private String regNumber;

    private String maritalStatus;

    private String phone;

    private String height;

    private String weight;

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

    private String applicationNumber;

    private String jambReg;

    private boolean status = true;

    @OneToOne
    private Country country;

    @OneToOne
    private State state;

    @OneToOne
    private Lga lga;

    @OneToOne
    private InstitutionLevel level;

    @OneToOne
    private InstitutionSession session;

    @OneToOne
    private InstitutionStructure institutionStructure;

    @OneToOne
    private InstitutionProgrammeType programmeType;

    @OneToOne
    private StudentPassport passport;

    @OneToOne
    private StudentEntryType entryType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    public Student(long id) {
        this.id = id;
    }

    public String getFullName(){
        return this.surname + ' ' +this.firstName + ' ' +this.lastName;
    }

    public Student(String surname,
                   String firstName,
                   String lastName,
                   String regNumber,
                   String email,
                   InstitutionStructure institutionStructure,
                   InstitutionLevel institutionLevel,
                   InstitutionProgrammeType institutionProgrammeType,
                   InstitutionSession institutionSession,
                   StudentEntryType studentEntryType,
                   Boolean status
                   ) {
        this.surname = surname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.regNumber = regNumber;
        this.email = email;
        this.institutionStructure = institutionStructure;
        this.level = institutionLevel;
        this.programmeType = institutionProgrammeType;
        this.session = institutionSession;
        this.entryType = studentEntryType;

    }



}
