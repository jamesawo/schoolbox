
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SLMS_InstitutionCurrentSession_Record") // Current Session and Semester
public class InstitutionCurrentSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long id;

    @OneToOne
    private InstitutionProgrammeType institutionProgrammeType;

    @OneToOne
    private InstitutionSemester institutionSemester;

    @OneToOne
    private InstitutionSession institutionSession;

    private boolean status = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    public InstitutionCurrentSession() {
    }

    public InstitutionCurrentSession(InstitutionProgrammeType institutionProgrammeType, InstitutionSemester institutionSemester) {
        this.institutionProgrammeType = institutionProgrammeType;
        this.institutionSemester = institutionSemester;
    }

    public InstitutionCurrentSession(InstitutionProgrammeType institutionProgrammeType, InstitutionSession institutionSession, boolean status) {
        this.institutionProgrammeType = institutionProgrammeType;
        this.institutionSession = institutionSession;
        this.status = status;
    }
}
