
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_GlobalSettings.service;

import com.jamesportal.slms.modules.SLMS_GlobalSettings.entity.GlobalSetting;

import java.util.List;


public interface IGlobalSettingService {

    List<GlobalSetting> getAllGlobalSetting();

    GlobalSetting saveGlobalSetting(GlobalSetting globalSetting);

    GlobalSetting findByKey(String key);

    void updateGlobalSetting(String value, String key);


}
