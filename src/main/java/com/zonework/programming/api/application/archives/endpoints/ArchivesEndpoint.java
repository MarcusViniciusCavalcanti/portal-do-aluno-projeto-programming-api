package com.zonework.programming.api.application.archives.endpoints;

import com.zonework.programming.api.application.archives.UploadFileResponse;
import com.zonework.programming.api.application.archives.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/archives")
public class ArchivesEndpoint {

    private final FileStorageService fileStorage;

    @Autowired
    public ArchivesEndpoint(FileStorageService fileStorage) {
        this.fileStorage = fileStorage;
    }

    @PostMapping("/upload")
    public UploadFileResponse upload(@RequestParam("file") MultipartFile file) {
       return fileStorage.storeFile(file);
    }

    @GetMapping(value = "/{fileName:.+}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) throws IOException {
       var resource = fileStorage.loadFileAsResource(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
