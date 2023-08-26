
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_CourseManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class CourseSearchDto {
    int pageNumber;

    int pageSize;

    int displayButton;

    int initialPage;

    @Nullable
    String reportFormat;

    long semester;

    long level;

    long institutionStructure;

    String courseTitle;

    int courseUnits;

    boolean status;



}
