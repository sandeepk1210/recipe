package com.sandeep.recipe.mvc;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sandeep.recipe.service.ConfigServices;

@Controller
@RequestMapping("/config")
public class ConfigController {
	private Logger logger = Logger.getLogger(ConfigController.class);

	@Autowired
	private ConfigServices configServices;

	@Resource
	private String[] configurationMenuList;

	@RequestMapping("/home")
	public String showConfigHomePage(Model model) {
		logger.info("Config-Inside home page. Redirecting to " + configurationMenuList[0] + ".");

		return "redirect:/config/" + configurationMenuList[0].toLowerCase();
	}
}
