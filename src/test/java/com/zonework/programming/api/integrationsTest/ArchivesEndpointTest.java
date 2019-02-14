package com.zonework.programming.api.integrationsTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

public class ArchivesEndpointTest extends AbstractIntegrationTest {

    @Before
    public void setUp() {
        super.setup();
    }

    private String baseUrl = "/v1/archives";

    @Test
    public void should_have_upload_file() throws Exception {
        var mock = new MockMultipartFile("file", "file.pdf", MediaType.APPLICATION_PDF_VALUE, "test file".getBytes());

        this.mockMvc.perform(MockMvcRequestBuilders.multipart(baseUrl + "/upload")
                .file(mock)
                .contentType(MediaType.APPLICATION_PDF_VALUE)

        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("fileName", Matchers.is("file.pdf")))
                .andExpect(MockMvcResultMatchers.jsonPath("fileDownloadUri", Matchers.containsString(".pdf")));
    }
}