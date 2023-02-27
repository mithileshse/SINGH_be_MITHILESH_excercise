package com.ecore.roles.web.rest;

import com.ecore.roles.client.model.User;
import com.ecore.roles.service.UsersService;
import com.ecore.roles.web.UsersApi;
import com.ecore.roles.web.dto.UserDto;
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

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/users")
public class UsersRestController implements UsersApi {

    private final UsersService usersService;

    /**
     * Returns a list of all users.
     *
     * @return A ResponseEntity containing a list of UserDto objects
     */
    @Override
    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<UserDto>> getUsers() {
        log.info("Getting all users");
        List<User> users = usersService.getUsers();
        List<UserDto> userDtos = users.stream().map(UserDto::fromModel).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userDtos);
    }

    /**
     * Returns a user by the given user ID.
     *
     * @param userId The ID of the user to retrieve
     * @return A ResponseEntity containing a UserDto object
     */
    @Override
    @GetMapping(path = "/{userId}", produces = {"application/json"})
    public ResponseEntity<UserDto> getUser(@PathVariable UUID userId) {
        log.info("Getting user with ID {}", userId);
        User user = usersService.getUser(userId);
        UserDto userDto = UserDto.fromModel(user);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
}
