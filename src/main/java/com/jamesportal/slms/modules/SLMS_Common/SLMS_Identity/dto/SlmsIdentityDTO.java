
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.dto;

import com.jamesportal.slms.entity.User;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlmsIdentityDTO {

    private long id;

    private long userId;

    private long studentId;


    public SlmsIdentityDTO(Long studentId, Long userId) {
        this.studentId = studentId;
        this.userId = userId;
    }
}
