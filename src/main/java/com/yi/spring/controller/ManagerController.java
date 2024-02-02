package com.yi.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @GetMapping("/setting")
    public String managerSettingPage() {
        return "/view/manager/manager_setting";
    }

}
