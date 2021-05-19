package com.recipe.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredients implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ingredients_id")
	private Long ingredientsId;
	
	
	//@ElementCollection
	@Column(name = "items_used")
	private String items ;
	
	@OneToOne
	@JoinColumn(name = "ingredientsId")
	private Recipe recipeId;

	public Ingredients() {}

	public Ingredients(Recipe recipeId, String items) {
		super();
		this.recipeId = recipeId;
		//this.recipeCd = recipeCd;
		this.items = items;
	}
	
	public Ingredients(String items) {
		super();
		//this.recipeCd = recipeCd;
		this.items = items;
	}

	public Ingredients(String items, Recipe recipeId) {
		super();
		this.items = items;
		this.recipeId = recipeId;
	}

	public Long getIngredientsId() {
		return ingredientsId;
	}

	public void setIngredientsId(Long ingredientsId) {
		this.ingredientsId = ingredientsId;
	}

	public Recipe getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Recipe recipeId) {
		this.recipeId = recipeId;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

}
