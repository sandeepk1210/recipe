package com.sandeep.recipe.tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ingredients")
public class Ingredient implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ingredient_id")
	private Integer id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="quantity")
	private String quantity;
	
	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", type=" + type + ", quantity="
				+ quantity + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
