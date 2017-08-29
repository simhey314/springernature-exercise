/**
 * @format:off
 * This file (NewsletterDataStoreTest.java) is part of newsletter.
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

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.springernature.newsletter.TestHelper;
import com.springernature.newsletter.model.Category;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.2
 */
public class NewsletterDataStoreTest {

	private static final String CATEGORY_CODE_EMPTY = "";
	private static final String CATEGORY_CODE_NO_MATCH = "no match";

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	/**
	 * Test method for {@link com.springernature.newsletter.data.NewsletterDataStore#getCategoriesByCodes(java.util.List)}.
	 */
	@Test
	public void testCategoriesByCodesEmptyFilterList() {
		TestHelper.setUpDatastore();

		final List<?> toTestData = NewsletterDataStore.getCategoriesByCodes(new ArrayList<>());

		assertFalse(toTestData == null);
		assertTrue(toTestData.isEmpty());
	}

	/**
	 * Test method for {@link com.springernature.newsletter.data.NewsletterDataStore#getCategoriesByCodes(java.util.List)}.
	 */
	@Test
	public void testCategoriesByCodesNoMatch() {
		TestHelper.setUpDatastore();

		final List<Category> toTestData = NewsletterDataStore.getCategoriesByCodes(Arrays.asList(CATEGORY_CODE_EMPTY, CATEGORY_CODE_NO_MATCH));

		assertFalse(toTestData == null);
		assertTrue(toTestData.isEmpty());
	}

	/**
	 * Test method for {@link com.springernature.newsletter.data.NewsletterDataStore#getCategoriesByCodes(java.util.List)}.
	 */
	@Test
	public void testCategoriesByCodesMatches() {
		TestHelper.setUpDatastore();

		final List<Category> toTestData = NewsletterDataStore.getCategoriesByCodes(Arrays.asList(TestHelper.CATEGORY_CODE_01, TestHelper.CATEGORY_CODE_03_2, CATEGORY_CODE_NO_MATCH));

		assertFalse(toTestData == null);
		assertEquals(2, toTestData.size());
		assertThat(toTestData.get(0).getCode(), anyOf(is(TestHelper.CATEGORY_CODE_01), is(TestHelper.CATEGORY_CODE_03_2)));
		assertThat(toTestData.get(1).getCode(), anyOf(is(TestHelper.CATEGORY_CODE_01), is(TestHelper.CATEGORY_CODE_03_2)));
	}

	/**
	 * Test method for {@link com.springernature.newsletter.data.NewsletterDataStore#getCategoriesByCodes(java.util.List)}.
	 */
	@Test
	public void testCategoriesByCodesExceptions() {
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(NewsletterDataStore.NO_NULL_VALUE_ALLOWED));

		NewsletterDataStore.getCategoriesByCodes(null);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.data.NewsletterDataStore#getBooksByCategories(java.util.List)}.
	 */
	@Test
	public void testGetBooksByCategories() {

	}

	/**
	 * Test method for {@link com.springernature.newsletter.data.NewsletterDataStore#addBook(com.springernature.newsletter.model.Book)}.
	 */
	@Test
	public void testAddBookNullVallue() throws Exception {
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(NewsletterDataStore.NO_NULL_VALUE_ALLOWED));

		NewsletterDataStore.addBook(null);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.data.NewsletterDataStore#addCategory(Category)}.
	 */
	@Test
	public void testAddCategoryNullVallue() throws Exception {
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(NewsletterDataStore.NO_NULL_VALUE_ALLOWED));

		NewsletterDataStore.addCategory(null);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.data.NewsletterDataStore#addSubscriber(com.springernature.newsletter.model.Subscriber)}.
	 */
	@Test
	public void testAddSubscriberNullVallue() throws Exception {
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(NewsletterDataStore.NO_NULL_VALUE_ALLOWED));

		NewsletterDataStore.addSubscriber(null);
	}

}
