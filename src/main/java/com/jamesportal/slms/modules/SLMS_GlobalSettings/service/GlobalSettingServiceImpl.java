
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_GlobalSettings.service;

import com.jamesportal.slms.modules.SLMS_GlobalSettings.entity.GlobalSetting;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.repository.GlobalSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalSettingServiceImpl implements IGlobalSettingService {

    @Autowired
    private GlobalSettingRepository globalSettingRepository;

    @Override
    public List<GlobalSetting> getAllGlobalSetting() { return globalSettingRepository.findAll(); }

    @Override
     public GlobalSetting findByKey(String key){
        //todo::refactor this
        GlobalSetting globalSetting = globalSettingRepository.findByKey(key);
        if (globalSetting != null){
            return globalSetting;
        }
        return null;
    }

    @Override
    public GlobalSetting saveGlobalSetting(GlobalSetting globalSetting){ return globalSettingRepository.save(globalSetting); }


    @Override
    public void updateGlobalSetting(String value, String key){ globalSettingRepository.updateGlobalSetting(value, key); }
}
