package com.sandeep.recipe.tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "recipe")
public class Recipe implements Serializable{
	private static final long serialVersionUID = 4398676897855994030L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	@NotBlank(message = "You can not leave this empty.")
	@NotNull(message = "You can not leave this empty.")
	@Size(max = 25, message = "Cannot input more than 25 chars")
	private String name;

	@Column(name = "ingredients")
	@NotBlank(message = "You can not leave this empty.")
	@NotNull(message = "You can not leave this empty.")
	@Size(max = 150, message = "Cannot input more than 150 chars")
	private String ingredients;

	@Column(name = "method")
	private String method;

	@Column(name = "tips")
	private String tips;

	@Column(name = "writeup")
	private String writeup;

	@Column(name = "source")
	private String source;

	@OneToOne
	@JoinColumn(name = "cuisine_id")
	private Cuisine cuisine;

	@OneToOne
	@JoinColumn(name = "person_id")
	private Person person;

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", ingredients="
				+ ingredients + ", method=" + method + ", tips=" + tips
				+ ", writeup=" + writeup + ", source=" + source + ", cuisine="
				+ cuisine + ", person=" + person + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getWriteup() {
		return writeup;
	}

	public void setWriteup(String writeup) {
		this.writeup = writeup;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
