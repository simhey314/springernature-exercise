/**
 * @format:off
 * This file (BookTest.java) is part of newsletter.
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

import static org.hamcrest.CoreMatchers.is;

import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.springernature.newsletter.TestHelper;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.2
 */
public class BookTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	public static final String TITLE = "title";

	/**
	 * Test method for {@link com.springernature.newsletter.model.Book#Book(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testBookConstructorNullTitle() {
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(Book.NO_NULL_TITLE));

		new Book(null, TestHelper.CATEGORY_CODES);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Book#Book(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testBookConstructorEmptyTitle() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(Book.NO_EMPTY_TITLE_ALLOWED));

		new Book("", TestHelper.CATEGORY_CODES);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Book#Book(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testBookConstructorWhitespacesTitle() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(Book.NO_WHITESPACE_TITLE_ALLOWED));

		new Book("   ", TestHelper.CATEGORY_CODES);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Book#Book(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testBookConstructorNullCategories() {
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(Book.NO_NULL_CATEGORIES));

		new Book(TITLE, null);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Book#Book(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testBookConstructorEmptyCategories() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(Book.NO_EMPTY_CATEGORIES));

		new Book(TITLE, Collections.emptyList());
	}

}
