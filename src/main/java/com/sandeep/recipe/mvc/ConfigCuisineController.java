package com.sandeep.recipe.mvc;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sandeep.recipe.service.ConfigServices;
import com.sandeep.recipe.tables.Cuisine;

@Controller
@RequestMapping("/config/cuisine")
@SessionAttributes(value = { "menuList", "cuisineList" })
public class ConfigCuisineController {
	private Logger logger = Logger.getLogger(ConfigCuisineController.class);

	@Autowired
	private ConfigServices configServices;

	/* Get the configuration menu list from the spring configuration file */
	@Resource
	private String[] configurationMenuList;

	/*
	 * menuList is an session variable. Populate this model attribute when class
	 * is loaded for first time
	 */
	@ModelAttribute("menuList")
	public String[] populateMenuList() {
		logger.info("Populated configuration menu model");
		return configurationMenuList;
	}

	/*
	 * Retrieve list of cuisines from database.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getCuisine(@ModelAttribute("menuList") String[] menuList,
			Model model) {
		logger.info("Config-Getting cuisine data");
		List<Cuisine> cuisines = configServices.getCuisine();

		model.addAttribute("cuisineList", cuisines);
		model.addAttribute("cuisine", new Cuisine());

		return "config/cuisine";
	}

	/*
	 * Add new recipe in the database
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String addCuisine(@Valid Cuisine cuisine, BindingResult result) {
		logger.info("Config Cuisine-Check for validation. Save object in database. Redirect to /config/cuisine");

		if (result.hasErrors()) {
			logger.error("Config Cuisine-Error while validating data. Not saving object in database.");
			return "config/cuisine";

		}
		configServices.addCuisine(cuisine);

		return createRedirectViewPath("/config/cuisine");
	}

	/*
	 * Delete recipe from the database.
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteCuisine(@RequestParam int id) {
		logger.info("Config Cuisine-Deleting id " + id
				+ " and redirecting to /config/cuisine");
		configServices.deleteCuisine(id);

		return createRedirectViewPath("/config/cuisine");
	}
	
	private String createRedirectViewPath(String requestMapping) {
        StringBuilder redirectViewPath = new StringBuilder();
        redirectViewPath.append("redirect:");
        redirectViewPath.append(requestMapping);
        return redirectViewPath.toString();
    }
}
