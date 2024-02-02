package com.yi.spring.service;

import com.yi.spring.entity.Member;
import com.yi.spring.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterMemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public RegisterMemberService(PasswordEncoder passwordEncoder, MemberRepository memberRepository) {
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
    }

    public Long join(String userid, String password){
        Member member = Member.createUser(userid, password, passwordEncoder);
        validateDuplicateMember(member);

        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByUserid(member.getUserid())
                .ifPresent( m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
