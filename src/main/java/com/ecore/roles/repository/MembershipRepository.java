package com.ecore.roles.repository;

import com.ecore.roles.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 
 * The MembershipRepository interface provides CRUD operations for Membership entities.
 */
@Repository
public interface MembershipRepository extends JpaRepository<Membership, UUID> {

    /**
     * 
     * Retrieves a membership with the given user ID and team ID from the database.
     * 
     * @param userId the ID of the user
     * @param teamId the ID of the team
     * @return an Optional containing the Membership object or an empty Optional if no matching record
     *         found.
     */
    Optional<Membership> findByUserIdAndTeamId(UUID userId, UUID teamId);

    /**
     * 
     * Retrieves a list of all memberships with the given role ID from the database.
     * 
     * @param roleId the ID of the role
     * @return a list of Membership objects.
     */
    List<Membership> findByRoleId(UUID roleId);
}
