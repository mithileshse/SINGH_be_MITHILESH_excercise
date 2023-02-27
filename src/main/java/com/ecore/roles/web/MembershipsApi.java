package com.ecore.roles.web;

import com.ecore.roles.web.dto.MembershipDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

/**
 * 
 * Defines the endpoints for managing memberships.
 */
public interface MembershipsApi {

    /**
     * 
     * Assigns a role to a membership.
     * 
     * @param membership the membership object containing the role and user/team information.
     * @return a ResponseEntity containing the created MembershipDto object.
     */
    ResponseEntity<MembershipDto> assignRoleToMembership(
            MembershipDto membership);

    /**
     * 
     * Retrieves a list of memberships for a given role ID.
     * 
     * @param roleId the ID of the role to retrieve memberships for.
     * @return a ResponseEntity containing a List of MembershipDto objects.
     */
    ResponseEntity<List<MembershipDto>> getMemberships(
            UUID roleId);
}
