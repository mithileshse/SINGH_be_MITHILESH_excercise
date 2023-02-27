package com.ecore.roles.web.rest;

import com.ecore.roles.service.TeamsService;
import com.ecore.roles.web.TeamsApi;
import com.ecore.roles.web.dto.TeamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ecore.roles.web.dto.TeamDto.fromModel;

/**
 * 
 * The TeamsRestController class is responsible for handling HTTP requests related to teams.
 */
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/teams")
public class TeamsRestController implements TeamsApi {

    private final TeamsService teamsService;

    /**
     * 
     * Retrieves a list of all teams.
     * 
     * @return ResponseEntity containing a list of TeamDto objects.
     */
    @Override
    @GetMapping(
            produces = {"application/json"})
    public ResponseEntity<List<TeamDto>> getTeams() {
        log.info("Retrieving all teams.");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(teamsService.getTeams().stream()
                        .map(TeamDto::fromModel)
                        .collect(Collectors.toList()));
    }

    /**
     * 
     * Retrieves a specific team by its ID.
     * 
     * @param teamId the ID of the team to retrieve.
     * @return ResponseEntity containing the TeamDto object representing the specified team.
     */
    @Override
    @GetMapping(
            path = "/{teamId}",
            produces = {"application/json"})
    public ResponseEntity<TeamDto> getTeam(
            @PathVariable UUID teamId) {
        log.info("Retrieving team with ID {}.", teamId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fromModel(teamsService.getTeam(teamId)));
    }
}
