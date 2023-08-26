
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
import com.jamesportal.slms.modules.SLMS_GlobalSettings.dto.GlobalSettingRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFileUploadService {

    FileUpload saveFile(MultipartFile file) throws FileUploadException;

    FileUpload updateFile(GlobalSettingRequest fileToUpdate) throws FileUploadException;

    FileUpload getFile(Long id) throws FileNotFoundException;

    void deleteFile(Long id);

    void writeFilesToDisk() throws IOException;

    String uploadFileToDisk(MultipartFile file) throws FileUploadException;

}
