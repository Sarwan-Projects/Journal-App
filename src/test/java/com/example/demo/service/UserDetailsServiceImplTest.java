package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

//@SpringBootTest
public class UserDetailsServiceImplTest
{
    //@Autowired
    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    //@MockBean
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadByUsernameTest()
    {
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Karan").password("ircick").roles(new ArrayList<>()).build());
        UserDetails user = userDetailService.loadUserByUsername("Karan");
        Assertions.assertNotNull(user);
    }
}
