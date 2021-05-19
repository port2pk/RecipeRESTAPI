package com.recipe.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RECIPE")
public class Recipe implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id")
	private Long recipeId;

	@Column(name = "recipe_cd")
	private String recipeCd;

	@Column(name = "recipe_creation_date")
	private Date recipeCreationDt;

	@Column(name = "recipe_modification_date")
	private Date recipeModificationDt;

	@Column(name = "recipe_is_active")
	private boolean isActive;
	
	@Column(name = "recipe_for_person")
	private Long noOfPerson;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
	//@OneToOne
	@JoinColumn(name = "ingredientsId") 
	private Ingredients ingredients;
	 
	
	

	public Recipe() {}

	public Recipe(String recipeCd, Date recipeCreationDt, Date recipeModificationDt, boolean isActive) {
		this.recipeCd = recipeCd;
		this.recipeCreationDt = recipeCreationDt;
		this.recipeModificationDt = recipeModificationDt;
		this.isActive = isActive;
	}
	
	
	public Recipe(String recipeCd, Date recipeCreationDt, Date recipeModificationDt, boolean isActive, Long noOfPerson,
			Ingredients ingredients) {
		super();
		this.recipeCd = recipeCd;
		this.recipeCreationDt = recipeCreationDt;
		this.recipeModificationDt = recipeModificationDt;
		this.isActive = isActive;
		this.noOfPerson = noOfPerson;
		this.ingredients = ingredients;
	}

	public Recipe(String recipeCd, Date recipeCreationDt, Date recipeModificationDt, boolean isActive,
			Long noOfPerson) {
		super();
		this.recipeCd = recipeCd;
		this.recipeCreationDt = recipeCreationDt;
		this.recipeModificationDt = recipeModificationDt;
		this.isActive = isActive;
		this.noOfPerson = noOfPerson;
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

	public Ingredients getIngredients() {
		return ingredients;
	}

	public void setIngredients(Ingredients ingredients) {
		this.ingredients = ingredients;
	}
	

}
