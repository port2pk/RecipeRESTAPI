package com.recipe.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeVO;


@Component
public class ConversionUtil {
		
	public static RecipeVO conversionRecipeToRecipeVO(Recipe recipe) {
		ModelMapper maper = new ModelMapper();
		RecipeVO vo =maper.map(recipe, RecipeVO.class);
		/*
		 * RecipeVO vo = new RecipeVO(); vo.setRecipeId(recipe.getRecipeId());
		 * vo.setRecipeId(recipe.getRecipeId()); vo.setRecipeCd(recipe.getRecipeCd());
		 * vo.setRecipeCreationDt(recipe.getRecipeCreationDt());
		 * vo.setRecipeModificationDt(recipe.getRecipeModificationDt());
		 * vo.setNoOfPerson(recipe.getNoOfPerson()); vo.setActive(recipe.isActive());
		 * vo.setIngredientsId(recipe.getIngredients().getIngredientsId());
		 */
		
		return vo;
	}
	
	public static List<RecipeVO> conversionRecipeToRecipeVO(List<Recipe> recipe) {
		List<RecipeVO> listRecipe = new ArrayList<RecipeVO>();
		ModelMapper maper = new ModelMapper();
		if(null != recipe && recipe.size()>0) {
			for(Recipe recipy:recipe) {
				RecipeVO vo =maper.map(recipy, RecipeVO.class);
				/*
				 * RecipeVO vo = new RecipeVO(); vo.setRecipeId(recipy.getRecipeId());
				 * vo.setRecipeId(recipy.getRecipeId()); vo.setRecipeCd(recipy.getRecipeCd());
				 * vo.setRecipeCreationDt(recipy.getRecipeCreationDt());
				 * vo.setRecipeModificationDt(recipy.getRecipeModificationDt());
				 * vo.setNoOfPerson(recipy.getNoOfPerson()); vo.setActive(recipy.isActive());
				 * vo.setIngredientsId(recipy.getIngredients().getIngredientsId());
				 */
				listRecipe.add(vo);
			}	
		}
		
		
		if(listRecipe.size()>0) {
			return listRecipe;
		}else {
			return null;
		}
	}
	
	

}
