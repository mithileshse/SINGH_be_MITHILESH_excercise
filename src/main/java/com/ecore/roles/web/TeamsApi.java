package com.ecore.roles.web;

import com.ecore.roles.web.dto.TeamDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

/**
 * Interface for managing team resources.
 */
public interface TeamsApi {

    /**
     * Returns a list of all teams.
     *
     * @return ResponseEntity containing a list of TeamDto objects and an HTTP status code
     */
    ResponseEntity<List<TeamDto>> getTeams();

    /**
     * Returns a single team by ID.
     *
     * @param teamId ID of the team to retrieve
     * @return ResponseEntity containing a TeamDto object and an HTTP status code
     */
    ResponseEntity<TeamDto> getTeam(UUID teamId);

}
