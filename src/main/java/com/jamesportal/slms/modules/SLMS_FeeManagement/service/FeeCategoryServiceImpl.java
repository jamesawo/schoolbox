
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.service;

import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.FeeCategory;
import com.jamesportal.slms.modules.SLMS_FeeManagement.repository.FeeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeCategoryServiceImpl implements IFeeCategoryService {

    @Autowired
    FeeCategoryRepository feeCategoryRepository;

    @Override
    public FeeCategory getFeeCategoryById(long id) {
        return feeCategoryRepository.getOne(id);
    }

    @Override
    public FeeCategory createFeeCategory(FeeCategory feeCategory) {
        return feeCategoryRepository.save(feeCategory);
    }

    @Override
    public FeeCategory updateFeeCategory(FeeCategory feeCategory) {
        return feeCategoryRepository.save(feeCategory);
    }

    @Override
    public void deactivateFeeCategory(FeeCategory feeCategory) {

    }

    @Override
    public void activateFeeCategory(FeeCategory feeCategory) {

    }

    @Override
    public List<FeeCategory> getAllFeeCategory() {
        return feeCategoryRepository.findAll();
    }

    @Override
    public List<FeeCategory> getAllFeeCategoryByCategoryType(String type) {
        return null;
    }

    @Override
    public FeeCategory getFeeCategoryByCategoryType(String type) {
        return null;
    }
}
