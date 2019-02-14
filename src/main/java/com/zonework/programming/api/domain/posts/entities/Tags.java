package com.zonework.programming.api.domain.posts.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tags")
@Data
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 10)
    private String name;

    @ManyToMany(mappedBy = "tagsList", cascade = { CascadeType.DETACH, CascadeType.PERSIST })
    private Set<Lesson> lessons;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;
}
