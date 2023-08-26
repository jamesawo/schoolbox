
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.repository;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentEntryType;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_StudentCommon.enity.StudentPassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEntryTypeRepository extends JpaRepository<StudentEntryType, Long> {
}
