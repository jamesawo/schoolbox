
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentEntryType;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentPassport;

import java.util.List;

public interface IStudentEntryTypeService {

    StudentEntryType createStudentEntryType(StudentEntryType entryType);

    List<StudentEntryType> getAllStudentEntryType();

    void createStudentEntryTypeFromList(List<StudentEntryType> entryTypeList);
	
	StudentEntryType getStudentEntryTypeById(long entryType);
	
}
