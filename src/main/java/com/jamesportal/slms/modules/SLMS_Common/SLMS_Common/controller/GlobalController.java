
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Common.controller;

import com.jamesportal.slms.modules.SLMS_GlobalSettings.entity.GlobalSetting;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.service.GlobalSettingServiceImpl;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.service.GlobalSettingServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalController {

    @Autowired
    GlobalSettingServiceImpl globalSettingServiceImpl;

    @Autowired
    GlobalSettingServiceWorker globalSettingServiceWorker;

    @ModelAttribute(name = "globalSettingContextMap")
    public Map<String, Object> setGlobalModelAttributes(HttpServletRequest request, Model model){

        Map<String, Object> map = new HashMap<>();

        GlobalSetting globalSetting;
        List<String> configValues;

        globalSetting = globalSettingServiceImpl.findByKey("SchoolName");
        if (globalSetting != null)
            map.put("CollegeName", globalSetting.getValue() );
        globalSetting = globalSettingServiceImpl.findByKey("SchoolSlogan");
        if (globalSetting != null)
            map.put("SchoolSlogan", globalSetting.getValue() );
        globalSetting = globalSettingServiceImpl.findByKey("SchoolAddress");
        if (globalSetting != null)
            map.put("SchoolAddress", globalSetting.getValue());
        configValues = globalSettingServiceWorker.getPhoneLinesAsList();
        if (configValues != null)
            map.put("PhoneLines", configValues);
        configValues = globalSettingServiceWorker.getPublicMailBagAsList();
        if (configValues != null)
            map.put("PublicMailBag", configValues);

        globalSetting = globalSettingServiceImpl.findByKey("ShowCustomizer");
        if (globalSetting != null)
            map.put("ShowCustomizer", globalSetting.getValue());

        globalSetting = globalSettingServiceImpl.findByKey("FrontCssMasterDefault");
        if (globalSetting != null)
            map.put("DefaultStyle", globalSetting.getValue());

        globalSetting = globalSettingServiceImpl.findByKey("FrontCssMasterCustomize");
        if (globalSetting != null)
            map.put("UserDefinedStyle", globalSetting.getValue());

        globalSetting = globalSettingServiceImpl.findByKey("SchoolWebSiteUrl");
        if (globalSetting != null)
            map.put("SchoolWebSiteUrl", globalSetting.getValue());




        model.addAttribute(map);
        return map;
    }


}
