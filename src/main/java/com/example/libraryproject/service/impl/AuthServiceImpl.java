package com.example.libraryproject.service.impl;

import com.example.libraryproject.model.User;
import com.example.libraryproject.model.exceptions.InvalidArgumentsException;
import com.example.libraryproject.model.exceptions.InvalidUserCredentialsException;
import com.example.libraryproject.repository.UserRepository;
import com.example.libraryproject.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
