package com.zonework.programming.api.unitsTest;

import com.zonework.programming.api.ApiApplication;
import com.zonework.programming.api.domain.posts.services.LessonService;
import com.zonework.programming.api.structure.dtos.LessonDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class LessonServiceTest {

    @Autowired
    private LessonService lessonService;

    @Test
    public void should_have_created_successfully_when_parameter_is_dto() {
        var dto = new LessonDTO();

        dto.setTitle("Primeira aula");
        dto.setDescription("Aula de introdução");
        dto.setBody("Esta inicia o curso de Desenvolvimento Web");

    }

}