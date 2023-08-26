
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.dto;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data @NoArgsConstructor @AllArgsConstructor
public class StudentSearchParamDTO {

    @Nullable
    long id;

    @Nullable
    String fullName;

    @Nullable
    String regNumber;

    @Nullable
    long country;

    @Nullable
    long programmeType;

    @Nullable
    InstitutionSession session;

    @Nullable
    String status;

    @Nullable
    long level;

    @Nullable
    String institutionStructure;

    @Nullable
    long institutionStructureValue;

    @Nullable
    long entryType;

    String command;

    List<Long> institutionStructureIds = new ArrayList<>();

    int pageNumber;

    int pageSize;

    int displayButton;

    int initialPage;
    
    @Nullable
    String reportFormat;

}
