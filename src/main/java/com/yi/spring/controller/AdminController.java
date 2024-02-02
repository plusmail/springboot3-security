package com.yi.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/setting")
    public String adminSettingPage() {
        return "/view/admin/admin_setting";
    }

}
