
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_GlobalSettings.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.entity.FileUpload;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.exception.FileUploadException;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.service.FileUploadServiceImpl;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.dto.GlobalSettingRequest;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.entity.GlobalSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.*;

@Service
public class GlobalSettingServiceWorker {
    @Autowired
    GlobalSettingServiceImpl globalSettingServiceImpl;

    @Autowired
    FileUploadServiceImpl fileUploadService;

    private boolean checkAllowStudentChangePassport(){
        final String key = "AllowPassportChange";
        GlobalSetting globalSetting =  globalSettingServiceImpl.findByKey(key);
        if ( globalSetting != null){
            return globalSetting.getValue().equals("Yes");
        }
        return false;
    }

    private boolean checkAllowStudentChangeState(){
        final String key = "AllowStateChange";
        GlobalSetting globalSetting =  globalSettingServiceImpl.findByKey(key);
        if ( globalSetting != null){
            return globalSetting.getValue().equals("Yes");
        }
        return false;
    }

    public String getSchoolOfficialLogo() throws FileNotFoundException {
        final String key = "SchoolOfficialLogo";
        GlobalSetting globalSetting = globalSettingServiceImpl.findByKey(key);
        if (globalSetting != null && !globalSetting.getValue().isEmpty()){
            long id = Long.parseLong(globalSetting.getValue());
            byte[] file = fileUploadService.getFile(id).getData();
            return Base64.getEncoder().encodeToString(file);
        }else {
            return "";
        }
    }

    private String getReceiptSignature() throws FileNotFoundException {
        final String key = "ReceiptSignature";

        Optional<GlobalSetting> globalSetting = Optional.ofNullable(globalSettingServiceImpl.findByKey(key));
        if (globalSetting.isPresent() && !globalSetting.get().getValue().isEmpty()){
            long id = Long.parseLong(globalSetting.get().getValue());
            byte[] file = fileUploadService.getFile(id).getData();
            return Base64.getEncoder().encodeToString(file);
        }
        else {
            return "";
        }
    }

    private String getRegistrarSignature() throws FileNotFoundException {
        final String key = "RegistrarSignature";
        Optional<GlobalSetting> globalSetting = Optional.ofNullable(globalSettingServiceImpl.findByKey(key));
        if (globalSetting.isPresent() && !globalSetting.get().getValue().isEmpty()){
            long id = Long.parseLong(globalSetting.get().getValue());
            byte[] file = fileUploadService.getFile(id).getData();
            return Base64.getEncoder().encodeToString(file);
        }
        else {
            return "";
        }

    }

    private String getAlternativeLogo() throws FileNotFoundException {
        final String key = "AlternativeLogo";
        Optional<GlobalSetting> globalSetting = Optional.ofNullable(globalSettingServiceImpl.findByKey(key));
        if (globalSetting.isPresent() && !globalSetting.get().getValue().isEmpty()){
            long id = Long.parseLong(globalSetting.get().getValue());
            byte[] file = fileUploadService.getFile(id).getData();
            return Base64.getEncoder().encodeToString(file);
        }
        else {
            return "";
        }

    }

    public List<String>  getPhoneLinesAsList(){
        final String key = "PhoneLines";
        GlobalSetting globalSetting = globalSettingServiceImpl.findByKey(key);
        if (globalSetting != null)
            return Arrays.asList(globalSetting.getValue().split("\\s*,\\s*"));
        return new ArrayList<>();
    }

    public List<String> getPublicMailBagAsList(){
        final String key = "PublicMailBag";
        GlobalSetting globalSetting = globalSettingServiceImpl.findByKey(key);
        if (globalSetting != null)
            return Arrays.asList(globalSetting.getValue().split("\\s*,\\s*"));
        return new ArrayList<>();
    }
    
    public Map<String, Object> getStudentSpecificConfigsKeyValue(){
        Map<String, Object> studentSpecificConfigs = new HashMap<>();
        studentSpecificConfigs.put("AllowPassportChange", checkAllowStudentChangePassport());
        studentSpecificConfigs.put("AllowStateChange", checkAllowStudentChangeState());
        return studentSpecificConfigs;
    }

    public Map<String, String > getAllGlobalSettingConfigMap() throws FileNotFoundException {
        Map<String, String> mappedSettings = new HashMap<>();

        List<GlobalSetting> globalSettingsList = globalSettingServiceImpl.getAllGlobalSetting();

        for (GlobalSetting setting : globalSettingsList)
        {
            if (setting != null){

                switch (setting.getKey()) {
                    case "SchoolOfficialLogo":
                        setting.setValue(getSchoolOfficialLogo());

                        break;
                    case "ReceiptSignature":
                        setting.setValue(getReceiptSignature());

                        break;
                    case "RegistrarSignature":
                        setting.setValue(getRegistrarSignature());

                        break;
                    case "AlternativeLogo":
                        setting.setValue(getAlternativeLogo());

                        break;
                }
                mappedSettings.put(setting.getKey(), setting.getValue());
            }
        }

        return  mappedSettings;

    }

    public void updateGlobalSetting(Map<String, MultipartFile> fileMap, Map<String, Object> configMap) throws FileUploadException, FileNotFoundException {
        
        for (Map.Entry<String, MultipartFile> file : fileMap.entrySet()){

            if (!file.getValue().isEmpty()){
                Optional<GlobalSetting>  key = Optional.ofNullable(globalSettingServiceImpl.findByKey(file.getKey()));  // key holds id for FileUpload Table
                if (key.isPresent() ){
                    String value = key.get().getValue();
                    if (value.equals("")){
                        FileUpload fileUpload = fileUploadService.saveFile(file.getValue());
                        globalSettingServiceImpl.updateGlobalSetting( String.valueOf( fileUpload.getId() ) , file.getKey());
                    }else {
                        FileUpload fileId = fileUploadService.getFile( Long.parseLong(value) );
                        if (fileId != null){
                            GlobalSettingRequest globalSettingDto = new GlobalSettingRequest(fileId.getId(), file.getValue()  );
                            FileUpload fileUpload = fileUploadService.updateFile(globalSettingDto);
                            globalSettingServiceImpl.updateGlobalSetting( String.valueOf( fileUpload.getId() ) , file.getKey());

                        }else{
                            FileUpload fileUpload = fileUploadService.saveFile(file.getValue());
                            globalSettingServiceImpl.updateGlobalSetting( String.valueOf( fileUpload.getId() ) , file.getKey());
                        }
                    }
                    
                }
                
            }
            
        }

        for (Map.Entry<String, Object> entry : configMap.entrySet()) {
            globalSettingServiceImpl.updateGlobalSetting(entry.getValue().toString(), entry.getKey());
        }
    }
    
    
    public String getReportHeader(){
        final String key = "ReportHeader";
        GlobalSetting globalSetting = globalSettingServiceImpl.findByKey(key);
        if (globalSetting != null){
            return globalSetting.getValue();
        }
        return "";
       
    }
    
    public String getReportFooter(){
        final String key = "ReportFooter";
        GlobalSetting globalSetting = globalSettingServiceImpl.findByKey(key);
        if (globalSetting != null){
            return globalSetting.getValue();
        }
        return "";
    }
    
    public String getSchoolAddress(){
        final String key = "SchoolAddress";
        GlobalSetting globalSetting = globalSettingServiceImpl.findByKey(key);
        if (globalSetting != null){
            return globalSetting.getValue();
        }
        return "";
        
    }

    public GlobalSetting getGlobalSettingConfigValue(String key){
        return globalSettingServiceImpl.findByKey(key);
    }
    


}
