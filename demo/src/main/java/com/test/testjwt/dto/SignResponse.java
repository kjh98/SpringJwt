package com.test.testjwt.dto;

import com.test.testjwt.entity.Member;
import com.test.testjwt.entity.Authority;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponse {
    private Long id;
    
    private String account;

    private String nickname;

    private String name;

    private String email;
    
    private List<Authority> roles = new ArrayList<>();
    
    private String token;
    
    public SignResponse(Member member) {
        this.id = member.getId();
        this.account = member.getAccount();
        this.nickname = member.getNickname();
        this.name = member.getName();
        this.email = member.getEmail();
        this.roles = member.getRoles();
    }
}
