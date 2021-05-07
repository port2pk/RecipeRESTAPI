package com.recipe.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.recipe.controller.RecipeController;
import com.recipe.entity.Recipe;
import com.recipe.service.RecipeService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RecipeController.class)

public class RecipeControllerTest {
	
	@MockBean
	private RecipeService recipeService;
	
	@Autowired
    MockMvc mockMvc;
	
	//GET test
	@Test
	public void testGetAllRecipe() throws Exception{
		Recipe recipe = new Recipe("XYZ",new Date(),new Date(),true,new Long(4));
		List<Recipe> list = new ArrayList<Recipe>();
		list.add(recipe);
		Mockito.when(recipeService.findAll()).thenReturn(list);
		mockMvc.perform(get("/users/recipes"))
        .andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].recipeCd", Matchers.is("XYZ")));
		 
	}
	
	//POST TEST
	@Test
	public void testCreateRecipe()throws Exception{
		Recipe recipe = new Recipe("XXZ",new Date(),new Date(),true,new Long(4));
		String exampleRecipe ="{\r\n"
				+ "\"recipeId\":null,\"recipeCd\":\"CRISPY COCONUT OATMEALS\",\"recipeCreationDt\":\"2021-05-05T18:30:00.000+00:00\",\"recipeModificationDt\":\"2021-05-05T18:30:00.000+00:00\",\"noOfPerson\":3,\"active\":true}";
		Mockito.when(recipeService.saveRecipe(Mockito.any(Recipe.class))).thenReturn(recipe);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/users/recipe")
				.accept(MediaType.APPLICATION_JSON).content(exampleRecipe)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
	}
}
