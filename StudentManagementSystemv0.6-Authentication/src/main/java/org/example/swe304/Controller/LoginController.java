package org.example.swe304.Controller;

import org.example.swe304.Model.Login;
import org.example.swe304.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "Login/Index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        Login login = loginService.authenticate(username, password);
        if (login != null) {
            model.addAttribute("studentId", login.getId());
            return "redirect:/schedule/" + login.getId();
        } else {
            model.addAttribute("error", "Geçersiz kullanıcı adı veya şifre");
            return "Login/Index";
        }
    }
}