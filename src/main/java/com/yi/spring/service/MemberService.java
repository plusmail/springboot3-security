package com.yi.spring.service;

import com.yi.spring.entity.Member;
import com.yi.spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findOne(String userId){
        return memberRepository.findByUserid(userId);
    }
}
