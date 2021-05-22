package com.recipe.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recipe.entity.Ingredients;
import com.recipe.entity.RecIngDto;
import com.recipe.entity.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
	
	public List<Recipe> findAll();
	
	public Optional<Recipe> findById(Long id);
	
	@Query("select new com.recipe.entity.RecIngDto(r.recipeId, r.recipeCd,ing.items,ing.ingredientsId)"
			+" From Recipe r INNER JOIN r.ingredients ing")
	public List<RecIngDto> checkCustomWithReturn();
	
	@Query("select p from Recipe p inner join Ingredients i on p.ingredients.ingredientsId=i.ingredientsId")
	public List<Recipe> checkCustom();
}
