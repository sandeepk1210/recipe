package com.sandeep.test.mvc;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import com.sandeep.recipe.mvc.ConfigPersonController;
import com.sandeep.recipe.service.ConfigServices;
import com.sandeep.recipe.tables.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:recipe-servlet-test.xml" })
public class ConfigPersonControllerTest {
	@Mock
	private ConfigServices configServices;

	@InjectMocks
	private ConfigPersonController configPersonController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(configPersonController)
				.build();
	}

	@Test
	public void testGetPerson() throws Exception {
		Person person1 = new Person.Builder(1).setName("Sandeep").build();
		Person person2 = new Person.Builder(2).setName("Rhea").build();
		
		when(configServices.getPerson()).thenReturn(
				Arrays.asList(person1, person2));

		mockMvc.perform(get("/config/person"))
				.andExpect(status().isOk())
				.andExpect(view().name("config/person"))
				.andExpect(model().attribute("personList", hasSize(2)))
				.andExpect(
						model().attribute(
								"personList",
								hasItem(allOf(hasProperty("id", is(1)),
										hasProperty("name", is("Sandeep"))))))
				.andExpect(
						model().attribute(
								"personList",
								hasItem(allOf(hasProperty("id", is(2)),
										hasProperty("name", is("Rhea"))))));
		
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
