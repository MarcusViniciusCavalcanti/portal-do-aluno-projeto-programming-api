package com.zonework.programming.api.unitsTest;

import com.zonework.programming.api.ApiApplication;
import com.zonework.programming.api.domain.archive.services.ArchivesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class ArchivesServiceTest {

    @Autowired
    private ArchivesService archivesService;

    @Test
    public void test() {

    }
}