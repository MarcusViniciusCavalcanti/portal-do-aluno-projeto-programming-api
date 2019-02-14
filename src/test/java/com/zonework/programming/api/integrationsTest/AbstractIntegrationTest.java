package com.zonework.programming.api.integrationsTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zonework.programming.api.ApiApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
@WebAppConfiguration
public abstract class AbstractIntegrationTest {

    protected MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    protected void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    protected <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        var mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    protected <T> List<T> mapListFromJson(String json, Class<T> clazz) throws IOException {
        var mapper = new ObjectMapper();
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
