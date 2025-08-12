package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Artist;

@Repository
public class ArtistRepository {

	private static final String TEMPORARY_IMPLEMENTATION = "Temporary implementation";

	public Optional<Artist> findById(long id) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public Artist save(Artist artist) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public void deleteById(long id) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public List<Artist> findAll() {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

}
