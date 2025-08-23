package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.example.demo.model.Artist;
import com.example.demo.model.Artwork;

class tempTest {

	@Test
	void testArtworkIsNotNull() {
		Artist artist = new Artist(1L, "Leonardo da Vinci", "Italian");
		Artwork artwork = new Artwork(1L, "Mona Lisa", "Oil", 1503);
		artwork.setArtists(Set.of(artist));
		assertNotNull(artwork);
	}

	@Test
	void testArtworkCreation() {
		Artist artist = new Artist(1L, "Vincent van Gogh", "Dutch");
		Artwork artwork = new Artwork(1L, "Starry Night", "Oil on canvas", 1889);
		artwork.setArtists(Set.of(artist));

		assertNotNull(artwork);
		assertEquals(1L, artwork.getId());
		assertEquals("Starry Night", artwork.getTitle());
		assertEquals("Oil on canvas", artwork.getMedium());
		assertEquals(1889, artwork.getYearCreated());
		assertEquals("Vincent van Gogh", artwork.getArtists().iterator().next().getName());
	}
}