package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Artist;

@Service
public class ArtistService {

	private static final String TEMPORARY_IMPLEMENTATION = "Temporary implementation";

	public List<Artist> getAllArtists() {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
}