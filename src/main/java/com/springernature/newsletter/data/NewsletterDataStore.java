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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.springernature.newsletter.model.Book;
import com.springernature.newsletter.model.Category;
import com.springernature.newsletter.model.Subscriber;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.1
 */
public class NewsletterDataStore {
	private static List<Subscriber> subcribers;
	private static Map<String, Category> categories;
	private static List<Book> books;

	static {
		subcribers = new ArrayList<>();
		categories = new HashMap<>();
		books = new ArrayList<>();
	}

	public static List<Subscriber> getSubcribers() {
		return subcribers;
	}

	public static void addSubcriber(final Subscriber aSubcriber) {
		checkNotNull(aSubcriber, "No NULL value allowed");
		subcribers.add(aSubcriber);
	}

	public static Map<String, Category> getCategories() {
		return categories;
	}

	public static void addCategory(final Category aCategory) {
		checkNotNull(aCategory, "No NULL value allowed");
		// TODO: check overwriting existing category, handle consistently
		categories.put(aCategory.getCode(), aCategory);
	}

	/**
	 * Get all existing categories by the given codes list
	 *
	 * @param categoryCodes
	 *            to filter
	 * @return a list of categories
	 * @throws NullPointerException
	 *             if category codes is null
	 */
	public static List<Category> getCategoriesByCodes(final List<String> categoryCodes) {
		checkNotNull(categoryCodes, "No NULL value allowed");

		return categories.values().parallelStream().filter(category -> categoryCodes.contains(category.getCode())).collect(Collectors.toList());
	}

	/**
	 *
	 * @param categories
	 * @return
	 * @throws NullPointerException
	 *             if category list is null
	 * @throws IllegalArgumentException
	 *             if category list is empty
	 */
	public static List<Book> getBooksByCategories(final List<Category> categories) {
		checkNotNull(categories, "No NULL value allowed");
		checkArgument(!categories.isEmpty(), "No empty category list allowed");

		/*
		 * match the category path to the given category list elements
		 */
		final Predicate<Category> categoryPathMatcher = categoryPath -> categories.contains(categoryPath);
		/*
		 * match any of the path category list to the given category list elements
		 */
		final Predicate<List<Category>> categoryPathListMatcher = categoryPathList -> categoryPathList.parallelStream().anyMatch(categoryPathMatcher);

		return books.parallelStream()
		        .filter(book -> book.getCategoryCodes().parallelStream().map(category -> category.getCategoryPath()).anyMatch(categoryPathListMatcher))
		        .collect(Collectors.toList());
	}

	public static List<Book> getBooks() {
		return books;
	}

	public static void addBook(final Book aBook) {
		checkNotNull(aBook, "No NULL value allowed");
		books.add(aBook);
	}
}
