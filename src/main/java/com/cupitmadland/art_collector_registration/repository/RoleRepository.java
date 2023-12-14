package com.cupitmadland.art_collector_registration.repository;

import com.cupitmadland.art_collector_registration.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
