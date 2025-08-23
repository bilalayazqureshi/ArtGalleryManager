package com.example.demo.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.Artist;
import com.example.demo.model.Artwork;
import com.example.demo.service.ArtworkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = com.example.demo.controllers.ArtworkRestController.class)
class ArtworkRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ArtworkService artworkService;

	@Test
	void testCreateArtwork() throws Exception {
		Artist artist = new Artist(1L, "Pablo Picasso", "Spanish");
		when(artworkService.insertNewArtwork(any(Artwork.class)))
				.thenReturn(new Artwork(3L, "New Piece", "Acrylic", 2025, artist));

		String newArtworkJson = """
				{
				  "title":"New Piece",
				  "medium":"Acrylic",
				  "yearCreated":2025,
				  "artist":{"id":1,"name":"Pablo Picasso","nationality":"Spanish"}
				}
				""";

		mvc.perform(post("/api/artworks/new").contentType(MediaType.APPLICATION_JSON).content(newArtworkJson))
				.andExpect(jsonPath("$.id", is(3))).andExpect(jsonPath("$.title", is("New Piece")))
				.andExpect(jsonPath("$.medium", is("Acrylic"))).andExpect(jsonPath("$.yearCreated", is(2025)))
				.andExpect(jsonPath("$.artist.name", is("Pablo Picasso")));
	}

	@Test
	void testUpdateArtworkExisting() throws Exception {
		Artist artist = new Artist(1L, "Pablo Picasso", "Spanish");
		when(artworkService.updateArtworkById(anyLong(), any(Artwork.class)))
				.thenReturn(new Artwork(1L, "Updated Piece", "Oil", 2024, artist));

		String updateJson = """
				{
				  "title":"Updated Piece",
				  "medium":"Oil",
				  "yearCreated":2024,
				  "artist":{"id":1,"name":"Pablo Picasso","nationality":"Spanish"}
				}
				""";

		mvc.perform(put("/api/artworks/1").contentType(MediaType.APPLICATION_JSON).content(updateJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.title", is("Updated Piece"))).andExpect(jsonPath("$.medium", is("Oil")))
				.andExpect(jsonPath("$.yearCreated", is(2024)))
				.andExpect(jsonPath("$.artist.name", is("Pablo Picasso")));
	}

	@Test
	void testUpdateArtworkNotFound() throws Exception {
		when(artworkService.updateArtworkById(anyLong(), any(Artwork.class))).thenReturn(null);

		String updateJson = """
				{
				  "title":"Nonexistent",
				  "medium":"Ink",
				  "yearCreated":2023,
				  "artist":{"id":99,"name":"Unknown","nationality":"None"}
				}
				""";

		mvc.perform(put("/api/artworks/99").contentType(MediaType.APPLICATION_JSON).content(updateJson))
				.andExpect(status().isOk()).andExpect(content().string(""));
	}

	@Test
	void testDeleteArtwork() throws Exception {
		doNothing().when(artworkService).deleteArtworkById(anyLong());

		mvc.perform(delete("/api/artworks/1")).andExpect(status().isOk()).andExpect(content().string(""));
	}
}