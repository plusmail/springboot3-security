package com.yi.spring.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "/view/user/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("loginId", user.getUsername());
        model.addAttribute("loginRoles", user.getAuthorities());

        return "/view/user/dashboard";
    }

    @GetMapping("/join")
    public String joinPage() {

        return "/view/user/join";
    }

    @GetMapping("/setting")
    public String userSettingPage() {

        return "/view/user/user_setting";
    }


}
