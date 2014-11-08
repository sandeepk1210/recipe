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
@Table(name = "person")
public class Person implements Serializable {
	private static final long serialVersionUID = -7374699907064358337L;

	@Id
	@Column(name = "person_id")
	private Integer id;

	@Column(name = "name")
	@NotBlank(message = "Please input a value.")
	@NotNull(message = "Please input a value.")
	@Pattern(regexp="^[A-Za-z ]+$", message="Only alphabets are allowed.")
	@Size(max=40, message="Person name must be less than 40 characters long.")
	private String name;

	@Column(name = "selected")
	private Integer selected;

	public static class Builder {
		private final int id;
		private String name;

		public Builder(int id) {
			this.id = id;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
	}

	public Person() {
	}

	public Person(Builder builder) {
		id = builder.id;
		name = builder.name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", selected=" + selected
				+ "]";
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

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}
}
