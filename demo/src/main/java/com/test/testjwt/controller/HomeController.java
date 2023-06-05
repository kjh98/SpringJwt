package com.test.testjwt.controller;

import java.net.http.HttpHeaders;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.test.testjwt.dto.SignRequest;
import com.test.testjwt.dto.SignResponse;
import com.test.testjwt.entity.MemberRepository;
import com.test.testjwt.service.SignService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
		private final MemberRepository memberRepository;
	    private final SignService memberService;
	    
		@RequestMapping(value = "/login") public String goLogin(HttpServletRequest request) { 
			return "login"; 
		}
	 
		@RequestMapping(value = "/home")
		public String goHome(HttpServletRequest request, HttpServletResponse response) {
			return "home";
		}
		
		@RequestMapping(value = "/goerror")
		public String goError(HttpServletRequest request) {
			return "error";
		}
		
		@PostMapping(value = "/login")
	    public void signin(@RequestBody SignRequest request, HttpServletResponse res) throws Exception {
	    	SignResponse signResponse = memberService.login(request);
	    	Cookie jwtCookie = new Cookie("jwt", signResponse.getToken());
	        jwtCookie.setPath("/");
	        res.addCookie(jwtCookie);
	        RestTemplate restTemplate = new RestTemplate();

	        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
	       
	        headers.set("Authorization", "Bearer "+signResponse.getToken());

	        HttpEntity<String> entity = new HttpEntity<>(headers);

	        String url = "/home";
	        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	        String responseBody = response.getBody();
	    }
		
}
