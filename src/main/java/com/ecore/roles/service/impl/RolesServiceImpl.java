package com.ecore.roles.service.impl;

import com.ecore.roles.client.model.Team;
import com.ecore.roles.exception.ResourceExistsException;
import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Membership;
import com.ecore.roles.model.Role;
import com.ecore.roles.repository.MembershipRepository;
import com.ecore.roles.repository.RoleRepository;
import com.ecore.roles.service.MembershipsService;
import com.ecore.roles.service.RolesService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link RolesService} interface.
 */
@Log4j2
@Service
public class RolesServiceImpl implements RolesService {

    public static final String DEFAULT_ROLE = "Developer";

    private final RoleRepository roleRepository;
    private final MembershipRepository membershipRepository;

    @Autowired
    public RolesServiceImpl(
            RoleRepository roleRepository,
            MembershipRepository membershipRepository,
            MembershipsService membershipsService) {
        this.roleRepository = roleRepository;
        this.membershipRepository = membershipRepository;
    }

    /**
     * 
     * Creates a new role.
     * 
     * @param r The role to create.
     * @return The created role.
     * @throws ResourceExistsException if a role with the same name already exists.
     */
    @Override
    public Role createRole(@NonNull Role r) {
        // Check if a role with the same name already exists
        if (roleRepository.findByName(r.getName()).isPresent()) {
            log.warn("Role {} already exists", r.getName());
            throw new ResourceExistsException(Role.class);
        }
        // Save the new role and log the success
        Role savedRole = roleRepository.save(r);
        log.info("Role {} created successfully", savedRole.getName());
        return savedRole;
    }

    /**
     * 
     * Retrieves a {@link Role} entity from the repository by the given ID.
     * 
     * @param rid the ID of the role to retrieve
     * @return the retrieved role entity
     * @throws ResourceNotFoundException if a role with the given ID does not exist
     */
    @Override
    public Role getRole(@NonNull UUID rid) {
        // Get the role with the given ID
        Role role = roleRepository.findById(rid)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class, rid));
        // Log the success and return the role
        log.info("Retrieved role {}", role.getName());
        return role;
    }

    /**
     * 
     * Retrieves a list of all roles.
     * 
     * @return A list of all roles.
     */
    @Override
    public List<Role> getRoles() {
        // Get all roles from the repository
        List<Role> roles = roleRepository.findAll();
        // Log the number of retrieved roles and return the list
        log.info("Retrieved {} roles", roles.size());
        return roles;
    }

    /**
     * 
     * Retrieves the role for the user with the specified ID on the specified team.
     * 
     * @param userId The ID of the user.
     * @param teamId The ID of the team.
     * @return The role of the user on the team.
     * @throws ResourceNotFoundException If the specified team does not exist or the user is not a
     *         member of the team.
     */
    @Override
    public Role getRoleByUserIdAndTeamId(UUID userId, UUID teamId) {
        // Get the membership for the given user and team
        Membership membership = membershipRepository.findByUserIdAndTeamId(userId, teamId)
                .orElseThrow(() -> new ResourceNotFoundException(Team.class, teamId));
        // Get the role from the membership and log the success
        Role role = membership.getRole();
        log.info("Retrieved role {} for user {} and team {}", role.getName(), userId, teamId);
        return role;
    }
}
