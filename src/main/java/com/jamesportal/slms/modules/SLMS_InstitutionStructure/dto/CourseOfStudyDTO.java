
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.dto;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCourseDuration;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class CourseOfStudyDTO {

    private long id;

    private int numberOfLevels;

    private int duration;

    private long institutionStructure;

    private long institutionProgrammeType;

    private List<InstitutionCourseDuration> courseDurationList;

    @Override
    public String toString() {
        return "CourseOfStudyDTO{" +
                "id=" + id +
                ", numberOfLevels=" + numberOfLevels +
                ", duration=" + duration +
                ", institutionStructure=" + institutionStructure +
                ", institutionProgrammeType=" + institutionProgrammeType +
                ", courseDurationList=" + courseDurationList +
                '}';
    }
}
