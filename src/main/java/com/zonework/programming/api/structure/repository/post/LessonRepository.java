package com.zonework.programming.api.structure.repository.post;

import com.zonework.programming.api.domain.posts.entities.Lesson;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends PagingAndSortingRepository<Lesson, Long> {
}
