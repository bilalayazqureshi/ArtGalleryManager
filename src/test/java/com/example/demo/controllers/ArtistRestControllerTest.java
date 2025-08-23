package com.example.demo.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.example.demo.model.Artist;
import com.example.demo.service.ArtistService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ArtistRestController.class)
public class ArtistRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ArtistService artistService;

	@Test
	public void testAllArtistsNotEmpty() throws Exception {
		Artist a1 = new Artist(1L, "Pablo Picasso", "Spanish");
		Artist a2 = new Artist(2L, "Vincent van Gogh", "Dutch");

		when(artistService.getAllArtists()).thenReturn(List.of(a1, a2));

		mvc.perform(get("/api/artists").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json("""
							[
								{"id":1,"name":"Pablo Picasso","nationality":"Spanish"},
								{"id":2,"name":"Vincent van Gogh","nationality":"Dutch"}
							]
						"""));
	}

	@Test
	public void testOneArtistByIdWithExistingArtist() throws Exception {
		when(artistService.getArtistById(anyLong())).thenReturn(new Artist(1L, "Pablo Picasso", "Spanish"));

		mvc.perform(get("/api/artists/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.name", is("Pablo Picasso")))
				.andExpect(jsonPath("$.nationality", is("Spanish")));
	}

	@Test
	public void testOneArtistByIdWithNotFoundArtist() throws Exception {
		when(artistService.getArtistById(anyLong())).thenReturn(null);

		mvc.perform(get("/api/artists/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(""));
	}
}