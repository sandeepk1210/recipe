package com.sandeep.recipe.exception;

import org.apache.log4j.Logger;

/*@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such recipe exists")*/
public class RecipeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -4225958582333538057L;
	Logger logger = Logger.getLogger(getClass());
	
	public RecipeNotFoundException(String recipeId) {
		super(recipeId);
	}
}