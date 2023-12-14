package com.cupitmadland.art_collector_registration.service;

import com.cupitmadland.art_collector_registration.entity.ArtCollector;

import java.util.List;

public interface ArtCollectorService {

    void saveArtCollector(ArtCollector artCollector);
    ArtCollector findArtCollectorByEmail(String email);
    List<ArtCollector> findAllArtCollector();

}
