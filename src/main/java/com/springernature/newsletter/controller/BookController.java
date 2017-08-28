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

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springernature.newsletter.data.NewsletterDataStore;
import com.springernature.newsletter.model.Book;
import com.springernature.newsletter.model.Category;

import static com.google.common.base.Preconditions.*;
/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.1
 */
@RestController
public class BookController {

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
	@RequestMapping(path = "/books", method = RequestMethod.POST)
	public void addBook(@RequestBody(required = true) final BookInput input) {
		checkNotNull(input.getCategoryCodes(), "No NULL value allowed");
		checkArgument(!input.categoryCodes.isEmpty(), "No empty category code list allowed");
				
		final List<Category> categories = NewsletterDataStore.getCategoriesByCodes(input.categoryCodes);
		final Book newBook = new Book(input.getTitle(), categories);

		NewsletterDataStore.addBook(newBook);
	}
}
