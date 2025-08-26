package com.example.demo.controllers;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlAnchor;
import org.htmlunit.html.HtmlButton;
import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlTable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Artwork;
import com.example.demo.services.ArtworkService;

@WebMvcTest(controllers = ArtworkWebController.class)
class ArtworkWebControllerHtmlUnitTest {

	@Autowired
	private WebClient webClient;

	@MockBean
	private ArtworkService artworkService;

	@Test
	void test_HomePageTitle() throws Exception {
		HtmlPage page = webClient.getPage("/artworks");
		assertThat(page.getTitleText()).isEqualTo("Artworks");
	}

	@Test
	void testHomePageWithNoArtworks() throws Exception {
		when(artworkService.getAllArtworks()).thenReturn(emptyList());
		HtmlPage page = webClient.getPage("/artworks");
		assertThat(page.getBody().getTextContent()).contains("No artwork");
	}

	@Test
	void test_HomePage_ShouldProvideALinkForCreatingANewArtwork() throws Exception {
		HtmlPage page = webClient.getPage("/artworks");
		HtmlAnchor newLink = page.getAnchorByText("New artwork");
		assertThat(newLink.getHrefAttribute()).isEqualTo("/artworks/new");
	}

	@Test
	void test_HomePageWithArtworks_ShouldShowThemInATable() throws Exception {
		Artwork a1 = new Artwork(1L, "Mona Lisa", "Oil", 1503);
		Artwork a2 = new Artwork(2L, "Starry Night", "Oil on canvas", 1889);
		when(artworkService.getAllArtworks()).thenReturn(asList(a1, a2));

		HtmlPage page = webClient.getPage("/artworks");
		assertThat(page.getBody().getTextContent()).doesNotContain("No artwork");

		HtmlTable table = page.getHtmlElementById("artwork_table");
		String normalized = removeWindowsCR(table.asNormalizedText());
		assertThat(normalized).isEqualTo("Artworks\n" + "ID\tTitle\tMedium\tYear\tEdit\tDelete\n"
				+ "1\tMona Lisa\tOil\t1503\tEdit\tDelete\n" + "2\tStarry Night\tOil on canvas\t1889\tEdit\tDelete");

		page.getAnchorByHref("/artworks/edit/1");
		page.getAnchorByHref("/artworks/edit/2");
	}

	@Test
	void testEditNonExistentArtwork() throws Exception {
		when(artworkService.getArtworkById(1L)).thenReturn(null);
		HtmlPage page = webClient.getPage("/artworks/edit/1");
		assertThat(page.getBody().getTextContent()).contains("No artwork found with id: 1");
	}

	@Test
	void testEditExistentArtwork() throws Exception {
		Artwork original = new Artwork(1L, "Original", "Oil", 1600);
		when(artworkService.getArtworkById(1L)).thenReturn(original);

		HtmlPage page = webClient.getPage("/artworks/edit/1");
		HtmlForm form = page.getFormByName("artwork_record");

		form.getInputByValue("Original").setValueAttribute("Modified");
		form.getInputByValue("Oil").setValueAttribute("Watercolor");
		form.getButtonByName("btn_submit").click();

		verify(artworkService).updateArtworkById(1L, new Artwork(1L, "Modified", "Watercolor", 1600));
	}

	@Test
	void testEditNewArtwork() throws Exception {
		HtmlPage page = webClient.getPage("/artworks/new");
		final HtmlForm form = page.getFormByName("artwork_record");

		form.getInputByName("title").setValueAttribute("NewArt");
		form.getInputByName("medium").setValueAttribute("Ink");
		form.getInputByName("yearCreated").setValueAttribute("2024");
		form.getButtonByName("btn_submit").click();

		verify(artworkService).insertNewArtwork(new Artwork(null, "NewArt", "Ink", 2024));
	}

	@Test
	void testDeleteArtwork_ShouldDisplayConfirmationMessage() throws Exception {
		doNothing().when(artworkService).deleteArtworkById(3L);

		HtmlPage page = webClient.getPage("/artworks/delete/3");

		verify(artworkService, times(1)).deleteArtworkById(3L);

		String content = page.getBody().getTextContent();
		assertThat(content).contains("Artwork with ID 3 was deleted.");

		HtmlButton newButton = page.getElementByName("btn_new_artwork");
		assertThat(newButton).isNotNull();

		HtmlButton allButton = page.getElementByName("btn_all_artworks");
		assertThat(allButton).isNotNull();
	}

	private String removeWindowsCR(String s) {
		return s.replace("\r", "");
	}
}