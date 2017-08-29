/**
 * @format:off
 * This file (Book.java) is part of newsletter.
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
package com.springernature.newsletter.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.1
 */
public class Book {

	public static final String NO_WHITESPACE_TITLE_ALLOWED = "No whitespace title allowed";
	public static final String NO_EMPTY_TITLE_ALLOWED = "No empty title allowed";
	public static final String NO_NULL_CATEGORIES = "No NULL for category codes alllowed";
	public static final String NO_NULL_TITLE = "No NULL for title allowed";
	public static final String NO_EMPTY_CATEGORIES = "No epmty category codes allowed";

	@JsonProperty("book")
	private final String title;

	@JsonProperty("categoryPaths")
	private final List<Category> categoryCodes;

	public Book(final String title, final List<Category> categoryCodes) {
		checkNotNull(title, NO_NULL_TITLE);
		checkArgument(!Strings.isNullOrEmpty(title), NO_EMPTY_TITLE_ALLOWED);
		checkArgument(!Strings.isNullOrEmpty(title.trim()), NO_WHITESPACE_TITLE_ALLOWED);
		checkNotNull(categoryCodes, NO_NULL_CATEGORIES);
		checkArgument(!categoryCodes.isEmpty(), NO_EMPTY_CATEGORIES);

		this.title = title;
		this.categoryCodes = categoryCodes;
	}

	@JsonIgnore
	public String getTitle() {
		return title;
	}

	@JsonIgnore
	public List<Category> getCategoryCodes() {
		return categoryCodes;
	}
}
