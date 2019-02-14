package com.zonework.programming.api.structure.repository.post;

import com.zonework.programming.api.domain.archive.entities.Archive;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivesRepository extends PagingAndSortingRepository<Archive, Long> {
}
