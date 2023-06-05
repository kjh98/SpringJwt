package com.test.testjwt.security;

import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Jwt가 유효성을 검증하는 Filter
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final JwtProvider jwtProvider;
	
	public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtProvider.resolveToken(request);

        if (token != null && jwtProvider.validateToken(token)) {
            // check access token
            token = token.split(" ")[1].trim();
            Authentication auth = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        try {
			filterChain.doFilter(request, response);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
    }
}
