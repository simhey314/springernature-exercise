/**
 * @format:off
 * This file (NewsletterDataStoreTestHelper.java) is part of newsletter.
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

import com.springernature.newsletter.model.Category;

/**
 * @author Simon Heyden <simon@family-heyden.net>
 *
 * @since v0.0.2
 */
public class NewsletterDataStoreTestHelper {

	static final String CATEGORY_CODE_01 = "code1";
	static final String CATEGORY_CODE_02 = "code2";
	static final String CATEGORY_CODE_03_1 = "code3.1";
	static final String CATEGORY_CODE_03_2 = "code3.2";
	static final String CATEGORY_CODE_04_2 = "code4.2";
	static final String CATEGORY_TITLE_01 = "title1";
	static final String CATEGORY_TITLE_02 = "title2";
	static final String CATEGORY_TITLE_03_1 = "title3.1";
	static final String CATEGORY_TITLE_03_2 = "title3.2";
	static final String CATEGORY_TITLE_04_2 = "title4.2";

	static void setUpDatastore() {
		NewsletterDataStore.resetData();

		Category parent = new Category(CATEGORY_CODE_01, CATEGORY_TITLE_01);
		NewsletterDataStore.addCategory(parent);
		Category category = new Category(CATEGORY_CODE_02, CATEGORY_TITLE_02, parent);
		NewsletterDataStore.addCategory(category);
		parent = category;
		category = new Category(CATEGORY_CODE_03_1, CATEGORY_TITLE_03_1, parent);
		NewsletterDataStore.addCategory(category);
		category = new Category(CATEGORY_CODE_03_2, CATEGORY_TITLE_03_2, parent);
		NewsletterDataStore.addCategory(category);
		parent = category;
		category = new Category(CATEGORY_CODE_04_2, CATEGORY_TITLE_04_2, parent);
		NewsletterDataStore.addCategory(category);
	}

}
