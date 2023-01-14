package com.preciseIT.controllers;

import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.User;
import com.preciseIT.enums.Role;
import com.preciseIT.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.returnsArgAt;


class UserControllerTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    private UserController userController;
    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = mock(UserService.class);
        User appUser1 = new User("b.rijnders@thedoc.nl", "Bart", "Rijnders", "password", Role.ADMIN);
        User appUser2 = new User("Testarjen@gmail.com", "Arjen", "Froma", "password", "YJXWKFYWYO", Role.CLIENT);
        Iterable<User> users = new ArrayList<>(List.of(appUser1, appUser2));
        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.save(any(User.class))).thenReturn(appUser1);
        when(userService.register(anyString(), anyString())).thenAnswer(i -> new User(i.getArgument(0), i.getArgument(1), "secret", Role.CLIENT));
        this.userController = new UserController(userRepository, userService);
    }


    @Test
    void getAllPersons() {
        assertNotNull(userController.getAllPersons());
        List<User> result = new ArrayList<>();
        userController.getAllPersons().forEach(result::add);
        assertEquals(2, result.size());
        for(Object object : result){
            assertEquals(User.class, object.getClass());
        }
    }

    @Test
    void saveNewUser() {
        User appUser1 = new User("b.rijnders@thedoc.nl", "Bart", "Rijnders", "password", Role.ADMIN);
        User result = userController.saveNewUser(appUser1);
        assertEquals(appUser1.getEmail(), result.getEmail());
        assertEquals(appUser1.getFirstName(), result.getFirstName());
        assertEquals(appUser1.getLastName(), result.getLastName());
        assertEquals(appUser1.getPassword(), result.getPassword());
    }

    @Test
    void register() {
        System.out.println(userService.register("b.rijnders@thedoc.nl", "wachtwoord").getEmail());
    }
}