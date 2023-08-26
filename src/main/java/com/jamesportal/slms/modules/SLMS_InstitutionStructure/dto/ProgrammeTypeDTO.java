
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;
import lombok.Data;

@Data
public class ProgrammeTypeDTO {

    private long id;

    private String code;

    private String description;

    private String aliasDescription;

    private String merchantId;

    private String certificate;

    private long parentId;

    private boolean status;

    private long institutionProgrammeCategoryId;

    private long reportType;

}


