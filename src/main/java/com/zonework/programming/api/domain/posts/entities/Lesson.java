package com.zonework.programming.api.domain.posts.entities;

import com.zonework.programming.api.domain.archive.entities.Archive;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false)
    private String body;

    @ManyToMany
    @JoinTable(
            name = "lessons_tags",
            joinColumns = { @JoinColumn(name = "lesson_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "tags_id", referencedColumnName = "id") }
    )
    private Set<Tags> tagsList;

    @ManyToMany
    @JoinTable(
            name = "lessons_tags",
            joinColumns = { @JoinColumn(name = "lessons_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "archives_id", referencedColumnName = "id") }
    )
    private Set<Archive> archives;

    @ManyToMany
    @JoinTable(
            name = "lessons_comments",
            joinColumns = { @JoinColumn(name = "lessons_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "comments_id", referencedColumnName = "id") }
    )
    private Set<Comment> comments;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

}
