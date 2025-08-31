package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Artwork {

	private Long id;
	private String title;
	private String medium;
	private int year;

	private Artist artist; 
	private List<Artist> artists = new ArrayList<>();

	public Artwork() {
	}

	public Artwork(Long id, String title, String medium, int year) {
		this.id = id;
		this.title = title;
		this.medium = medium;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Artwork))
			return false;
		Artwork artwork = (Artwork) o;
		return year == artwork.year && Objects.equals(id, artwork.id) && Objects.equals(title, artwork.title)
				&& Objects.equals(medium, artwork.medium);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, medium, year);
	}

	@Override
	public String toString() {
		return "Artwork{" + "id=" + id + ", title='" + title + '\'' + ", medium='" + medium + '\'' + ", year=" + year
				+ '}';
	}
}