package com.sandeep.recipe.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.sandeep.recipe.tables.Recipe;

@Repository
public class RecipeDaoImpl implements RecipeDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger(RecipeDaoImpl.class);

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<Recipe> getRecipes(int recipesPerPage) {
		Criteria cr = getSession().createCriteria(Recipe.class)
				.setMaxResults(recipesPerPage).addOrder(Order.desc("id"));

		logger.info("Recipe Dao-Retrieved list from database.");

		return cr.list();
	}

	public Recipe getRecipeById(int id) {
		String hql = "from Recipe where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);

		return (Recipe) query.uniqueResult();
	}

	public void addRecipe(Recipe recipe) {
		String hql = "select max(id) from Recipe";
		Boolean result = validate(recipe);

		Integer id = ((Integer) getSession().createQuery(hql).uniqueResult()) + 1;

		recipe.setId(id);
		getSession().save(recipe);
	}

	private Boolean validate(Recipe recipe) {
		/*
		 * select * from Recipe where lower(name) =
		 * recipe.getName().toLowerCase();
		 */
		List<Recipe> recipes  = getSession()
				.createCriteria(Recipe.class)
				.add(Restrictions.sqlRestriction(
						"lower({alias}.name) like lower(?)", recipe.getName(),
						Hibernate.STRING)).list();

		if(recipes.size() != 0){
			throw new DataIntegrityViolationException("Name already exists");
		}
		return true;
	}

	public void updateRecipe(Recipe recipe) {
		getSession().update(recipe);
	}
	
	public void deleteRecipe(int id) {
		Query query = getSession().createQuery(
				"delete from Recipe where id = :id");
		query.setLong("id", id);

		int result = query.executeUpdate();
	}
}