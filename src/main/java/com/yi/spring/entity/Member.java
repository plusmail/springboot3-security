package com.yi.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userid", unique = true)
    private String userid;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;

    @Column(name = "email")
    private String email;

    private Member(Long id, String userid, String password, String roleUser){
        this.id = id;
        this.userid = userid;
        this.password = password;
        this.roles = roleUser;
    }

    public static Member createUser(String userid, String password, PasswordEncoder passwordEncoder){
        return new Member(null, userid, passwordEncoder.encode(password), "USER");
    }

}