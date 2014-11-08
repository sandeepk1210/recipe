package com.sandeep.recipe.tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "cuisine")
public class Cuisine implements Serializable {
	private static final long serialVersionUID = 6972309524907787483L;

	@Id
	@Column(name = "cuisine_id")
	private Integer id;

	@Column(name = "name")
	@NotBlank(message = "Please input a value.")
	@NotNull(message = "Please input a value.")
	@Pattern(regexp="^[A-Za-z ]+$", message="Only alphabets are allowed.")
	@Size(max=40, message="Cuisine name must be less than 40 characters long.")
	private String name;

	public Cuisine() {
	}

	public Cuisine(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public static class Builder {
		// required
		private final int id;

		// optional
		private String name;

		public Builder(int id) {
			this.id = id;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Cuisine build() {
			return new Cuisine(this);
		}
	}

	private Cuisine(Builder builder) {
		id = builder.id;
		name = builder.name;
	}

	@Override
	public String toString() {
		return "Cuisine [id=" + id + ", name=" + name + "]";
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
}
