package com.example.demo.controllers;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import com.example.demo.model.Artist;
import com.example.demo.services.ArtistService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ArtistWebController.class)
class ArtistWebControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ArtistService artistService;

	@Test
	void testStatus200_ListView() throws Exception {
		mvc.perform(get("/artists")).andExpect(status().is2xxSuccessful());
	}

	@Test
	void testReturnArtistView() throws Exception {
		ModelAndViewAssert.assertViewName(mvc.perform(get("/artists")).andReturn().getModelAndView(), "artist");
	}

	@Test
	void test_ListView_ShowsArtists() throws Exception {
		List<Artist> artists = asList(new Artist(1L, "Da Vinci", "Italian"));
		when(artistService.getAllArtists()).thenReturn(artists);

		mvc.perform(get("/artists")).andExpect(view().name("artist")).andExpect(model().attribute("artists", artists))
				.andExpect(model().attribute("message", ""));
	}

	@Test
	void test_ListView_ShowsMessageWhenNoArtists() throws Exception {
		when(artistService.getAllArtists()).thenReturn(emptyList());

		mvc.perform(get("/artists")).andExpect(view().name("artist"))
				.andExpect(model().attribute("artists", emptyList()))
				.andExpect(model().attribute("message", "No artist"));
	}

	@Test
	void test_EditArtist_WhenFound() throws Exception {
		Artist artist = new Artist(2L, "Picasso", "Spanish");
		when(artistService.getArtistById(2L)).thenReturn(artist);

		mvc.perform(get("/artists/edit/2")).andExpect(view().name("edit_artist"))
				.andExpect(model().attribute("artist", artist)).andExpect(model().attribute("message", ""));
	}

	@Test
	void test_EditArtist_WhenNotFound() throws Exception {
		when(artistService.getArtistById(3L)).thenReturn(null);

		mvc.perform(get("/artists/edit/3")).andExpect(view().name("edit_artist"))
				.andExpect(model().attribute("artist", nullValue()))
				.andExpect(model().attribute("message", "No artist found with id: 3"));
	}

	@Test
	void test_EditNewArtist() throws Exception {
		mvc.perform(get("/artists/new")).andExpect(view().name("artist"))
				.andExpect(model().attribute("artist", new Artist())).andExpect(model().attribute("message", ""));
		verifyNoMoreInteractions(artistService);
	}

	@Test
	void test_PostArtistWithoutId_ShouldInsertNewArtist() throws Exception {
		mvc.perform(post("/artists/save").param("name", "Van Gogh").param("nationality", "Dutch"))
				.andExpect(view().name("redirect:/artists"));

		verify(artistService).insertNewArtist(new Artist(null, "Van Gogh", "Dutch"));
	}

	@Test
	void test_PostArtistWithId_ShouldUpdateExistingArtist() throws Exception {
		mvc.perform(post("/artists/save").param("id", "4").param("name", "Frida Kahlo").param("nationality", "Mexican"))
				.andExpect(view().name("redirect:/artists"));

		verify(artistService).updateArtistById(4L, new Artist(4L, "Frida Kahlo", "Mexican"));
	}

	@Test
	void test_DeleteArtist() throws Exception {
		mvc.perform(delete("/artists/delete/5")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/artists"));

		verify(artistService).deleteArtistById(5L);
	}
}