package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Artwork;

@Repository
public class ArtworkRepository {

	private static final String TEMPORARY_IMPLEMENTATION = "Temporary implementation";

	public Optional<Artwork> findById(long id) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public Artwork save(Artwork artwork) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public void deleteById(long id) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}

	public List<Artwork> findAll() {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
}
