
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSemester;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SemesterDTO {

    private long id;

    private String description;

    private int hierarchy;

    List<InstitutionSemester> institutionSemesterList;

}
