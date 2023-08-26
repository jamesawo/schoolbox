
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import lombok.Data;

import java.util.List;

@Data
public class ProgrammeCategoryDTO {

    private long id;

    private String code;

    private String description;

    private String aliasDescription;

    private boolean status;

    private List<InstitutionProgrammeType> institutionProgrammeTypeList;
}
