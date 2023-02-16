package com.example.libraryproject.service.impl;

import com.example.libraryproject.model.User;
import com.example.libraryproject.model.enumerations.Role;
import com.example.libraryproject.model.exceptions.InvalidUsernameOrPasswordException;
import com.example.libraryproject.model.exceptions.PasswordsDoNotMatchException;
import com.example.libraryproject.model.exceptions.UsernameAlreadyExistsException;
import com.example.libraryproject.repository.UserRepository;
import com.example.libraryproject.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,role);
        User user1=userRepository.save(user);
        this.emailSenderService.sendEmail(user1.getUsername(),"Registration successfully","Dear user, Your library account has been created. Welcome to the community! Click here to login http://localhost:8080/login .");
        return userRepository.save(user);
    }
}

