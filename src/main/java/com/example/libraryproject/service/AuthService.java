package com.example.libraryproject.service;

import com.example.libraryproject.model.User;

public interface AuthService {
    User login(String username, String password);
}

