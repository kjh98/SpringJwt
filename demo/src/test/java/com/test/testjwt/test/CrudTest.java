package com.test.testjwt.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.test.testjwt.entity.Member;
import com.test.testjwt.entity.MemberRepository;

import lombok.Builder;

@SpringBootTest
public class CrudTest {
	@Autowired
	MemberRepository memberRepository;
	@Test
	void save() throws UsernameNotFoundException {
		 Member member = memberRepository.findByAccount("").orElseThrow(
	                () -> new UsernameNotFoundException("Invalid authentication!")
	        );
	}
}
