package com.ecore.roles.service;

import com.ecore.roles.client.UsersClient;
import com.ecore.roles.client.model.User;
import com.ecore.roles.service.impl.UsersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.ecore.roles.utils.TestData.GIANNI_USER;
import static com.ecore.roles.utils.TestData.GIANNI_USER_UUID;
import static com.ecore.roles.utils.TestData.JAREN_USER_UUID;
import static com.ecore.roles.utils.TestData.MARION_USER_UUID;
import static com.ecore.roles.utils.TestData.DEFAULT_USERS;
import static com.ecore.roles.utils.TestData.UUID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @InjectMocks
    private UsersServiceImpl usersService;
    @Mock
    private UsersClient usersClient;

    @Test
    void shouldGetUserWhenUserIdExists() {
        User gianniUser = GIANNI_USER();
        when(usersClient.getUser(UUID_1))
                .thenReturn(ResponseEntity
                        .status(HttpStatus.OK)
                        .body(gianniUser));

        assertNotNull(usersService.getUser(UUID_1));
    }
    
    @Test
    void shouldGetAllUsers() {    	
    	when(usersClient.getUsers())
    	.thenReturn(ResponseEntity
    			.status(HttpStatus.OK).body(DEFAULT_USERS()));
    	
    	List<User> users = usersService.getUsers();
    	
    	assertThat(users.size()).isEqualTo(3);
    	assertEquals(GIANNI_USER_UUID, users.get(0).getId());
    	assertEquals(JAREN_USER_UUID, users.get(1).getId());  
    	assertEquals(MARION_USER_UUID, users.get(2).getId());  
    }
}
