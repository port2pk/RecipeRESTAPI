package com.recipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.entity.Recipe;
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
		if(null == recipe) {
			return null;
		}else {
			if(recipe.get().getRecipeId()==id) {
				recipee = recipe.get();
			}
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
			//update the existing one, so first delete then add.
			boolean isDeleted = deleteById(recipe.getRecipeId());
			if(isDeleted){
				persistedRecipe = recipeRepos.save(recipe);
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
		if(null != fetchedRecipe) {
			recipeRepos.delete(fetchedRecipe.get());
			isDeleted = true;
		}
		return isDeleted;
	}
	
}
