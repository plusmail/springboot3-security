package com.yi.spring.controller;

import com.yi.spring.entity.Member;
import com.yi.spring.service.RegisterMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegisterMemberService registerMemberService;

    public AuthController(RegisterMemberService registerMemberService) {
        this.registerMemberService = registerMemberService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody Member dto){
        try{
            registerMemberService.join(dto.getUserid(), dto.getPassword());
            return ResponseEntity.ok("join success");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
