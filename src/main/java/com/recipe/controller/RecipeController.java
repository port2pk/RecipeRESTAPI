package com.recipe.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.recipe.entity.Recipe;
import com.recipe.exception.NoEntityFoundException;
import com.recipe.jwt.util.JWTUtil;
import com.recipe.jwt.util.models.AuthenticationRequest;
import com.recipe.jwt.util.models.AuthenticationResponse;
import com.recipe.security.MyUserDetailsService;
import com.recipe.service.RecipeService;




@RestController
//@CrossOrigin(origins="http://localhost:9000")
public class RecipeController {
	
	Logger log = LoggerFactory.getLogger(RecipeController.class);
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtTokenUtil;
	
	//Get All Recipe
	@GetMapping("/users/recipes")
	public List<Recipe> getAllRecipe(){
		log.info("getAllRecipe");
		return recipeService.findAll();
		
	}
	
	// Get Recipe By id
	@GetMapping("/users/recipe/{id}")
	public Recipe getRecipeById(
			@PathVariable Long id) {
		log.info("getRecipeById");
		return recipeService.findById(id);
	}
	
	
	//Delete by recipe id
	@DeleteMapping("/users/recipe/{id}")
	public ResponseEntity<Void> deleteRecepe(
			@PathVariable Long id){
		log.info("deleteRecepe");
		boolean isDeleted = recipeService.deleteById(id);
		if(isDeleted) {
			//build the responseEntity without body, only status in header builder
			return ResponseEntity.noContent().build();
		}
		throw new NoEntityFoundException("No Record Found for id :"+id);
		//return ResponseEntity.notFound().build();
	}
	
	//create recipe
	@PostMapping("users/recipe")
	public ResponseEntity<Void> createRecipe(
			@RequestBody Recipe recipe){
		log.info("createRecipe");
		Recipe newlyRecipe = recipeService.saveRecipe(recipe);
		
		  URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		  .path("/{id}").buildAndExpand(newlyRecipe.getRecipeId()).toUri();
		  return ResponseEntity.created(uri).build();
		 
		//return newlyBooking.getBookingId();
	}
	
	//update existing recipe
	@PutMapping("users/recipe/{id}")
	public ResponseEntity<Recipe> updateRecipe(
			@PathVariable String id,
			@RequestBody Recipe booking){
		log.info("updateRecipe");
		Recipe updatedRecipe = recipeService.saveRecipe(booking);
		return new ResponseEntity<Recipe>(updatedRecipe, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		log.info("createAuthenticationToken");
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
	
}
