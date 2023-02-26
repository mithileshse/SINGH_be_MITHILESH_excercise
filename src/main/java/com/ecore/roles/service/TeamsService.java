package com.ecore.roles.service;

import com.ecore.roles.client.model.Team;

import java.util.List;
import java.util.UUID;

/**

 A service interface for managing teams.
 */
public interface TeamsService {

    /**

     Retrieve a {@link Team} by its ID.
     @param id the ID of the team to retrieve
     @return the retrieved team
     */
    Team getTeam(UUID id) ;
    /**

     Retrieve a list of all {@link Team}s.
     @return the list of all teams
     */
    List<Team> getTeams();
}
