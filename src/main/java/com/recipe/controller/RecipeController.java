package com.recipe.controller;

import java.net.URI;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.apache.tomcat.util.http.parser.EntityTag;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.recipe.entity.RecIngDto;
import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeVO;
import com.recipe.exception.NoEntityFoundException;
import com.recipe.jwt.util.JWTUtil;
import com.recipe.jwt.util.models.AuthenticationRequest;
import com.recipe.jwt.util.models.AuthenticationResponse;
import com.recipe.security.MyUserDetailsService;
import com.recipe.service.RecipeService;
import com.recipe.util.ConversionUtil;




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
	public List<RecipeVO> getAllRecipe(){
		log.info("getAllRecipe");
		List<Recipe> list = recipeService.findAll();
		if(null != list && list.size()>0) {
			return ConversionUtil.conversionRecipeToRecipeVO(list);
		}
		
		return null;
	}
	//sample Demo with Custom Repository methods
	@GetMapping("/user/recipe")
	public List<RecIngDto> testCustomMethodCall(){
		log.info("testCustomMethodCall");
		List<RecIngDto> list = recipeService.testCustome();
		if(null != list && list.size()>0) {
			return list;
		}
		
		return null;
	}
	
	@GetMapping("/users/recipe")
	public List<RecipeVO> testCustomRecipe(){
		log.info("testCustomMethodCall");
		List<Recipe> list=recipeService.testCustomRecipe();
		if(null != list && list.size()>0) {
			return ConversionUtil.conversionRecipeToRecipeVO(list);
		}
		
		return null;
	}
	
	// Get Recipe By id
	@GetMapping(path="/users/recipe/{id}")
	public ResponseEntity<?> getRecipeById(
			@PathVariable Long id
			) {
		log.info("getRecipeById");
		Recipe recipy= recipeService.findById(id);
		if(null != recipy) {
			RecipeVO vo= ConversionUtil.conversionRecipeToRecipeVO(recipy);
			return ResponseEntity.ok().eTag(Long.toString(recipy.getVersion())).body(vo);
		}
		
		return null;
		
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
			@RequestBody Recipe recipe) throws Exception{
		log.info("createRecipe");
		Recipe newlyRecipe = recipeService.saveRecipe(recipe);
		
		  URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		  .path("/{id}").buildAndExpand(newlyRecipe.getRecipeId()).toUri();
		  return ResponseEntity.created(uri).build();
		 
		//return newlyBooking.getBookingId();
	}
	
	//update existing recipe
	@PutMapping("users/recipe/{id}")
	public ResponseEntity<RecipeVO> updateRecipe(
			@PathVariable String id,
			@RequestBody Recipe booking) throws Exception{
		log.info("updateRecipe");
		Recipe updatedRecipe = recipeService.saveRecipe(booking);
		RecipeVO vo=ConversionUtil.conversionRecipeToRecipeVO(updatedRecipe);
		return new ResponseEntity<RecipeVO>(vo, HttpStatus.OK);
	}
	
	@PatchMapping(path="test/txn/{id}",headers = HttpHeaders.IF_MATCH)
	public ResponseEntity<?> testLocking(
			@PathVariable Long id,
			@RequestHeader(name=HttpHeaders.IF_MATCH,required = false)String ifMatch) {
		log.info("testLocking");
		System.out.println("inside testLocking");
		Recipe fetched=recipeService.findById(id);
		Recipe updatedRecipe = null;
		RecipeVO vo = null;
		if(ifMatch==null) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).build(); 
		}
		try {
			System.out.println(" Long.parseLong(ifMatch) : "+ifMatch.replace("\"", "")+" - fetched.getVersion() : "+fetched.getVersion());
			if (Long.parseLong(ifMatch.replace("\"", "")) == fetched.getVersion()) {
				for (int i = 0; i < 2000; i++) {
					updatedRecipe = recipeService.testTxn(id);
					vo = ConversionUtil.conversionRecipeToRecipeVO(updatedRecipe);
				}
			} else {
				return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
			}
		}catch(OptimisticLockException ex) {
			System.out.println("inside catch block");
			throw new OptimisticLockException("Due to conflict target resources, transaction got failed.");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return new ResponseEntity<RecipeVO>(vo, HttpStatus.OK);
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
