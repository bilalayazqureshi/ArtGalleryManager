package com.example.demo.model;

import java.util.Objects;

public class Artwork {

	private Long id;
	private String title;
	private String medium;
	private int yearCreated;

	public Artwork(Long id, String title, String medium, int yearCreated) {
		this.id = id;
		this.title = title;
		this.medium = medium;
		this.yearCreated = yearCreated;
	}

	public Artwork() {
		// Default constructor
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getMedium() {
		return medium;
	}

	public int getYearCreated() {
		return yearCreated;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public void setYearCreated(int yearCreated) {
		this.yearCreated = yearCreated;
	}

	@Override
	public String toString() {
		return "Artwork [id=" + id + ", title=" + title + ", medium=" + medium + ", yearCreated=" + yearCreated + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, medium, yearCreated);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Artwork other = (Artwork) obj;
		return yearCreated == other.yearCreated &&
			   Objects.equals(id, other.id) &&
			   Objects.equals(title, other.title) &&
			   Objects.equals(medium, other.medium);
	}
}