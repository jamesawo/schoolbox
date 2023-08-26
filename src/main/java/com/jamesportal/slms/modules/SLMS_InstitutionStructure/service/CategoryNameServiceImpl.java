
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_InstitutionStructure.service;

import com.jamesportal.slms.modules.SLMS_InstitutionStructure.entity.InstitutionCategory;
import com.jamesportal.slms.modules.SLMS_InstitutionStructure.repository.CategoryNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryNameServiceImpl implements ICategoryNameService {

    @Autowired
    CategoryNameRepository categoryNameRepository;

    @Override
    public List<InstitutionCategory> getAllCategoryNames() {
        return categoryNameRepository.getCategoryNames();
    }

    @Override
    public InstitutionCategory saveCategoryName(InstitutionCategory categoryName) {
        return categoryNameRepository.save(categoryName);
    }

    @Override
    public InstitutionCategory updateCategoryName(InstitutionCategory categoryName) {
        return categoryNameRepository.save(categoryName);
    }

    @Override
    public InstitutionCategory getCategoryNameByDescription(String description) {
        return categoryNameRepository.findByDescription(description);
    }

    @Override
    public InstitutionCategory getCategoryNameByLeastHierarchy() {
        int leastHierarchy = categoryNameRepository.findByLeastHierarchy();
        return categoryNameRepository.findByHierarchy(leastHierarchy);
    }

    @Override
    public InstitutionCategory getCategoryNameById(Long id)
    {
        return categoryNameRepository.getOne(id);
    }

    @Override
    public void createCategoryNameFromList(List<InstitutionCategory> categoryList) {
         categoryNameRepository.saveAll(categoryList);
    }

    @Override
    public InstitutionCategory getCategoryNameByHighestHierarchy() {
        int highestHierarchy = categoryNameRepository.findByHighestHierarchy();
        return categoryNameRepository.findByHierarchy(highestHierarchy);
    }

    @Override
    public InstitutionCategory getCategoryNameByHierarchy(int i) {
        return categoryNameRepository.findByHierarchy(i);
    }
}
