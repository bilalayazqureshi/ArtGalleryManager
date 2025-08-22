package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Artwork;

@Service
public class ArtworkService {

	private static final String TEMPORARY_IMPLEMENTATION = "Temporary implementation";

	public List<Artwork> getAllArtworks() {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
}