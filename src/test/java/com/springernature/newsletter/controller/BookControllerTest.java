/**
 * @format:off
 * This file (BookControllerTest.java) is part of newsletter.
 *
 *    newsletter is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    any later version.
 *
 *    newsletter is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with newsletter. If not, see <http://www.gnu.org/licenses/>.
 * @format:on
 */
package com.springernature.newsletter.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.springernature.newsletter.NewsletterApplication;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewsletterApplication.class)
@WebAppConfiguration
public class BookControllerTest {

	private static final String REQUEST_JSON_PATTERN = "{ \"title\": \"%s\", \"categoryCodes\": %s }";
	private static final String BOOK_JSON_COMPLETE = String.format(REQUEST_JSON_PATTERN, "Erstes Buch", "[\"code1\"]");
	private static final String BOOK_JSON_NULL_CATEGORIES = String.format(REQUEST_JSON_PATTERN, "Erstes Buch", "null");
	private static final String BOOK_JSON_EMPTY_CATEGORIES = String.format(REQUEST_JSON_PATTERN, "Erstes Buch", "[]");;
	private static final String BOOK_JSON_NULL_TITLE = String.format(REQUEST_JSON_PATTERN, "null", "[\"code1\"]");;
	private static final String BOOK_JSON_EMPTY_TITLE = String.format(REQUEST_JSON_PATTERN, "   ", "[\"code1\"]");;

	private final MediaType contentTypeJSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	private void setUp() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Test method for {@link com.springernature.newsletter.controller.BookController#addBook(com.springernature.newsletter.controller.BookController.BookInput)}.
	 */
	@Test
	public void testAddBook() throws Exception {
		setUp();

		mockMvc.perform(post(BookController.REQUEST_PATH_BOOKS).content(BOOK_JSON_COMPLETE).contentType(contentTypeJSON)).andExpect(status().isOk());

		mockMvc.perform(get(BookController.REQUEST_PATH_BOOKS)).andExpect(status().is4xxClientError());
		mockMvc.perform(post(BookController.REQUEST_PATH_BOOKS)).andExpect(status().is4xxClientError());
		mockMvc.perform(post(BookController.REQUEST_PATH_BOOKS).content(BOOK_JSON_NULL_CATEGORIES).contentType(contentTypeJSON))
		.andExpect(status().is5xxServerError());
		mockMvc.perform(post(BookController.REQUEST_PATH_BOOKS).content(BOOK_JSON_EMPTY_CATEGORIES).contentType(contentTypeJSON))
		.andExpect(status().is5xxServerError());
		mockMvc.perform(post(BookController.REQUEST_PATH_BOOKS).content(BOOK_JSON_NULL_TITLE).contentType(contentTypeJSON))
		.andExpect(status().is4xxClientError());
		mockMvc.perform(post(BookController.REQUEST_PATH_BOOKS).content(BOOK_JSON_EMPTY_TITLE).contentType(contentTypeJSON))
		.andExpect(status().is4xxClientError());
	}

}
