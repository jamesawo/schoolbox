
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FeeCategoryDTO {

    private long id;

    private String description;

    private String type;

    boolean status;



}
