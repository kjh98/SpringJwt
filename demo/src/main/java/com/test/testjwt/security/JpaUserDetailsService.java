package com.test.testjwt.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.testjwt.entity.Member;
import com.test.testjwt.entity.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
	  private final MemberRepository memberRepository;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	        Member member = memberRepository.findByAccount(username).orElseThrow(
	                () -> new UsernameNotFoundException("Invalid authentication!")
	        );

	        return new CustomUserDetails(member);
	    }

}
