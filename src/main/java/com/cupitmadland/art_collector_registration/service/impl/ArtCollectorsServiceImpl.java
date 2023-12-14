package com.cupitmadland.art_collector_registration.service.impl;

import com.cupitmadland.art_collector_registration.entity.ArtCollector;
import com.cupitmadland.art_collector_registration.entity.Role;
import com.cupitmadland.art_collector_registration.repository.ArtCollectorsRepository;
import com.cupitmadland.art_collector_registration.repository.RoleRepository;
import com.cupitmadland.art_collector_registration.service.ArtCollectorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtCollectorsServiceImpl implements ArtCollectorService {
    private ArtCollectorsRepository artCollectorsRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public ArtCollectorsServiceImpl(ArtCollectorsRepository artCollectorsRepository, RoleRepository roleRepository,
                                    PasswordEncoder passwordEncoder) {
        super();
        this.artCollectorsRepository = artCollectorsRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

        @Override
        public void saveArtCollector(ArtCollector artCollector) {
            //ArtCollector artcollector = new ArtCollector();

            artCollector.setUsername(artCollector.getUsername());
            artCollector.setEmail(artCollector.getEmail());
            artCollector.setPassword(passwordEncoder.encode(artCollector.getPassword()));

            Role role = roleRepository.findByName("ROLE_USER");
            if (role == null) {
                role = checkRoleExist(); //method we set up below
            }
            artCollector.setRoles(Arrays.asList(role));
            artCollectorsRepository.save(artCollector);


    }

    @Override
    public ArtCollector findArtCollectorByEmail(String email) {
        return artCollectorsRepository.findByEmail(email);
    }

    @Override
    public List<ArtCollector> findAllArtCollector() {
        List<ArtCollector> artcollectors = artCollectorsRepository.findAll();
        return artcollectors;

    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}


