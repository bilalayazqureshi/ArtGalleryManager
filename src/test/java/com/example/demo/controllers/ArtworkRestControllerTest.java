package com.example.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ArtworkRestController.class)
class ArtworkRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ArtworkService artworkService;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void testAllArtworksEmpty() throws Exception {
		when(artworkService.getAllArtworks()).thenReturn(List.of());

		mvc.perform(get("/api/artworks").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json("[]"));
	}

	@Test
	void testAllArtworksNotEmpty() throws Exception {
		Artist artist = new Artist(1L, "Leonardo da Vinci", "Italian");
		Artwork artwork = new Artwork(1L, "Mona Lisa", "Oil", 1503, artist);
		List<Artwork> artworks = List.of(artwork);

		when(artworkService.getAllArtworks()).thenReturn(artworks);

		String expectedJson = objectMapper.writeValueAsString(artworks);

		mvc.perform(get("/api/artworks").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(expectedJson));
	}
}