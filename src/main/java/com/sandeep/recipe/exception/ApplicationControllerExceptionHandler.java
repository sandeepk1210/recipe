package com.sandeep.recipe.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ApplicationControllerExceptionHandler {
	private Logger logger = Logger
			.getLogger(ApplicationControllerExceptionHandler.class);

	@ExceptionHandler(RecipeNotFoundException.class)
	public ModelAndView handleRecipeNotFoundException(HttpServletRequest req,
			Exception exception) {
		logger.info("Request: " + req.getRequestURL() + " raised " + exception);
		String messages[] = { "Invalid recipe id " + exception.getMessage() + ".",
				"No such recipe found in database."};

		ModelAndView mav = new ModelAndView();
		mav.addObject("messages", messages);
		mav.addObject("ex", exception);
		mav.addObject("url", req.getRequestURL());

		mav.setViewName("errors/knownerrors");

		return mav;
	}

	@ExceptionHandler(CuisinePersonCannotBeDeletedException.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		logger.info("Request: " + req.getRequestURL() + " raised " + exception);
		logger.info("Error message: " + exception.getMessage());
		logger.info("Description: " + exception.toString());
		String messages[] = { "Item cannot be deleted as it is being referred by Recipe(s).",
				"Please delete/modify recipes that are referring to this object and then try removing it again."};
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("messages", messages);
		mav.addObject("ex", exception);
		mav.addObject("url", req.getRequestURL());

		mav.setViewName("errors/knownerrors");

		return mav;
	}

}
