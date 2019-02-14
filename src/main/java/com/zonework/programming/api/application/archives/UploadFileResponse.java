package com.zonework.programming.api.application.archives;

import lombok.Data;

@Data
public class UploadFileResponse {
    private String fileName;
    private String hash;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String hash, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.hash = hash;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
