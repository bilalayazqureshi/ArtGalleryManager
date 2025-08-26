package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Artist;

@Service
public class ArtistService {

	private static final String TEMPORARY_IMPLEMENTATION = "Temporary implementation";

	public Artist getArtistById(long id) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public Artist insertNewArtist(Artist artist) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public Artist updateArtistById(long id, Artist updatedArtist) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public void deleteArtistById(long id) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public List<Artist> getAllArtists() {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
}
