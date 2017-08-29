/**
 * @format:off
 * This file (CategoryTest.java) is part of newsletter.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.2
 */
public class CategoryTest {

	private static final String TITLE_VALUE = "title value";
	private static final String TITLE_FIRST = "title value first";
	private static final String TITLE_MIDDLE = "title value middle";
	private static final String TITLE_LAST = "title value last";
	private static final String CODE_VALUE = "code value";
	private static final String CODE_THREE = "code value three";
	private static final String CODE_TWO = "code value two";
	private static final String CODE_ONE = "code value one";
	private Category rootCategory;
	private Category middleCategory;
	private Category lastCategory;

	private void setUp() {
		rootCategory = new Category(CODE_ONE, TITLE_FIRST);
		middleCategory = new Category(CODE_TWO, TITLE_MIDDLE, rootCategory);
		lastCategory = new Category(CODE_THREE, TITLE_LAST, middleCategory);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#getCategoryPath()}.
	 */
	@Test
	public void testFullCategoryPath() {
		setUp();

		final List<Category> pathToTest = lastCategory.getCategoryPath();

		assertFalse(pathToTest == null);
		assertEquals(3, pathToTest.size());
		assertEquals(lastCategory, pathToTest.get(0));
		assertEquals(middleCategory, pathToTest.get(1));
		assertEquals(rootCategory, pathToTest.get(2));
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#getCategoryPath()}.
	 */
	@Test
	public void testMiddleCategoryPath() {
		setUp();

		final List<Category> pathToTest = middleCategory.getCategoryPath();

		assertFalse(pathToTest == null);
		assertEquals(2, pathToTest.size());
		assertEquals(middleCategory, pathToTest.get(0));
		assertEquals(rootCategory, pathToTest.get(1));
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#getCategoryPath()}.
	 */
	@Test
	public void testMinCategoryPath() {
		setUp();

		final List<Category> pathToTest = rootCategory.getCategoryPath();

		assertFalse(pathToTest == null);
		assertEquals(1, pathToTest.size());
		assertEquals(rootCategory, pathToTest.get(0));
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#toString()}.
	 */
	@Test
	public void testToString() {
		final Category toTest = new Category(CODE_VALUE, TITLE_VALUE);

		assertEquals(TITLE_VALUE, toTest.getTitle());
		assertEquals(toTest.getTitle(), toTest.toString());
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#getCategoryPathStringList()}.
	 */
	@Test
	public void testFullCategoryPathStringList() {
		setUp();

		final List<String> stringPathList = lastCategory.getCategoryPathStringList();

		assertFalse(stringPathList == null);
		assertEquals(3, stringPathList.size());
		assertEquals(TITLE_FIRST, stringPathList.get(0));
		assertEquals(TITLE_MIDDLE, stringPathList.get(1));
		assertEquals(TITLE_LAST, stringPathList.get(2));
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#getCategoryPathStringList()}.
	 */
	@Test
	public void testMiddleCategoryPathStringList() {
		setUp();

		final List<String> stringPathList = middleCategory.getCategoryPathStringList();

		assertFalse(stringPathList == null);
		assertEquals(2, stringPathList.size());
		assertEquals(TITLE_FIRST, stringPathList.get(0));
		assertEquals(TITLE_MIDDLE, stringPathList.get(1));
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#getCategoryPathStringList()}.
	 */
	@Test
	public void testMinCategoryPathStringList() {
		setUp();

		final List<String> stringPathList = rootCategory.getCategoryPathStringList();

		assertFalse(stringPathList == null);
		assertEquals(1, stringPathList.size());
		assertEquals(TITLE_FIRST, stringPathList.get(0));
	}
}