package com.zonework.programming.api.structure.dtos;

import lombok.Data;

import java.util.List;

@Data
public class LessonDTO {
    private String title;

    private String description;

    private String body;

    private List<Integer> tagsId;

    private List<String> archiveUrls;
}
