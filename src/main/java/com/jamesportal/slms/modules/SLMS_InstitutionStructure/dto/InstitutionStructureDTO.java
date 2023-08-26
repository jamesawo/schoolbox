
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import lombok.Data;

@Data
public class InstitutionStructureDTO {
    private long id;
    private String description;
    private int hierarchy;
    private String code;
    private InstitutionStructure parent_id;
    // category ID
    private long group_id;

    public InstitutionStructureDTO(long id, String description, int hierarchy, String code, InstitutionStructure parent_id, long group_id) {
        this.id = id;
        this.description = description;
        this.hierarchy = hierarchy;
        this.code = code;
        this.parent_id = parent_id;
        this.group_id = group_id;
    }

    public InstitutionStructureDTO() {
    }

    @Override
    public String toString() {
        return "InstitutionStructureDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", hierarchy=" + hierarchy +
                ", code='" + code + '\'' +
                ", parent_id=" + parent_id +
                ", group_id=" + group_id +
                '}';
    }
}
