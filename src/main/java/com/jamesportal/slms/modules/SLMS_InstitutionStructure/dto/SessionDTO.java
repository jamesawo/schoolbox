
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSemester;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString @NoArgsConstructor @AllArgsConstructor
public class SessionDTO {

    private long id;

    private String description;

    private String code;

    private String command;

    private boolean status = true;

    private Date startDate;

    private Date endDate;

    private InstitutionProgrammeType institutionProgrammeType;

    private InstitutionSemester institutionSemester;

    private InstitutionSession institutionSession;

    public SessionDTO(long id, String description) {
        this.id = id;
        this.description = description;
    }
}
