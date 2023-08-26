
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto;

import lombok.Data;

@Data
public class LevelDTO {

    private long id;

    private String description;

    private String alternativeDescription;

    private int hierarchy;


}
