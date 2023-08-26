
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentEntryType;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.repository.StudentEntryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentEntryTypeServiceImpl implements IStudentEntryTypeService {

    @Autowired
    StudentEntryTypeRepository entryTypeRepository;

    @Override
    public StudentEntryType createStudentEntryType(StudentEntryType entryType) {
        return entryTypeRepository.save(entryType);
    }

    @Override
    public List<StudentEntryType> getAllStudentEntryType() {
        return entryTypeRepository.findAll();
    }

    @Override
    public void createStudentEntryTypeFromList(List<StudentEntryType> entryTypeList) {
        entryTypeRepository.saveAll(entryTypeList);
    }
    
    @Override
    public StudentEntryType getStudentEntryTypeById(long entryType) {
        return entryTypeRepository.getOne(entryType);
    }
    
}
