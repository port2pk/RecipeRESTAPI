package com.recipe.entity;

public class RecIngDto {
	private Long recipeId;
	private String recipeCd;
	private String items ;
	private Long ingredientsId;
	public RecIngDto(Long recipeId, String recipeCd, String items, Long ingredientsId) {
		super();
		this.recipeId = recipeId;
		this.recipeCd = recipeCd;
		this.items = items;
		this.ingredientsId = ingredientsId;
	}
	public RecIngDto() {
		super();
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
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public Long getIngredientsId() {
		return ingredientsId;
	}
	public void setIngredientsId(Long ingredientsId) {
		this.ingredientsId = ingredientsId;
	}
	
	

}
