
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Common.dto;

import com.jamesportal.slms.modules.SLMS_FeeManagement.dto.SpecificFeeDTO;
import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.SpecificFee;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentProfileDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PaginationDto {

    private List<StudentProfileDTO> studentProfileDTOS;

    private List<SpecificFeeDTO> specificFeeDTOS;

    private boolean hasPrevious;

    private boolean hasNext;

    private int pageNumber;

    private int totalPages;

    private int pageSize;

    private long totalResultSize;
}
