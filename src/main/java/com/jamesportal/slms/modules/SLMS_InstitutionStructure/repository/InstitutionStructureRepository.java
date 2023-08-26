
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_AutoComplete.dto.SuggestionDTO;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionStructureRepository extends JpaRepository<InstitutionStructure, Long> {

    List<InstitutionStructure> findByCategory(InstitutionCategory categoryName);

    List<InstitutionStructure> findByDescriptionContaining(String description);

    List<InstitutionStructure> findByParent(InstitutionStructure parent);

    List<InstitutionStructure> findAllByParent(InstitutionStructure parent);

    @Query(value = "select  new com.jamesportal.slms.modules.SLMS_Common.SLMS_AutoComplete.dto.SuggestionDTO(s.id, s.description) from InstitutionStructure s where lower(s.description) like lower( concat( '%', :description, '%' ) ) ")
    List<SuggestionDTO> findAllByDescriptionLike(@Param("description") String description );

    List<InstitutionStructure> findByCategoryAndParent(InstitutionCategory category, InstitutionStructure parent);


    InstitutionStructure findByDescription(String description);

}
