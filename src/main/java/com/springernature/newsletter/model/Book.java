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

import java.util.List;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.1
 */
public class Book {
	private final String title;
	private final List<Category> categoryCodes;

	public Book(final String title, final List<Category> categoryCodes) {
		super();
		this.title = title;
		this.categoryCodes = categoryCodes;
	}

	public String getTitle() {
		return title;
	}

	public List<Category> getCategoryCodes() {
		return categoryCodes;
	}

}
