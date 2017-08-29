/**
 * @format:off
 * This file (BookController.java) is part of newsletter.
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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.springernature.newsletter.data.NewsletterDataStore;
import com.springernature.newsletter.model.Book;
import com.springernature.newsletter.model.Category;
/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.1
 */
@RestController
public class BookController {

	public static final String REQUEST_PATH_BOOKS = "/books";
	public static final String NO_EXISTING_CATEGORIES = "No existing categories";

	public static class BookInput {
		private List<String> categoryCodes;
		private String title;

		public BookInput() {
		}

		public BookInput(final List<String> categoryCodes, final String title) {
			super();
			this.categoryCodes = categoryCodes;
			this.title = title;
		}

		public List<String> getCategoryCodes() {
			return categoryCodes;
		}

		public String getTitle() {
			return title;
		}
	}

	/**
	 * create a new book model data from input and add all existing category models by the input codes
	 *
	 * @param input
	 * @return a new book
	 */
	@RequestMapping(path = REQUEST_PATH_BOOKS, method = RequestMethod.POST)
	public void addBook(@RequestBody(required = true) final BookInput input) {
		checkNotNull(input.getCategoryCodes(), Book.NO_NULL_CATEGORIES);
		checkArgument(!input.categoryCodes.isEmpty(), Book.NO_EMPTY_CATEGORIES);
		checkArgument(!Strings.isNullOrEmpty(input.getTitle()), Book.NO_EMPTY_TITLE_ALLOWED);
		checkArgument(!Strings.isNullOrEmpty(input.getTitle().trim()), Book.NO_WHITESPACE_TITLE_ALLOWED);

		final List<Category> existingCategories = NewsletterDataStore.getCategoriesByCodes(input.categoryCodes);
		if (!existingCategories.isEmpty()) {
			final Book newBook = new Book(input.getTitle(), existingCategories);
			NewsletterDataStore.addBook(newBook);
		} else {
			throw new IllegalStateException(NO_EXISTING_CATEGORIES);
		}
	}
}
