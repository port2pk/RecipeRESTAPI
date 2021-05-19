package com.recipe.entity;

public class IngredientsVO {
	private Long ingredientsId;
	private String items ;
	
	
	public IngredientsVO() {
		super();
	}
	public IngredientsVO(Long ingredientsId, String items) {
		super();
		this.ingredientsId = ingredientsId;
		this.items = items;
	}
	public Long getIngredientsId() {
		return ingredientsId;
	}
	public void setIngredientsId(Long ingredientsId) {
		this.ingredientsId = ingredientsId;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	
	

}
