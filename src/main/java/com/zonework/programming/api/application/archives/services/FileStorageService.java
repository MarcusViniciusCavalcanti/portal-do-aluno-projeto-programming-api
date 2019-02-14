package com.zonework.programming.api.application.archives.services;

import com.zonework.programming.api.application.archives.UploadFileResponse;
import com.zonework.programming.api.domain.archive.configurations.FileStorageProperties;
import com.zonework.programming.api.structure.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public UploadFileResponse storeFile(MultipartFile file) {
        var fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..") || !fileName.endsWith(".pdf")) {
                throw new FileStorageException("Invalid file " + fileName);
            }
            var hasName = createHashBy(fileName);
            var targetLocation = this.fileStorageLocation.resolve(hasName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            var fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/v1/archives/")
                    .path(hasName)
                    .toUriString();

            return new UploadFileResponse(fileName, hasName, fileDownloadUri, file.getContentType(), file.getSize());
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) throws MalformedURLException, FileNotFoundException {
        var filePath = this.fileStorageLocation.resolve(fileName).normalize();
        var resource = new UrlResource(filePath.toUri());
        if(resource.exists()) {
            return resource;
        } else {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }

    private String createHashBy(String name) {
        var tmp =  System.currentTimeMillis() + "-" + name;
        try {
            var digest = MessageDigest.getInstance("SHA-256");
            var hasBytes = digest.digest(tmp.getBytes(StandardCharsets.UTF_8));

            var stringBuilder = new StringBuilder();

            for (byte hasByte : hasBytes) {
                stringBuilder.append(Integer.toString((hasByte & 0xff) + 0x100, 16).substring(1));
            }

            return stringBuilder.toString() + ".pdf";
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
