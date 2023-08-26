
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoryNameRepository extends JpaRepository<InstitutionCategory, Long > {

    InstitutionCategory findByDescription(String description);

    InstitutionCategory getOne(Long id);

    InstitutionCategory findByHierarchy(int hierarchy);

    @Query("select min(hierarchy) from InstitutionCategory")
    int findByLeastHierarchy();

    @Query("select max(hierarchy) from InstitutionCategory")
    int findByHighestHierarchy();

    @Transactional
    @Modifying
    @Query("FROM InstitutionCategory c ORDER BY c.hierarchy ASC ")
    List<InstitutionCategory> getCategoryNames();

}
