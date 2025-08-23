package com.example.demo.jpa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.model.Artist;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ArtistJpaTest {

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testJpaMapping() {
		Artist saved = entityManager.persistFlushFind(new Artist(null, "Pablo Picasso", "Spanish"));
	}
}