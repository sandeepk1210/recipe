package com.sandeep.recipe.mvc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	private Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	@Qualifier("recipeController")
	private RecipeController recipeController;

	/*
	 * Display recipe as home page
	 */
	@RequestMapping(value = { "/", "index" }, method = RequestMethod.GET)
	public String showHomePage(Model model) {
		logger.info("Recipe - Called home page. Handled [/, index] URLs.");
		return "redirect:/recipe/home";
	}
}