package com.ecore.roles.web.rest;

import com.ecore.roles.model.Role;
import com.ecore.roles.service.RolesService;
import com.ecore.roles.web.RolesApi;
import com.ecore.roles.web.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ecore.roles.web.dto.RoleDto.fromModel;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/roles")
public class RolesRestController implements RolesApi {

    private final RolesService rolesService;

    /**
     * Creates a new role.
     *
     * @param role the role to create
     * @return the created role
     */
    @Override
    @PostMapping(
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<RoleDto> createRole(
            @Valid @RequestBody RoleDto role) {
        log.debug("Creating role: {}", role);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(fromModel(rolesService.createRole(role.toModel())));
    }

    /**
     * Retrieves all roles.
     *
     * @return the list of all roles
     */
    @Override
    @GetMapping(
            produces = {"application/json"})
    public ResponseEntity<List<RoleDto>> getRoles() {
        log.debug("Retrieving all roles");
        List<Role> getRoles = rolesService.getRoles();
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (Role role : getRoles) {
            RoleDto roleDto = fromModel(role);
            roleDtoList.add(roleDto);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roleDtoList);
    }

    /**
     * Retrieves a role by ID.
     *
     * @param roleId the ID of the role to retrieve
     * @return the retrieved role
     */
    @Override
    @GetMapping(
            path = "/{roleId}",
            produces = {"application/json"})
    public ResponseEntity<RoleDto> getRole(
            @PathVariable UUID roleId) {
        log.debug("Retrieving role by ID: {}", roleId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fromModel(rolesService.getRole(roleId)));
    }

    /**
     * Searches for a role by user ID and team ID.
     *
     * @param userId the ID of the user
     * @param teamId the ID of the team
     * @return the retrieved role
     */
    @Override
    @GetMapping(
            path = "/search",
            produces = {"application/json"})
    public ResponseEntity<RoleDto> searchRoleByUserIdAndTeamId(
            @RequestParam("teamMemberId") UUID userId,
            @RequestParam UUID teamId) {
        log.debug("Searching for role by user ID {} and team ID {}", userId, teamId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fromModel(rolesService.getRoleByUserIdAndTeamId(userId, teamId)));
    }
}
