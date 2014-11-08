package com.sandeep.recipe.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sandeep.recipe.exception.CuisinePersonCannotBeDeletedException;
import com.sandeep.recipe.tables.Cuisine;
import com.sandeep.recipe.tables.Ingredient;
import com.sandeep.recipe.tables.Person;

@Repository
public class ConfigDaoImpl implements ConfigDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger(ConfigDaoImpl.class);

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * Cusines get, add and delete methods
	 */
	public List<Cuisine> getCuisine() {
		return getSession().createQuery("FROM Cuisine ORDER BY id").list();
	}

	public void addCuisine(Cuisine cuisine) {
		String hql = "SELECT max(id) FROM Cuisine";
		int id = (Integer) getSession().createQuery(hql).uniqueResult();
		id++;

		cuisine.setId(id);

		getSession().save(cuisine);
		logger.info("Cuisine Dao-Successfully added " + cuisine);
	}

	public void deleteCuisine(int id) {
		Query query = getSession().createQuery(
				"delete from Cuisine where id = :id");
		query.setLong("id", id);

		try {
			query.executeUpdate();
			logger.info("Cuisine id " + id + " successfully deleted.");
		} catch (ConstraintViolationException e) {
			logger.info("Constraint voilation exception while deleting cuisine id "
					+ id);
			throw new CuisinePersonCannotBeDeletedException("" + id);
		} catch (Exception e) {
			logger.info("Some error occured while deleting cuisine id " + id);
			logger.info("Error message: " + e.getMessage());
			logger.info("Description: " + e.toString());
		}
	}

	/*
	 * Person get, add and delete methods
	 */
	public List<Person> getPerson() {
		return getSession().createQuery(
				"FROM Person ORDER BY selected desc, id").list();
	}

	public void addPerson(Person person) {
		int id = (Integer) getSession().createQuery(
				"SELECT max(id) FROM Person").uniqueResult();
		id++;

		person.setId(id);
		person.setSelected(0);
		logger.info("Person Dao-Adding " + person);

		getSession().save(person);

	}

	public void deletePerson(int id) {
		Query query = getSession().createQuery(
				"delete from Person where id = :id");
		query.setLong("id", id);

		try{
			query.executeUpdate();
		} catch (ConstraintViolationException e) {
			logger.info("Constraint voilation exception while deleting person id "
					+ id);
			throw new CuisinePersonCannotBeDeletedException("" + id);
		} catch (Exception e) {
			logger.info("Some error occured while deleting person id " + id);
			logger.info("Error message: " + e.getMessage());
			logger.info("Description: " + e.toString());
		}
	}

	public List<Ingredient> getIngredient() {
		return getSession().createQuery("FROM Ingredient ORDER BY id").list();
	}
}