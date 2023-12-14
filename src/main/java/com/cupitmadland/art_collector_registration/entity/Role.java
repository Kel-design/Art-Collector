package com.cupitmadland.art_collector_registration.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table
public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
@Column(nullable=false, unique = true)
    String name;
@ManyToMany(mappedBy = "roles")
    List<ArtCollector> artCollector = new ArrayList<>();

}
