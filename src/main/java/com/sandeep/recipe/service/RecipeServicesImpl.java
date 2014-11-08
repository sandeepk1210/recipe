package com.sandeep.recipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandeep.recipe.dao.RecipeDao;
import com.sandeep.recipe.tables.Recipe;

@Service
public class RecipeServicesImpl implements RecipeServices {

	@Autowired
	private RecipeDao recipeDao;

	public List<Recipe> getRecipes(int recipesPerPage) {
		return recipeDao.getRecipes(recipesPerPage);
	}

	public Recipe getRecipeById(int id) {
		return recipeDao.getRecipeById(id);
	}

	public void addRecipe(Recipe recipe) {
		recipeDao.addRecipe(recipe);
	}

	public void updateRecipe(Recipe recipe) {
		recipeDao.updateRecipe(recipe);
	}	
	
	public void deleteRecipe(int id) {
		recipeDao.deleteRecipe(id);
	}
}
