package com.cupitmadland.art_collector_registration.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
public class ArtCollector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String country;

    @Column(nullable = false)
    String username;

    String email;

    @Column(nullable=false, unique = true)
    String password;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name ="artcollector_roles", joinColumns = {@JoinColumn(name = "artcollector_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
   public List<Role> roles = new ArrayList<>();
}
