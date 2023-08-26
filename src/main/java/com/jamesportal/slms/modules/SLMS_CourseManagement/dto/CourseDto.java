
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_CourseManagement.dto;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionLevel;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSemester;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString @AllArgsConstructor @NoArgsConstructor
public class CourseDto {

    long id;
    String courseTitle;
    String courseCode;
    int CourseUnit;
    InstitutionStructure institutionStructure;
    InstitutionSemester semester;
    InstitutionLevel level;
    Boolean status;

}
