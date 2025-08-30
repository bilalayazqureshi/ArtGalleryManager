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
	public void testCreateArtist() throws Exception {
		when(artistService.insertNewArtist(any(Artist.class))).thenReturn(new Artist(3L, "Bob Brown", "American"));

		String newArtistJson = """
				{"name":"Bob Brown","nationality":"American"}
				""";

		this.mvc.perform(post("/api/artists/new").contentType(MediaType.APPLICATION_JSON).content(newArtistJson))
				.andExpect(jsonPath("$.id", is(3))).andExpect(jsonPath("$.name", is("Bob Brown")))
				.andExpect(jsonPath("$.nationality", is("American")));
	}

	@Test
	public void testUpdateArtistExisting() throws Exception {
		when(artistService.updateArtistById(anyLong(), any(Artist.class)))
				.thenReturn(new Artist(1L, "John Doe Jr.", "Canadian"));

		String updateJson = "{\"name\":\"John Doe Jr.\",\"nationality\":\"Canadian\"}";

		this.mvc.perform(put("/api/artists/1").contentType(MediaType.APPLICATION_JSON).content(updateJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("John Doe Jr."))).andExpect(jsonPath("$.nationality", is("Canadian")));
	}

	@Test
	public void testUpdateArtistNotFound() throws Exception {
		when(artistService.updateArtistById(anyLong(), any(Artist.class))).thenReturn(null);

		String updateJson = "{\"name\":\"Nobody\",\"nationality\":\"None\"}";

		this.mvc.perform(put("/api/artists/99").contentType(MediaType.APPLICATION_JSON).content(updateJson))
				.andExpect(status().isOk()).andExpect(content().string(""));
	}

	@Test
	public void testDeleteArtist() throws Exception {
		doNothing().when(artistService).deleteArtistById(anyLong());

		this.mvc.perform(delete("/api/artists/1")).andExpect(status().isOk()).andExpect(content().string(""));
	}
}