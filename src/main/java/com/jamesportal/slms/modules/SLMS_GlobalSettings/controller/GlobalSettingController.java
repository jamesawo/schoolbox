
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_GlobalSettings.controller;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.entity.FileUpload;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.exception.FileUploadException;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.service.FileUploadServiceImpl;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.entity.GlobalSetting;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.service.GlobalSettingServiceImpl;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.service.GlobalSettingServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/SLMS/GlobalSetting")
public class GlobalSettingController {


    @Autowired
    GlobalSettingServiceWorker globalSettingServiceWorker;

    @Autowired
    FileUploadServiceImpl fileUploadService;

    @RequestMapping( value = "/ManageSpecialEffects", method = RequestMethod.GET)
    public ModelAndView GlobalIndex() throws IOException {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("settings", globalSettingServiceWorker.getAllGlobalSettingConfigMap());
        modelAndView.setViewName("/modules/backend/views/global_settings/index");

        return modelAndView;
    }

    @RequestMapping(value = "/ManageSpecialEffects", method = RequestMethod.POST)
    public String UpdateGlobalSetting(@RequestParam Map<String, Object> globalSettingRequest,
                                      MultipartRequest request,
                                      HttpServletRequest servletRequest,
                                      RedirectAttributes redirectAttributes) throws IOException, FileUploadException {


        Map<String,MultipartFile> fileRequest = new HashMap<>();
        fileRequest.putAll(request.getFileMap());

        globalSettingServiceWorker.updateGlobalSetting(fileRequest, globalSettingRequest);
        redirectAttributes.addFlashAttribute("message", "Update Successfully.");

        return "redirect:" + servletRequest.getHeader("Referer");
    }

}

