
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCourseDuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDurationRepository extends JpaRepository<InstitutionCourseDuration, Long> {
}
