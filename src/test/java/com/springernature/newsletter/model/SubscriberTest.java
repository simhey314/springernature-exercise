/**
 * @format:off
 * This file (SubscriberTest.java) is part of newsletter.
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.2
 */
public class SubscriberTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private static final List<Category> CATEGORY_CODES = Arrays.asList(new Category("1", "1"));
	private static final String EMAIL = "muster@email.de";

	/**
	 * Test method for {@link com.springernature.newsletter.model.Subscriber#Subscriber(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testBookConstructorNullEmail() {
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(Subscriber.NO_NULL_EMAIL));

		new Subscriber(null, CATEGORY_CODES);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Subscriber#Subscriber(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testBookConstructorEmptyEmail() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(Subscriber.NO_EMPTY_EMAIL));

		new Subscriber("", CATEGORY_CODES);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Subscriber#Subscriber(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testBookConstructorWhitespacesEmail() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(Subscriber.NO_WHITESPACE_EMAIL));

		new Subscriber("   ", CATEGORY_CODES);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Subscriber#Subscriber(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testBookConstructorNullCategories() {
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(Subscriber.NO_NULL_CATEGORIES));

		new Subscriber(EMAIL, null);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Subscriber#Subscriber(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testBookConstructorEmptyCategories() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(Subscriber.NO_EMPTY_CATEGORIES));

		new Subscriber(EMAIL, Collections.emptyList());
	}

}
