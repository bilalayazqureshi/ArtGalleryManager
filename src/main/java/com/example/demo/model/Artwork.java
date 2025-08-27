package com.example.demo.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artwork")
public class Artwork {

	@Id
	@GeneratedValue
	private Long id;

	private String title;
	private String medium;
	private int yearCreated;

	@ManyToMany
	@JoinTable(name = "artwork_artist", joinColumns = @JoinColumn(name = "artwork_id"), inverseJoinColumns = @JoinColumn(name = "artist_id"))
	private Set<Artist> artists = new HashSet<>();

	public Artwork(Long id, String title, String medium, int yearCreated) {
		this.id = id;
		this.title = title;
		this.medium = medium;
		this.yearCreated = yearCreated;
	}

	public Artwork() {
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

	public Set<Artist> getArtists() {
		return artists;
	}

	public void setArtists(Set<Artist> artists) {
		this.artists = artists;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Artwork))
			return false;
		Artwork artwork = (Artwork) o;
		return Objects.equals(id, artwork.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}