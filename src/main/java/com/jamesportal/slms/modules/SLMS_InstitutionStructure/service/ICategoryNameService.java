
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;


import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;

import java.util.List;

public interface ICategoryNameService {

    List<InstitutionCategory> getAllCategoryNames();

    InstitutionCategory saveCategoryName(InstitutionCategory categoryName);

    InstitutionCategory updateCategoryName(InstitutionCategory categoryName);

    InstitutionCategory getCategoryNameByDescription(String description);

    InstitutionCategory getCategoryNameByLeastHierarchy();

    InstitutionCategory getCategoryNameById(Long id);

    void createCategoryNameFromList(List<InstitutionCategory>  categoryList);

    InstitutionCategory getCategoryNameByHighestHierarchy();

    InstitutionCategory getCategoryNameByHierarchy(int i);
}
