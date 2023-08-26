
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class SFeeSearchParamDto {

    @Nullable
    long id;

    @Nullable
    long programmeCategory;

    @Nullable
    long programmeType;

    @Nullable
    long session;

    @Nullable
    long level;

    @Nullable
    long semester;

    @Nullable
    String status;

    @Nullable
    long feeCategory;

    @Nullable
    String institutionStructure;

    @Nullable
    long institutionStructureValue;

    @Nullable
    String feeType;

    @Nullable
    List<Long> institutionStructureIds = new ArrayList<>();

    int pageNumber;

    int pageSize;

    int displayButton;

    int initialPage;

    @Nullable
    String reportFormat;

    @Nullable
    String studentRegNumber;



}
