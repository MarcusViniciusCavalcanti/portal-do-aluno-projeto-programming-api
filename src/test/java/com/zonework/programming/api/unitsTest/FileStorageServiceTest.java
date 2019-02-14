package com.zonework.programming.api.unitsTest;

import com.zonework.programming.api.ApiApplication;
import com.zonework.programming.api.application.archives.services.FileStorageService;
import org.aspectj.util.FileUtil;
import org.h2.store.fs.FileUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class FileStorageServiceTest {

    @Autowired
    private FileStorageService fileStorageService;

    @Test
    public void should_have_storage_pdf() throws Exception {
        String fileName = "file.pdf";

        var mock = new MockMultipartFile("file", fileName, MediaType.APPLICATION_PDF_VALUE, "test file".getBytes());
        var result = fileStorageService.storeFile(mock);

        assertThat(result.getFileName(), is(fileName));
        assertThat(result.getFileDownloadUri(), Matchers.containsString(".pdf"));
        assertThat(result.getFileType(), Matchers.containsString("application/pdf"));
    }

    @Test
    public void should_have_return_file() throws Exception {
        var fileName = "file.pdf";
        var mock = new MockMultipartFile("file", fileName, MediaType.APPLICATION_PDF_VALUE, "test file".getBytes());
        var fileResponse = fileStorageService.storeFile(mock);

        var result = fileStorageService.loadFileAsResource(fileResponse.getHash());

        assertThat(result.getURI().getPath(), CoreMatchers.containsString(fileResponse.getHash()));
    }

}