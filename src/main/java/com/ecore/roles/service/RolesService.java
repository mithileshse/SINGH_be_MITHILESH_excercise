package com.ecore.roles.service;

import com.ecore.roles.model.Role;

import java.util.List;
import java.util.UUID;

/**
 * 
 * This interface defines the methods that can be used to manage roles in the system.
 */
public interface RolesService {

    /**
     * 
     * Creates a new role with the given details.
     * 
     * @param role the role to be created
     * @return the newly created role
     */
    Role createRole(Role role);

    /**
     * 
     * Retrieves a role by its ID.
     * 
     * @param id the ID of the role to be retrieved
     * @return the role with the given ID
     */
    Role getRole(UUID id);

    /**
     * 
     * Retrieves a list of all roles in the system.
     * 
     * @return a list of all roles
     */
    List<Role> getRoles();

    /**
     * 
     * Retrieves the role assigned to the given user in the given team.
     * 
     * @param userId the ID of the user whose role is to be retrieved
     * @param teamId the ID of the team the user belongs to
     * @return the role assigned to the user in the team
     */
    Role getRoleByUserIdAndTeamId(UUID userId, UUID teamId);
}
