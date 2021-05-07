package com.recipe.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.recipe.entity.Recipe;
import com.recipe.repos.RecipeRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

	@InjectMocks
	RecipeService service;
      
    @Mock
    RecipeRepository dao;
    
    @Test
    public void findAllRecipeTest() throws Exception{
    	Recipe recipe = new Recipe("XXX",new Date(),new Date(),true,new Long(4));
		List<Recipe> list = new ArrayList<Recipe>();
		list.add(recipe);
		
		when(dao.findAll()).thenReturn(list);
		List<Recipe> recipeList = service.findAll();
        
        assertEquals(1, recipeList.size());
        verify(dao, times(1)).findAll();
		
    }
    
    @Test
    public void createRecipeTest() throws Exception{
    	Recipe recipe = new Recipe("XXX",new Date(),new Date(),true,new Long(4));
        
        service.saveRecipe(recipe);
          
        verify(dao, times(1)).save(recipe);
    }
    
    
}
