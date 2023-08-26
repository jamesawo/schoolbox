
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_FeeManagement.service;

import com.jamesportal.slms.modules.SLMS_FeeManagement.entity.FeeCategory;

import java.util.List;

public interface IFeeCategoryService {

    FeeCategory getFeeCategoryById(long id);

    FeeCategory createFeeCategory(FeeCategory feeCategory);

    FeeCategory updateFeeCategory(FeeCategory feeCategory);

    void deactivateFeeCategory(FeeCategory feeCategory);

    void activateFeeCategory(FeeCategory feeCategory);

    List<FeeCategory> getAllFeeCategory();

    List<FeeCategory> getAllFeeCategoryByCategoryType(String type);

    FeeCategory getFeeCategoryByCategoryType(String type);




}
