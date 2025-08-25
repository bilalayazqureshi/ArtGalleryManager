package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Artwork;

@Service
public class ArtworkService {

	private static final String TEMPORARY_IMPLEMENTATION = "Temporary implementation";

	public Artwork getArtworkById(long id) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public Artwork insertNewArtwork(Artwork artwork) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public Artwork updateArtworkById(long id, Artwork updatedArtwork) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public void deleteArtworkById(long id) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public List<Artwork> getAllArtworks() {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
}