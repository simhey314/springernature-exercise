/**
 * @format:off
 * This file (TestHelper.java) is part of newsletter.
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
package com.springernature.newsletter;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;

import com.springernature.newsletter.data.NewsletterDataStore;
import com.springernature.newsletter.model.Category;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.2
 */
public class TestHelper {

	public static final String EMAIL = "muster@email.de";

	public static final String CATEGORY_CODE_01 = "code1";
	public static final String CATEGORY_CODE_02 = "code2";
	public static final String CATEGORY_CODE_03_1 = "code3.1";
	public static final String CATEGORY_CODE_03_2 = "code3.2";
	public static final String CATEGORY_CODE_04_2 = "code4.2";
	public static final String CATEGORY_TITLE_01 = "title1";
	public static final String CATEGORY_TITLE_02 = "title2";
	public static final String CATEGORY_TITLE_03_1 = "title3.1";
	public static final String CATEGORY_TITLE_03_2 = "title3.2";
	public static final String CATEGORY_TITLE_04_2 = "title4.2";

	public static final String TITLE_VALUE = CATEGORY_TITLE_01;
	public static final String TITLE_FIRST = CATEGORY_TITLE_01;
	public static final String TITLE_MIDDLE = CATEGORY_TITLE_02;
	public static final String TITLE_LAST = CATEGORY_TITLE_03_1;
	public static final String CODE_VALUE = CATEGORY_CODE_01;
	public static final String CODE_THREE = CATEGORY_CODE_03_1;
	public static final String CODE_TWO = CATEGORY_CODE_02;
	public static final String CODE_ONE = CATEGORY_CODE_01;

	public static final Category CATEGORY = new Category(CATEGORY_CODE_01, CATEGORY_TITLE_01);
	public static final List<Category> CATEGORY_CODES = Arrays.asList(CATEGORY);

	public static final MediaType CONTENT_TYPE_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	public static void setUpDatastore() {
		NewsletterDataStore.resetData();
		addCategories();
	}

	public static void addCategories() {
		Category parent = new Category(TestHelper.CATEGORY_CODE_01, TestHelper.CATEGORY_TITLE_01);
		NewsletterDataStore.addCategory(parent);
		Category category = new Category(TestHelper.CATEGORY_CODE_02, TestHelper.CATEGORY_TITLE_02, parent);
		NewsletterDataStore.addCategory(category);
		parent = category;
		category = new Category(TestHelper.CATEGORY_CODE_03_1, TestHelper.CATEGORY_TITLE_03_1, parent);
		NewsletterDataStore.addCategory(category);
		category = new Category(TestHelper.CATEGORY_CODE_03_2, TestHelper.CATEGORY_TITLE_03_2, parent);
		NewsletterDataStore.addCategory(category);
		parent = category;
		category = new Category(TestHelper.CATEGORY_CODE_04_2, TestHelper.CATEGORY_TITLE_04_2, parent);
		NewsletterDataStore.addCategory(category);
	}
}
