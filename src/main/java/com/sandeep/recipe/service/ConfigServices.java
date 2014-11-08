package com.sandeep.recipe.service;

import java.util.List;

import com.sandeep.recipe.tables.Cuisine;
import com.sandeep.recipe.tables.Ingredient;
import com.sandeep.recipe.tables.Person;

public interface ConfigServices {
	public List<Cuisine> getCuisine();

	public void addCuisine(Cuisine cuisine);
	
	public void deleteCuisine(int id);
	
	public List<Person> getPerson();

	public void addPerson(Person person);
	
	public void deletePerson(int id);
	
	public List<Ingredient> getIngredient();
}
