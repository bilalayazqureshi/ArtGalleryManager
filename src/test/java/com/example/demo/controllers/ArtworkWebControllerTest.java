package com.example.demo.controllers;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.example.demo.model.Artwork;
import com.example.demo.services.ArtworkService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ArtworkWebController.class)
public class ArtworkWebControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ArtworkService artworkService;

	@Test
	void testStatus200_ListView() throws Exception {
		mvc.perform(get("/artworks")).andExpect(status().is2xxSuccessful());
	}

	@Test
	void testReturnArtworkView() throws Exception {
		ModelAndViewAssert.assertViewName(mvc.perform(get("/artworks")).andReturn().getModelAndView(), "artwork");
	}

	@Test
	void test_ListView_ShowsArtworks() throws Exception {
		List<Artwork> artworks = asList(new Artwork(1L, "Mona Lisa", "Oil", 1503));
		when(artworkService.getAllArtworks()).thenReturn(artworks);

		mvc.perform(get("/artworks")).andExpect(view().name("artwork"))
				.andExpect(model().attribute("artworks", artworks)).andExpect(model().attribute("message", ""));
	}

	@Test
	void test_ListView_ShowsMessageWhenNoArtworks() throws Exception {
		when(artworkService.getAllArtworks()).thenReturn(emptyList());

		mvc.perform(get("/artworks")).andExpect(view().name("artwork"))
				.andExpect(model().attribute("artworks", emptyList()))
				.andExpect(model().attribute("message", "No artwork"));
	}

	@Test
	void test_EditArtwork_WhenFound() throws Exception {
		Artwork art = new Artwork(2L, "Starry Night", "Oil", 1889);
		when(artworkService.getArtworkById(2L)).thenReturn(art);

		mvc.perform(get("/artworks/edit/2")).andExpect(view().name("artwork"))
				.andExpect(model().attribute("artwork", art)).andExpect(model().attribute("message", ""));
	}

	@Test
	void test_EditArtwork_WhenNotFound() throws Exception {
		when(artworkService.getArtworkById(3L)).thenReturn(null);

		mvc.perform(get("/artworks/edit/3")).andExpect(view().name("artwork"))
				.andExpect(model().attribute("artwork", nullValue()))
				.andExpect(model().attribute("message", "No artwork found with id: 3"));
	}

	@Test
	void test_EditNewArtwork() throws Exception {
		mvc.perform(get("/artworks/new")).andExpect(view().name("artwork"))
				.andExpect(model().attribute("artwork", new Artwork(null, null, null, 0)))
				.andExpect(model().attribute("message", ""));
		verifyNoMoreInteractions(artworkService);
	}

	@Test
	void test_PostArtworkWithoutId_ShouldInsertNewArtwork() throws Exception {
		mvc.perform(
				post("/artworks/save").param("title", "Sunflowers").param("medium", "Oil").param("yearCreated", "1888"))
				.andExpect(view().name("redirect:/artworks"));

		verify(artworkService).insertNewArtwork(new Artwork(null, "Sunflowers", "Oil", 1888));
	}

	@Test
	void test_PostArtworkWithId_ShouldUpdateExistingArtwork() throws Exception {
		mvc.perform(post("/artworks/save").param("id", "4").param("title", "Water Lilies").param("medium", "Oil")
				.param("yearCreated", "1906")).andExpect(view().name("redirect:/artworks"));

		verify(artworkService).updateArtworkById(4L, new Artwork(4L, "Water Lilies", "Oil", 1906));
	}

	@Test
	void test_DeleteArtwork() throws Exception {
		mvc.perform(delete("/artworks/delete/5")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/artworks"));

		verify(artworkService).deleteArtworkById(5L);
	}
}