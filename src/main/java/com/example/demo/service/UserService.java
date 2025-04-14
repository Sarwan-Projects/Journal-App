package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    //private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean saveNewUser(User my)
    {
        try
        {
            my.setPassword(passwordEncoder.encode(my.getPassword()));
            my.setRoles(Arrays.asList("USER"));
            userRepository.save(my);
            return true;
        } catch (Exception e) {
            log.error("Error Occurred for {} ",my.getUsername(),e);
            /*log.warn("ghghghghghghg");
            log.info("kjkjkjkjk");
            log.debug("lklklklklk");
            log.trace("fdfdfdfdf");*/
            return false;
        }
    }
    public void saveUser(User my)
    {
        try
        {
            userRepository.save(my);
        } catch (Exception e) {
            log.error("Exception : ",e);
        }
    }

    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id)
    {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id)
    {
        userRepository.deleteById(id);
    }

    public User findByUserName(String username)
    {
        return userRepository.findByUsername(username);
    }

    public void saveAdmin(User my)
    {
        try
        {
            my.setPassword(passwordEncoder.encode(my.getPassword()));
            my.setRoles(Arrays.asList("USER","ADMIN"));
            userRepository.save(my);
        } catch (Exception e) {
            log.error("Exception : ",e);
        }
    }
}
