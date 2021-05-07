package com.recipe.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.recipe.entity.Recipe;
import com.recipe.service.RecipeService;


@RestController
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	//Get All Recipe
	@GetMapping("/users/recipes")
	public List<Recipe> getAllRecipe(){
		return recipeService.findAll();
		
	}
	
	// Get Recipe By id
	@GetMapping("/users/recipe/{id}")
	public Recipe getRecipeById(
			@PathVariable Long id) {
		return recipeService.findById(id);
	}
	
	
	//Delete by recipe id
	@DeleteMapping("/users/recipe/{id}")
	public ResponseEntity<Void> deleteRecepe(
			@PathVariable Long id){
		boolean isDeleted = recipeService.deleteById(id);
		if(isDeleted) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	//create recipe
	@PostMapping("users/recipe")
	public ResponseEntity<Void> createRecipe(
			@RequestBody Recipe recipe){
		Recipe newlyRecipe = recipeService.saveRecipe(recipe);
		
		  URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		  .path("/{id}").buildAndExpand(newlyRecipe.getRecipeId()).toUri();
		  return ResponseEntity.created(uri).build();
		 
		//return newlyBooking.getBookingId();
	}
	
	@PutMapping("users/recipe/{id}")
	public ResponseEntity<Recipe> updateBooking(
			@PathVariable String id,
			@RequestBody Recipe booking){
		Recipe updatedRecipe = recipeService.saveRecipe(booking);
		return new ResponseEntity<Recipe>(updatedRecipe, HttpStatus.OK);
	}
	
}
