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
	
	//@OneToOne(mappedBy="ingredients")
	//@JoinColumn(name = "ingredients_id")
	private Long recipeId;

	@Column(name = "recipe_cd")
	private String recipeCd;

	//@ElementCollection
	@Column(name = "items_used")
	private String items ;

	public Ingredients() {}

	public Ingredients(Long recipeId, String recipeCd, String items) {
		super();
		this.recipeId = recipeId;
		this.recipeCd = recipeCd;
		this.items = items;
	}
	
	public Ingredients(String recipeCd, String items) {
		super();
		this.recipeCd = recipeCd;
		this.items = items;
	}

	public Long getIngredientsId() {
		return ingredientsId;
	}

	public void setIngredientsId(Long ingredientsId) {
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

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

}
