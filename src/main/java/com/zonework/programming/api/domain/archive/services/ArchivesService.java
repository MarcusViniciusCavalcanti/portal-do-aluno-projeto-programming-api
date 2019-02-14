package com.zonework.programming.api.domain.archive.services;

import com.zonework.programming.api.structure.repository.post.ArchivesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchivesService {

    private final ArchivesRepository archivesRepository;

    @Autowired
    public ArchivesService(ArchivesRepository archivesRepository) {
        this.archivesRepository = archivesRepository;
    }
}
