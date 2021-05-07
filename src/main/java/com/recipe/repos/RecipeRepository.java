package com.recipe.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recipe.entity.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
	
	public List<Recipe> findAll();
	
	public Optional<Recipe> findById(Long id);
}
