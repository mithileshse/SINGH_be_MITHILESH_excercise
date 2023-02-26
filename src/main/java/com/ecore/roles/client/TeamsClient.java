package com.ecore.roles.client;

import com.ecore.roles.client.model.Team;
import com.ecore.roles.configuration.ClientsConfigurationProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

/**
 * TeamsClient is a component responsible for making HTTP requests to the Teams API.
 */
@Log4j2
@RequiredArgsConstructor
@Component
public class TeamsClient {

    private final RestTemplate restTemplate;
    private final ClientsConfigurationProperties clientsConfigurationProperties;

    /**
     * Retrieves a team with the given ID from the Teams API.
     * 
     * @param id the ID of the team to retrieve.
     * @return a ResponseEntity containing the team object.
     */
    public ResponseEntity<Team> getTeam(UUID id) {
        log.info("Retrieving team with ID {}", id);
        return restTemplate.exchange(
                clientsConfigurationProperties.getTeamsApiHost() + "/" + id,
                HttpMethod.GET,
                null,
                Team.class);
    }

    /**
     * Retrieves a list of all teams from the Teams API.
     * 
     * @return a ResponseEntity containing a list of team objects.
     */
    public ResponseEntity<List<Team>> getTeams() {
        log.info("Retrieving all teams");
        return restTemplate.exchange(
                clientsConfigurationProperties.getTeamsApiHost(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
    }
}
