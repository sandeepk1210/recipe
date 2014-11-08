package com.sandeep.test.mvc;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static java.util.Arrays.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import com.sandeep.recipe.mvc.ConfigCuisineController;
import com.sandeep.recipe.service.ConfigServices;
import com.sandeep.recipe.tables.Cuisine;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:recipe-servlet-test.xml" })
/* @WebAppConfiguration */
public class ConfigCuisineControllerTest {
	@Mock
	private ConfigServices configServices;

	@InjectMocks
	private ConfigCuisineController configCuisineController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(configCuisineController)
				.build();
	}

	@Test
	public void testGetCuisine() throws Exception {
		Cuisine cuisine1 = new Cuisine.Builder(1).name("Indian").build();
		Cuisine cuisine2 = new Cuisine.Builder(2).name("Italian").build();
		String menuList[] = { "Cuisine", "Person" };

		when(configServices.getCuisine()).thenReturn(
				Arrays.asList(cuisine1, cuisine2));

		mockMvc.perform(
				get("/config/cuisine").sessionAttr("menuList", menuList))
				.andExpect(status().isOk())
				.andExpect(view().name("config/cuisine"))
				.andExpect(model().attribute("cuisineList", hasSize(2)))
				.andExpect(
						model().attribute(
								"cuisineList",
								hasItem(allOf(hasProperty("id", is(1)),
										hasProperty("name", is("Indian"))))))
				.andExpect(
						model().attribute(
								"cuisineList",
								hasItem(allOf(hasProperty("id", is(2)),
										hasProperty("name", is("Italian"))))));

	}

	@Test
	public void add_NameTooLong_ShouldRenderFormViewAndReturnValidationErrorsForName()
			throws Exception {
		String name = TestUtil.createStringWithLength(41);
		String menuList[] = { "Cuisine", "Person" };

		mockMvc.perform(
				post("/config/cuisine")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("name", name)
						.sessionAttr("menuList", menuList)
						.sessionAttr("cuisine", new Cuisine()))
				.andExpect(status().isOk())
				.andExpect(view().name("config/cuisine"))
				.andExpect(model().attributeHasFieldErrors("cuisine", "name"))
				.andExpect(
						model().attribute("cuisine",
								hasProperty("id", nullValue())))
				.andExpect(model().attribute("name", is(name)));
	}

	@Test
	public void add_NewCuisine_ShouldAddCuisineEntryAndRender() throws Exception{
		Cuisine cuisine = new Cuisine.Builder(1).name("Indian").build();
		
	}
	
	/*
	 * private MockMvc mockMvc;
	 * 
	 * @Autowired private ConfigServices configServices;
	 * 
	 * @Autowired private WebApplicationContext webApplicationContext;
	 * 
	 * @Before public void setup(){ Mockito.reset(configServices); mockMvc =
	 * MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); }
	 * 
	 * @Test public void testGetCuisine() throws Exception{ Cuisine cuisine1 =
	 * new Cuisine(1, "Indian"); Cuisine cuisine2 = new Cuisine(2, "Italian");
	 * 
	 * when(configServices.getCuisine()).thenReturn(Arrays.asList(cuisine1,
	 * cuisine2));
	 * 
	 * mockMvc.perform(get("/config/cuisine")) .andExpect(status().isOk())
	 * .andExpect(view().name("config/cuisine"))
	 * .andExpect(forwardedUrl("/WEB-INF/jsp/main_template.jsp"))
	 * .andExpect(model().attribute("cuisineList", hasSize(4))); }
	 */
}
