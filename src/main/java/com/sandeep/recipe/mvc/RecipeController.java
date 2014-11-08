package com.sandeep.recipe.mvc;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sandeep.recipe.exception.RecipeNotFoundException;
import com.sandeep.recipe.service.ConfigServices;
import com.sandeep.recipe.service.RecipeServices;
import com.sandeep.recipe.tables.Cuisine;
import com.sandeep.recipe.tables.Ingredient;
import com.sandeep.recipe.tables.Person;
import com.sandeep.recipe.tables.Recipe;

@Controller
@RequestMapping("/recipe")
@SessionAttributes(value = { "recipesList", "cuisineList", "personList" })
public class RecipeController {
	@Autowired
	private RecipeServices recipeServices;

	@Autowired
	private ConfigServices configServices;

	@Value("${DEFAULT_RECIPES_PER_PAGE}")
	private Integer DEFAULT_RECIPES_PER_PAGE;

	@ModelAttribute("cuisineList")
	public List<Cuisine> populateCuisine() {
		return configServices.getCuisine();
	}

	@ModelAttribute("personList")
	public List<Person> populatePerson() {
		return configServices.getPerson();
	}

	private Logger logger = Logger.getLogger(RecipeController.class);

	/*
	 * Display recipe as home page
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String showHomePage(Model model) {
		logger.info("Recipe - Get list of recipes from database and display new page or first recipe.");

		List<Recipe> recipes = recipeServices
				.getRecipes(DEFAULT_RECIPES_PER_PAGE);
		model.addAttribute("recipesList", recipes);

		if (recipes.isEmpty()) {
			logger.info("Recipe-No recipes exists. Displaying new recipe creation form");
			return newRecipe(recipes, model);
		} else {
			logger.info("Recipe-Recipes exists. Display 0th element from List");
			model.addAttribute("index", 0);
			return "recipes/recipe";
		}
	}

	/*
	 * Search recipe by ID and display "recipes/recipe"
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showRecipeById(@PathVariable int id,
			@ModelAttribute("recipesList") List<Recipe> recipes,
			Map<String, Object> map) {
		int index = findIndex(recipes, id);
		
		/*
		 * No recipe exists. Throw exception.
		 */
		if (index < 0){
			logger.info("Recipe-No recipe found. Throws exception RecipeNotFoundException");
			throw new RecipeNotFoundException("" + id);
		}
		
		map.put("index", index);
		logger.info("Recipe-Setting recipe id:" + id + " index is:" + index);

		return "recipes/recipe";
	}

	/*
	 * Add new Recipe. Display the form to add the recipe
	 */
	@RequestMapping("/add")
	public String newRecipe(
			@ModelAttribute("recipesList") List<Recipe> recipes, Model model) {
		logger.info("Recipe-Adding new Recipe. Display the new recipe form");

		Recipe recipe = new Recipe();

		/* Get Ingredient list */
		List<Ingredient> ingredients = configServices.getIngredient();
		StringBuilder ing = new StringBuilder();

		int i = 0;
		int count = ingredients.size();

		for (Ingredient ingredient : ingredients) {
			ing.append(ingredient.getType() + " - " + ingredient.getQuantity());
			i++;
			if (i != count) {
				ing.append(System.getProperty("line.separator"));
			}
			/* System.lineSeparator ()); */
		}
		recipe.setIngredients(ing.toString());

		/* Get cuisine list */
		List<Cuisine> cuisines = configServices.getCuisine();
		model.addAttribute("cuisineList", cuisines);

		/* Get person list */
		List<Person> persons = configServices.getPerson();
		model.addAttribute("personList", persons);

		model.addAttribute("recipe", recipe);
		model.addAttribute("cuisine", new Cuisine());
		model.addAttribute("person", new Person());

		return "recipes/new";
	}

	/*
	 * Modify a recipe
	 */
	@RequestMapping(value = "/modify/{id}")
	public String modifyRecipe(@PathVariable int id,
			@ModelAttribute("recipesList") List<Recipe> recipes,
			@ModelAttribute("cuisineList") List<Cuisine> cuisines,
			@ModelAttribute("personList") List<Person> persons, Model model) {
		if (cuisines.isEmpty()) {
			cuisines = configServices.getCuisine();
			model.addAttribute("cuisineList", cuisines);
			logger.info("Recipe-Modify. Cuisine was empty, so have populated");
		}
		if (persons.isEmpty()) {
			persons = configServices.getPerson();
			model.addAttribute("personList", persons);
			logger.info("Recipe-Modify. Person was empty, so have populated");
		}

		int index = findIndex(recipes, id);
		logger.info("Recipe-Modify recipe id [" + id + "] index [" + index
				+ "]");

		Recipe recipe = recipes.get(index);
		model.addAttribute("recipe", recipe);

		return "recipes/modify";
	}

	/*
	 * POST request. Validate the new Recipe added and save it to into the
	 * database.
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addRecipe(@Valid Recipe recipe,
			@ModelAttribute("recipesList") List<Recipe> recipes,
			@ModelAttribute("cuisineList") List<Cuisine> cuisines,
			@ModelAttribute("personList") List<Person> persons,
			BindingResult result, Model model) {

		logger.info("From JSP [" + recipe + "]");

		/*
		 * Validation - BindingResult has error. Cuisine is not selected by user
		 */
		if (result.hasErrors() || recipe.getCuisine().getId() == null) {
			if (recipe.getCuisine().getId() == null) {
				result.rejectValue("cuisine.id", "error.recipe",
						"Cuisine must be selected.");
			}

			logger.error("Recipe-Error while validating recipe "
					+ result.toString());
			return "recipes/new";
		}

		try {
			recipeServices.addRecipe(recipe);

			recipes = recipeServices.getRecipes(DEFAULT_RECIPES_PER_PAGE);
			model.addAttribute("recipesList", recipes);

			logger.info("Recipe-Recipe id [" + recipe.getId()
					+ "] added. Redirecting to /recipe/" + recipe.getId());

			return "redirect:/recipe/" + recipe.getId();
		} catch (DataIntegrityViolationException d) {
			result.rejectValue("name", "error.recipe",
					"Already recipe with same name exists. Choose different recipe name.");

			return "recipes/new";
		}
	}

	/*
	 * POST request. Validate the Recipe and update it the database
	 */
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String updateRecipe(@Valid Recipe recipe,
			@ModelAttribute("recipesList") List<Recipe> recipes,
			@ModelAttribute("cuisineList") List<Cuisine> cuisines,
			@ModelAttribute("personList") List<Person> persons,
			BindingResult result, Model model) {

		logger.info("From JSP [" + recipe + "]");

		/*
		 * Validation - BindingResult has error.
		 */
		if (result.hasErrors()) {
			logger.error("Recipe-Error while validating recipe "
					+ result.toString());
			return "recipes/modify";
		}

		/* recipe.setId(id); */
		recipeServices.updateRecipe(recipe);

		recipes = recipeServices.getRecipes(DEFAULT_RECIPES_PER_PAGE);
		model.addAttribute("recipesList", recipes);

		logger.info("Recipe-Recipe id [" + recipe.getId()
				+ "] updated. Redirecting to /recipe/" + recipe.getId());

		return "redirect:/recipe/" + recipe.getId();
	}

	/*
	 * Remove recipe based on ID
	 */
	@RequestMapping(value = "/delete/{id}")
	public String removeRecipe(@PathVariable int id) {
		logger.info("Recipe-Deleting recipe id [" + id
				+ "] and redirect to home page");
		recipeServices.deleteRecipe(id);

		return "redirect:/index";
	}

	/*
	 * Find the index of an id from the List<Recipe>
	 */
	private int findIndex(List<Recipe> recipes, int recipeId) {
		int counter = 0, index = -1;

		for (Recipe recipe : recipes) {
			if (recipe.getId() == recipeId) {
				index = counter;
				break;
			}
			counter++;
		}
		return index;
	}
}
