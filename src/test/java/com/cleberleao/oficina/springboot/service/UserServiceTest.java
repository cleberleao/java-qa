package com.cleberleao.oficina.springboot.service;

import com.cleberleao.oficina.springboot.entity.User;
import com.cleberleao.oficina.springboot.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriar() {
        User user = new User();
        user.setPassword("password");

        when(repository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.criar(user);

        assertNotNull(createdUser);
        assertEquals(user.getPassword(), createdUser.getPassword());
        verify(repository, times(1)).save(user);
    }

    @Test
    void testAlterar() {
        User user = new User();

        when(repository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.alterar(user);

        assertNotNull(updatedUser);
        verify(repository, times(1)).save(user);
    }

    @Test
    void testApagar() {
        User user = new User();
        user.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(repository).delete(user);

        User deletedUser = userService.apagar(1L);

        assertNotNull(deletedUser);
        assertEquals(1L, deletedUser.getId());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(user);
    }

    @Test
    void testBuscarUsuario() {
        User user = new User();
        user.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.buscarUsuario(1L);

        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testBuscarTodos() {
//        List<User> users = List.of(new User(), new User());
//
//        when(repository.findAll()).thenReturn(users);
//
//        List<User> foundUsers = userService.buscarTodos();
//
//        assertNotNull(foundUsers);
//        assertEquals(2, foundUsers.size());
//        verify(repository, times(1)).findAll();
    }
}
