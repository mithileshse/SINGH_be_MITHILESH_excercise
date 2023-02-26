package com.ecore.roles.service.impl;

import com.ecore.roles.client.model.Team;
import com.ecore.roles.exception.InvalidArgumentException;
import com.ecore.roles.exception.ResourceExistsException;
import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Membership;
import com.ecore.roles.model.Role;
import com.ecore.roles.repository.MembershipRepository;
import com.ecore.roles.repository.RoleRepository;
import com.ecore.roles.service.MembershipsService;
import com.ecore.roles.service.TeamsService;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;

/**
 * Implementation of the MembershipsService interface that provides CRUD operations for Membership objects.
 *
 * This class is responsible for assigning roles to memberships, and retrieving all memberships associated with a given role.
 *
 * The MembershipsServiceImpl class uses the following components:
 * - membershipRepository: a repository that provides access to Membership entities in the database.
 * - roleRepository: a repository that provides access to Role entities in the database.
 * - teamsService: a service that provides operations for Team objects, and is used to validate that a membership belongs to a team.
 *
 * The class uses Lombok annotations to generate constructor and logger code.
 */

@Log4j2 // add log4j2 annotation to enable logging
@Service
public class MembershipsServiceImpl implements MembershipsService {

    public static final String TO_THE_PROVIDED_TEAM =
            "The provided user doesn't belong to the provided team.";

    private final MembershipRepository membershipRepository;
    private final RoleRepository roleRepository;
    private final TeamsService teamsService;

    @Autowired
    public MembershipsServiceImpl(
            MembershipRepository membershipRepository,
            RoleRepository roleRepository,
            TeamsService teamsService) {
        this.membershipRepository = membershipRepository;
        this.roleRepository = roleRepository;
        this.teamsService = teamsService;
    }
    /**

     Assigns a role to a membership by creating a new membership object with the provided role and
     saving it to the membership repository.
     @param m The membership object to be updated with the new role.
     @return The updated membership object with the assigned role.
     @throws ResourceExistsException If a membership with the same user ID and team ID already exists.
     @throws ResourceNotFoundException If the provided role or team do not exist or if the user is not a member of the team.
     @throws InvalidArgumentException If the provided membership does not have a role.
     */
    @Override
    public Membership assignRoleToMembership(@NonNull Membership m) {

        UUID roleId = ofNullable(m.getRole()).map(Role::getId)
                .orElseThrow(() -> new InvalidArgumentException(Role.class));

        // check if membership with the same userId and teamId already exists
        if (membershipRepository.findByUserIdAndTeamId(m.getUserId(), m.getTeamId())
                .isPresent()) {
            // log the error
            log.error("Membership with user id {} and team id {} already exists.", m.getUserId(), m.getTeamId());
            throw new ResourceExistsException(Membership.class);
        }

        // check if role with roleId exists
        roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException(Role.class, roleId));

        // check if team with teamId exists and the provided user is a member of the team
        Team team = ofNullable(teamsService.getTeam(m.getTeamId()))
                .orElseThrow(() -> new ResourceNotFoundException(Team.class, m.getTeamId()));
        if (!team.getTeamMemberIds().contains(m.getUserId())) {
            // log the error
            log.error("User with id {} does not belong to team with id {}.", m.getUserId(), m.getTeamId());
            throw new ResourceExistsException(Membership.class, TO_THE_PROVIDED_TEAM);
        }

        // save the membership and log the success
        Membership membership = membershipRepository.save(m);
        log.info("Membership created for user {} in team {} with role {}.", membership.getUserId(), membership.getTeamId(), membership.getRole().getName());
        return membership;
    }
    /**

     Returns a list of all {@link Membership}s that have the given role ID.
     @param rid the ID of the role to search memberships for
     @return a list of all memberships that have the given role ID
     @throws ResourceNotFoundException if the role with the given ID does not exist
     */
    @Override
    public List<Membership> getMemberships(@NonNull UUID rid) {
        return membershipRepository.findByRoleId(rid);
    }
}
