package com.yi.spring.config;

import com.yi.spring.service.MemberService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.yi.spring.entity.Member;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private final MemberService memberService;

    public MyUserDetailsService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
        Optional<Member> findOne = memberService.findOne(insertedUserId);
        Member member = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다 ㅠ"));

        return User.builder()
                .username(member.getUserid())
                .password(member.getPassword())
                .roles(member.getRoles())
                .build();
    }
}