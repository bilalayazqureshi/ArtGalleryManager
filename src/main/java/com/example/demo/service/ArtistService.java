package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Artist;
import com.example.demo.repositories.ArtistRepository;

@Service
public class ArtistService {

	private ArtistRepository artistRepository;

	public ArtistService(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}

	public Artist getArtistById(long id) {
		Optional<Artist> optionalArtist = artistRepository.findById(id);
		return optionalArtist.orElse(null);
	}

	public Artist insertNewArtist(Artist artist) {
		return artistRepository.save(artist);
	}

	public Artist updateArtistById(long id, Artist updatedArtist) {
		Optional<Artist> existingArtist = artistRepository.findById(id);
		if (existingArtist.isPresent()) {
			return artistRepository.save(updatedArtist);
		}
		return null;
	}

	public void deleteArtistById(long id) {
		artistRepository.deleteById(id);
	}

	public List<Artist> getAllArtists() {
		return artistRepository.findAll();
	}

}
