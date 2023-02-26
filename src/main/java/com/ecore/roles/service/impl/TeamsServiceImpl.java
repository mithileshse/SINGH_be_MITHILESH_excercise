package com.ecore.roles.service.impl;

import com.ecore.roles.client.TeamsClient;
import com.ecore.roles.client.model.Team;
import com.ecore.roles.service.TeamsService;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class TeamsServiceImpl implements TeamsService {

    private final TeamsClient teamsClient;

    @Autowired
    public TeamsServiceImpl(TeamsClient teamsClient) {
        this.teamsClient = teamsClient;
    }

    /**
     * Retrieves a single team with the given ID from the external TeamsClient.
     *
     * @param id The ID of the team to retrieve.
     * @return The Team object with the given ID.
     */
    public Team getTeam(UUID id) {
        log.info("Retrieving team with ID {}", id);
        return teamsClient.getTeam(id).getBody();
    }

    public List<Team> getTeams() {
        log.info("Retrieving all teams");
        List<Team> teams = teamsClient.getTeams().getBody();
        log.info("Retrieved {} teams", teams.size());
        return teams;
    }
}
