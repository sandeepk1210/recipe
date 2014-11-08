package com.sandeep.recipe.exception;

public class CuisinePersonCannotBeDeletedException extends RuntimeException {
	private static final long serialVersionUID = 9219038893429518969L;

	public CuisinePersonCannotBeDeletedException(String cuisineId) {
		super(cuisineId);
	}
}
