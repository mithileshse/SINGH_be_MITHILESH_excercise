package com.ecore.roles.repository;

import com.ecore.roles.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * 
 * The RoleRepository interface provides CRUD operations for Role entities.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    /**
     * 
     * Retrieves a role with the given name from the database.
     * 
     * @param name the name of the role.
     * @return an Optional containing the Role object or an empty Optional if no matching record found.
     */
    Optional<Role> findByName(String name);
}
