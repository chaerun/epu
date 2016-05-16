package com.kemenkes.epu.ui.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kemenkes.epu.common.util.SpringSecurityUtil;

@Controller
public class LoginController {

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		if (SpringSecurityUtil.isAuthenticated()) {
			return "redirect:/home";
		}
		return "login";
	}
	
	
	
	

}
