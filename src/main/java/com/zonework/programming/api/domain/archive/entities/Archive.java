package com.zonework.programming.api.domain.archive.entities;

import com.zonework.programming.api.domain.posts.entities.Lesson;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "archives")
@Data
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false)
    private String url;

    @ManyToMany(mappedBy = "archives", cascade = { CascadeType.DETACH, CascadeType.PERSIST })
    private Set<Lesson> lessons;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

}
