package com.cupitmadland.art_collector_registration.repository;

import com.cupitmadland.art_collector_registration.entity.ArtCollector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtCollectorsRepository extends JpaRepository<ArtCollector, Long> {

    ArtCollector findByEmail(String email);
}
