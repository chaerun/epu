package com.kemenkes.epu.ui.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kemenkes.epu.service.ConfigService;

@Controller
@RequestMapping("config")
public class ConfigController extends BaseController {

	@Autowired
	private ConfigService configService;

	@RequestMapping("/")
	public String index(ModelMap map) {
		return "config/list";
	}

	@RequestMapping("/precreate")
	public String precreate() {

		return "redirect:/config/";
	}

}
