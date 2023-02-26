package com.ecore.roles.service;

import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Membership;

import java.util.List;
import java.util.UUID;

/**

 This interface represents the service layer for membership related operations.
 */
public interface MembershipsService {

    /**

     Assigns a role to a membership.
     @param membership The membership object containing the role and user information.
     @return The updated membership object.
     @throws ResourceNotFoundException If the membership or role was not found.
     */
    Membership assignRoleToMembership(Membership membership) throws ResourceNotFoundException;
    /**

     Gets all memberships for the given role ID.
     @param roleId The ID of the role to retrieve memberships for.
     @return A list of membership objects associated with the given role ID.
     */
    List<Membership> getMemberships(UUID roleId);
}