
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_SystemPerformance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor  @AllArgsConstructor
public class TestCategoryStructureDto {

    int categoryDepth;

    int startHierarchy;

    boolean isUniversity = false;

    boolean isPolytechnic  = false;

    boolean isCollegeOfEducation  = false;


}
