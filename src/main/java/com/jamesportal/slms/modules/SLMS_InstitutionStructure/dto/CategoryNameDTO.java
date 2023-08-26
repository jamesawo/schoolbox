
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

import java.util.List;

@Data
public class CategoryNameDTO {

    private long id;

    private String description;

    private int hierarchy;

    private String code;

    private long parent_id;

    private long group_id;

    private List<InstitutionCategory> categoryList;

    public CategoryNameDTO(long id, String description, int hierarchy, String code, long parent_id, long group_id) {
        this.id = id;
        this.description = description;
        this.hierarchy = hierarchy;
        this.code = code;
        this.parent_id = parent_id;
        this.group_id = group_id;
    }

    public CategoryNameDTO(){}


}
