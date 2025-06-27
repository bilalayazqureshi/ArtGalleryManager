package com.example.demo.model;

public class Artwork {

	private Long id;
	private String title;
	private String medium;
	private int yearCreated;
	private Artist artist;

	public Artwork(String title, String medium, int yearCreated, Artist artist) {
		this.title = title;
		this.medium = medium;
		this.yearCreated = yearCreated;
		this.artist = artist;
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

	public int getYearCreated() {
		return yearCreated;
	}

	public void setYearCreated(int yearCreated) {
		this.yearCreated = yearCreated;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
}
