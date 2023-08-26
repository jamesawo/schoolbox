
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgrammeTypeRepository extends JpaRepository<InstitutionProgrammeType, Long> {

    List<InstitutionProgrammeType> findByInstitutionProgrammeCategory(InstitutionProgrammeCategory institutionProgrammeCategory);

}
