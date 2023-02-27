package com.ecore.roles.web;

import com.ecore.roles.web.dto.RoleDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

/**
 * 
 * API for managing roles.
 */
public interface RolesApi {

    /**
     * 
     * Create a new role.
     * 
     * @param role The role to create.
     * @return The created role.
     */
    ResponseEntity<RoleDto> createRole(RoleDto role);

    /**
     * 
     * Get all roles.
     * 
     * @return The list of roles.
     */
    ResponseEntity<List<RoleDto>> getRoles();

    /**
     * 
     * Get a role by ID.
     * 
     * @param roleId The ID of the role to get.
     * @return The role with the specified ID.
     */
    ResponseEntity<RoleDto> getRole(UUID roleId);

    /**
     * 
     * Search for a role by user ID and team ID.
     * 
     * @param userId The ID of the user.
     * @param teamId The ID of the team.
     * @return The role that matches the specified user and team IDs.
     */
    ResponseEntity<RoleDto> searchRoleByUserIdAndTeamId(UUID userId, UUID teamId);
}
