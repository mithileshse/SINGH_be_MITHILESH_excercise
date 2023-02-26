package com.ecore.roles.client;

import com.ecore.roles.client.model.User;
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
 * UsersClient is a component responsible for making HTTP requests to the Users API.
 */
@Log4j2
@RequiredArgsConstructor
@Component
public class UsersClient {

    private final RestTemplate restTemplate;
    private final ClientsConfigurationProperties clientsConfigurationProperties;

    /**
     * Retrieves a user with the given ID from the Users API.
     * 
     * @param id the ID of the user to retrieve.
     * @return a ResponseEntity containing the user object.
     */
    public ResponseEntity<User> getUser(UUID id) {
        log.info("Retrieving user with ID {}", id);
        return restTemplate.exchange(
                clientsConfigurationProperties.getUsersApiHost() + "/" + id,
                HttpMethod.GET,
                null,
                User.class);
    }

    /**
     * Retrieves a list of all users from the Users API.
     * 
     * @return a ResponseEntity containing a list of user objects.
     */
    public ResponseEntity<List<User>> getUsers() {
        log.info("Retrieving all users");
        return restTemplate.exchange(
                clientsConfigurationProperties.getUsersApiHost(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
    }
}
