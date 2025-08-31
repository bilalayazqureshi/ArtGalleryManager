package com.example.demo.model;

import java.util.Objects;

public class Artist {

	private Long id;
	private String name;
	private String nationality;
	private Artwork artwork;

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

	public String getName() {
		return name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Artwork getArtwork() {
		return artwork;
	}

	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", nationality=" + nationality + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, nationality);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(nationality, other.nationality);
	}
}