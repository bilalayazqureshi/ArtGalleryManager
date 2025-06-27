package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.example.demo.model.Artist;
import com.example.demo.model.Artwork;

class tempTest {

	@Test
	public void test() {
		Artist artist = new Artist("Leonardo da Vinci", "Italian");
		assertNotNull(new Artwork("Mona Lisa", "Oil", 1503, artist));
	}

	@Test
	public void testArtworkCreation() {
		Artist artist = new Artist("Vincent van Gogh", "Dutch");

		Artwork artwork = new Artwork("Starry Night", "Oil on canvas", 1889, artist);
		assertNotNull(artwork);
		assertEquals("Starry Night", artwork.getTitle());
		assertEquals("Oil on canvas", artwork.getMedium());
		assertEquals(1889, artwork.getYearCreated());
		assertEquals("Vincent van Gogh", artwork.getArtist().getName());
	}
}
