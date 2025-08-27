package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Artwork {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String medium;
	private int yearCreated;

	@ManyToMany
	@JoinTable(name = "artwork_artist", joinColumns = @JoinColumn(name = "artwork_id"), inverseJoinColumns = @JoinColumn(name = "artist_id"))
	private List<Artist> artists = new ArrayList<>();

	public Artwork() {

	}

	public Artwork(Long id, String title, String medium, int yearCreated) {
		this.id = id;
		this.title = title;
		this.medium = medium;
		this.yearCreated = yearCreated;
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

	public List<Artist> getArtists() {
		return artists;
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

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
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
		return Objects.equals(id, other.id) && Objects.equals(title, other.title)
				&& Objects.equals(medium, other.medium) && yearCreated == other.yearCreated;
	}
}