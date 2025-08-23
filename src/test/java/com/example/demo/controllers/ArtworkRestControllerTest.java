package com.example.demo.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.example.demo.model.Artist;
import com.example.demo.model.Artwork;
import com.example.demo.service.ArtworkService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ArtworkRestController.class)
class ArtworkRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ArtworkService artworkService;

	@Test
	void testAllArtworksNotEmpty() throws Exception {
		Artist artist = new Artist(1L, "Pablo Picasso", "Spanish");
		Artwork a1 = new Artwork(1L, "Guernica", "Oil on canvas", 1937, artist);
		Artwork a2 = new Artwork(2L, "The Weeping Woman", "Oil on canvas", 1937, artist);

		when(artworkService.getAllArtworks()).thenReturn(List.of(a1, a2));

		String expectedJson = """
				    [
				      { "id":1, "title":"Guernica", "medium":"Oil on canvas", "yearCreated":1937,
				        "artist":{ "id":1, "name":"Pablo Picasso", "nationality":"Spanish" } },
				      { "id":2, "title":"The Weeping Woman", "medium":"Oil on canvas", "yearCreated":1937,
				        "artist":{ "id":1, "name":"Pablo Picasso", "nationality":"Spanish" } }
				    ]
				""";

		mvc.perform(get("/api/artworks").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(expectedJson));
	}

	@Test
	void testOneArtworkByIdWithExistingArtwork() throws Exception {
		Artist artist = new Artist(1L, "Pablo Picasso", "Spanish");
		Artwork artwork = new Artwork(1L, "Guernica", "Oil on canvas", 1937, artist);

		when(artworkService.getArtworkById(anyLong())).thenReturn(artwork);

		mvc.perform(get("/api/artworks/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.title", is("Guernica")))
				.andExpect(jsonPath("$.medium", is("Oil on canvas"))).andExpect(jsonPath("$.yearCreated", is(1937)))
				.andExpect(jsonPath("$.artist.name", is("Pablo Picasso")));
	}

	@Test
	void testOneArtworkByIdWithNotFoundArtwork() throws Exception {
		when(artworkService.getArtworkById(anyLong())).thenReturn(null);

		mvc.perform(get("/api/artworks/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(""));
	}
}