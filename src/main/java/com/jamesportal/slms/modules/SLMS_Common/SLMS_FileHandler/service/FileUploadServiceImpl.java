
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.entity.FileUpload;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.exception.FileUploadException;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.repository.FileUploadRepository;
import com.jamesportal.slms.modules.SLMS_GlobalSettings.dto.GlobalSettingRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadServiceImpl implements IFileUploadService {

//    @Value("${app.upload-dir}")
    public String uploadDIr = "src/main/resources/static/assets/images/global/";

    @Autowired
    FileUploadRepository fileRepository;

    @Override
    public FileUpload saveFile(MultipartFile file) throws FileUploadException {

        String fileName = StringUtils.cleanPath(file.getName());

        try {
            if(fileName.contains("..")) {
                throw new FileUploadException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            //Check if file with name already exist
            boolean fileExist = fileRepository.existsFilesByName(fileName);
            if ( fileExist  ){
                FileUpload fileT = fileRepository.findByName(fileName);
                fileT.setName(fileName);
                fileT.setType(file.getContentType());
                fileT.setData(readAllBytes(file.getInputStream()));
                return fileRepository.save(fileT);
            }
            else{
                FileUpload fileT = new FileUpload(fileName, file.getContentType(), readAllBytes(file.getInputStream()));
                return fileRepository.save(fileT);
            }
        } catch (IOException ex) {
            throw new FileUploadException("Could not store file " + fileName + ". Please try again!", ex);
        }


    }

    @Override
    public FileUpload updateFile(GlobalSettingRequest globalSettingDto) throws FileUploadException {
        String fileName = StringUtils.cleanPath(globalSettingDto.getFile().getName());
        try {
            if(fileName.contains("..")) {
                throw new FileUploadException("Sorry, Filename contains invalid path sequence " + fileName);
            }
            FileUpload fileT = fileRepository.getOne(globalSettingDto.getId());
            if (fileT != null){
                fileT.setName(fileName);
                fileT.setType(globalSettingDto.getFile().getContentType());
                fileT.setData(readAllBytes(globalSettingDto.getFile().getInputStream()));
            }
            return fileRepository.save(fileT);
        } catch (IOException ex) {
            throw new FileUploadException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public FileUpload getFile(Long id) throws FileNotFoundException
    {
        if (id != 0){
            return fileRepository.findById(id).orElseThrow(() -> new FileNotFoundException("File not found with id " + id));
        }else {
            return null;
        }
    }

    @Override
    public void deleteFile(Long id) { }

    /*
        TODO::CLEAN SERVICE CLASS
        REMOVE CHECK LOGIC TO CLASS FACTORY
     */

    @Override
    public void writeFilesToDisk() throws IOException {
        // retrieve image from DB via SpringJPA
        for(FileUpload file : fileRepository.findAll()){
            java.nio.file.Files.write(Paths.get(uploadDIr + file.getName() + "." + file.getType().split("[/]")[1]), file.getData());
        }
    }

    @Override
    public String uploadFileToDisk(MultipartFile file) throws FileUploadException {

        if (!file.isEmpty()) {

            try {
                String fileName = file.getName() + "." + file.getContentType().split( "[/]" )[ 1 ];
                InputStream is = file.getInputStream();
                String path = uploadDIr  + fileName;
                Files.copy(is, Paths.get(uploadDIr + fileName), StandardCopyOption.REPLACE_EXISTING);

                return fileName;

            } catch (IOException e) {

                String msg = String.format("Failed to store file %s", file.getName());
                throw new FileUploadException(msg, e);
            }
        }
        return null;
    }

    private byte[] readAllBytes(InputStream is){
        try {
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
