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
import com.springernature.newsletter.model.Book;
import com.springernature.newsletter.model.Category;
import com.springernature.newsletter.model.Subscriber;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.2
 */
public class TestHelper {

	public static final String EMAIL = "muster@email.de";
	public static final String EMAIL_02 = "max@email.de";

	public static final String CATEGORY_CODE_01 = "cat code1";
	public static final String CATEGORY_CODE_02 = "cat code2";
	public static final String CATEGORY_CODE_03_1 = "cat code3.1";
	public static final String CATEGORY_CODE_03_2 = "cat code3.2";
	public static final String CATEGORY_CODE_04_2 = "cat code4.2";
	public static final String CATEGORY_TITLE_01 = "cat title1";
	public static final String CATEGORY_TITLE_02 = "cat title2";
	public static final String CATEGORY_TITLE_03_1 = "cat title3.1";
	public static final String CATEGORY_TITLE_03_2 = "cat title3.2";
	public static final String CATEGORY_TITLE_04_2 = "cat title4.2";

	public static final String TITLE_VALUE = CATEGORY_TITLE_01;
	public static final String TITLE_FIRST = CATEGORY_TITLE_01;
	public static final String TITLE_MIDDLE = CATEGORY_TITLE_02;
	public static final String TITLE_LAST = CATEGORY_TITLE_03_1;
	public static final String CODE_VALUE = CATEGORY_CODE_01;
	public static final String CODE_THREE = CATEGORY_CODE_03_1;
	public static final String CODE_TWO = CATEGORY_CODE_02;
	public static final String CODE_ONE = CATEGORY_CODE_01;

	public static final String BOOK_TITLE_01 = "book title1";
	public static final String BOOK_TITLE_02 = "book title2";

	public static final Category CATEGORY = new Category(CATEGORY_CODE_01, CATEGORY_TITLE_01);
	public static final List<Category> CATEGORY_CODES = Arrays.asList(CATEGORY);

	public static final MediaType CONTENT_TYPE_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	public static void setUpDatastore() {
		NewsletterDataStore.resetData();
		addCategories();
	}

	public static void setUpDataStoreComplete() {
		addCategories();
		addSubscribers();
		addBooks();
	}

	private static void addBooks() {
		List<Category> caategoryCodes = Arrays.asList(NewsletterDataStore.getCategories().get(CATEGORY_CODE_03_1),
				NewsletterDataStore.getCategories().get(CATEGORY_CODE_04_2));
		NewsletterDataStore.addBook(new Book(BOOK_TITLE_02, caategoryCodes));
		caategoryCodes = Arrays.asList(NewsletterDataStore.getCategories().get(CATEGORY_CODE_03_2));
		NewsletterDataStore.addBook(new Book(BOOK_TITLE_01, caategoryCodes));
	}

	private static void addSubscribers() {
		List<Category> caategoryCodes = Arrays.asList(NewsletterDataStore.getCategories().get(CATEGORY_CODE_04_2));
		NewsletterDataStore.addSubscriber(new Subscriber(EMAIL, caategoryCodes));
		caategoryCodes = Arrays.asList(NewsletterDataStore.getCategories().get(CATEGORY_CODE_02));
		NewsletterDataStore.addSubscriber(new Subscriber(EMAIL_02, caategoryCodes));
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
