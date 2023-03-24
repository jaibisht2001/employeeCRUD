package com.example.employeesecurity.jwt;

import java.io.IOException;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.employeesecurity.security.EmployeeLogInDetailService;
import com.example.employeesecurity.security.EmployeeLogInDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtills jwtUtills;

	@Autowired
	private EmployeeLogInDetailService loginUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String auth = request.getHeader("Authorization");
		String token = null;
		String email = null;  //username
		if (auth != null && auth.startsWith("Bearer")) {
			token = auth.substring(6); //extract token
			email = jwtUtills.getUsernameFromToken(token);
		}

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			//load current user
			EmployeeLogInDetails currentUser = (EmployeeLogInDetails) loginUserDetailsService.loadUserByUsername(email);
             //sending current user to the token(Spring security internal token)
			UsernamePasswordAuthenticationToken userAuthToken = new UsernamePasswordAuthenticationToken(currentUser,
					null, currentUser.getAuthorities());
			userAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(userAuthToken);
		}

		filterChain.doFilter(request, response);

	}
}
