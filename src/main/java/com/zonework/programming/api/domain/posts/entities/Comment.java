package com.zonework.programming.api.domain.posts.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "comments")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String body;

    @ManyToMany(mappedBy = "comments", cascade = { CascadeType.DETACH, CascadeType.PERSIST })
    private Set<Lesson> lessons;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;
}
