package com.sandeep.recipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandeep.recipe.dao.ConfigDao;
import com.sandeep.recipe.tables.Cuisine;
import com.sandeep.recipe.tables.Ingredient;
import com.sandeep.recipe.tables.Person;

@Service
public class ConfigServicesImpl implements ConfigServices {
	@Autowired
	private ConfigDao configDao;

	public List<Cuisine> getCuisine() {
		return configDao.getCuisine();
	}

	public void addCuisine(Cuisine cuisine) {
		configDao.addCuisine(cuisine);
	}

	public void deleteCuisine(int id) {
		configDao.deleteCuisine(id);

	}

	public List<Person> getPerson() {
		return configDao.getPerson();
	}

	public void addPerson(Person person) {
		configDao.addPerson(person);

	}

	public void deletePerson(int id) {
		configDao.deletePerson(id);
	}

	public List<Ingredient> getIngredient() {
		return configDao.getIngredient();
	}
}
