package org.example.swe304.Services;

import org.example.swe304.Model.Login;

public interface LoginService {
    Login authenticate(String username, String password);
}