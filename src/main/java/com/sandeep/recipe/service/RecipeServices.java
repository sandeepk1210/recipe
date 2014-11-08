package com.sandeep.recipe.service;

import java.util.List;

import com.sandeep.recipe.tables.Recipe;

public interface RecipeServices {
	public List<Recipe> getRecipes(int recipesPerPage);
	
	public Recipe getRecipeById(int id);
	
	public void addRecipe(Recipe recipe);

	public void updateRecipe(Recipe recipe);

	public void deleteRecipe(int id);
}
