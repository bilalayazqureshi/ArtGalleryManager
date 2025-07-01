package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.Artist;
import com.example.demo.model.Artwork;
import com.example.demo.repository.ArtworkRepository;

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
		Artist artist = new Artist(null,"Vincent van Gogh", "Dutch");
		Artwork artwork = new Artwork(1L, "Mona Lisa", "Oil", 1503, artist);
		when(artworkRepository.findById(1L)).thenReturn(Optional.of(artwork));

		Artwork result = artworkService.getArtworkById(1L);

		assertNotNull(result);
		assertEquals("Mona Lisa", result.getTitle());
		assertEquals("Oil", result.getMedium());
		assertEquals(1503, result.getYearCreated());
		assertEquals("Leonardo da Vinci", result.getArtist().getName());
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
		Artist artist = new Artist(null,"Vincent van Gogh", "Dutch");
		Artwork artwork = new Artwork(null, "Starry Night", "Oil on canvas", 1889, artist);
		Artwork savedArtwork = new Artwork(1L, "Starry Night", "Oil on canvas", 1889, artist);

		when(artworkRepository.save(artwork)).thenReturn(savedArtwork);

		Artwork result = artworkService.insertNewArtwork(artwork);

		assertNotNull(result);
		assertEquals("Starry Night", result.getTitle());
		assertEquals("Oil on canvas", result.getMedium());
		assertEquals(1889, result.getYearCreated());
		assertEquals("Vincent van Gogh", result.getArtist().getName());
		verify(artworkRepository, times(1)).save(artwork);
	}

	@Test
	public void testUpdateArtwork() {
		Artist artist = new Artist(null,"Vincent van Gogh", "Dutch");
		Artwork existingArtwork = new Artwork(1L, "Starry Night", "Oil on canvas", 1889, artist);
		Artwork updatedArtwork = new Artwork(1L, "Starry Night Updated", "Oil on canvas", 1890, artist);

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
		Artist artist1 = new Artist(null,"Leonardo da Vinci", "Italian");
		Artist artist2 = new Artist(null,"Vincent van Gogh", "Dutch");

		Artwork artwork1 = new Artwork(1L, "Mona Lisa", "Oil", 1503, artist1);
		Artwork artwork2 = new Artwork(2L, "Starry Night", "Oil on canvas", 1889, artist2);

		when(artworkRepository.findAll()).thenReturn(List.of(artwork1, artwork2));

		List<Artwork> artworks = artworkService.getAllArtworks();

		assertNotNull(artworks);
		assertEquals(2, artworks.size());
		verify(artworkRepository, times(1)).findAll();
	}

}
