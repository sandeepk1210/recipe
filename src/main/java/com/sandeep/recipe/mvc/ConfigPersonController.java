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
import com.sandeep.recipe.tables.Person;

@Controller
@RequestMapping("/config/person")
@SessionAttributes(value = { "menuList", "personList" })
public class ConfigPersonController {
	private Logger logger = Logger.getLogger(ConfigPersonController.class);

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
	 * Retrieve list of person from database.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getPerson(@ModelAttribute("menuList") String[] menuList,
			Model model) {
		logger.info("Config-Getting person data");
		List<Person> persons = configServices.getPerson();

		model.addAttribute("personList", persons);
		model.addAttribute("person", new Person());

		return "config/person";
	}

	/*
	 * Add new recipe in the database
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String getPerson(@Valid Person person, BindingResult result) {
		logger.info("Config Person-Check for validation. Save object in database. Redirect to /config/person");

		if (result.hasErrors()) {
			logger.error("Person-Error while validating data. Not saving object in database.");
			return "config/person";

		}
		configServices.addPerson(person);

		return "redirect:/config/person";
	}

	/*
	 * Delete recipe from the database.
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public String deletePerson(@RequestParam int id) {
		logger.info("Config Person-Deleting particular id and redirecting to /config/person");
		configServices.deletePerson(id);

		return "redirect:/config/person";
	}
}
