package com.ecore.roles.service.impl;

import com.ecore.roles.client.UsersClient;
import com.ecore.roles.client.model.User;
import com.ecore.roles.service.UsersService;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 
 * Implementation of the {@link UsersService} interface that uses a {@link UsersClient} to interact
 * with an external
 * 
 * users service.
 */
@Log4j2
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersClient usersClient;

    @Autowired
    public UsersServiceImpl(UsersClient usersClient) {
        this.usersClient = usersClient;
    }

    /**
     * 
     * Retrieves the user with the given ID from the external users service.
     * 
     * @param id the ID of the user to retrieve
     * @return the user with the given ID
     */
    public User getUser(UUID id) {
        log.info("Retrieving user with ID {}", id);
        return usersClient.getUser(id).getBody();
    }

    /**
     * 
     * Retrieves a list of all users from the external users service.
     * 
     * @return a list of all users
     */
    public List<User> getUsers() {
        log.info("Retrieving all users");
        return usersClient.getUsers().getBody();
    }
}
