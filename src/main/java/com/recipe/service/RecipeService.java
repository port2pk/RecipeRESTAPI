package com.recipe.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.recipe.entity.RecIngDto;
import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeVO;
import com.recipe.exception.NoEntityFoundException;
import com.recipe.repos.RecipeRepository;

/**
 * Service 
 * @author PKumar14
 *
 */
@Service
public class RecipeService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	RecipeRepository recipeRepos;
	
	/**
	 * sample logic
	 * @return
	 */
	public List<Recipe> findAll(){
		return recipeRepos.findAll();
	}
	
	public List<RecIngDto> testCustome(){
		return recipeRepos.checkCustomWithReturn();
	}
	
	public List<Recipe> testCustomRecipe(){
		return recipeRepos.checkCustom();
	}
	/**
	 * sample logic
	 * @param id
	 * @return
	 */
	@Transactional
	public Recipe findById(Long id) {
		Recipe recipee =null;
		Optional<Recipe> recipe =recipeRepos.findById(id);
		if(recipe.isPresent()) {
			recipee = recipe.get();
			entityManager.lock(recipee, LockModeType.OPTIMISTIC);
		}else {
			throw new NoEntityFoundException("No record found for id :"+id);
		}
		return recipee;
	}
	/**
	 * This is just a sample logic for save.
	 */
	
	public Recipe saveRecipe(Recipe recipe) throws Exception {
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
	@Transactional
	public Recipe testTxn(Long id) throws OptimisticLockException {
		
		//System.out.println("TestTxn started for id : "+id);
		Recipe recipe = entityManager.find(Recipe.class, id);
		Long personTotal = recipe.getNoOfPerson()+1;
		recipe.setNoOfPerson(personTotal);
		recipe.setRecipeModificationDt(new Date());
		Recipe savedRecipe = recipeRepos.save(recipe);
		entityManager.flush();
		//System.out.println("TestTxn ended here for id :"+savedRecipe.getRecipeId());
		return savedRecipe;	
		
	}
	
}
