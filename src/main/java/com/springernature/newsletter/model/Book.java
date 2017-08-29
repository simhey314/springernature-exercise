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

	@JsonProperty("book")
	private final String title;

	@JsonProperty("categoryPaths")
	private final List<Category> categoryCodes;

	public Book(final String title, final List<Category> categoryCodes) {
		checkArgument(!Strings.isNullOrEmpty(title), "No empty title allowed");
		checkArgument(!Strings.isNullOrEmpty(title.trim()), "No whitespace title allowed");

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
