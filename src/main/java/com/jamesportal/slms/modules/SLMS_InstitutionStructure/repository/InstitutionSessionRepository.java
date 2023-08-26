
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionProgrammeType;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionSessionRepository extends JpaRepository<InstitutionSession, Long> {
    List<InstitutionSession> getAllByProgrammeType(InstitutionProgrammeType programmeType);

    @Query("update InstitutionSession s set s.status = false where s.id=:id")
    void deactivateById(@Param("id") long id);

    @Query("update InstitutionSession s set s.status = true where s.id=:id")
    void activateById(@Param("id") long id);

}
