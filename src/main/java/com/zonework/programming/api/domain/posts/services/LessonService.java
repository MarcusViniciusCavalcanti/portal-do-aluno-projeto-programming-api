package com.zonework.programming.api.domain.posts.services;

import com.zonework.programming.api.domain.posts.entities.Lesson;
import com.zonework.programming.api.structure.dtos.LessonDTO;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

    public static Lesson createNewLesson() {
        return new Lesson();
    }

    public Lesson create(LessonDTO dto) {
        var lesson = new Lesson();



        return lesson;
    }

}
