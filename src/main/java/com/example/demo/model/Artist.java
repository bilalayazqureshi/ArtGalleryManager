package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Artist {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String nationality;

	public Artist(Long id, String name, String nationality) {
		this.id = id;
		this.name = name;
		this.nationality = nationality;
	}

	public Artist() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}