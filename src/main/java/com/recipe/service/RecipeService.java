package com.recipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.entity.Recipe;
import com.recipe.exception.NoEntityFoundException;
import com.recipe.repos.RecipeRepository;

/**
 * Service 
 * @author PKumar14
 *
 */
@Service
public class RecipeService {
	
	@Autowired
	RecipeRepository recipeRepos;
	
	/**
	 * sample logic
	 * @return
	 */
	public List<Recipe> findAll(){
		return recipeRepos.findAll();
	}
	/**
	 * sample logic
	 * @param id
	 * @return
	 */
	public Recipe findById(Long id) {
		Recipe recipee =null;
		Optional<Recipe> recipe =recipeRepos.findById(id);
		if(recipe.isPresent()) {
			recipee = recipe.get();
		}else {
			throw new NoEntityFoundException("No record found for id :"+id);
		}
		return recipee;
	}
	/**
	 * This is just a sample logic for save.
	 */
	public Recipe saveRecipe(Recipe recipe) {
		Recipe persistedRecipe = null;
		if(recipe.getRecipeId() == null) {
			persistedRecipe = recipeRepos.save(recipe);
		}else {
			//update
			persistedRecipe =findById(recipe.getRecipeId());
			if(null != persistedRecipe) {
				persistedRecipe = recipeRepos.save(recipe);
			}else {
				throw new NoEntityFoundException("No record found for id :"+recipe.getRecipeId());
			}
		}
		return persistedRecipe;
	}
	/**
	 * sample logic.
	 * @param recipeId
	 * @return
	 */
	public boolean deleteById(Long recipeId) {
		boolean isDeleted = false;
		Optional<Recipe> fetchedRecipe=recipeRepos.findById(recipeId);
		if(fetchedRecipe.isPresent()) {
			recipeRepos.delete(fetchedRecipe.get());
			isDeleted = true;
		}
		return isDeleted;
	}
	
}
