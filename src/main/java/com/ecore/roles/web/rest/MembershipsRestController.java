package com.ecore.roles.web.rest;

import com.ecore.roles.model.Membership;
import com.ecore.roles.service.MembershipsService;
import com.ecore.roles.web.MembershipsApi;
import com.ecore.roles.web.dto.MembershipDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ecore.roles.web.dto.MembershipDto.fromModel;

/**
 * 
 * MembershipsRestController is a REST controller that handles requests related to memberships.
 */
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/roles/memberships")
public class MembershipsRestController implements MembershipsApi {

    private final MembershipsService membershipsService;

    /**
     * 
     * Assigns a role to a membership.
     * 
     * @param membershipDto a MembershipDto object containing the membership details.
     * 
     * @return a ResponseEntity containing the created MembershipDto object.
     */
    @Override
    @PostMapping(
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<MembershipDto> assignRoleToMembership(
            @NotNull @Valid @RequestBody MembershipDto membershipDto) {

        Membership membership = membershipsService.assignRoleToMembership(membershipDto.toModel());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(fromModel(membership));
    }

    /**
     * 
     * Retrieves a list of memberships based on the given role ID.
     * 
     * @param roleId the ID of the role to retrieve memberships for.
     * 
     * @return a ResponseEntity containing a list of MembershipDto objects.
     */
    @Override
    @GetMapping(
            path = "/search",
            produces = {"application/json"})
    public ResponseEntity<List<MembershipDto>> getMemberships(
            @RequestParam UUID roleId) {

        List<Membership> memberships = membershipsService.getMemberships(roleId);

        List<MembershipDto> newMembershipDto = new ArrayList<>();

        for (Membership membership : memberships) {
            MembershipDto membershipDto = fromModel(membership);
            newMembershipDto.add(membershipDto);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(newMembershipDto);
    }

}
