
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.exception;

import java.io.IOException;

public class FileUploadException extends Throwable {

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }


    public FileUploadException(String message, IOException ex) throws FileUploadException {
        super(message, ex);
    }
}
