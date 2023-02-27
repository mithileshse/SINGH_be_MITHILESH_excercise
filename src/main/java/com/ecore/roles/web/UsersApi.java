package com.ecore.roles.web;

import com.ecore.roles.web.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

/**
 * The UsersApi interface defines the endpoints for managing users in the system.
 */
public interface UsersApi {

    /**
     * Returns a list of all users in the system.
     *
     * @return a ResponseEntity containing a list of UserDto objects and an HTTP status code.
     */
    ResponseEntity<List<UserDto>> getUsers();

    /**
     * Returns a single user with the specified ID.
     *
     * @param userId the ID of the user to retrieve.
     * @return a ResponseEntity containing a UserDto object and an HTTP status code.
     */
    ResponseEntity<UserDto> getUser(UUID userId);
}
