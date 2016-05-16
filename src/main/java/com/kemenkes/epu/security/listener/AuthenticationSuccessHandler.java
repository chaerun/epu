package com.kemenkes.epu.security.listener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.kemenkes.epu.common.util.Constant;
import com.kemenkes.epu.entity.User;
import com.kemenkes.epu.service.UserService;

@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User user = userService.findByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
		if (user == null) {
			user = new User();
			user.setUsername(((UserDetails) authentication.getPrincipal()).getUsername());

		}
		request.getSession().setAttribute(Constant.SESSION_USER, user);
		response.sendRedirect("home/");
	}

}
