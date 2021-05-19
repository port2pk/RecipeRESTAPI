package com.recipe.entity;

import java.util.Date;

public class RecipeVO {
	private Long recipeId;
	private String recipeCd;
	private Date recipeCreationDt;
	private Date recipeModificationDt;
	private boolean isActive;
	private Long noOfPerson;
	private Long ingredientsId;
	
	
	
	public RecipeVO() {
		super();
	}

	public RecipeVO(Long recipeId, String recipeCd, Date recipeCreationDt, Date recipeModificationDt, boolean isActive,
			Long noOfPerson, Long ingredientsId) {
		super();
		this.recipeId = recipeId;
		this.recipeCd = recipeCd;
		this.recipeCreationDt = recipeCreationDt;
		this.recipeModificationDt = recipeModificationDt;
		this.isActive = isActive;
		this.noOfPerson = noOfPerson;
		this.ingredientsId = ingredientsId;
	}
	
	public Long getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}
	public String getRecipeCd() {
		return recipeCd;
	}
	public void setRecipeCd(String recipeCd) {
		this.recipeCd = recipeCd;
	}
	public Date getRecipeCreationDt() {
		return recipeCreationDt;
	}
	public void setRecipeCreationDt(Date recipeCreationDt) {
		this.recipeCreationDt = recipeCreationDt;
	}
	public Date getRecipeModificationDt() {
		return recipeModificationDt;
	}
	public void setRecipeModificationDt(Date recipeModificationDt) {
		this.recipeModificationDt = recipeModificationDt;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Long getNoOfPerson() {
		return noOfPerson;
	}
	public void setNoOfPerson(Long noOfPerson) {
		this.noOfPerson = noOfPerson;
	}
	public Long getIngredientsId() {
		return ingredientsId;
	}
	public void setIngredientsId(Long ingredientsId) {
		this.ingredientsId = ingredientsId;
	}
	
	

}
