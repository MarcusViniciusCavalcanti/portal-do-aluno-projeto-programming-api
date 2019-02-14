package com.zonework.programming.api.structure.repository.post;

import com.zonework.programming.api.domain.posts.entities.Tags;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends PagingAndSortingRepository<Tags, Integer> {
}
