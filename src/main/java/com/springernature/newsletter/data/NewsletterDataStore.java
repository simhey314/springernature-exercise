/**
 * @format:off
 * This file (NewsletterDataStore.java) is part of newsletter.
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
package com.springernature.newsletter.data;

import java.util.ArrayList;
import java.util.List;

import com.springernature.newsletter.model.Book;
import com.springernature.newsletter.model.Category;
import com.springernature.newsletter.model.Subcriber;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.1
 */
public class NewsletterDataStore {
	private static List<Subcriber> subcribers;
	private static List<Category> categories;
	private static List<Book> books;

	static {
		subcribers = new ArrayList<>();
		categories = new ArrayList<>();
		books = new ArrayList<>();
	}

	public static List<Subcriber> getSubcribers() {
		return subcribers;
	}

	public static void addSubcriber(final Subcriber aSubcriber) {
		subcribers.add(aSubcriber);
	}

	public static List<Category> getCategories() {
		return categories;
	}

	public static void addCategory(final Category aCategory) {
		categories.add(aCategory);
	}

	public static List<Book> getBooks() {
		return books;
	}

	public static void addBook(final Book aBook) {
		books.add(aBook);
	}
}
