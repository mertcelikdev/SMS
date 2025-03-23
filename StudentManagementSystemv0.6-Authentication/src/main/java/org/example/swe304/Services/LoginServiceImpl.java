package org.example.swe304.Services;

import org.example.swe304.Model.Login;
import org.example.swe304.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Login authenticate(String username, String password) {
        return loginRepository.findByUsernameAndPassword(username, password);
    }
}