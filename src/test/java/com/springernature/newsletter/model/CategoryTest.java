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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.springernature.newsletter.TestHelper;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.2
 */
public class CategoryTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private Category rootCategory;
	private Category middleCategory;
	private Category lastCategory;

	private void setUp() {
		rootCategory = new Category(TestHelper.CODE_ONE, TestHelper.TITLE_FIRST);
		middleCategory = new Category(TestHelper.CODE_TWO, TestHelper.TITLE_MIDDLE, rootCategory);
		lastCategory = new Category(TestHelper.CODE_THREE, TestHelper.TITLE_LAST, middleCategory);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#Category(String, String)}.
	 */
	@Test
	public void testCategoryContructorTitleNull() {
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(Category.NO_NULL_TITLE_ALLOWED));

		new Category(TestHelper.CODE_ONE, null);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#Category(String, String)}.
	 */
	@Test
	public void testCategoryContructorTitleWhitespaces() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(Category.NO_WHITESPACE_TITLE_ALLOWED));

		new Category(TestHelper.CODE_ONE, "   ");
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#Category(String, String)}.
	 */
	@Test
	public void testCategoryContructorTitleEmpty() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(Category.NO_EMPTY_TITLE_ALLOWED));

		new Category(TestHelper.CODE_ONE, "");
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#Category(String, String)}.
	 */
	@Test
	public void testCategoryContructorCodeEmpty() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(Category.NO_EMPTY_CODE_ALLOWED));

		new Category("", TestHelper.TITLE_VALUE);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#Category(String, String)}.
	 */
	@Test
	public void testCategoryContructorCodeWhitespaces() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(Category.NO_WHITESPACE_CODE_ALLOWED));

		new Category("   ", TestHelper.TITLE_VALUE);
	}

	/**
	 * Test method for {@link com.springernature.newsletter.model.Category#Category(String, String)}.
	 */
	@Test
	public void testCategoryContructorCodeNull() {
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(Category.NO_NULL_CODE_ALLOWED));

		new Category(null, TestHelper.TITLE_VALUE);
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
		final Category toTest = new Category(TestHelper.CODE_VALUE, TestHelper.TITLE_VALUE);

		assertEquals(TestHelper.TITLE_VALUE, toTest.getTitle());
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
		assertEquals(TestHelper.TITLE_FIRST, stringPathList.get(0));
		assertEquals(TestHelper.TITLE_MIDDLE, stringPathList.get(1));
		assertEquals(TestHelper.TITLE_LAST, stringPathList.get(2));
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
		assertEquals(TestHelper.TITLE_FIRST, stringPathList.get(0));
		assertEquals(TestHelper.TITLE_MIDDLE, stringPathList.get(1));
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
		assertEquals(TestHelper.TITLE_FIRST, stringPathList.get(0));
	}
}
