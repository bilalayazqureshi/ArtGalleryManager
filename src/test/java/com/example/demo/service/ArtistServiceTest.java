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
import com.example.demo.repository.ArtistRepository;

class ArtistServiceTest {

	@Mock
	private ArtistRepository artistRepository;

	private ArtistService artistService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		artistService = new ArtistService(artistRepository);
	}

	@Test
	public void testGetArtistById() {
		Artist artist = new Artist(1L, "Pablo Picasso", "Spanish");
		when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));

		Artist result = artistService.getArtistById(1L);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Pablo Picasso", result.getName());
		assertEquals("Spanish", result.getNationality());
		verify(artistRepository, times(1)).findById(1L);
	}

	@Test
	public void testGetArtistByIdNotFound() {
		when(artistRepository.findById(1L)).thenReturn(Optional.empty());

		Artist result = artistService.getArtistById(1L);

		assertNull(result);
		verify(artistRepository, times(1)).findById(1L);
	}

	@Test
	public void testInsertNewArtist() {
		Artist artist = new Artist(null, "Pablo Picasso", "Spanish");
		Artist savedArtist = new Artist(1L, "Pablo Picasso", "Spanish");

		when(artistRepository.save(artist)).thenReturn(savedArtist);

		Artist result = artistService.insertNewArtist(artist);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Pablo Picasso", result.getName());
		assertEquals("Spanish", result.getNationality());
		verify(artistRepository, times(1)).save(artist);
	}

	@Test
	public void testUpdateArtist() {
		Artist existingArtist = new Artist(1L, "Pablo Picasso", "Spanish");
		Artist updatedArtist = new Artist(1L, "Pablo Picasso Updated", "Spanish");

		when(artistRepository.findById(1L)).thenReturn(Optional.of(existingArtist));
		when(artistRepository.save(updatedArtist)).thenReturn(updatedArtist);

		Artist result = artistService.updateArtistById(1L, updatedArtist);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Pablo Picasso Updated", result.getName());
		assertEquals("Spanish", result.getNationality());
		verify(artistRepository, times(1)).save(updatedArtist);
	}

	@Test
	public void testDeleteArtist() {
		long id = 1L;
		doNothing().when(artistRepository).deleteById(id);

		artistService.deleteArtistById(id);

		verify(artistRepository, times(1)).deleteById(id);
	}

	@Test
	public void testGetAllArtists() {
		Artist artist1 = new Artist(1L, "Pablo Picasso", "Spanish");
		Artist artist2 = new Artist(2L, "Another Artist", "French");

		when(artistRepository.findAll()).thenReturn(List.of(artist1, artist2));

		List<Artist> artists = artistService.getAllArtists();

		assertNotNull(artists);
		assertEquals(2, artists.size());
		assertEquals("Pablo Picasso", artists.get(0).getName());
		verify(artistRepository, times(1)).findAll();
	}
}
