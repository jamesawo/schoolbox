
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_GlobalSettings.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Data
public class GlobalSettingRequest {

    private Map<String, String> data;

    private long id;

    private MultipartFile file;

    public GlobalSettingRequest(long id, MultipartFile file) {
        this.id = id;
        this.file = file;
    }

    public GlobalSettingRequest() {

    }
}
