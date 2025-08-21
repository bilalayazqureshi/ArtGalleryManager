package com.example.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.ArtworkRestController;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ArtworkRestController.class)
public class ArtworkRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testAllArtworksEmpty() throws Exception {
		this.mvc.perform(get("/api/artworks").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("[]")); 
	}
}