package com.cupitmadland.art_collector_registration.security;

import com.cupitmadland.art_collector_registration.entity.ArtCollector;
import com.cupitmadland.art_collector_registration.repository.ArtCollectorsRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private ArtCollectorsRepository artCollectorsRepository;

    public CustomUserDetailsService(ArtCollectorsRepository artCollectorsRepository){
        this.artCollectorsRepository = artCollectorsRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        ArtCollector artCollector = artCollectorsRepository.findByEmail(email);
        if(artCollector != null){
            return new org.springframework.security.core.userdetails.User(artCollector.getUsername(),
                    artCollector.getPassword(),
                    artCollector.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));

        }else{
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
