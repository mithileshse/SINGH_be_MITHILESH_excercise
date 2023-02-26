package com.ecore.roles.service;

import com.ecore.roles.client.model.User;

import java.util.List;
import java.util.UUID;

/**

 This interface defines methods related to managing users.
 */
public interface UsersService {

    /**

     Retrieves a user with the given ID.
     @param id The ID of the user to retrieve.
     @return The user with the given ID.
     */
    User getUser(UUID id);
    /**

     Retrieves a list of all users.
     @return A list of all users.
     */
    List<User> getUsers();
}