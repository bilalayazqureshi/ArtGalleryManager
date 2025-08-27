package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.Artwork;
import com.example.demo.repositories.ArtworkRepository;
import com.example.demo.service.ArtworkService;

class ArtworkServiceTest {

	@Mock
	private ArtworkRepository artworkRepository;

	private ArtworkService artworkService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		artworkService = new ArtworkService(artworkRepository);
	}

	@Test
	public void testGetArtworkById() {
		Artwork artwork = new Artwork(1L, "Mona Lisa", "Oil", 1503);
		when(artworkRepository.findById(1L)).thenReturn(Optional.of(artwork));

		Artwork result = artworkService.getArtworkById(1L);

		assertNotNull(result);
		assertEquals("Mona Lisa", result.getTitle());
		assertEquals("Oil", result.getMedium());
		assertEquals(1503, result.getYearCreated());
		verify(artworkRepository, times(1)).findById(1L);
	}

	@Test
	public void testGetArtworkByIdNotFound() {
		when(artworkRepository.findById(1L)).thenReturn(Optional.empty());

		Artwork result = artworkService.getArtworkById(1L);

		assertNull(result);
		verify(artworkRepository, times(1)).findById(1L);
	}

	@Test
	public void testInsertNewArtwork() {
		Artwork artwork = new Artwork(null, "Starry Night", "Oil on canvas", 1889);
		Artwork savedArtwork = new Artwork(1L, "Starry Night", "Oil on canvas", 1889);

		when(artworkRepository.save(artwork)).thenReturn(savedArtwork);

		Artwork result = artworkService.insertNewArtwork(artwork);

		assertNotNull(result);
		assertEquals("Starry Night", result.getTitle());
		assertEquals("Oil on canvas", result.getMedium());
		assertEquals(1889, result.getYearCreated());
		verify(artworkRepository, times(1)).save(artwork);
	}

	@Test
	public void testUpdateArtwork() {
		Artwork existingArtwork = new Artwork(1L, "Starry Night", "Oil on canvas", 1889);
		Artwork updatedArtwork = new Artwork(1L, "Starry Night Updated", "Oil on canvas", 1890);

		when(artworkRepository.findById(1L)).thenReturn(Optional.of(existingArtwork));
		when(artworkRepository.save(updatedArtwork)).thenReturn(updatedArtwork);

		Artwork result = artworkService.updateArtworkById(1L, updatedArtwork);

		assertNotNull(result);
		assertEquals("Starry Night Updated", result.getTitle());
		assertEquals(1890, result.getYearCreated());
		verify(artworkRepository, times(1)).save(updatedArtwork);
	}

	@Test
	public void testDeleteArtwork() {
		long id = 1L;
		doNothing().when(artworkRepository).deleteById(id);

		artworkService.deleteArtworkById(id);

		verify(artworkRepository, times(1)).deleteById(id);
	}

	@Test
	public void testGetAllArtworks() {
		Artwork artwork1 = new Artwork(1L, "Mona Lisa", "Oil", 1503);
		Artwork artwork2 = new Artwork(2L, "Starry Night", "Oil on canvas", 1889);

		when(artworkRepository.findAll()).thenReturn(List.of(artwork1, artwork2));

		List<Artwork> artworks = artworkService.getAllArtworks();

		assertNotNull(artworks);
		assertEquals(2, artworks.size());
		verify(artworkRepository, times(1)).findAll();
	}
}